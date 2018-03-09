package algoritmosemgrafos.view;

import javax.swing.JFileChooser;


/**
 *
 * @author joseph
 */
public class ChooseFileGUI extends JFileChooser {

    public ChooseFileGUI(String lastDir) {
        super(lastDir);
        setDialogTitle("Select a file to load the Graph");
    }
    
}
