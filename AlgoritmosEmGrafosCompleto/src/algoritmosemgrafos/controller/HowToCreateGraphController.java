package algoritmosemgrafos.controller;

import algoritmosemgrafos.view.HowToCreateGraphGUI;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author joseph
 */
public class HowToCreateGraphController {
    
    private final MainController mainController;
    
    private HowToCreateGraphGUI howToCreateGraphGUI;
    
    private ChooseFileController chooseFileController;
    private ChooseRandomDataController chooseRandomDataController;

    public HowToCreateGraphController(final MainController mainController) {
        this.mainController = mainController;
        initComponents();
    }
    
    private void initComponents() {
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                howToCreateGraphGUI = new HowToCreateGraphGUI(mainController);
                howToCreateGraphGUI.addActions(new Actions());
                //howToCreateGraphGUI.addWindowListener(new MyWindowListener());
                howToCreateGraphGUI.addKeyListener(new KeyListenerForActions());
                howToCreateGraphGUI.setVisible(true);
            }
        });
    }
    
    private void closing() {
        howToCreateGraphGUI.dispose();
    }
    
    public class Actions implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Ok")) {
                int option = howToCreateGraphGUI.getSelectedOption();
                
                if (option != 0) {
                    howToCreateGraphGUI.dispose();
                    
                    switch (option) {
                        case 1:
                            mainController.readingFile();
                            break;
                        case 2:
                            mainController.readingNumVertexAndDensity();
                            break;
                        case 3:
                            mainController.setGraph();
                            break;
                        default:
                            JOptionPane.showMessageDialog(howToCreateGraphGUI, "Unknown option.");
                            System.exit(1);
                    }
                    
                    
                } else {
                    JOptionPane.showMessageDialog(howToCreateGraphGUI, "Please, select a option from ComboBox", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getActionCommand().equals("Cancel")) {
                howToCreateGraphGUI.dispose();
            }
        }
    }
    
    public class KeyListenerForActions implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                closing();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
}
