package co.edu.uptc.presenter;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;

public class MainPresenter implements PresenterInterface {

    private ModelInterface model;
    private ViewInterface view;

    @Override
    public void setModel(ModelInterface model) {
        this.model = model;
    }

    @Override
    public void setView(ViewInterface view) {
        this.view = view;
    }

}
