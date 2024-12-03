package co.edu.uptc.views.dialogs;

import javax.swing.JDialog;
import co.edu.uptc.views.MainView;
import co.edu.uptc.views.dialogs.panelsPlay.DisplacementPlay;
import co.edu.uptc.views.dialogs.panelsPlay.InformationPlay;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class DialogPlay extends JDialog {

    public DisplacementPlay displacementPlay;
    public MainView mainView;
    public InformationPlay informationPlay;
    
    public DialogPlay(MainView mainView) {
        this.mainView = mainView;
        initFrame();
        addPanels();
    }

    public void initFrame() {
        this.setTitle("Play");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width-200, screenSize.height-200);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }

    public void begin() {
        this.setVisible(true);
    }

    private void createDisplacementPlay() {
        displacementPlay = new DisplacementPlay(mainView, 
        "resources\\fondoJuego.jpg", mainView.ufoPath);
        this.add(displacementPlay,  BorderLayout.CENTER);
    }

    private void createInformationPlay() {
        informationPlay = new InformationPlay(mainView);
        this.add(informationPlay, BorderLayout.WEST);
    }

    private void addPanels() {
        createDisplacementPlay();
        createInformationPlay();
    }
}
