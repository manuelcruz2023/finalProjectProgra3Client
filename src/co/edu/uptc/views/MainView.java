package co.edu.uptc.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import co.edu.uptc.interfaces.Contract;
import co.edu.uptc.interfaces.Contract.Presenter;
import co.edu.uptc.views.dialogs.DialogPlay;
import co.edu.uptc.views.resourcesView.BackgroundPanel;
import co.edu.uptc.views.resourcesView.RoundedButton;

public class MainView extends JFrame implements Contract.View {

    public Contract.Presenter presenter;
    private JPanel panel = new JPanel();
    public DialogPlay dialogPlay;
    public int numberOfShips;
    public int velocity;
    public int aparitionTime;
    private JTextField numberOfShipsField;
    private JTextField aparitionTimeField;
    private JTextField velocityField;
    private JComboBox<String> UFOS;
    public String ufoPath;
    public JRadioButton buttonTratectory;
    private String host;
    private String port;
    private String name;

    public MainView() {
        initFrame();
        createContentPane();
        showInputDialog();
        addTittle();
        createButtons();
    }
    
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void begin() {
        this.setVisible(true);
    }

    public void showInputDialog() {
        host = JOptionPane.showInputDialog("Ingrese la dirección IP del servidor:");
        port = JOptionPane.showInputDialog("Ingrese el puerto del servidor:");
        name = JOptionPane.showInputDialog("Ingrese su nombre:");
        addPanel();
    }

    public void initFrame() {
        this.setResizable(false);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
    }

    private void createContentPane() {
        BackgroundPanel backgroundPanel = new BackgroundPanel(
                "resources\\inicioJuegoNaves.jpg");
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        this.setContentPane(backgroundPanel);
    }

    private void addTittle() {
        JLabel tittle = new JLabel("Simulador de Naves");
        tittle.setForeground(Color.WHITE);
        tittle.setFont(new java.awt.Font("Arial", 1, 30));
        panel.add(tittle);
        panel.add(Box.createVerticalStrut(250));
    }

    private void createButtonStart() {
        RoundedButton buttonStart = new RoundedButton("Iniciar Juego");
        buttonStart.setPreferredSize(new java.awt.Dimension(150, 50));
        buttonStart.addActionListener(e -> {
            createDialogPlay();
            buttonStart.setEnabled(false);
        });
        panel.add(buttonStart);
    }

    private void createButtonOptions() {
        RoundedButton buttonOptions = new RoundedButton("Opciones");
        buttonOptions.setPreferredSize(new java.awt.Dimension(150, 50));
        buttonOptions.addActionListener(e -> {
            createInputPanel();
            buttonOptions.setEnabled(false);
        });
        panel.add(buttonOptions);
    }

    private void createButtonManual() {
        RoundedButton buttonManual = new RoundedButton("Manual");
        buttonManual.setPreferredSize(new java.awt.Dimension(150, 50));
        buttonManual.addActionListener(e -> {
            addTextManual();
        });
        panel.add(buttonManual);
    }

    private void addTextManual() {
        JOptionPane.showMessageDialog(null,
                "(Las entradas para velocidady tiempo de aparicion están en millisegundos) \n" +
                        "Favor ingresar nombres diferentes para cada cliente. " +
                        "1. Ingrese el número de naves que desea que aparezcan en pantalla.\n"
                        + "2. Ingrese el tiempo de aparición de las naves.\n"
                        + "3. Ingrese la velocidad de las naves.\n"
                        + "4. Seleccione el tipo de naves que desea que aparezcan.\n"
                        + "5. Haga clic en el botón 'Iniciar Juego'.\n"
                        + "6. Haga clic en el botón 'Opciones' para cambiar las opciones del juego.\n"
                        + "7. Haga clic en el botón 'Salir' para cerrar el juego.\n"
                        + "8. Una vez iniciado el juego, haga click derecho para aumentar o disminuir la velocidad de las naves. \n"
                        + "9. Haga click izquierdo para mover usted la nave, para dejar de moverla de nuevamente click izquierdo. \n"
                        + "10. Tenga en cuenta que despues de una vez presionado el boton jugar, el juego comenzara y si quiere cambiar las opciones tendrá que reiniciar el programa", 
                "Manual", JOptionPane.INFORMATION_MESSAGE);
    }

