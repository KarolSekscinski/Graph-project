package com.example.graphproject.graphUtils;

import javafx.scene.paint.Color;

public class ColorPicker {
    public Color determineColorForCanvas(double value, double startOfRange, double endOfRange) {
        //(255,0,0) -> (255,255,0) -> (0, 255, 0) -> (0,255,255) -> (0, 0, 255)
        double stop1 = (endOfRange - startOfRange) / 4 + startOfRange;
        double stop2 = (endOfRange - startOfRange) / 4 + stop1;
        double stop3 = (endOfRange - startOfRange) / 4 + stop2;

        double ratio = 255 / ((endOfRange - startOfRange) / 4);

        if (value <= stop1) {
            return Color.rgb(255, (int) ((value - startOfRange) * ratio), 0);
        }
        if (value <= stop2) {
            return Color.rgb((int) ((value - stop2) * (-ratio)), 255, 0);
        }
        if (value <= stop3) {
            return Color.rgb(0, 255, (int) ((value - stop2) * ratio));
        } else {
            return Color.rgb(0, (int) ((value - endOfRange) * (-ratio)), 255);
        }
    }
}
