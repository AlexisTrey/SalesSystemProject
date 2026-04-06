package co.edu.uptc.view.components;

import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class AppButton extends JButton {
    public enum Style { PRIMARY, DANGER, SUCCESS }

    private Color normalColor;
    private Color hoverColor;

    public AppButton(String text, Style style) {
        super(text);
        applyStyle(style);
        applyBaseConfig();
        addHoverEffect();
    }

    public AppButton(String text) {
        this(text, Style.PRIMARY);
    }

    private void applyStyle(Style style) {
        switch (style) {
            case DANGER  -> { normalColor = AppColors.BTN_DANGER;  hoverColor = AppColors.BTN_DANGER_HOVER; }
            case SUCCESS -> { normalColor = AppColors.BTN_SUCCESS; hoverColor = AppColors.BTN_SUCCESS_HOVER; }
            default      -> { normalColor = AppColors.BTN_PRIMARY; hoverColor = AppColors.BTN_PRIMARY_HOVER; }
        }
        setBackground(normalColor);
    }

    private void applyBaseConfig() {
        setForeground(AppColors.BTN_TEXT);
        setFont(AppFonts.BUTTON);
        setFocusPainted(false);
        setOpaque(true);
        setBorderPainted(false);
        setBorder(new EmptyBorder(8, 18, 8, 18));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
    }

    private void addHoverEffect() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { setBackground(hoverColor); }
            public void mouseExited(MouseEvent e)  { setBackground(normalColor); }
        });
    }
}
