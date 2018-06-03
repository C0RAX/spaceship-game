package game1;

import java.awt.*;

public class Constants {

    public static final int FRAME_HEIGHT = 1000;
    public static final int FRAME_WIDTH = 1200;
    public static final Dimension FRAME_SIZE = new Dimension(
            Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    // sleep time between two frames in milliseconds
    public static final int DELAY = 20;
    // estimate for time passed between two frames in seconds 
    public static final double DT = DELAY / 1000.0;
}
