package controller;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
public class UndoRedoHandeler extends UndoManager implements UndoableEditListener {
    private EditorController controller;
    @Override
    public void undoableEditHappened(UndoableEditEvent e){
        addEdit(e.getEdit());
        controller.updateUndoRedoState();
        controller.setModified(true);
    }
    @Override
    public void undo() {
        super.undo();
        controller.updateUndoRedoState();
    }

    @Override
    public void redo() {
        super.redo();
        controller.updateUndoRedoState();
    }
}
