package it.unibo.pixArt.view.impl;

import java.util.stream.Collectors;

import it.unibo.pixArt.controller.game.GameSetupController;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameSetupView extends AbstractFXView {

    @FXML
    private ListView<ImageView> projectsList;

    @FXML
    private ChoiceBox<String> timerChoiceBox;

    @FXML
    private void onStartClicked() {
        PageLoader.getInstance().switchPage(getStage(), Pages.GAMEVIEW, this.getController().getModel());
    }

    @FXML
    private void onDiscardClicked() {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.MENU, this.getController().getModel());
    }

    @Override
    public void init() {
        this.getGameSetupController().setProjects();
        this.projectsList.getItems().addAll(getGameSetupController().getProjects()
                                    .stream()
                                    .map(e -> new ImageView(new Image(e)))
                                    .collect(Collectors.toList()));
    }

    private GameSetupController getGameSetupController() {
        return (GameSetupController) getController();
    }
}
