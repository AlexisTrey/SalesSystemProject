package co.edu.uptc.view;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.pojo.Product;
import co.edu.uptc.view.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProductPanel extends BasePanel {
    private AppField fldDescription;
    private AppField fldPrice;
    private AppComboBox<String> cmbUnit;

    private AppTable table;
    private PaginationPanel pagination;

    private AppButton btnAdd;
    private AppButton btnRetire;
    private AppButton btnExport;

    public ProductPanel(PresenterInterface presenter) {
        super(presenter);
        buildUI();
    }

    private void buildUI() {
        add(buildHeader(i18n.get("menu.products.title")), BorderLayout.NORTH);
        add(buildCenter(), BorderLayout.CENTER);
        add(buildStatusBar(), BorderLayout.SOUTH);
    }

    private JPanel buildCenter() {
        JPanel center = new JPanel(new BorderLayout(0, 12));
        center.setBackground(AppColors.BACKGROUND);
        center.setBorder(new EmptyBorder(14, 16, 14, 16));
        center.add(buildFormCard(), BorderLayout.NORTH);
        center.add(buildTableCard(), BorderLayout.CENTER);
        return center;
    }

    private JPanel buildFormCard() {
        JPanel card = buildCard();
        card.setLayout(new BorderLayout(0, 10));

        card.add(new AppLabel(i18n.get("menu.products.adding"), AppLabel.Style.SECTION), BorderLayout.NORTH);

        JPanel fields = new JPanel(new GridLayout(3, 1, 6, 6));
        fields.setBackground(AppColors.SURFACE);

        fldDescription = new AppField(i18n.get("field.description"));
        fldPrice       = new AppField(i18n.get("field.price"));
        cmbUnit        = new AppComboBox<>(new String[]{
                i18n.get("unit.pound"), i18n.get("unit.kilo"),
                i18n.get("unit.bale"),  i18n.get("unit.ton")
        });

        fields.add(buildFormRow("field.description", fldDescription));
        fields.add(buildFormRow("field.unit",        cmbUnit));
        fields.add(buildFormRow("field.price",       fldPrice));
        card.add(fields, BorderLayout.CENTER);

        btnAdd    = new AppButton(i18n.get("menu.products.add"),    AppButton.Style.SUCCESS);
        btnRetire = new AppButton(i18n.get("menu.products.retire"), AppButton.Style.DANGER);
        btnExport = new AppButton(i18n.get("menu.products.export"), AppButton.Style.PRIMARY);

        card.add(buildButtonRow(btnAdd, btnRetire, btnExport), BorderLayout.SOUTH);
        addListeners();
        return card;
    }

    private JPanel buildTableCard() {
        JPanel card = buildCard();
        card.setLayout(new BorderLayout(0, 8));

        card.add(new AppLabel(i18n.get("menu.products.listing"), AppLabel.Style.SECTION), BorderLayout.NORTH);

        table      = new AppTable(getHeaders());
        pagination = new PaginationPanel(table, pageSize);

        card.add(table, BorderLayout.CENTER);
        card.add(pagination, BorderLayout.SOUTH);
        return card;
    }

    private void addListeners() {
        btnAdd.addActionListener(e -> addProduct());
        btnRetire.addActionListener(e -> retireProduct());
        btnExport.addActionListener(e -> exportCsv());
    }

    private void addProduct() {
        String description = fldDescription.getValue();
        String unit        = cmbUnit.getSelectedValue();
        double price       = parsePrice(fldPrice.getValue());

        if (price <= 0) { showError("error.positive.value"); return; }

        Product product = new Product(0, description, unit, price);
        if (presenter.addProduct(product)) {
            showSuccess("menu.products.success");
            clearForm();
            refresh();
        } else {
            showError("menu.products.error");
        }
    }

    private void retireProduct() {
        Product retired = presenter.retireFromStack();
        if (retired == null) { showError("list.empty"); return; }

        showSuccess("menu.products.retired");
        JOptionPane.showMessageDialog(this,
                retired.getDescription() + " | " + retired.getUnit()
                        + " | $" + String.format("%.2f", retired.getPrice()),
                i18n.get("menu.products.retired"), JOptionPane.INFORMATION_MESSAGE);
        refresh();
    }

    private void exportCsv() {
        presenter.exportProductsCsv();
        showSuccess("csv.export.success");
    }

    private double parsePrice(String value) {
        try {
            return Double.parseDouble(value.replace(",", "."));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void clearForm() {
        fldDescription.clear();
        fldPrice.clear();
        cmbUnit.setSelectedIndex(0);
    }

    @Override
    public void refresh() {
        pagination.load(presenter.getProductsAsTable());
    }

    private String[] getHeaders() {
        return new String[]{
                i18n.get("header.id"),    i18n.get("header.description"),
                i18n.get("header.unit"),  i18n.get("header.price")
        };
    }
}
