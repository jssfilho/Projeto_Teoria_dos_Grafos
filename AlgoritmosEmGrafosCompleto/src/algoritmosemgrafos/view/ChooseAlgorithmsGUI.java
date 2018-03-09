/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosemgrafos.view;

import algoritmosemgrafos.controller.MainController.ActionForAlgorithms;
import algoritmosemgrafos.controller.MainController.FocusForChooseAlgorithms;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author joseph
 */
public class ChooseAlgorithmsGUI extends JDialog {
    
    private JPanel    algorithmsJPanel;
    
    private JCheckBox extendShortestPathJCheckBox;
    private JCheckBox floydWarshallJCheckBox;
    private JCheckBox dijkstraJCheckBox;
    private JCheckBox johnsonJCheckBox;
    private JCheckBox allJCheckBox;
    private JButton continueJButton;
    
    /****************************************/
    
    private JPanel     intervalsJPanel;
    
    private JPanel     nIntervalsJPanel;
    
    private JTextField nMinJTextField;
    private JTextField nMaxJTextField;
    private JTextField increasenJTextField;
    
    private JPanel     dIntervalsJPanel;
    private JTextField dMinJTextField;
    private JTextField dMaxJTextField;
    private JTextField increasedJTextField;

    public ChooseAlgorithmsGUI() {
        setTitle("Configure some parameters");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        initComponents();
        
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        
        intervalsJPanel = new JPanel(new FlowLayout());
        add(intervalsJPanel, BorderLayout.NORTH);
        
        nIntervalsJPanel = new JPanel(new FlowLayout());
        nIntervalsJPanel.setBorder(BorderFactory.createTitledBorder("|V| Intervals"));
        
        nIntervalsJPanel.add(new JLabel("Min |V|"));
        nMinJTextField = new JTextField(3);
        nIntervalsJPanel.add(nMinJTextField);
        
        nIntervalsJPanel.add(new JLabel("Max |V|"));
        nMaxJTextField = new JTextField(3);
        nIntervalsJPanel.add(nMaxJTextField);
        
        nIntervalsJPanel.add(new JLabel("Increase"));
        increasenJTextField = new JTextField(3);
        nIntervalsJPanel.add(increasenJTextField);
        
        intervalsJPanel.add(nIntervalsJPanel);
        
        dIntervalsJPanel = new JPanel(new FlowLayout());
        dIntervalsJPanel.setBorder(BorderFactory.createTitledBorder("D Intervals"));
        
        dIntervalsJPanel.add(new JLabel("Min D"));
        dMinJTextField = new JTextField(3);
        dIntervalsJPanel.add(dMinJTextField);
        
        dIntervalsJPanel.add(new JLabel("%   Max D"));
        dMaxJTextField = new JTextField(3);
        dIntervalsJPanel.add(dMaxJTextField);
        
        dIntervalsJPanel.add(new JLabel("%   Increase"));
        increasedJTextField = new JTextField(3);
        dIntervalsJPanel.add(increasedJTextField);
        
        dIntervalsJPanel.add(new JLabel("%"));
        
        intervalsJPanel.add(dIntervalsJPanel);
        
        ButtonGroup group = new ButtonGroup();
        group.add(extendShortestPathJCheckBox);
        group.add(floydWarshallJCheckBox);
        group.add(dijkstraJCheckBox);
        group.add(johnsonJCheckBox);
        group.add(allJCheckBox);
        
        algorithmsJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        algorithmsJPanel.setBorder(BorderFactory.createTitledBorder("Select the algorithms you want to run tests"));
        add(algorithmsJPanel);
        
        extendShortestPathJCheckBox = new JCheckBox("Extend Shortest Path");
        extendShortestPathJCheckBox.setActionCommand("extend");
        algorithmsJPanel.add(extendShortestPathJCheckBox);
        
        floydWarshallJCheckBox = new JCheckBox("Floyd Warshall");
        floydWarshallJCheckBox.setActionCommand("floyd");
        algorithmsJPanel.add(floydWarshallJCheckBox);
        
        dijkstraJCheckBox = new JCheckBox("Dijkstra");
        dijkstraJCheckBox.setActionCommand("dijkstra");
        algorithmsJPanel.add(dijkstraJCheckBox);
        
        johnsonJCheckBox = new JCheckBox("Johnson");
        johnsonJCheckBox.setActionCommand("johnson");
        algorithmsJPanel.add(johnsonJCheckBox);
        
        allJCheckBox = new JCheckBox("Select/deselect All");
        allJCheckBox.setActionCommand("all");
        
        allJCheckBox.setFont(new Font(null, Font.BOLD, allJCheckBox.getFont().getSize()));
        algorithmsJPanel.add(allJCheckBox);
        
        continueJButton = new JButton("Continue");
        continueJButton.setActionCommand("continue");
        add(continueJButton, BorderLayout.SOUTH);
        
        /********************SET DEFAULT VALUES *****************/
        setDefaultValues(10, 100, 10, 30, 100, 10);
        selectAll(true);
        /********************************************************/
        
        JOptionPane.showMessageDialog(this, "Just click Continue button");
    }
    
    private void selectAll(boolean selected) {
        allJCheckBox.setSelected(selected);
        selectAll();
    }
    public void selectAll() {
        boolean select = allJCheckBox.isSelected();
        
        extendShortestPathJCheckBox.setSelected(select);
        floydWarshallJCheckBox.setSelected(select);
        dijkstraJCheckBox.setSelected(select);
        johnsonJCheckBox.setSelected(select);
        checkIfEverithingIsOk();
    }
    
