package algoritmosemgrafos.view;

import algoritmosemgrafos.controller.HowToCreateGraphController.Actions;
import algoritmosemgrafos.controller.MainController;
import java.awt.FlowLayout;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author joseph
 */
public class HowToCreateGraphGUI extends JFrame {

    private JComboBox optionsJComboBox;
    private JButton okJButton;
    private JButton cancelJButton;
    
    public HowToCreateGraphGUI(MainController mainController) {
        super("Choose how to create the Graph");
        
        initComponents();
    }

    private void initComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        String[] items = {
            "-- Select how to create the Graph --",
            "Reading from a file",
            "Reading Num Vertices and Density",
            "Create an Empty Graph"
        };
        
        optionsJComboBox = new JComboBox(items);
        add(optionsJComboBox);
        
        okJButton = new JButton("Ok");
        add(okJButton);
        
        cancelJButton = new JButton("Cancel");
        add(cancelJButton);
        
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        super.addKeyListener(l);
        
        optionsJComboBox.addKeyListener(l);
        okJButton.addKeyListener(l);
        cancelJButton.addKeyListener(l);
    }

    public void addActions(Actions actions) {
        okJButton.addActionListener(actions);
        cancelJButton.addActionListener(actions);
    }

    public int getSelectedOption() {
        return optionsJComboBox.getSelectedIndex();
    }
    
}
