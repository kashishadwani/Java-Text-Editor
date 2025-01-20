package controller;
import view.EditorView;
import javax.swing.JTextArea;
import java.util.regex.Pattern;

public class FindReplaceHandler {
    private EditorView view;
    private JTextArea textArea;
    public FindReplaceHandler(EditorView view){
        this.view = view;
        this.textArea = view.getTextArea();
    }
    public void findText(String searchText){
        String text = textArea.getText();
        int start = textArea.getCaretPosition();

        int index = text.indexOf(searchText, start);
        if (index >=0){
            textArea.setCaretPosition(index);
            textArea.select(index, index+searchText.length());
            textArea.requestFocusInWindow();
        }else {
            view.showInfo("Text not found");
        }
    }
    public void replaceText(String searchText, String replaceText){
        if (textArea.getSelectedText()!=null){
            textArea.replaceSelection(replaceText);
        }
        findText(searchText);
    }
    public void replaceAllText(String searchText, String replaceText){
        String text = textArea.getText();
        text = text.replaceAll(Pattern.quote(searchText),replaceText);
        textArea.setText(text);
    }
}
