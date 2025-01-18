package Java_Text_Editor.model;

import javax.swing.event.DocumentListener;
import javax.swing.text.StyleContext;
import java.io.*;

public class Document {
    private String content;
    private String filePath;
    private boolean isModified;
    private StyleContext styleContext;

    public Document(){
        this.content = "";
        this.isModified = false;
        this.styleContext = new StyleContext();
    }
    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
        setModified(true);
    }

    public String getFilePath(){
        return filePath;
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public boolean isModified(){
        return isModified;
    }

    public void setModified(boolean modified){
        isModified = modified;
    }

    public StyleContext getStyleContext(){
        return styleContext;
    }

}