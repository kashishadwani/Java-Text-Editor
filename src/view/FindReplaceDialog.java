package view;

import controller.FindReplaceHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FindReplaceDialog extends JDialog{
    private JTextField findField;
    private JTextField replaceField;
    private FindReplaceHandler handler;

    public FindReplaceDialog(EditorView parent,FindReplaceHandler handler){
        super(parent,"Find and Replace", true);
        this.handler = handler;
        initializeComponents();
        pack();
        setLocationRelativeTo(parent);
    }
    private void initializeComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        findField = new JTextField(20);
        replaceField = new JTextField(20);
        gbc.gridx =0;
        gbc.gridy =0;
        add(new JLabel("Find:"),gbc);
        gbc.gridx =1;
        add(findField,gbc);
        gbc.gridx =0;
        gbc.gridy =1;
        add(new JLabel("Replace with:"),gbc);

        gbc.gridx =1;
        add(replaceField, gbc);

        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Find", e -> handler.findText(findField.getText()));
        addButton(buttonPanel, "Replace", e -> handler.replaceText(findField.getText(), replaceField.getText()));
        addButton(buttonPanel, "Replace All", e -> handler.replaceAllText(findField.getText(), replaceField.getText()));

        gbc.gridx =0;
        gbc.gridy =2;
        gbc.gridwidth =2;
        add(buttonPanel, gbc);
    }
    private void addButton(JPanel panel, String label, ActionListener listener){
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

}