    public void setSelectAll() {
        if (extendShortestPathJCheckBox.isSelected() && floydWarshallJCheckBox.isSelected() && dijkstraJCheckBox.isSelected() && johnsonJCheckBox.isSelected()) {
            allJCheckBox.setSelected(true);
        } else {
            allJCheckBox.setSelected(false);
        }
        checkIfEverithingIsOk();
    }
    
    public void addActions(ActionForAlgorithms actionForAlgorithms) {
        extendShortestPathJCheckBox.addActionListener(actionForAlgorithms);
        floydWarshallJCheckBox.addActionListener(actionForAlgorithms);
        dijkstraJCheckBox.addActionListener(actionForAlgorithms);
        johnsonJCheckBox.addActionListener(actionForAlgorithms);
        
        allJCheckBox.addActionListener(actionForAlgorithms);
        
        continueJButton.addActionListener(actionForAlgorithms);
    }

    public int getSelected() {
        int sum = 0;
        
        if (extendShortestPathJCheckBox.isSelected())
            sum += 1;
        if (floydWarshallJCheckBox.isSelected())
            sum += 2;
        if (dijkstraJCheckBox.isSelected())
            sum += 4;
        if (johnsonJCheckBox.isSelected())
            sum += 8;
        
        return sum;
    }
    
    private void setDefaultValues(int nMin, int nMax, int incn, int dMin, int dMax, int incd) {
        nMinJTextField.setText(Integer.toString(nMin));
        nMaxJTextField.setText(Integer.toString(nMax));
        increasenJTextField.setText(Integer.toString(incn));
        
        dMinJTextField.setText(Integer.toString(dMin));
        dMaxJTextField.setText(Integer.toString(dMax));
        increasedJTextField.setText(Integer.toString(incd));
    }
    
    /*
    private void setnMin(int value) {
        try {
            nMinJTextField.setText(Integer.toString(value));
            if (Integer.parseInt(nMaxJTextField.getText()) < Integer.parseInt(nMinJTextField.getText()))
                nMaxJTextField.setText(Integer.toString(value));
        } catch (NumberFormatException ex) {
            
        }
    }
    */
    /*
    private void setnMax(int value) {
        try {
            nMaxJTextField.setText(Integer.toString(value));
            if (Integer.parseInt(nMinJTextField.getText()) + Integer.parseInt(increasenJTextField.getText()) > Integer.parseInt(nMaxJTextField.getText())) {
                increasenJTextField.setText(Integer.toString(Integer.parseInt(nMaxJTextField.getText()) - Integer.parseInt(nMinJTextField.getText())));
            }
        } catch (NumberFormatException ex) {
            
        }
    }
    */

    public int getnMin() {
        return Integer.parseInt(nMinJTextField.getText());
    }
    
    public int getnMax() {
        return Integer.parseInt(nMaxJTextField.getText());
    }
    
    public int getIncreasen() {
        return Integer.parseInt(increasenJTextField.getText());
    }
    
    public int getdMin() {
        return Integer.parseInt(dMinJTextField.getText());
    }
    
    public int getdMax() {
        return Integer.parseInt(dMaxJTextField.getText());
    }
    
    public int getIncreased() {
        return Integer.parseInt(increasedJTextField.getText());
    }

    public void checkIfEverithingIsOk() {
        if (extendShortestPathJCheckBox.isSelected() ||
                floydWarshallJCheckBox.isSelected() ||
                dijkstraJCheckBox.isSelected() ||
                johnsonJCheckBox.isSelected())
        {
            try {
                if (Integer.parseInt(nMinJTextField.getText()) <= 0) {
                    nMinJTextField.setText("1");
                }
                if (Integer.parseInt(nMaxJTextField.getText()) < Integer.parseInt(nMinJTextField.getText())) {
                    nMaxJTextField.setText(nMinJTextField.getText());
                }
                if (Integer.parseInt(nMinJTextField.getText()) + Integer.parseInt(increasenJTextField.getText()) > Integer.parseInt(nMaxJTextField.getText())) {
                    increasenJTextField.setText(Integer.toString(Integer.parseInt(nMaxJTextField.getText()) - Integer.parseInt(nMinJTextField.getText())));
                }
                if (Integer.parseInt(increasenJTextField.getText()) <= 0) {
                    increasenJTextField.setText("1");
                }
                
                if (Integer.parseInt(dMinJTextField.getText()) <= 0) {
                    dMinJTextField.setText("1");
                }
                if (Integer.parseInt(dMaxJTextField.getText()) < Integer.parseInt(dMinJTextField.getText())) {
                    dMaxJTextField.setText(dMinJTextField.getText());
                }
                if (Integer.parseInt(dMinJTextField.getText()) + Integer.parseInt(increasedJTextField.getText()) > Integer.parseInt(dMaxJTextField.getText())) {
                    increasedJTextField.setText(Integer.toString(Integer.parseInt(dMaxJTextField.getText()) - Integer.parseInt(dMinJTextField.getText())));
                }
                if (Integer.parseInt(increasedJTextField.getText()) <= 0) {
                    increasedJTextField.setText("1");
                }
                continueJButton.setEnabled(true);
            } catch (NumberFormatException ex) {
                continueJButton.setEnabled(false);
            }
        } else {
            continueJButton.setEnabled(false);
        }
    }

    public void setFocusListener(FocusForChooseAlgorithms focusForChooseAlgorithms) {
        nMinJTextField.addFocusListener(focusForChooseAlgorithms);
        nMaxJTextField.addFocusListener(focusForChooseAlgorithms);
        increasenJTextField.addFocusListener(focusForChooseAlgorithms);
        
        dMinJTextField.addFocusListener(focusForChooseAlgorithms);
        dMaxJTextField.addFocusListener(focusForChooseAlgorithms);
        increasedJTextField.addFocusListener(focusForChooseAlgorithms);
    }
    
}
