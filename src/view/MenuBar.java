package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    private EditorView view;
    private JMenuItem undoItem;
    private JMenuItem redoItem;
    public MenuBar(EditorView view){
        this.view = view;
        createMenus();
    }
    private void createMenus(){
        add(createFileMenu());
        add(createEditMenu());
        add(createSearchMenu());
    }
    private JMenu createFileMenu(){
        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu,"New",KeyEvent.VK_N,e -> view.getController().newFile());
        addMenuItem(fileMenu,"Open",KeyEvent.VK_O,e -> view.getController().openFile());
        addMenuItem(fileMenu,"Save",KeyEvent.VK_S,e -> view.getController().saveFile());
        addMenuItem(fileMenu,"Save As",KeyEvent.VK_A,e -> view.getController().saveFileAs());
        fileMenu.addSeparator();
        addMenuItem(fileMenu,"Exit",KeyEvent.VK_X,e ->System.exit(0));
        return fileMenu;
    }
    private JMenu createEditMenu(){
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        undoItem = addMenuItem(editMenu, "Undo",KeyEvent.VK_Z, e->view.getController().undo());
        redoItem = addMenuItem(editMenu, "Redo",KeyEvent.VK_Y, e->view.getController().redo());
        editMenu.addSeparator();
        addMenuItem(editMenu,"Cut",KeyEvent.VK_X,e ->view.getTextArea().cut());
        addMenuItem(editMenu,"Copy",KeyEvent.VK_C,e ->view.getTextArea().copy());
        addMenuItem(editMenu,"Paste",KeyEvent.VK_V,e ->view.getTextArea().paste());
        undoItem.setEnabled(false);
        redoItem.setEnabled(false);
        return editMenu;
    }
    private JMenu createSearchMenu(){
        JMenu searchMenu = new JMenu("Search");
        searchMenu.setMnemonic(KeyEvent.VK_S);
        addMenuItem(searchMenu,"Find/Replace",KeyEvent.VK_F,e-> view.showFindReplaceDialog());
        return searchMenu;
    }
    private JMenuItem addMenuItem(JMenu menu, String label, int mnemonic, ActionListener listener){
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.setMnemonic(mnemonic);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
        return menuItem;
    }
    public void enableUndoRedo(boolean canUndo, boolean canRedo) {
        undoItem.setEnabled(canUndo);
        redoItem.setEnabled(canRedo);
    }
}
