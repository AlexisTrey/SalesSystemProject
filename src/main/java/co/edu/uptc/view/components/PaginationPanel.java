package co.edu.uptc.view.components;

import co.edu.uptc.config.I18n;

import javax.swing.*;
import java.awt.*;

public class PaginationPanel extends JPanel {
    private final AppTable table;
    private final AppButton btnPrev;
    private final AppButton btnNext;
    private final AppLabel lblPage;

    private String[][] allRows;
    private int currentPage = 0;
    private int pageSize;

    public PaginationPanel(AppTable table, int pageSize) {
        this.table    = table;
        this.pageSize = pageSize;
        this.btnPrev  = new AppButton(I18n.getInstance().get("pagination.prev"), AppButton.Style.PRIMARY);
        this.btnNext  = new AppButton(I18n.getInstance().get("pagination.next.btn"), AppButton.Style.PRIMARY);
        this.lblPage  = new AppLabel("", AppLabel.Style.SECONDARY);
        buildLayout();
        addListeners();
    }

    private void buildLayout() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 14, 6));
        setBackground(AppColors.BACKGROUND);
        add(btnPrev);
        add(lblPage);
        add(btnNext);
    }

    private void addListeners() {
        btnPrev.addActionListener(e -> { if (currentPage > 0) { currentPage--; refresh(); } });
        btnNext.addActionListener(e -> {
            if (allRows != null && (currentPage + 1) * pageSize < allRows.length) {
                currentPage++;
                refresh();
            }
        });
    }

    public void load(String[][] rows) {
        this.allRows   = rows;
        this.currentPage = 0;
        refresh();
    }

    private void refresh() {
        if (allRows == null || allRows.length == 0) {
            table.clearData();
            lblPage.setText("");
            updateButtons();
            return;
        }
        int from  = currentPage * pageSize;
        int to    = Math.min(from + pageSize, allRows.length);
        int total = (int) Math.ceil((double) allRows.length / pageSize);

        String[][] page = new String[to - from][];
        System.arraycopy(allRows, from, page, 0, to - from);
        table.setData(page);

        lblPage.setText(I18n.getInstance().get("page.label") + " " + (currentPage + 1) + " / " + total);
        updateButtons();
    }

    private void updateButtons() {
        int total = allRows == null ? 0 : (int) Math.ceil((double) allRows.length / pageSize);
        btnPrev.setEnabled(currentPage > 0);
        btnNext.setEnabled(currentPage < total - 1);
    }
}
