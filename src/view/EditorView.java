package view;

import controller.EditorController;
import controller.FindReplaceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class EditorView extends Component {
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
        setupWindowListener();
        setLocationRelativeTo(null);
    }
    private void initializeComponents(){
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setTabSize(4);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        findReplaceHandler = new FindReplaceHandler(this);

    }
    private void setupWindowListener(){
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                if (controller!=null){
                    controller.handleExit();
                }else {
                    System.exit(0);
                }
            }
        });
    }
    public void initializeMenuAndToolbar(){
        menuBar = new MenuBar(this);
        toolBar = new ToolBar(this);

        setJMenuBar(menuBar);
        add(toolBar,BorderLayout.NORTH);
    }
    

    public void setController(EditorController controller){
        this.controller = controller;
        initializeMenuAndToolbar();
    }
    public JTextArea getTextArea(){
        return textArea;
    }
    public void updateContent(String content){
        textArea.setText(content);
        textArea.setCaretPosition(0);
    }
    public String getContent(){
        return textArea.getText();
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
        return fileChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION;
    }

    public boolean showSaveDialog(){
        return fileChooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION;
    }
    public int showConfirmDialog(String message){
        return JOptionPane.showConfirmDialog(
                this, message,"Confirmation",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    public String getSelectedFilePath(){
        return fileChooser.getSelectedFile().getPath();
    }
    public void showError(String message){
        JOptionPane.showMessageDialog(this,message,"Error",JOptionPane.ERROR_MESSAGE);
    }
    public void showInfo(String message){
        JOptionPane.showMessageDialog(this,message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    public void showFindReplaceDialog(){
        FindReplaceDialog dialog = new FindReplaceDialog(this, findReplaceHandler);
        dialog.setVisible(true);
    }
    public void requestFocusOnTextArea(){
        textArea.requestFocusInWindow();
    }
    public void setTextAreaEditable(boolean editable){
        textArea.setEditable(editable);
    }
    public void setCaretPosition(int position){
        textArea.setCaretPosition(position);
    }
    public int getCaretPosition(){
        return textArea.getCaretPosition();
    }
    public void selectText(int start, int end){
        textArea.select(start,end);
    }
    public String getSelectedText(){
        return textArea.getSelectedText();
    }
    public void replaceSelection(String text){
        textArea.replaceSelection(text);
    }
    public void setModifiedStatus(boolean modified){
        if (controller!=null){
            controller.setModified(modified);
        }
    }
    public void enableUndoRedo(boolean canUndo, boolean canRedo){
        menuBar.enableUndoRedo(canUndo, canRedo);
        toolBar.enableUndoRedo(canUndo, canRedo);
    }
}
