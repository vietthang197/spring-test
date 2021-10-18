package com.thanglv.springtest.obj;

import java.io.Serializable;

public class GhtkCommand implements Serializable {
    private float widthPaper; // millimeter
    private float heightPaper; // millimeter
    private double gap;
    private double direction;
    private double speed;

    public float getWidthPaper() {
        return widthPaper;
    }

    public void setWidthPaper(float widthPaper) {
        this.widthPaper = widthPaper;
    }

    public float getHeightPaper() {
        return heightPaper;
    }

    public void setHeightPaper(float heightPaper) {
        this.heightPaper = heightPaper;
    }

    public double getGap() {
        return gap;
    }

    public void setGap(double gap) {
        this.gap = gap;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
