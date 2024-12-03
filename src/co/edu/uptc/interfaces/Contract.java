package co.edu.uptc.interfaces;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;
import co.edu.uptc.models.Ship;

public interface Contract {

    public interface View {
        public void setPresenter(Contract.Presenter presenter);

        public String getHost();
        public int getPort();
        public String getNickname();
        public void updateInformationPlay();

        public void begin();
        public int numberOfShips();
        public int setVelocity();
        public int setAparitionTime();
        public void updatePosition();
        public Dimension screenSize();

        public void updateTotalShipsOnScreen(int totalShips);
        public void updateTotalShipsCrashed(int totalCrashed);

        
        public void repaintInformationPlay(int numberOfShips, int velocity, int aparitionTime);
    }

    public interface Presenter {
        public void setView(Contract.View view);
        public void setModel(Contract.Model model);

        public String getHost();
        public int getPort();
        public String getNickname();
        public List<String> getListClientsNames();
        public void updateInformationPlay();

        public int getNumberOfShips();
        public int getVelocity();
        public int getAparitionTime();

        public void changePosition();
        public Dimension getScreenSize();
        public void createShips();
        public void changeVelocity(Ship ship, int velocity, int index);
        public void updateShipPosition(Ship ship, int x, int y, int index);
        public void continueMovement(Ship ship, int index);

        public void updateTotalShipsOnScreen();
        public void updateTotalShipsCrashed();

        public void updateShips(List<Ship> ships);
        public List<Ship> getShips();

        public int setNumberOfShips();
        public int setVelocity();
        public int setAparitionTime();

        public boolean isFirst();
        public void repaintInformationPlay(int numberOfShips, int velocity, int aparitionTime);
    }

    public interface Model {
        public void setPresenter(Contract.Presenter presenter);
        public List<String> getListClientsNames();
        public void createShips() throws IOException, ClassNotFoundException;
        public void changeVelocity(Ship ship, int velocity, int index) throws IOException;
        public void updateShipPosition(Ship ship, int x, int y, int index) throws IOException;
        public void continueMovement(Ship ship, int index) throws IOException ;
        int getTotalShipsOnScreen();
        int getTotalShipsCrashed();
        public void update();

        public int setNumberOfShips();
        public int setVelocity();
        public int setAparitionTime();

        public boolean isFirst();
    }
}
