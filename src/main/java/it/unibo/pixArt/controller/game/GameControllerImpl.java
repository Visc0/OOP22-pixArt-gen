package it.unibo.pixArt.controller.game;

import java.util.Map;
import java.util.Set;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.colorstack.ColorStack;
import it.unibo.pixArt.model.colorstack.ColorStackImpl;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.timer.GameTimer;
import javafx.scene.paint.Color;

public class GameControllerImpl extends SimpleController implements GameController{

    private ColorStack colorStack;
    private boolean isDrawing;

    @Override
    public GameTimer getTimer() {
        return this.getModel().getTimer();
    }

    @Override
    public int getFrameSize() {
        return this.getModel().getProject().getAllFrames().get(0).getColumns();
    }

    @Override
    public Map<Color,Set<Pixel>> getColorStack() {
        return this.colorStack.getColorMap();
    }

    @Override
    public void setColorStack() {
        this.colorStack = new ColorStackImpl(this.getModel().getProject().getAllFrames().get(0).getPixels());
    }

    @Override
    public boolean checkPixel(int x, int y, Color color) {
        var pixel = new PixelBuilder.PxlBuilder().setColor(color).setX(x).setY(y).build();
        if(this.colorStack.isPresent(pixel)) {
            this.colorStack.removePixel(color, pixel);
            return true;
        }
        return false;
    }

    @Override
    public boolean colorStackIsEmpty() {
        return this.colorStack.isEmpty();
    }

    @Override
    public float getPercentage() {
        return this.colorStack.getPercentage();
    }

    @Override
    public boolean getIsDrawing() {
        return this.isDrawing;
    }

    @Override
    public void setIsDrawing() {
        this.isDrawing = !this.isDrawing;
    }
}
