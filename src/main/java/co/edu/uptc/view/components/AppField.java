package co.edu.uptc.view.components;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AppField extends JTextField {
    private final String placeholder;

    public AppField(String placeholder) {
        this.placeholder = placeholder;
        setText(placeholder);
        setForeground(AppColors.TEXT_PLACEHOLDER);
        setFont(AppFonts.BODY);
        setBackground(AppColors.SURFACE);
        applyBorder(AppColors.BORDER);
        addFocusListeners();
    }

    public AppField() {
        this("");
    }

    private void addFocusListeners() {
        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(AppColors.TEXT_PRIMARY);
                }
                applyBorder(AppColors.BTN_PRIMARY);
            }
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(AppColors.TEXT_PLACEHOLDER);
                }
                applyBorder(AppColors.BORDER);
            }
        });
    }

    private void applyBorder(Color color) {
        setBorder(new CompoundBorder(
                new LineBorder(color, 1, true),
                new EmptyBorder(6, 10, 6, 10)));
    }

    public String getValue() {
        String text = getText().trim();
        return text.equals(placeholder) ? "" : text;
    }

    public void clear() {
        setText(placeholder);
        setForeground(AppColors.TEXT_PLACEHOLDER);
    }
}