    private void createButtonExit() {
        RoundedButton buttonExit = new RoundedButton("Salir");
        buttonExit.setPreferredSize(new java.awt.Dimension(150, 50));
        buttonExit.addActionListener(e -> System.exit(0));
        panel.add(buttonExit);
    }

    private void createInputPanel() {
        numberOfShipsField = createTextField(5);
        aparitionTimeField = createTextField(5);
        velocityField = createTextField(5);
        UFOS = createComboBox();

        JPanel myPanel = createPanel(numberOfShipsField, aparitionTimeField, velocityField, UFOS);
        showConfirmDialog(myPanel, numberOfShipsField, aparitionTimeField);
    }

    private JTextField createTextField(int columns) {
        return new JTextField(columns);
    }

    private JComboBox<String> createComboBox() {
        UFOS = new JComboBox<>();
        UFOS.addItem("OVNI 1");
        UFOS.addItem("OVNI 2");
        UFOS.addItem("OVNI 3");
        UFOS.setSelectedIndex(0);
        selectedUFO();
        UFOS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedUFO();
            }
        });
        return UFOS;
    }
    
    private void selectedUFO() {
        switch (UFOS.getSelectedIndex()) {
            case 0:
                ufoPath = "resources\\UFO1.png";
                break;
            case 1:
                ufoPath = "resources\\UFO2.png";
                break;
            case 2:
                ufoPath = "resources\\UFO3.png";
                break;
        }
    }

    private JPanel createPanel(JTextField numberOfShipsField, JTextField aparitionTimeField, JTextField velocityField,
            JComboBox<String> UFOS) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(new JLabel("Número de naves:"));
        panel.add(numberOfShipsField);
        panel.add(new JLabel("Tiempo de aparición de naves:"));
        panel.add(aparitionTimeField);
        panel.add(new JLabel("Velocidad de las naves:"));
        panel.add(velocityField);
        panel.add(new JLabel("Tipo de naves:"));
        panel.add(UFOS);
        panel.add(addRadioButtonTrayectory());
        return panel;
    }

    private void showConfirmDialog(JPanel panel, JTextField numberOfShipsField, JTextField aparitionTimeField) {
        int result = JOptionPane.showConfirmDialog(null, panel, "Opciones", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (numberOfShipsField.getText().isEmpty() || aparitionTimeField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    numberOfShips = Integer.parseInt(numberOfShipsField.getText());
                    aparitionTime = Integer.parseInt(aparitionTimeField.getText());
                    velocity = Integer.parseInt(velocityField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private JRadioButton addRadioButtonTrayectory() {
        buttonTratectory = new JRadioButton();
        buttonTratectory.setText("Mostrar trayectoria");
        return buttonTratectory;
    }

    private void addPanel() {
        panel.setOpaque(false);
        this.add(panel);
    }

    private void createButtons() {
        createButtonStart();
        createButtonOptions();
        createButtonExit();
        createButtonManual();
    }

    private void createDialogPlay() {
        dialogPlay = new DialogPlay(this);
        dialogPlay.begin();
    }


    @Override
    public void updatePosition() {
        if (dialogPlay != null) {
            dialogPlay.displacementPlay.updatePosition();
        }
    }

    @Override
    public Dimension screenSize() {
        Dimension size = new Dimension();
        if (dialogPlay != null && dialogPlay.displacementPlay != null) {
            size = dialogPlay.displacementPlay.getSize();
        }
        return size;
    }

    @Override
    public int numberOfShips() {
        return numberOfShips;
    }

    @Override
    public int setVelocity() {
        return velocity;
    }

    @Override
    public int setAparitionTime() {
        return aparitionTime;
    }

    @Override
    public void updateTotalShipsOnScreen(int totalShips) {
        dialogPlay.informationPlay.updateTotalShipsOnScreen(totalShips);
    }

    @Override
    public void updateTotalShipsCrashed(int totalCrashed) {
        dialogPlay.informationPlay.updateTotalShipsCrashed(totalCrashed);
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return Integer.parseInt(port);
    }

    @Override
    public String getNickname() {
        return name;
    }

    @Override
    public void updateInformationPlay() {
        dialogPlay.informationPlay.updateInformationPlay();
    }

    @Override
    public void repaintInformationPlay(int numberOfShips, int velocity, int aparitionTime) {
        dialogPlay.informationPlay.repaintInformationPlay(numberOfShips, velocity, aparitionTime);
    }
}