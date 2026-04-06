package co.edu.uptc.view;

import co.edu.uptc.config.AppConfig;
import co.edu.uptc.config.I18n;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.view.components.AppColors;
import co.edu.uptc.view.components.AppLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class BasePanel extends JPanel {
    protected final I18n i18n           = I18n.getInstance();
    protected final PresenterInterface presenter;
    protected final int pageSize        = AppConfig.getInstance().getInt("ui.page.size", 10);

    protected final AppLabel lblStatus  = new AppLabel("", AppLabel.Style.BODY);

    protected BasePanel(PresenterInterface presenter) {
        this.presenter = presenter;
        setBackground(AppColors.BACKGROUND);
        setLayout(new BorderLayout(0, 0));
    }

    protected JPanel buildHeader(String title) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AppColors.SIDEBAR_BG);
        header.setBorder(new EmptyBorder(14, 20, 14, 20));
        AppLabel lbl = new AppLabel(title, AppLabel.Style.SUBTITLE);
        lbl.setForeground(AppColors.SIDEBAR_TEXT);
        header.add(lbl, BorderLayout.WEST);
        return header;
    }

    protected JPanel buildButtonRow(JButton... buttons) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        row.setBackground(AppColors.BACKGROUND);
        for (JButton b : buttons) row.add(b);
        return row;
    }

    protected JPanel buildFormRow(String labelKey, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setBackground(AppColors.SURFACE);
        row.setBorder(new EmptyBorder(4, 0, 4, 0));
        AppLabel lbl = new AppLabel(i18n.get(labelKey) + ":", AppLabel.Style.BODY);
        lbl.setPreferredSize(new Dimension(200, 30));
        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);
        return row;
    }

    protected JPanel buildStatusBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 4));
        bar.setBackground(AppColors.SURFACE_ALT);
        bar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, AppColors.BORDER));
        bar.add(lblStatus);
        return bar;
    }

    protected void showSuccess(String key) {
        lblStatus.setSuccess(i18n.get(key));
    }

    protected void showError(String key) {
        lblStatus.setError(i18n.get(key));
    }

    protected void showDialog(String messageKey) {
        JOptionPane.showMessageDialog(this, i18n.get(messageKey),
                "", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void showErrorDialog(String messageKey) {
        JOptionPane.showMessageDialog(this, i18n.get(messageKey),
                "", JOptionPane.ERROR_MESSAGE);
    }

    protected JPanel buildCard() {
        JPanel card = new JPanel();
        card.setBackground(AppColors.SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.BORDER, 1),
                new EmptyBorder(16, 16, 16, 16)));
        return card;
    }

    public abstract void refresh();
}
