package it.unibo.pixArt.model.historyframe;

/**
 * doc.
 */
public interface HistoryFrame {
    /**
     * @return the historyframe's path.
     */
    String getPath();

    /**
     * @param path the path of the relative image.
     */
    void setPath(String path);

    /**
     * @param duration new value for the animation's duration.
     */
    void setAnimationDuration(int duration);

    /**
     * @return the animation's duration.
     */
    int getAnimationDuration();

    /**
     * @return the historyframe's index.
     */
    int getIndex();
}

