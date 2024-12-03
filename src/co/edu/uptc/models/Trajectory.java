package co.edu.uptc.models;

import java.awt.Point;
import java.io.Serializable;

public class Trajectory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Point origin;
    private Point destination;

    public Trajectory(Point origin, Point destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Trajectory [origin=" + origin + ", destination=" + destination + "]";
    }
}
