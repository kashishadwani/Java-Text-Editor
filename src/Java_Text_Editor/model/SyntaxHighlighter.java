package Java_Text_Editor.model;

import java.awt.*;
import java.util.regex.Pattern;

public class SyntaxHighlighter {
    private static final String[] KEYWORDS = {
            "public", "private","protected","class","interface",
            "void","int","String","boolean","return"
    };
    public static Pattern[] getKeywordPatterns(){
        Pattern[] patterns = new Pattern[KEYWORDS.length];
        for (int i=0;i<KEYWORDS.length;i++){
            patterns[i] = Pattern.compile("\\b"+KEYWORDS[i]+"\\b");
        }
        return patterns;
    }
    public static Color getKeywordColor(){
        return Color.BLUE;
    }


}
