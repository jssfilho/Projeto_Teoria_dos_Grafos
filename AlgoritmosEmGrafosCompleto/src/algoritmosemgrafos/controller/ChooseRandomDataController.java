package algoritmosemgrafos.controller;

import algoritmosemgrafos.view.ChooseRandomDataGUI;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author joseph
 */
public class ChooseRandomDataController {

    private final MainController mainController;
    private ChooseRandomDataGUI chooseRandomDataGUI;
    
    ChooseRandomDataController(MainController mainController) {
        this.mainController = mainController;
        init();
    }

    private void init() {
        chooseRandomDataGUI = new ChooseRandomDataGUI();
        chooseRandomDataGUI.addActionListener(new MyActionListener());
        chooseRandomDataGUI.addWindowListener(new MyWindowListener());
        chooseRandomDataGUI.addFocusListener(new MyFocusListener());
        chooseRandomDataGUI.setVisible(true);
    }
    
    private void initRandom(int n, int d) {
        mainController.setGraph(n, d);
    }
    
    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Ok")) {
                int n = chooseRandomDataGUI.getN();
                int d = chooseRandomDataGUI.getD();
                
                if (n <= 0 || d < 0 || d > 100) {
                    JOptionPane.showMessageDialog(chooseRandomDataGUI, "Error: You have to choose {n, d | n > 0 && d >= 0 && d <= 100}", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    chooseRandomDataGUI.dispose();
                    initRandom(n, d);
                }
                
            } else {
                chooseRandomDataGUI.dispose();
            }
        }
        
    }
    
    public class MyWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) {
            Window window = e.getWindow();
            
            mainController.closing(window);
        }

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
        
    }

    public class MyFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            JTextField textField = (JTextField) e.getComponent();
            textField.select(0, textField.getText().length());
        }

        @Override
        public void focusLost(FocusEvent e) {}
        
    }
}
