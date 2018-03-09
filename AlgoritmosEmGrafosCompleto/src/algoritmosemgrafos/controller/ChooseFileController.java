package algoritmosemgrafos.controller;

import algoritmosemgrafos.view.ChooseFileGUI;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author joseph
 */
public class ChooseFileController {

    private final MainController mainController;
    
    private ChooseFileGUI chooseFileGUI;
    
    public ChooseFileController(final MainController mainController) {
        this.mainController = mainController;
        init();
    }

    private void init() {
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                chooseFileGUI = new ChooseFileGUI(mainController.getLastDir());
                
                boolean fileReaded = false;
                int option;
                
                while (!fileReaded) {
                    option = chooseFileGUI.showOpenDialog(chooseFileGUI);
                    
                    if (option == ChooseFileGUI.APPROVE_OPTION) {
                        File file = chooseFileGUI.getSelectedFile();
                        mainController.setLastDir(file.getParent());

                        if (file.exists() && file.canRead()) {
                            fileReaded = true;

                            mainController.setGraph(file.getAbsolutePath());

                        } else {
                            JOptionPane.showMessageDialog(chooseFileGUI, "Error trying to read file. Be sure you have access right to this file and try again", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        fileReaded = true;
                    }
                }
            }
        });
    }
    
}
