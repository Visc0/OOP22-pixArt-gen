package it.unibo.pixArt.view;

import javafx.stage.Stage;

public interface JavaFXView extends View {

    Stage getStage();

    void setStage();

    void init();
}