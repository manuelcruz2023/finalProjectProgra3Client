package co.edu.uptc.presenters;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;
import co.edu.uptc.interfaces.Contract;
import co.edu.uptc.interfaces.Contract.Model;
import co.edu.uptc.interfaces.Contract.View;
import co.edu.uptc.models.Ship;

public class Presenter implements Contract.Presenter {

    private Contract.View view;
    private Contract.Model model; 
    private List<Ship> ships;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void changePosition() {
        view.updatePosition();
    }

    @Override
    public Dimension getScreenSize() {
        return view.screenSize();
    }

    @Override
    public int getNumberOfShips() {
        return view.numberOfShips();
    }

    @Override
    public int getVelocity() {
        return view.setVelocity();
    }

    @Override
    public int getAparitionTime() {
        return view.setAparitionTime();
    }

    @Override
    public void updateTotalShipsOnScreen() {
        int totalShips = model.getTotalShipsOnScreen();
        view.updateTotalShipsOnScreen(totalShips);
    }

    @Override
    public void updateTotalShipsCrashed() {
        int totalCrashed = model.getTotalShipsCrashed();
        view.updateTotalShipsCrashed(totalCrashed);
    }

    @Override
    public void createShips() {
        try {
            model.createShips();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeVelocity(Ship ship, int velocity, int index) {
        try {
            model.changeVelocity(ship, velocity, index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateShipPosition(Ship ship, int x, int y, int index) {
        try {
            model.updateShipPosition(ship, x, y, index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void continueMovement(Ship ship, int index) {
        try {
            model.continueMovement(ship, index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public List<Ship> getShips() {
        return ships;
    }

    @Override
    public String getHost() {
        return view.getHost();
    }

    @Override
    public int getPort() {
        return view.getPort();
    }

    @Override
    public String getNickname() {
        return view.getNickname();
    }

    @Override
    public List<String> getListClientsNames() {
        return model.getListClientsNames();
    }

    @Override
    public void updateInformationPlay() {
        view.updateInformationPlay();
    }

    @Override
    public int numberOfShips() {
        return model.numberOfShips();
    }

    @Override
    public int velocity() {
        return model.velocity();
    }

    @Override
    public int aparitionTime() {
        return model.aparitionTime();
    }

    @Override
    public boolean otherClients() {
        return model.otherClients();
    }
}
