package algoritmosemgrafos.view;

import algoritmosemgrafos.controller.ChooseRandomDataController.MyActionListener;
import algoritmosemgrafos.controller.ChooseRandomDataController.MyFocusListener;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author joseph
 */
public class ChooseRandomDataGUI extends JFrame {
    
    private JTextField nJTextField;
    private JTextField dJTextField;
    
    private JButton okJButton;
    private JButton cancelJButton;
    
    public ChooseRandomDataGUI() {
        super("Choose Ransom Data");
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        add(new JLabel("Number of Vertices"));
        nJTextField = new JTextField(5);
        add(nJTextField);
        
        add(new JLabel("Density of the Graph"));
        dJTextField = new JTextField(5);
        add(dJTextField);
        
        okJButton = new JButton("Ok");
        add(okJButton);
        
        cancelJButton = new JButton("Cancel");
        add(cancelJButton);
        
        pack();
        setLocationRelativeTo(null);
    }

    public void addActionListener(MyActionListener myActionListener) {
        nJTextField.setActionCommand("Ok");
        dJTextField.setActionCommand("Ok");
        nJTextField.addActionListener(myActionListener);
        dJTextField.addActionListener(myActionListener);
        okJButton.addActionListener(myActionListener);
        cancelJButton.addActionListener(myActionListener);
    }
    
    public void addFocusListener(MyFocusListener myFocusListener) {
        super.addFocusListener(myFocusListener);
        nJTextField.addFocusListener(myFocusListener);
        dJTextField.addFocusListener(myFocusListener);
    }

    public int getN() {
        try {
            return Integer.parseInt(nJTextField.getText());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    public int getD() {
        try {
            return Integer.parseInt(dJTextField.getText());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
    
}
