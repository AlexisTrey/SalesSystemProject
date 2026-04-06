package co.edu.uptc.view;

import co.edu.uptc.config.I18n;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.view.components.AppColors;
import co.edu.uptc.view.components.AppFonts;
import co.edu.uptc.view.components.AppLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainWindow extends JFrame {
    private static final int SIDEBAR_W = 260;
    private final I18n i18n = I18n.getInstance();

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel     = new JPanel(cardLayout);

    private final Map<String, BasePanel> panels = new LinkedHashMap<>();
    private final Map<String, JButton>   navBtns = new LinkedHashMap<>();

    private String activeKey = "";

    public MainWindow(PresenterInterface presenter) {
        super();
        setTitle(i18n.get("menu.welcome"));
        setSize(960, 680);
        setMinimumSize(new Dimension(800, 580));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panels.put("persons",    new PersonPanel(presenter));
        panels.put("products",   new ProductPanel(presenter));
        panels.put("accounting", new AccountingPanel(presenter));

        buildUI();
        navigateTo("persons");
        setVisible(true);
    }

    private void buildUI() {
        setLayout(new BorderLayout());
        add(buildSidebar(), BorderLayout.WEST);
        buildCardPanel();
        add(cardPanel, BorderLayout.CENTER);
    }

    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(AppColors.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(SIDEBAR_W, 0));
        sidebar.add(buildSidebarHeader());
        sidebar.add(Box.createVerticalStrut(10));
        addNavButtons(sidebar);
        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }

    private JPanel buildSidebarHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AppColors.SIDEBAR_SELECTED);
        header.setBorder(new EmptyBorder(20, 16, 20, 16));
        AppLabel lbl = new AppLabel(i18n.get("menu.welcome"), AppLabel.Style.BODY);
        lbl.setForeground(AppColors.SIDEBAR_TEXT);
        lbl.setFont(AppFonts.SIDEBAR_TITLE);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(lbl, BorderLayout.CENTER);
        return header;
    }

    private void addNavButtons(JPanel sidebar) {
        String[][] items = {
                {"persons",    "menu.main.persons"},
                {"products",   "menu.main.products"},
                {"accounting", "menu.main.accounting"}
        };
        for (String[] item : items) {
            JButton btn = buildNavButton(item[1]);
            navBtns.put(item[0], btn);
            btn.addActionListener(e -> navigateTo(item[0]));
            sidebar.add(btn);
        }
    }

    private JButton buildNavButton(String i18nKey) {
        JButton btn = new JButton(i18n.get(i18nKey));
        btn.setFont(AppFonts.SIDEBAR_ITEM);
        btn.setForeground(AppColors.SIDEBAR_TEXT);
        btn.setBackground(AppColors.SIDEBAR_BG);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(12, 20, 12, 20));
        btn.setMaximumSize(new Dimension(SIDEBAR_W, 48));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (!btn.getBackground().equals(AppColors.SIDEBAR_SELECTED))
                    btn.setBackground(AppColors.SIDEBAR_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (!btn.getBackground().equals(AppColors.SIDEBAR_SELECTED))
                    btn.setBackground(AppColors.SIDEBAR_BG);
            }
        });
        return btn;
    }

    private void buildCardPanel() {
        cardPanel.setBackground(AppColors.BACKGROUND);
        for (Map.Entry<String, BasePanel> entry : panels.entrySet()) {
            cardPanel.add(entry.getValue(), entry.getKey());
        }
    }

    private void navigateTo(String key) {
        if (activeKey.equals(key)) return;
        if (!activeKey.isEmpty()) {
            navBtns.get(activeKey).setBackground(AppColors.SIDEBAR_BG);
        }
        activeKey = key;
        navBtns.get(key).setBackground(AppColors.SIDEBAR_SELECTED);
        cardLayout.show(cardPanel, key);
        panels.get(key).refresh();
    }
}
