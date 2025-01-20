import Java_Text_Editor.model.Document;
import view.EditorView;
import controller.EditorController;

import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            Document document = new Document();
            EditorView view = new EditorView();
            EditorController controller = new EditorController(document, view);
            view.setController(controller);
            view.setVisible(true);
        });
    }
}
