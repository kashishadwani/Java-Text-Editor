package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    private EditorView view;
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
        addMenuItem(fileMenu,"New",e -> view.getController().newFile());
        addMenuItem(fileMenu,"Open",e -> view.getController().openFile());
        addMenuItem(fileMenu,"Save",e -> view.getController().saveFile());
        addMenuItem(fileMenu,"Save As",e -> view.getController().saveFileAs());
        fileMenu.addSeparator();
        addMenuItem(fileMenu,"Exit",e ->System.exit(0));
        return fileMenu;
    }
    private JMenu createdEditMenu(){
        JMenu editMenu = new JMenu("Edit");
        addMenuItem(editMenu, "Undo", e->view.getController().undo());
        addMenuItem(editMenu, "Redo", e->view.getController().redo());
        editMenu.addSeparator();
        addMenuItem(editMenu,"Cut",e ->view.getTextArea().cut());
        addMenuItem(editMenu,"Copy",e ->view.getTextArea().copy());
        addMenuItem(editMenu,"Paste",e ->view.getTextArea().paste());
        return editMenu;
    }
    private JMenu createSearchMenu(){
        JMenu searchMenu = new JMenu("Search");
        addMenuItem(searchMenu,"Find/Replace",e-> view.showFindReplaceDialog());
        return searchMenu;
    }
    private void addMenuItem(JMenu menu, String label, ActionListener listener){
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }
}
