package algoritmosemgrafos;

import algoritmosemgrafos.controller.MainController;

/**
 * @author joseph - joseph@alu.ufc.br / josephseraos@gmail.com
 * 
 * Version 1.0: 2011/08/23
 * Building the Graph Data Structure
 * 
 * Version 2.0: 2011/08/31
 * Added Graphic User Interface
 * Remake of Graph Data Structure
 * Added some functions
 * 
 * Version 3.0: 2011/09/02
 * Improved Graph Data Structure
 * Added Full Screen
 * Added About
 * 
 * version 3.1: 2011/09/08
 * Improved GUI - Added arrows
 * to indicate orientation
 * of a arc
 * 
 * version 3.2: 2011/09/09
 * Improved GUI - Added new functions
 * Add/Remove Vertex from GUI
 * 
 * version 3.3: 2011/09/14
 * Added BFS Function
 * 
 * version 3.4: 2011/10/28
 * Added new Functions
 * 
 */
public class AlgoritmosEmGrafos {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String version = "3.4";
        MainController mainController = new MainController(version);
    }
}
