package algoritmosemgrafos.view;

import algoritmosemgrafos.controller.MainController;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author joseph
 */
public class AboutGUI extends JDialog {

    private final MainController mainController;
    
    public AboutGUI(final MainController mainController) {
        super(mainController.getShowFunctionsController().getShowFunctionsGUI(), true);
        
        this.mainController = mainController;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("About");
        
        JLabel text = new JLabel(""
                + "<html>"
                + "<b>Universidade Federal do Ceará - UFC</b><br />"
                + "<i>Campus</i> Avançado de Sobral<br />"
                + "Engenharia da Computação<br /><br />"
                + "Trabalho da disciplina de Algoritmos em Grafos<br />"
                + "Professora Andréa Linhares<br /><br />"
                + "Estudante: <b>Joseph Soares Alcântara</b> - 7º semestre"
                + "</html>");
        text.setFont(new Font(null, Font.PLAIN, 16));
        text.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(text);
        
        pack();
        setLocationRelativeTo(mainController.getShowFunctionsController().getShowFunctionsGUI());
    }
    
}
