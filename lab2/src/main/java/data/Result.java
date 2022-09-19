package data;

import java.io.Serializable;

public class Result implements Serializable {
    private double x;
    private double y;
    private int r;
    private String currTime;
    private String execTime;
    private boolean hitResult;

    public Result(double x, double y, int r, String currTime, String execTime, boolean hitResult) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.currTime = currTime;
        this.execTime = execTime;
        this.hitResult = hitResult;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public boolean isHitResult() {
        return hitResult;
    }

    public void setHitResult(boolean hitResult) {
        this.hitResult = hitResult;
    }
}
