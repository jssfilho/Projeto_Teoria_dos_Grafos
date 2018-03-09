package algoritmosemgrafos.view;

import algoritmosemgrafos.controller.MainController;
import algoritmosemgrafos.controller.MainController.ActionForChooseAnOption;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

/**
 *
 * @author joseph
 */
public class ChooseAnOption extends JFrame {

    private final MainController mainController;
    
    public ChooseAnOption(MainController mainController) {
        this.mainController = mainController;
        
        setTitle("Choose an Option");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }
    
    private ButtonGroup group;
    private JRadioButton createGraphJRadioButton;
    private JRadioButton testsJRadioButton;
    
    private JButton okJButton;
    
    private JLabel warningJLabel;
    
    private JPanel checkButtonsJPanel;
    private GridBagConstraints gbc;
    
    private void initComponents() {
        checkButtonsJPanel = new JPanel(new GridBagLayout());
        checkButtonsJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.ipadx = 2;
        gbc.ipady = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        
        createGraphJRadioButton = new JRadioButton("Create or Open a Graph");
        gbc.gridx = 0;
        gbc.gridy = 0;
        checkButtonsJPanel.add(createGraphJRadioButton, gbc);
        
        testsJRadioButton = new JRadioButton("Run tests");
        gbc.gridy = 1;
        checkButtonsJPanel.add(testsJRadioButton, gbc);
        
        group = new ButtonGroup();
        group.add(createGraphJRadioButton);
        group.add(testsJRadioButton);
        
        warningJLabel = new JLabel(" ");
        warningJLabel.setForeground(Color.red);
        gbc.gridx = 0;
        gbc.gridy = 2;
        checkButtonsJPanel.add(warningJLabel, gbc);
        
        okJButton = new JButton("Ok");
        gbc.ipadx = 1;
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        checkButtonsJPanel.add(okJButton, gbc);
        
        add(checkButtonsJPanel);
    }

    

    public int getSelectedOption() {
        int selected = -1;
        
        if (createGraphJRadioButton.isSelected())
            selected = 0;
        else if (testsJRadioButton.isSelected())
            selected = 1;
        
        return selected;
    }

    public void addActionListener(ActionForChooseAnOption actionForChooseAnOption) {
        okJButton.addActionListener(actionForChooseAnOption);
    }

    public void nothingSelected() {
        warningJLabel.setText("Please, select one option.");
    }
    
}
