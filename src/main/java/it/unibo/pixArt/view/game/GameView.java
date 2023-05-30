package it.unibo.pixArt.view.game;

import it.unibo.pixArt.controller.game.GameController;
import it.unibo.pixArt.model.timer.TimerThread;
import it.unibo.pixArt.utilities.parser.GridPaneParser;
import it.unibo.pixArt.utilities.parser.PixelsParser;


import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.components.PixelsPane;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameView extends AbstractFXView{

    @FXML
    private Label timer;

    @FXML
    private BorderPane root;

    private PixelsParser pixelsParser;

    private GridPaneParser paneParser;

    private Color selectedColor = Color.BEIGE;

    @FXML
    public void onMenuClick(){
        if (this.getGameController().getTimer().isRunning()) {
            this.getGameController().getTimer().stop();
        }
        PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
    }

    @Override
    public void init() {
        this.getGameController().setColorStack();
        paneParser = new GridPaneParser();
        pixelsParser = new PixelsParser();
        this.getGameController().setColorStack();
        final var e = new EventHandler<ActionEvent>(){
            @Override
            public void handle(final ActionEvent event){
                final var button = (Button)event.getSource();
                boolean checkPixel = getGameController().checkPixel(GridPane.getColumnIndex(button), GridPane.getRowIndex(button), selectedColor);
                if (checkPixel){
                    button.setStyle("-fx-background-color: #" + selectedColor.toString().substring(2));
                }
            }
        };

        final GridPane center = new PixelsPane.GridPaneBuilder()
                .setColumns(this.getGameController().getFrameSize())
                .setRows(this.getGameController().getFrameSize())
                .setGridLinesVisible(true)
                .setAction(e)
                .build();
        this.root.setCenter(center);

        this.getGameController().getTimer().start();
        new TimerThread(this.getGameController().getTimer(), this::onTimeFinish, this::OnTimeUpdate).start();
        System.out.println(getController().getModel().getProject().getPath());
    }

    private void OnTimeUpdate(){
        Platform.runLater(()->{
           this.timer.setText(timeToString(this.getGameController().getTimer().getRemainingTime()));
        });
    }

    private void onTimeFinish(){
        Platform.runLater(this::onGameFinish);
    }

    private void onGameFinish(){
        this.getGameController().getTimer().stop();
    }

    private String timeToString(final double remainingTime){
        double minutes = remainingTime/60;
        double seconds = remainingTime % 60;
        return Integer.toString((int) minutes) + ":" + Integer.toString((int) seconds);
    }

    private GameController getGameController(){
        return (GameController) this.getController();
    }
    
}
