package algoritmosemgrafos.controller;

import algoritmosemgrafos.model.Edge;
import algoritmosemgrafos.model.Graph;
import algoritmosemgrafos.model.Vertex;
import algoritmosemgrafos.view.AboutGUI;
import algoritmosemgrafos.view.ShowFunctionsGUI;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author joseph
 */
public class ShowFunctionsController {

    private final MainController mainController;
    private final Graph graph;
    private ShowFunctionsGUI showFunctionsGUI;
    
    private AboutGUI about;
    
    private boolean isOnFullScreen;
    private GraphicsDevice device;
    
    ShowFunctionsController(MainController mainController) {
        this.mainController = mainController;
        graph = mainController.getGraph();
        init();
    }

    private void init() {
        isOnFullScreen = false;
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                showFunctionsGUI = new ShowFunctionsGUI();
                showFunctionsGUI.setTitle(mainController.getVersion());
                if (!device.isFullScreenSupported())
                    showFunctionsGUI.disableFullScreen();
                showFunctionsGUI.addActionListener(new MyActionListener());
                showFunctionsGUI.getTextArea().addMouseListener(new MyMouseListener());
                showFunctionsGUI.addActionListenerForLookAndFeel(new ActionForLookAndFeel());
                showFunctionsGUI.addWindowListener(new MyWindowListener());
                showFunctionsGUI.addKeyListener(new MyKeyListener());
                showFunctionsGUI.setVisible(true);
            }
        });
        
    }

    public ShowFunctionsGUI getShowFunctionsGUI() {
        return showFunctionsGUI;
    }
    
    private void showAbout() {
        about = new AboutGUI(mainController);
        about.addKeyListener(new MyKeyListener());
        
        about.setVisible(true);
    }
    
    private void setFullScreen() {
        
        if (device.isFullScreenSupported()) {
            if (!isOnFullScreen) {
                device.setFullScreenWindow(showFunctionsGUI);
                isOnFullScreen = true;
            } else {
                device.setFullScreenWindow(null);
                isOnFullScreen = false;
            }
        } else {
            System.err.println("No Full Screen suported");
        }
    }
    
    private DrawGraphController drawGraphController;
    
    private void drawGraph(boolean directed) {
        
        if (directed) {
            drawGraphController = new DrawGraphController(graph);
        } else {
            drawGraphController = new DrawGraphController(graph.getUndirectedVersion());
        }
        
    }
    
    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            final String command = e.getActionCommand();
            
            System.out.println(command);
            showFunctionsGUI.setStatus(command);
            
            try {
                
                if (command.equals("Load a new Graph")) {
                    
                    if (JOptionPane.showConfirmDialog(showFunctionsGUI, "Are you sure you want load a new graph? This operation will delete all information from this graph.", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        showFunctionsGUI.dispose();
                        mainController.howToCreateGraph();
                    }
                    
                    showFunctionsGUI.setStatus("Ready");
                    return;
                    
                    // String[] op = {"Destroy this Graph", "Open on isOnFullScreen new window"};
                    // int opChoosed = JOptionPane.showOptionDialog(showFunctionsGUI, "Choose if you want to destroy this Graph or open isOnFullScreen new graph on isOnFullScreen new window", "Choose how to proceed", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, op, op[0]);
                    // if (opChoosed == 0) {
                        
                    // } else if (opChoosed == 1) {
                    //     mainController.getHowToCreateGraphControllers().add(new HowToCreateGraphController(mainController));
                    // }

                } else if (command.equals("Exit")) {
                    mainController.closing(showFunctionsGUI);
                    showFunctionsGUI.setStatus("Ready");
                    return;
                } else if (command.equals("Full Screen")) {
                    setFullScreen();
                    showFunctionsGUI.setStatus("Ready");
                    return;
                } else if (command.equals("Build Adjacency Matrix")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    graph.buildAdjacencyMatrix();
                } else if (command.equals("Destroy Adjacency Matrix")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    graph.destroyAdjacencyMatrix();
                } else if (command.equals("Display Adjacency Matrix")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    graph.displayAdjacencyMatrix(showFunctionsGUI.getTextArea());
                } else if (command.equals("Get Number of Edges")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    showFunctionsGUI.getTextArea().append(new Integer(graph.getNumEdges()).toString() + "\n\n");
                } else if (command.equals("Get Number of Vertices")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    showFunctionsGUI.getTextArea().append(new Integer(graph.getNumVertices()).toString() + "\n\n");
                } else if (command.equals("Get Out Degree") || command.equals("Get In Degree")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");

                    int op = (command.equals("Get Out Degree")? 0 : 1);

                    String enter = JOptionPane.showInputDialog("Enter the Label of the Vertex");

                    if (enter != null) {
                        int label = Integer.parseInt(enter.trim());
                        Vertex vertex = graph.getVertex(label);
                        if (vertex != null) {
                            if (op == 0) {
                                showFunctionsGUI.getTextArea().append("Out Degree V(" + label + ") = " + new Integer(graph.getOutDegree(vertex)).toString());
                            } else {
                                showFunctionsGUI.getTextArea().append("In Degree V(" + label + ") = " + new Integer(graph.getInDegree(vertex)).toString());
                            }
                        } else {
                            showFunctionsGUI.getTextArea().append("Error: you entered a non existent vertex");
                        }
                    } else {
                        showFunctionsGUI.getTextArea().append("Canceled");
                    }

                } else if (command.equals("Add Vertex") || command.equals("Remove Vertex")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    
                    int op = (command.equals("Add Vertex")? 0 : 1);
                    
                    String enter1 = JOptionPane.showInputDialog("Enter the Label of the Vertex");
                    
                    if (enter1 != null) {
                        int label = Integer.parseInt(enter1.trim());
                        
                        if (label >= 0) {
                            if (op == 0) {
                                if (graph.addVertex(label) == 0) {
                                    showFunctionsGUI.getTextArea().append("Vertex added: V(" + label + ")");
                                } else {
                                    showFunctionsGUI.getTextArea().append("You cannot add an Vertex with this label. There is already an Vertex");
                                }
                            } else {
                                if (graph.removeVertex(label) == 0) {
                                    showFunctionsGUI.getTextArea().append("Vertex removed: V(" + label + ")");
                                } else {
                                    showFunctionsGUI.getTextArea().append("There is no any Vertex with this label");
                                }
                            }
                        } else {
                            showFunctionsGUI.getTextArea().append("Label must be a positive value");
                        }
                        
                    } else {
                        showFunctionsGUI.getTextArea().append("Canceled");
                    }
                } else if (command.equals("Add Edge") || command.equals("Remove Edge")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    
                    int op = (command.equals("Add Edge")? 0 : 1);

                    String enter1 = JOptionPane.showInputDialog("Enter the Label of Origin Vertex");
                    String enter2 = null;
                    String enter3 = null;

                    if (enter1 != null) {
                        int labelOrigin  = Integer.parseInt(enter1.trim());
                        Vertex vertexOrigin  = graph.getVertex(labelOrigin);

                        if (vertexOrigin != null) {
                            enter2 = JOptionPane.showInputDialog("Enter the Label of Destiny Vertex");

                            if (enter2 != null) {
                                int labelDestiny = Integer.parseInt(enter2.trim());
                                Vertex vertexDestiny = graph.getVertex(labelDestiny);

                                if (vertexDestiny != null) {

                                    if (op == 0) {

                                        enter3 = JOptionPane.showInputDialog("Enter the Cost of the Edge");

                                        if (enter3 != null) {
                                            double cost = Double.parseDouble(enter3.trim());

                                            if (cost >= 0) {
                                                if (graph.addEdge(vertexOrigin, vertexDestiny, cost) == 0) {
                                                    showFunctionsGUI.getTextArea().append("Edge added: E(Origin("+ vertexOrigin.getLabel() + "), Destiny("+ vertexDestiny.getLabel() + "), Cost("+ cost + "))");
                                                } else {
                                                    showFunctionsGUI.getTextArea().append("You cannot add an Edge in this position. There is already an edge");
                                                }
                                            } else {
                                                showFunctionsGUI.getTextArea().append("Cost must be a positive value");
                                            }
                                        } else {
                                            showFunctionsGUI.getTextArea().append("Canceled");
                                        }

                                    } else {

                                        if (graph.removeEdge(vertexOrigin, vertexDestiny)) {
                                            showFunctionsGUI.getTextArea().append("Edge removed: E(Origin("+ vertexOrigin.getLabel() + "), Destiny("+ vertexDestiny.getLabel() + "))");
                                        } else {
                                            showFunctionsGUI.getTextArea().append("There is no any edge in this position");
                                        }

                                    }

                                } else {
                                    showFunctionsGUI.getTextArea().append("Error: you entered a non existent vertex");
                                }
                            } else {
                                showFunctionsGUI.getTextArea().append("Canceled");
                            }
                        } else {
                            showFunctionsGUI.getTextArea().append("Error: you entered a non existent vertex");
                        }
                    } else {
                        showFunctionsGUI.getTextArea().append("Canceled");
                    }


                } if (command.equals("Has Edge") || command.equals("Get Edge Cost")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");

                    int op = (command.equals("Has Edge")? 0 : 1);

                    String enter1 = JOptionPane.showInputDialog("Enter the Label of Origin Vertex");
                    String enter2 = null;

                    if (enter1 != null) {
                        int labelOrigin  = Integer.parseInt(enter1.trim());
                        Vertex vertexOrigin  = graph.getVertex(labelOrigin);

                        if (vertexOrigin != null) {
                            enter2 = JOptionPane.showInputDialog("Enter the Label of Destiny Vertex");

                            if (enter2 != null) {
                                int labelDestiny = Integer.parseInt(enter2.trim());
                                Vertex vertexDestiny = graph.getVertex(labelDestiny);

                                if (vertexDestiny != null) {

                                    if (graph.hasEdge(vertexOrigin, vertexDestiny)) {
                                        if (op == 0) {
                                            showFunctionsGUI.getTextArea().append("Yes. There is an Edge : E(Origin("+ vertexOrigin.getLabel() + "), Destiny("+ vertexDestiny.getLabel() + "), Cost("+ graph.getEdgeCost(vertexOrigin, vertexDestiny) + "))");
                                        } else {
                                            showFunctionsGUI.getTextArea().append("Edge Cost E(Origin("+ vertexOrigin.getLabel() + "), Destiny("+ vertexDestiny.getLabel() + ") = " + graph.getEdgeCost(vertexOrigin, vertexDestiny));
                                        }
                                    } else {
                                        showFunctionsGUI.getTextArea().append("There is no any edge in this position");
                                    }

                                } else {
                                    showFunctionsGUI.getTextArea().append("Error: you entered a non existent vertex");
                                }
                            } else {
                                showFunctionsGUI.getTextArea().append("Canceled");
                            }
                        } else {
                            showFunctionsGUI.getTextArea().append("Error: you entered a non existent vertex");
                        }
                    } else {
                        showFunctionsGUI.getTextArea().append("Canceled");
                    }

                } if (command.equals("Neighborhood") || command.equals("Reverse Neighborhood")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");

                    int op = (command.equals("Neighborhood")? 0 : 1);
                    String enter1 = JOptionPane.showInputDialog("Enter the Label Vertex");

                    if (enter1 != null) {
                        int labelOrigin  = Integer.parseInt(enter1.trim());
                        Vertex vertex  = graph.getVertex(labelOrigin);

                        if (vertex != null) {
                            ArrayList<Vertex> neighborhood = null;

                            if (JOptionPane.showConfirmDialog(showFunctionsGUI, "Enclosed?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                if (op == 0) {
                                    neighborhood = graph.neighbourhood(vertex, true);
                                } else {
                                    neighborhood = graph.reverseNeighbourhood(vertex, true);
                                }
                            } else {
                                if (op == 0) {
                                    neighborhood = graph.neighbourhood(vertex);
                                } else {
                                    neighborhood = graph.reverseNeighbourhood(vertex);
                                }
                            }

                            if (neighborhood != null) {
                                Iterator<Vertex> it = neighborhood.iterator();

                                if (op == 0) {
                                    showFunctionsGUI.getTextArea().append("Neighborhood V(" + vertex.getLabel() + ") = ");
                                } else {
                                    showFunctionsGUI.getTextArea().append("Reverse Neighborhood V(" + vertex.getLabel() + ") = ");
                                }

                                Vertex v = null;
                                while (it.hasNext()) {
                                    v = it.next();

                                    showFunctionsGUI.getTextArea().append(v.getLabel() + " ");
                                }

                            } else {
                                showFunctionsGUI.getTextArea().append("Error to process this vertex" + "\n\n");
                            }

                        } else {
                            showFunctionsGUI.getTextArea().append("Error: you entered a non existent vertex");
                        }
                    } else {
                        showFunctionsGUI.getTextArea().append("Canceled");
                    }

                } else if (command.equals("Transitive Closure") || command.equals("Reverse Transitive Closure")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");

                    int op = (command.equals("Transitive Closure")? 0 : 1);
                    String enter1 = JOptionPane.showInputDialog("Enter the Label Vertex");

                    if (enter1 != null) {
                        int labelOrigin  = Integer.parseInt(enter1.trim());
                        Vertex vertex  = graph.getVertex(labelOrigin);

                        if (vertex != null) {
                            ArrayList<Vertex> transitiveClosure = null;
                            
                            if (op == 0)
                                transitiveClosure = graph.transitiveClosure(vertex);
                            else
                                transitiveClosure = graph.reverseTransitiveClosure(vertex);

                            Iterator<Vertex> it = transitiveClosure.iterator();

                            if (op == 0)
                                showFunctionsGUI.getTextArea().append("Transitive Closure V(" + vertex.getLabel() + ") = ");
                            else
                                showFunctionsGUI.getTextArea().append("Reverse Transitive Closure V(" + vertex.getLabel() + ") = ");

                            Vertex v = null;
                            while (it.hasNext()) {
                                v = it.next();

                                showFunctionsGUI.getTextArea().append(v.getLabel() + " ");
                            }

                        } else {
                            showFunctionsGUI.getTextArea().append("Error: you entered a non existent vertex");
                        }
                    } else {
                        showFunctionsGUI.getTextArea().append("Canceled");
                    }

                } else if (command.equals("Strongly Connected Components")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    ArrayList<ArrayList<Vertex>> FCONEX = graph.FCONEX(graph.getVertex(0), null, null);

                    Iterator<ArrayList<Vertex>> it = FCONEX.iterator();
                    Iterator<Vertex> it2 = null;

                    showFunctionsGUI.getTextArea().append("{");
                    while (it.hasNext()) {
                        it2 = it.next().iterator();
                        showFunctionsGUI.getTextArea().append("{");
                        while (it2.hasNext()) {
                            showFunctionsGUI.getTextArea().append(it2.next().getLabel() + "");
                            if (it2.hasNext())
                                showFunctionsGUI.getTextArea().append(", ");
                        }
                        showFunctionsGUI.getTextArea().append("}");

                        if (it.hasNext())
                            showFunctionsGUI.getTextArea().append(", ");
                    }
                    showFunctionsGUI.getTextArea().append("}");

                } else if (command.equals("Get a Minimum Tree")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    ArrayList<Edge> prim = graph.prim();
                    for (Edge edge: prim) {
                        showFunctionsGUI.getTextArea().append(edge.getOrigin().getLabel() + ", " + edge.getDestiny().getLabel() + ", " + edge.getCost() + "\n");
                    }
                    
//                    Graph tmpGraph = new Graph();
//                    
//                    for (Vertex vertex : graph.getVertices()) {
//                        tmpGraph.addVertex(vertex.getLabel());
//                    }
//                    
//                    for (Edge edge : prim) {
//                        tmpGraph.addEdge(tmpGraph.getVertex(edge.getOrigin().getLabel()), tmpGraph.getVertex(edge.getDestiny().getLabel()), edge.getCost());
//                    }
//                    
//                    tmpGraph.setDirected(false);
//                    if (JOptionPane.showConfirmDialog(showFunctionsGUI, "Show Tree?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                        new DrawGraphController(tmpGraph);
//                    }
                } else if (command.equals("Extend Shortest Paht")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    
                    if (graph.bellmanFord(graph.getVertex(0))) {
                        double[][] W = graph.buildWeightMatrix();
                        double[][] L = W.clone();

                        showFunctionsGUI.getTextArea().append("L(0) == W\n\n");
                        graph.printMatrix(W, showFunctionsGUI.getTextArea());
                        showFunctionsGUI.getTextArea().append("\n");

                        for (int k = 0; k < W.length-2; ++k) {
                            showFunctionsGUI.getTextArea().append("k == " + (k+1) + "\n");
                            L = graph.extendShortestPath(L, W);
                            graph.printMatrix(L, showFunctionsGUI.getTextArea());
                            showFunctionsGUI.getTextArea().append("\n");
                        }

                        for (int i = 0; i < graph.getNumVertices(); ++i) {
                            if (graph.bellmanFord(graph.getVertex(i), 1)) {
                                for (int j = 0; j < graph.getNumVertices(); ++j) {
                                    if (i != j) {
                                        graph.printPath(graph.getVertex(i), graph.getVertex(j), showFunctionsGUI.getTextArea());
                                    }
                                }
                                showFunctionsGUI.getTextArea().append("\n");
                            } else {
                                showFunctionsGUI.getTextArea().append("Cannot calculate");
                            }
                        }
                    } else {
                        showFunctionsGUI.getTextArea().append("There is negative cycle\n");
                    }
                    
                } else if (command.equals("Floyd Warshall")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    
                    double[][] W = graph.buildWeightMatrix();
                    double[][] D = graph.floydWarshall(W);
                    
                    showFunctionsGUI.getTextArea().append("L(0) == W\n\n");
                    graph.printMatrix(W, showFunctionsGUI.getTextArea());
                    showFunctionsGUI.getTextArea().append("\n");
                    
                    graph.printMatrix(D, showFunctionsGUI.getTextArea());
                    
                    showFunctionsGUI.getTextArea().append("\n");
                    
                    for (int i = 0; i < graph.getNumVertices(); ++i) {
                        if (graph.bellmanFord(graph.getVertex(i), 1)) {
                            for (int j = 0; j < graph.getNumVertices(); ++j) {
                                if (i != j) {
                                    graph.printPath(graph.getVertex(i), graph.getVertex(j), showFunctionsGUI.getTextArea());
                                }
                            }
                            showFunctionsGUI.getTextArea().append("\n");
                        } else {
                            showFunctionsGUI.getTextArea().append("Cannot calculate");
                        }
                    }
                    
                } else if (command.equals("Dijkstra")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    
                    for (int i = 0; i < graph.getNumVertices(); ++i) {
                        graph.dijkstra(graph.getVertex(i));
                        for (int j = 0; j < graph.getNumVertices(); ++j) {
                            if (i != j) {
                                graph.printPath(graph.getVertex(i), graph.getVertex(j), showFunctionsGUI.getTextArea());
                            }
                            showFunctionsGUI.getTextArea().append("\n");
                        }
                    }
                } else if (command.equals("Johnson")) {
                    showFunctionsGUI.getTextArea().append("<" + command + ">\n");
                    
                    double[][] D = graph.johnson();
                    
                    for (int i = 0; i < D.length; ++i) {
                        for (int j = 0; j < D[0].length; ++j) {
                            showFunctionsGUI.getTextArea().append(D[i][j] + " ");
                        }
                        showFunctionsGUI.getTextArea().append("\n");
                    }
                    
                    showFunctionsGUI.getTextArea().append("\n");
                    
                    for (int i = 0; i < graph.getNumVertices(); ++i) {
                        graph.bellmanFord(graph.getVertex(i), 1);
                        for (int j = 0; j < graph.getNumVertices(); ++j) {
                            if (i != j) {
                                graph.printPath(graph.getVertex(i), graph.getVertex(j), showFunctionsGUI.getTextArea());
                            }
                        }
                    }
                } else if (command.equals("Draw Graph")) {
                    drawGraph(true);
                    showFunctionsGUI.setStatus("Ready");
                    return;
                } else if (command.equals("Draw Non Directed Graph")) {
                    JOptionPane.showMessageDialog(showFunctionsGUI, "It does not work correctly yet", "Warning!", JOptionPane.WARNING_MESSAGE);
                    drawGraph(false);
                    showFunctionsGUI.setStatus("Ready");
                    return;
                } else if (command.equals("About")) {
                    showAbout();
                    showFunctionsGUI.setStatus("Ready");
                    return;
                } else if (command.equals("Clear text")) {
                    showFunctionsGUI.getTextArea().setText("");
                    showFunctionsGUI.setStatus("Ready");
                    return;
                }
                
            } catch (NumberFormatException ex) {
                showFunctionsGUI.getTextArea().append("Error: You entered a invalid value");
            }
            
            showFunctionsGUI.getTextArea().append("\n\n");
            showFunctionsGUI.setStatus("Ready");
        }
        
    }
    
    public class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {}

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3)
                showFunctionsGUI.showMenuPopup(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
        
    }
    
    public class ActionForLookAndFeel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                UIManager.setLookAndFeel(e.getActionCommand());
                if (showFunctionsGUI.getParent() != null)
                    SwingUtilities.updateComponentTreeUI(showFunctionsGUI.getParent());
                
                SwingUtilities.updateComponentTreeUI(showFunctionsGUI);
                
                mainController.setLastLook(e.getActionCommand());
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(showFunctionsGUI, "Error attempting to apply new theme." + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (InstantiationException ex) {
                JOptionPane.showMessageDialog(showFunctionsGUI, "Error attempting to apply new theme." + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalAccessException ex) {
                JOptionPane.showMessageDialog(showFunctionsGUI, "Error attempting to apply new theme." + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(showFunctionsGUI, "Error attempting to apply new theme." + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    public class MyKeyListener implements KeyListener {
        
        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                if ((e.getComponent()).getClass().getName().equals(JTextArea.class.getName())) {
                    mainController.closing(showFunctionsGUI);
                } else if ((e.getComponent()).getClass().getName().equals(AboutGUI.class.getName())) {
                    ((Window) e.getComponent()).dispose();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
}
