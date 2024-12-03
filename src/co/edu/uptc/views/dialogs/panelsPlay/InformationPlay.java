package co.edu.uptc.views.dialogs.panelsPlay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import co.edu.uptc.views.MainView;
import co.edu.uptc.views.resourcesView.BackgroundPanel;

public class InformationPlay extends BackgroundPanel {

    private MainView mainView;
    private JLabel labelMovementShips;
    private JLabel labelCrashedShips;
    private JScrollPane scrollPane;
    private List<String> clients;
    private String[] columnNames;
    private JTable table;
    private int numberOfShips = 0;
    private int velocity = 0;
    private int aparitionTime = 0;

    public InformationPlay(MainView mainView) {
        super("resources\\backgroundInformation.png");
        this.mainView = mainView;
        initFrame();
        begin();
        addLabels();
    }

    public void initFrame() {
        this.setBackground(new Color(56, 116, 120));
        this.setPreferredSize(new Dimension(300, 150));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void begin() {
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void addLabelNumShips() {
        JLabel label = new JLabel("<html><div style='text-align: center;'>Número de OVNIS: " +
                numberOfShips + "</div></html>");
        label.setPreferredSize(new Dimension(100, 150));
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(0, 30, 0, 30));
        this.add(label);
    }

    private void addLabelAparitionTime() {
        JLabel label = new JLabel("<html><div style='text-align: center;'>Tiempo de aparición de OVNIS:"
                + aparitionTime + " ms</div></html>");
        label.setPreferredSize(new Dimension(100, 150));
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(0, 30, 0, 30));
        this.add(label);
    }

    private void addLabelVelocity() {
        JLabel label = new JLabel(
                "<html><div style='text-align: center;'>Velocidad de OVNIS: " + velocity
                        + "ms</div></html>");
        label.setPreferredSize(new Dimension(100, 150));
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(0, 30, 0, 30));
        this.add(label);
    }

    private void addLabelMovementShips() {
        labelMovementShips = new JLabel("<html><div style='text-align: center;'>OVNIS en movimiento: 0</div></html>");
        labelMovementShips.setPreferredSize(new Dimension(100, 150));
        labelMovementShips.setFont(new Font("Arial", Font.BOLD, 20));
        labelMovementShips.setForeground(Color.WHITE);
        labelMovementShips.setBorder(new EmptyBorder(0, 30, 0, 30));
        this.add(labelMovementShips);
    }

    private void addLabelCrashedShips() {
        labelCrashedShips = new JLabel("<html><div style='text-align: center;'>OVNIS estrellados: 0</div></html>");
        labelCrashedShips.setPreferredSize(new Dimension(100, 150));
        labelCrashedShips.setFont(new Font("Arial", Font.BOLD, 20));
        labelCrashedShips.setForeground(Color.WHITE);
        labelCrashedShips.setBorder(new EmptyBorder(0, 30, 0, 30));
        this.add(labelCrashedShips);
    }

    public void createTable() {
        String[] columnNames = { "Clientes" };
        clients = mainView.presenter.getListClientsNames();
        if (clients == null) {
            clients = new ArrayList<>();
        }
        Object[][] data = new Object[clients.size()][1];
        for (int i = 0; i < clients.size(); i++) {
            data[i][0] = clients.get(i);
        }
        JTable table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.revalidate();
        this.repaint();
    }

    public void updateInformationPlay() {
        columnNames = new String[] { "Clientes" };
        clients = mainView.presenter.getListClientsNames();
        if (clients == null) {
            clients = new ArrayList<>();
        }
        Object[][] data = new Object[clients.size()][1];
        for (int i = 0; i < clients.size(); i++) {
            data[i][0] = clients.get(i);
        }
        table = new JTable(data, columnNames);
        scrollPane.setViewportView(table);
        this.revalidate();
        this.repaint();
    }

    public void repaintInformationPlay(int numberOfShips, int velocity, int aparitionTime) {
        this.numberOfShips = mainView.presenter.getNumberOfShips();
        this.velocity = mainView.presenter.getVelocity();
        this.aparitionTime = mainView.presenter.getAparitionTime();
        System.out.println("Numero de ovnis: " + numberOfShips);
        System.out.println("Velocidad de ovnis: " + velocity);
        System.out.println("Tiempo de aparicion de ovnis: " + aparitionTime);
        this.removeAll();
        addLabels();
        this.revalidate();
        this.repaint();
    }

    private void addLabels() {
        addLabelNumShips();
        addLabelAparitionTime();
        addLabelVelocity();
        addLabelMovementShips();
        addLabelCrashedShips();
        createTable();
    }

    public void updateTotalShipsOnScreen(int totalShips) {
        labelMovementShips
                .setText("<html><div style='text-align: center;'>OVNIS en movimiento: " + totalShips + "</div></html>");
    }

    public void updateTotalShipsCrashed(int totalCrashed) {
        labelCrashedShips
                .setText("<html><div style='text-align: center;'>OVNIS estrellados: " + totalCrashed + "</div></html>");
    }
}