package it.unibo.pixArt.model.historyframe;

public class HistoryFrameImpl implements HistoryFrame {
    private static final int DEF_DELAY = 100;
    private static final String DEF_IMAGE = "/image/def-historyFrame.png";
    private String imagePath;
    private int animationDuration;
    private int index;

    public HistoryFrameImpl() {
        this.imagePath = DEF_IMAGE;
        this.animationDuration = DEF_DELAY;
    }

    public HistoryFrameImpl(final String path, final int index) {
        this.imagePath = path;
        this.animationDuration = DEF_DELAY;
        this.index = index;
    }

    @Override
    public String getPath() {
        return this.imagePath;
    }

    @Override
    public void setPath(String path) {
       this.imagePath = path;
    }

    @Override
    public void setAnimationDuration(int duration) {
        this.animationDuration = duration;
    }

    @Override
    public int getAnimationDuration() {
        return this.animationDuration;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
    
}
