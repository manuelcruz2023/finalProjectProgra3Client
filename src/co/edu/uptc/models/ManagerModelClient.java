package co.edu.uptc.models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.interfaces.Contract;
import co.edu.uptc.interfaces.Contract.Presenter;

public class ManagerModelClient implements Contract.Model {

    private Contract.Presenter presenter;
    public Socket socket;
    ObjectInputStream in;
    private ObjectOutputStream out;
    private Net net;
    public Boolean isRunning = true;
    List<Ship> shipsUp;
    private int numberOfShips = 0;
    private int velocity = 0;
    private int aparitionTime = 0;
    private int totalShipsOnScreen;
    private int totalShipsCrashed;
    private List<String> clientsNames = new ArrayList<>();
    private String name;
    private boolean first = false;

    public ManagerModelClient(String host, int port, String name) throws UnknownHostException, IOException {
        this.name = name;
        socket = new Socket("127.0.0.1", port);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(name);
        in = new ObjectInputStream(socket.getInputStream());
        net = new Net(socket);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void createShips() throws IOException, ClassNotFoundException {
        out.writeObject("CREATE_SHIPS");
        out.flush();
        String message = in.readUTF();
        if (message.equals(name)) {
            first = true;
            out.writeObject("yes");

            numberOfShips = presenter.getNumberOfShips();
            velocity = presenter.getVelocity();
            aparitionTime = presenter.getAparitionTime();
            presenter.repaintInformationPlay(numberOfShips, velocity, aparitionTime);

            out.writeObject(presenter.getNumberOfShips());
            velocity = presenter.getVelocity();
            out.writeObject(presenter.getVelocity());
            out.writeObject(presenter.getAparitionTime());
            out.writeObject(presenter.getScreenSize());
            out.flush();
            String arrayShips = in.readUTF();
            shipsUp = net.getMyGson().fromJson(arrayShips, new TypeToken<List<Ship>>() {
            }.getType());
            presenter.updateShips(shipsUp);
        } else {
            out.writeObject("no");
            out.flush();
            String arrayShips = in.readUTF();
            shipsUp = net.getMyGson().fromJson(arrayShips, new TypeToken<List<Ship>>() {
            }.getType());

            int numberOfShipsIn = in.readInt();
            int velocityIn = in.readInt();
            int aparitionTimeIn = in.readInt();
            presenter.repaintInformationPlay(numberOfShipsIn, velocityIn, aparitionTimeIn);
            
            presenter.updateShips(shipsUp);
        }
        presenter.updateShips(shipsUp);
        presenter.changePosition();
        update();
    }

    @Override
    public void update() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    synchronized (this) {
                        String newArrayShips = in.readUTF();
                        shipsUp = net.getMyGson().fromJson(newArrayShips, new TypeToken<List<Ship>>() {
                        }.getType());
                        String clientsNamesString = in.readUTF();
                        List<String> updatedClients = net.getMyGson().fromJson(clientsNamesString,
                                new TypeToken<List<String>>() {
                                }.getType());
                        if (clientsNames.size() != updatedClients.size()) {
                            clientsNames = updatedClients;
                            presenter.updateInformationPlay();
                        }
                        totalShipsOnScreen = in.readInt();
                        totalShipsCrashed = in.readInt();
                    }
                    presenter.updateTotalShipsOnScreen();
                    presenter.updateTotalShipsCrashed();
                    presenter.updateShips(shipsUp);
                    presenter.changePosition();

                } catch (IOException e) {
                    break;
                }
            }
        });
        thread.start();
    }

    @Override
    public void changeVelocity(Ship ship, int velocity, int index) throws IOException {
        out.writeObject("CHANGE_VELOCITY");
        String shipJson = net.getMyGson().toJson(ship);
        out.writeObject(shipJson);
        out.writeInt(velocity);
        out.writeInt(index);
        out.flush();
    }

    @Override
    public void updateShipPosition(Ship ship, int x, int y, int index) throws IOException {
        if (index >= 0 && index < shipsUp.size()) {
            if (!ship.getSelected2() && shipsUp.get(index) != null && !shipsUp.isEmpty()) {
                out.writeObject("UPDATE_SHIP_POSITION");
                String shipJson = net.getMyGson().toJson(ship);
                out.writeObject(shipJson);
                out.writeInt(x);
                out.writeInt(y);
                out.writeInt(index);
                //UtilThread.sleep(ship.getVelocity());
            }
        }
    }

    @Override
    public void continueMovement(Ship ship, int index) throws IOException {
        out.writeObject("CONTINUE_MOVEMENT");
        String shipJson = net.getMyGson().toJson(ship);
        out.writeObject(shipJson);
        out.writeInt(index);
        out.flush();
    }

    @Override
    public int getTotalShipsOnScreen() {
        return totalShipsOnScreen;
    }

    @Override
    public int getTotalShipsCrashed() {
        return totalShipsCrashed;
    }

    @Override
    public List<String> getListClientsNames() {
        return clientsNames;
    }

    @Override
    public int setNumberOfShips() {
        return this.numberOfShips;
    }

    @Override
    public int setVelocity() {
        return this.velocity;
    }

    @Override
    public int setAparitionTime() {
        return this.aparitionTime;
    }

    @Override
    public boolean isFirst() {
        return first;
    }
}
