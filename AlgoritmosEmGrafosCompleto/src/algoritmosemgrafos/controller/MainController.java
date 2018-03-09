package algoritmosemgrafos.controller;

import algoritmosemgrafos.view.ChooseAlgorithmsGUI;
import algoritmosemgrafos.model.Graph;
import algoritmosemgrafos.model.Vertex;
import algoritmosemgrafos.view.ChooseAnOption;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author joseph
 */
public class MainController {
    
    private String version;

    private ConfigController configController;
    private HowToCreateGraphController howToCreateGraphController;
    
    private Graph graph;
    private ShowFunctionsController showFunctionsController;
    
    /**
     * The Main Construct of the application
     */
    public MainController(final String version) {
        this.version = version;
        initProgram();
    }

    /**
     * The Program will initialize from here
     */
    private void initProgram() {
        configController = new ConfigController();
        chooseAnOption();
        //howToCreateGraph();
    }

    /**
     * Returns the Controller for Main Window
     * @return the Controller for Main Window
     */
    public ShowFunctionsController getShowFunctionsController() {
        return showFunctionsController;
    }

    /**
     * Returns the Graph
     * @return 
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Returns the Program's Version
     * @return 
     */
    public String getVersion() {
        return version;
    }
    
    /**
     * Returns the Last Directory that a file was opened
     * @return 
     */
    public String getLastDir() {
        return configController.getLastDir();
    }

    /**
     * Store the Last Directory that a file was opened
     * @param lastDir the Last directory
     */
    void setLastDir(String lastDir) {
        configController.setLastDir(lastDir);
    }

    /**
     * Store the Last Look And Feel selected
     * @param lastLook the Last Look And Feel
     */
    void setLastLook(String lastLook) {
        configController.setLastLook(lastLook);
    }
    
    /**
     * Create a new Graph from file
     * @param absolutePath the file that contains the Graph Data
     */
    void setGraph(String absolutePath) {
        graph = new Graph(absolutePath);
        showFunctionsController = new ShowFunctionsController(this);
    }
    
    /**
     * Create a new Empty Graph
     * @param absolutePath the file that contains the Graph Data
     */
    void setGraph() {
        graph = new Graph(0, 0);
        showFunctionsController = new ShowFunctionsController(this);
    }
    
    /**
     * Create a new Graph from number of Vertices and the Density of the Graph
     * @param n Number of Vertices
     * @param d Density of the Graph
     */
    void setGraph(int n, int d) {
        graph = new Graph(n, d);
        showFunctionsController = new ShowFunctionsController(this);
    }
    
