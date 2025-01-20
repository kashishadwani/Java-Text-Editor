package controller;

import Java_Text_Editor.model.Document;
import view.EditorView;
import javax.swing.*;
import java.io.*;

public class EditorController {
    private Document document;
    private EditorView view;
    private UndoRedoHandeler undoRedoHandeler;
    public EditorController(Document document, EditorView view){
        this.document = document;
        this.view = view;
        this.undoRedoHandeler = new UndoRedoHandeler();
        initializeListeners();
    }
    private void initializeListeners(){
        view.getTextArea().getDocument().addUndoableEditListener(undoRedoHandeler);
    }
    public void setModified(boolean modified){
        document.setModified(modified);
        view.updateTitle(document.getFilePath(),modified);
    }
    public void handleExit(){
        if (document.isModified()){
            int response = view.showConfirmDialog("Do you want to save changes before exiting?");
            if (response == JOptionPane.YES_OPTION){
                saveFile();
                if (!document.isModified()){
                    System.exit(0);
                }
            }else if (response == JOptionPane.NO_OPTION){
                System.exit(0);
            }else {
                System.exit(0);
            }
        }
    }
    public void updateUndoRedoState(){
        view.enableUndoRedo(undoRedoHandeler.canUndo(),undoRedoHandeler.canRedo());
    }
    public void newFile(){
        if (document.isModified()){
            int response = view.showSaveDialog();
            if (response==JOptionPane.YES_OPTION){
                saveFile();
            } else if (response == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        document.setContent("");
        document.setFilePath(null);
        document.setModified(false);
        view.updateContent("");
        view.updateTitle(null, false);
    }
    public void openFile(){
        if(view.showOpenDialog()){
            String filePath = view.getSelectedFilePath();
            try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null){
                    content.append(line).append("\n");
                }
                document.setContent(content.toString());
                document.setFilePath(filePath);
                document.setModified(false);
                view.updateContent(content.toString());
                view.updateTitle(filePath, false);
            } catch (IOException e){
                view.showError("Error reading file");
            }
        }
    }
    public void saveFile(){
        String filePath = document.getFilePath();
        if (filePath==null){
            saveFileAs();
        }else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
                writer.write(view.getTextArea().getText());
                document.setModified(false);
                view.updateTitle(filePath, false);
            } catch (IOException e){
                view.showError("Error saving file");
            }
        }
    }
    public void saveFileAs(){
        if (view.showSaveDialog()){
            String filePath = view.getSelectedFilePath();
            if (!filePath.toLowerCase().endsWith(".txt")){
                filePath+=".txt";
            }
            document.setFilePath(filePath);
            saveFile();
        }
    }
    public void undo(){
        if (undoRedoHandeler.canUndo()){
            undoRedoHandeler.undo();
        }
    }
    public void redo(){
        if (undoRedoHandeler.canRedo()){
            undoRedoHandeler.redo();
        }
    }
}
