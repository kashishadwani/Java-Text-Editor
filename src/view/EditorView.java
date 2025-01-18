package view;

import controller.EditorController;
import controller.FindReplaceHandler;

import javax.swing.*;
import java.awt.*;
public class EditorView {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private EditorController controller;
    private FindReplaceHandler findReplaceHandler;
    private MenuBar menuBar;
    private JToolBar toolBar;

    public EditorView(){
        setTitle("Advanced Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        setLocationRelativeTo(null);
    }
    private void initializeComponents(){
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setTabSize(4);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
        findReplaceHandler = new FindReplaceHandler(this);

        menuBar = new MenuBar();
        toolBar = new JToolBar(this);

        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.NORTH);
    }

    public void setController(EditorController controller){
        this.controller = controller;
    }
    public JTextArea getTextArea(){
        return textArea;
    }
    public void updateContent(String content){
        textArea.setText(content);
    }
    public void updateTitle(String filePath, boolean modified){
        String title = "Advanced Text Editor";
        if (filePath != null){
            title+= " - "+ filePath;
        }
        if (modified){
            title +=" *";
        }
        setTitle(title);
    }

    public boolean showOpenDialog(){
        return fileChooser.showOpenDialog(this);
    }

    public boolean showSaveDialog(){
        return fileChooser.showSaveDialog(this);
    }

    public String getSelectedFilePath(){
        return fileChooser.getSelectedFile().getPath();
    }
    public void showError(String message){
        JOptionPane.showMessageDialog(this,message,"Information",JOptionPane.INFORMATION_MESSAGE);
    }
    public void showFindReplaceDialog(){
        FindReplaceDialog dialog = new FindReplaceDialog(this, findReplaceHandler);
        dialog.setVisible(true);
    }
}
