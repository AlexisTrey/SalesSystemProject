package co.edu.uptc.view.components;

import javax.swing.*;

public class AppLabel extends JLabel {
    public enum Style { TITLE, SUBTITLE, SECTION, BODY, SUCCESS, ERROR, SECONDARY }

    public AppLabel(String text, Style style) {
        super(text);
        applyStyle(style);
    }

    public AppLabel(String text) {
        this(text, Style.BODY);
    }

    private void applyStyle(Style style) {
        switch (style) {
            case TITLE     -> { setFont(AppFonts.TITLE);    setForeground(AppColors.TEXT_PRIMARY); }
            case SUBTITLE  -> { setFont(AppFonts.SUBTITLE); setForeground(AppColors.TEXT_PRIMARY); }
            case SECTION   -> { setFont(AppFonts.SECTION);  setForeground(AppColors.TEXT_PRIMARY); }
            case SUCCESS   -> { setFont(AppFonts.BODY);     setForeground(AppColors.TEXT_SUCCESS); }
            case ERROR     -> { setFont(AppFonts.BODY);     setForeground(AppColors.TEXT_ERROR); }
            case SECONDARY -> { setFont(AppFonts.SMALL);    setForeground(AppColors.TEXT_SECONDARY); }
            default        -> { setFont(AppFonts.BODY);     setForeground(AppColors.TEXT_PRIMARY); }
        }
    }

    public void setSuccess(String text) {
        setText(text);
        setForeground(AppColors.TEXT_SUCCESS);
        setFont(AppFonts.BODY_BOLD);
    }

    public void setError(String text) {
        setText(text);
        setForeground(AppColors.TEXT_ERROR);
        setFont(AppFonts.BODY_BOLD);
    }

    public void clear() {
        setText("");
    }
}
