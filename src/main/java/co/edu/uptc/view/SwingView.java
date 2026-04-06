package co.edu.uptc.view;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;

import javax.swing.*;

public class SwingView implements ViewInterface {
    private PresenterInterface presenter;

    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> new MainWindow(presenter));
    }
}
