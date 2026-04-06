package co.edu.uptc.view.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class AppTable extends JScrollPane {
    private final JTable table;
    private final DefaultTableModel model;

    public AppTable(String[] headers) {
        model = new DefaultTableModel(headers, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        applyTableStyles();
        setViewportView(table);
        setBorder(BorderFactory.createLineBorder(AppColors.BORDER));
        getViewport().setBackground(AppColors.SURFACE);
    }

    private void applyTableStyles() {
        table.setFont(AppFonts.TABLE_CELL);
        table.setRowHeight(32);
        table.setShowGrid(true);
        table.setGridColor(AppColors.TABLE_GRID);
        table.setBackground(AppColors.SURFACE);
        table.setForeground(AppColors.TEXT_PRIMARY);
        table.setSelectionBackground(AppColors.TABLE_SELECTED);
        table.setSelectionForeground(AppColors.TEXT_PRIMARY);
        table.setFillsViewportHeight(true);
        applyHeaderStyles();
        applyAlternatingRows();
    }

    private void applyHeaderStyles() {
        JTableHeader header = table.getTableHeader();
        header.setFont(AppFonts.TABLE_HEADER);
        header.setBackground(AppColors.TABLE_HEADER_BG);
        header.setForeground(AppColors.SIDEBAR_TEXT);
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getWidth(), 36));
    }

    private void applyAlternatingRows() {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean selected, boolean focused, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, value, selected, focused, row, col);
                if (!selected) {
                    c.setBackground(row % 2 == 0 ? AppColors.SURFACE : AppColors.TABLE_ROW_ALT);
                }
                setFont(AppFonts.TABLE_CELL);
                setBorder(new javax.swing.border.EmptyBorder(0, 8, 0, 8));
                return c;
            }
        });
    }

    public void setData(String[][] rows) {
        model.setRowCount(0);
        for (String[] row : rows) model.addRow(row);
    }

    public void clearData() {
        model.setRowCount(0);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public JTable getTable() {
        return table;
    }
}
