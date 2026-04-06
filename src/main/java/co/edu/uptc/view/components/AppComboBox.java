package co.edu.uptc.view.components;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AppComboBox<T> extends JComboBox<T> {
    public AppComboBox(T[] items) {
        super(items);
        setFont(AppFonts.BODY);
        setBackground(AppColors.SURFACE);
        setForeground(AppColors.TEXT_PRIMARY);
        setBorder(new CompoundBorder(
                new LineBorder(AppColors.BORDER, 1, true),
                new EmptyBorder(4, 6, 4, 6)));
        setFocusable(false);
        setPreferredSize(new Dimension(200, 34));
    }

    @SuppressWarnings("unchecked")
    public T getSelectedValue() {
        return (T) getSelectedItem();
    }
}