    /**
     * Ask if want really close application
     * @param window The window to close (dispose)
     */
    public void closing(Window window) {
        if (JOptionPane.showConfirmDialog(window, "Are you sure you want exit?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            window.dispose();
        }
    }

    /**
     * Create a Window where choose the way to create a new Graph
     */
    void howToCreateGraph() {
        howToCreateGraphController = new HowToCreateGraphController(this);
    }

    void readingFile() {
        ChooseFileController chooseFileController = new ChooseFileController(this);
    }

    void readingNumVertexAndDensity() {
        ChooseRandomDataController chooseRandomDataController = new ChooseRandomDataController(this);
    }

    private ChooseAlgorithmsGUI chooseAlgorithmsGUI;
    void executeTests() {
        JOptionPane.showMessageDialog(null, "Now we are taking some tests about Shortest Paths Problem Algorithms");
        
        chooseAlgorithmsGUI = new ChooseAlgorithmsGUI();
        chooseAlgorithmsGUI.addActions(new ActionForAlgorithms());
        chooseAlgorithmsGUI.setFocusListener(new FocusForChooseAlgorithms());
        chooseAlgorithmsGUI.setVisible(true);
        
        runTests(nMin, nMax, increasen, dMin, dMax, increased, selected);
    }
    
    private int selected;
    
    private int nMin;
    private int nMax;
    private int increasen;
    
    private int dMin;
    private int dMax;
    private int increased;

    private ChooseAnOption chooseAnOption;
    private void chooseAnOption() {
        chooseAnOption = new ChooseAnOption(this);
        chooseAnOption.addActionListener(new ActionForChooseAnOption());
        chooseAnOption.setVisible(true);
    }

    private void runTests(int nMin, int nMax, int increasen, int dMin, int dMax, int increased, int selected) {
        Graph graphTest;
        long[][][] times = new long[4][(nMax - nMin + increasen) / increasen][(dMax - dMin + increased) / increased];
        
        long ini = 0, end = 0;
        
        for (int n = nMin; n <= nMax; n += increasen) {
            for (int d = dMin; d <= dMax; d += increased) {
                if (selected % 2 != 0) { // Extend
                    
//                    do {
                        graphTest = new Graph(n, d, false, true);
//                    } while (!graphTest.bellmanFord(graphTest.getVertex(0)));
                    
                    ini = System.currentTimeMillis();
                    
                    double[][] W = graphTest.buildWeightMatrix();
                    double[][] L = W.clone();
                    
                    for (int k = 0; k < W.length-2; ++k) {
                        L = graphTest.extendShortestPath(L, W);
                    }
                    
                    end = System.currentTimeMillis();
                    times[0][(n-nMin)/increasen][(d-dMin)/increased] = end - ini;
                }
                
                if (selected == 2 || selected == 3 || selected == 6 || selected == 7 || selected == 10 || selected == 11 || selected == 14 || selected == 15) { // Floyd
                    
//                    do {
                        graphTest = new Graph(n, d, false, true);
//                    } while (!graphTest.bellmanFord(graphTest.getVertex(0)));
                    
                    ini = System.currentTimeMillis();
                    
                    double[][] W = graphTest.buildWeightMatrix();
                    double[][] D = graphTest.floydWarshall(W);
                    
                    end = System.currentTimeMillis();
                    times[1][(n-nMin)/increasen][(d-dMin)/increased] = end - ini;
                }
                
                if (selected == 4 || selected == 5 || selected == 6 || selected == 7 || selected == 12 || selected == 13 || selected == 14 || selected == 15) { // Dijkstra
//                    do {
                        graphTest = new Graph(n, d, false, false);
//                    } while (!graphTest.bellmanFord(graphTest.getVertex(0)));
                    
                    ini = System.currentTimeMillis();
                    
                    for (int i = 0; i < graphTest.getNumVertices(); ++i) {
                        graphTest.dijkstra(graphTest.getVertex(i));
                    }
                    
                    end = System.currentTimeMillis();
                    times[2][(n-nMin)/increasen][(d-dMin)/increased] = end - ini;
                }
                
                if (selected >= 8) { // Johnson
//                    do {
                        graphTest = new Graph(n, d, false, true);
//                    } while (!graphTest.bellmanFord(graphTest.getVertex(0)));
                    
                    ini = System.currentTimeMillis();
                    
                    double[][] D = graphTest.johnson();
                    
                    end = System.currentTimeMillis();
                    times[3][(n-nMin)/increasen][(d-dMin)/increased] = end - ini;
                }
                System.out.println("|V| = " + n + ", d = " + d + "% ok");
            }
        }
        
        /*
        for (int k = 0; k < 4; k++) {
            for (int n = nMin; n <= nMax; n += increasen) {
                for (int d = dMin; d <= dMax; d += increased) {
                    switch (k) {
                        case 0: // 0 - Extend_Shortest_Path
                            graphTest = new Graph(n, d, false, true);
                            ini = System.currentTimeMillis();
                            // run algorithm here
                            end = System.currentTimeMillis();
                            break;
                        case 1: // 1 - Floyd-Warshall
                            graphTest = new Graph(n, d, false, true);
                            ini = System.currentTimeMillis();
                            // run algorithm here
                            end = System.currentTimeMillis();
                            break;
                        case 2: // 2 - Dijkstra
                            graphTest = new Graph(n, d, false, false);
                            Iterator<Vertex> it = graphTest.getVertices().iterator();
                            Vertex vertex = null;
                            
                            ini = System.currentTimeMillis();
                            while (it.hasNext()) {
                                vertex = it.next();
                                graphTest.dijkstra(vertex);
                            }
                            end = System.currentTimeMillis();
                            break;
                        case 3: // 3 - Johnson
                            graphTest = new Graph(n, d, false, true);
                            ini = System.currentTimeMillis();
                            // run algorithm here
                            end = System.currentTimeMillis();
                            break;
                    }
                    times[k][(n-nMin)/increasen][(d-dMin)/increased] = end - ini;
                }
            }
        }
        */
        
        save(times);
        /*
        for (int k = 0; k < 4; k++) {
            for (int n = nMin; n <= nMax; n += increasen) {
                for (int d = dMin; d <= dMax; d += increased) {
                    System.out.print(times[k][(n-nMin)/increasen][(d-dMin)/increased] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
         * 
         */
        
    }

    private void save(long[][][] times) {
        JFileChooser save = new JFileChooser(System.getProperty("user.home"));
        
        if (save.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new FileWriter(save.getSelectedFile()));

                for (int k = 0; k < times.length; k++) {
                    for (int i = 0; i < times[0].length; i++) {
                        for (int j = 0; j < times[0][0].length; j++) {
                            out.write(Long.toString(times[k][i][j]) + " ");
                        }
                        out.write("\n");
                    }
                    out.write("\n");
                }
                out.close();
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {

            }
        }
        System.exit(0);
    }
    
    
    public class ActionForChooseAnOption implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Ok")) {
                int select = chooseAnOption.getSelectedOption();
                
                switch (select) {
                    case 0:
                        chooseAnOption.dispose();
                        howToCreateGraph();
                        break;
                    case 1:
                        chooseAnOption.dispose();
                        executeTests();
                        break;
                    default:
                        chooseAnOption.nothingSelected();
                }
            }
        }
        
    }
    
    public class ActionForAlgorithms implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (command.equals("all")) {
                chooseAlgorithmsGUI.selectAll();
            } else if (command.equals("extend") || command.equals("floyd") || command.equals("dijkstra") || command.equals("johnson")) {
                chooseAlgorithmsGUI.setSelectAll();
            } else if (command.equals("continue")) {
                
                nMin = chooseAlgorithmsGUI.getnMin();
                nMax = chooseAlgorithmsGUI.getnMax();
                increasen = chooseAlgorithmsGUI.getIncreasen();

                dMin = chooseAlgorithmsGUI.getdMin();
                dMax = chooseAlgorithmsGUI.getdMax();
                increased = chooseAlgorithmsGUI.getIncreased();
                selected = chooseAlgorithmsGUI.getSelected();

                chooseAlgorithmsGUI.dispose();
            }
            
        }
        
    }
    
    public class FocusForChooseAlgorithms implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            JTextField textField = (JTextField) e.getComponent();
            textField.select(0, textField.getText().length());
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField textField = (JTextField) e.getComponent();
            chooseAlgorithmsGUI.checkIfEverithingIsOk();
        }
        
    }
    
}
