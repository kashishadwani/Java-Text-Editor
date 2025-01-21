package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar{
    private EditorView view;
    private JButton undoButton;
    private JButton redoButton;

    public ToolBar(EditorView view){
        this.view = view;
        setFloatable(false);
        createButtons();
    }
    private void createButtons(){
        addButton("New","Create new file", e-> view.getController().newFile());
        addButton("Open","Open file", e-> view.getController().openFile());
        addButton("Save","Save file", e-> view.getController().saveFile());
        addSeparator();
        undoButton = addButton("Undo","Undo last action", e-> view.getController().undo());
        redoButton = addButton("Redo","Redo last action", e-> view.getController().redo());
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);
    }

    private JButton addButton(String label, String tooltip, ActionListener listener){
        JButton button = new JButton(label);
        button.setToolTipText(tooltip);
        button.addActionListener(listener);
        add(button);
        return button;
    }
    public void enableUndoRedo(boolean canUndo, boolean canRedo) {
        undoButton.setEnabled(canUndo);
        redoButton.setEnabled(canRedo);
    }

}
