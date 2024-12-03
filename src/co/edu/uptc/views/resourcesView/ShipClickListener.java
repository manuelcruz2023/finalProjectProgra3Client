package co.edu.uptc.views.resourcesView;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import co.edu.uptc.models.Ship;

public class ShipClickListener extends MouseAdapter {

    private Ship ship;
    private int shipWidth;
    private int shipHeight;
    public boolean isClicked = false;

    public ShipClickListener(Ship ship, int shipWidth, int shipHeight) {
        this.ship = ship;
        this.shipWidth = shipWidth;
        this.shipHeight = shipHeight;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        isClicked = false;
        Point point = ship.getPoint();
        if (x >= point.x && x <= point.x + shipWidth &&
                y >= point.y && y <= point.y + shipHeight) {
            isClicked = true;
            System.out.println("Ship clicked!");
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}