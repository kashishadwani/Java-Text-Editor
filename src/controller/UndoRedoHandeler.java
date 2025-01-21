package controller;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
public class UndoRedoHandeler extends UndoManager implements UndoableEditListener {
    private EditorController controller;
    public UndoRedoHandeler(EditorController controller){
        this.controller = controller;
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e){
        addEdit(e.getEdit());
        if (controller!=null) {
            controller.updateUndoRedoState();
            controller.setModified(true);
        }
    }
    @Override
    public void undo() {
        super.undo();
        if (controller!=null) {
            controller.updateUndoRedoState();
        }
    }

    @Override
    public void redo() {
        super.redo();
        if (controller!=null) {
            controller.updateUndoRedoState();
        }
    }
}
