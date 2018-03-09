package algoritmosemgrafos.view;

import algoritmosemgrafos.controller.ShowFunctionsController.ActionForLookAndFeel;
import algoritmosemgrafos.controller.ShowFunctionsController.MyActionListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author joseph
 */
public class ShowFunctionsGUI extends JFrame {

    private JMenuBar  menuBar;
    
    private JMenu     fileJMenu;
    private JMenuItem loadNewGraphJMenuItem;
    private JMenuItem exitJMenuItem;
    
    private JMenu     viewJMenu;
    private JMenuItem fullScreenJMenuItem;
    
    private JMenu     functionsJMenu;
    private JMenu     adjacencyMatrixJMenu;
    private JMenuItem buildAdjacencyMatrixJMenuItem;
    private JMenuItem destroyAdjacencyMatrixJMenuItem;
    private JMenuItem displayAdjacencyMatrixJMenuItem;
    
    private JMenu     sizeJMenu;
    private JMenuItem getNumEdgesJMenuItem;
    private JMenuItem getNumVerticesJMenuItem;
    
    private JMenu     degreeJMenu;
    private JMenuItem getOutDegreeJMenuItem;
    private JMenuItem getInDegreeJMenuItem;
    
    private JMenu     addElementJMenu;
    private JMenu     removeElementJMenu;
    
    private JMenuItem addVertexJMenuItem;
    private JMenuItem removeVertexJMenuItem;
    
    private JMenuItem addEdgeJMenuItem;
    private JMenuItem removeEdgeJMenuItem;
    
    private JMenuItem hasEdgeJMenuItem;
    private JMenuItem getEdgeCostJMenuItem;
    
    private JMenuItem getNeighbourhoodJMenuItem;
    private JMenuItem getReverseNeighbourhoodJMenuItem;
    
    private JMenuItem getTransitiveClosureListJMenuItem;
    private JMenuItem getReverseTransitiveClosureListJMenuItem;
    
    private JMenuItem getStronglyConnectedComponentsJMenuItem;
    
    private JMenuItem getMinimumTreeJMenuItem;
    
    private JMenu     algoJMenu;
    private JMenuItem extendShortestPathJMenuItem;
    private JMenuItem floydWarshallJMenuItem;
    private JMenuItem dijkstraJMenuItem;
    private JMenuItem johnsonJMenuItem;
    
    private JMenu     drawGraphJMenu;
    private JMenuItem drawGraphJMenuItem;
    private JMenuItem drawNonDirectedGraphJMenuItem;
    
    private JMenu     toolsJMenu;
    private JMenu     lookAndFeelJMenu;
    private JMenuItem[] lookAndFeelJMenuItem;
    
    private JMenu     helpJMenu;
    private JMenuItem aboutJMenuItem;
    
    private JTextArea textArea;
    private JScrollPane scrollPane;
    
    private JPopupMenu popupMenu;
    private JMenuItem clearTextJMenuItem;
    
    private JMenuItem fullScreenJMenuItem2;
    
    private JLabel statusJLabel;

    @Override
    public void setTitle(String title) {
        super.setTitle("Algoritmos em Grafos v. " + title + " | Joseph Soares Alcântara");
    }
    
    public ShowFunctionsGUI() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(
            Toolkit.getDefaultToolkit().getScreenSize().width * 2 / 3,
            Toolkit.getDefaultToolkit().getScreenSize().height * 2 / 3
        );
        
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        fileJMenu = new JMenu("File");
        fileJMenu.setToolTipText("Load a new Graph, exit from the program");
        fileJMenu.setMnemonic('f');
        menuBar.add(fileJMenu);
        
        loadNewGraphJMenuItem = new JMenuItem("Load a new Graph", 'n');
        loadNewGraphJMenuItem.setToolTipText("Discard this Graph and load another one");
        fileJMenu.add(loadNewGraphJMenuItem);
        
        fileJMenu.add(new JSeparator());
        
        exitJMenuItem = new JMenuItem("Exit", 'x');
        exitJMenuItem.setToolTipText("Exit from this program");
        exitJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        fileJMenu.add(exitJMenuItem);
        
        viewJMenu = new JMenu("View");
        viewJMenu.setToolTipText("Change the mode that this program is displayed");
        viewJMenu.setMnemonic('v');
        menuBar.add(viewJMenu);
        
        fullScreenJMenuItem = new JMenuItem("Full Screen", 'f');
        fullScreenJMenuItem.setToolTipText("Change window for full screen mode");
        fullScreenJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
        viewJMenu.add(fullScreenJMenuItem);
        
        functionsJMenu = new JMenu("Functions");
        functionsJMenu.setToolTipText("Functions to operate on the Graph");
        functionsJMenu.setMnemonic('c');
        menuBar.add(functionsJMenu);
        
        adjacencyMatrixJMenu = new JMenu("Adjacency Matrix");
        adjacencyMatrixJMenu.setToolTipText("Show functions to create, display and destroy an Adjacency Matrix");
        functionsJMenu.add(adjacencyMatrixJMenu);
        
        buildAdjacencyMatrixJMenuItem = new JMenuItem("Build Adjacency Matrix", 'b');
        buildAdjacencyMatrixJMenuItem.setToolTipText("Build the Adjacency Matrix from Graph");
        adjacencyMatrixJMenu.add(buildAdjacencyMatrixJMenuItem);
        
        destroyAdjacencyMatrixJMenuItem = new JMenuItem("Destroy Adjacency Matrix", 'd');
        destroyAdjacencyMatrixJMenuItem.setToolTipText("Destroy the Adjacency Matrix");
        adjacencyMatrixJMenu.add(destroyAdjacencyMatrixJMenuItem);
        
        displayAdjacencyMatrixJMenuItem = new JMenuItem("Display Adjacency Matrix", 's');
        adjacencyMatrixJMenu.add(displayAdjacencyMatrixJMenuItem);
        
        sizeJMenu = new JMenu("Size");
        functionsJMenu.add(sizeJMenu);
        
        getNumEdgesJMenuItem = new JMenuItem("Get Number of Edges", 'e');
        getNumEdgesJMenuItem.setToolTipText("Shows how many Edges there is on the Graph");
        sizeJMenu.add(getNumEdgesJMenuItem);
        
        getNumVerticesJMenuItem = new JMenuItem("Get Number of Vertices", 'v');
        getNumVerticesJMenuItem.setToolTipText("Show how many Vertices there is on the Graph");
        sizeJMenu.add(getNumVerticesJMenuItem);
        
        degreeJMenu = new JMenu("Degree");
        functionsJMenu.add(degreeJMenu);
        
        getOutDegreeJMenuItem = new JMenuItem("Get Out Degree", 'o');
        getOutDegreeJMenuItem.setToolTipText("Shows a Vertex's Out Degree");
        degreeJMenu.add(getOutDegreeJMenuItem);
        
        getInDegreeJMenuItem = new JMenuItem("Get In Degree", 'i');
        getInDegreeJMenuItem.setToolTipText("Shows a Vertex's In Degree");
        degreeJMenu.add(getInDegreeJMenuItem);
        
        addElementJMenu = new JMenu("Add Element");
        functionsJMenu.add(addElementJMenu);
        
        addVertexJMenuItem = new JMenuItem("Add Vertex", 'd');
        addVertexJMenuItem.setToolTipText("Create a new Vertex and add it on the Graph");
        addElementJMenu.add(addVertexJMenuItem);
        
        addEdgeJMenuItem = new JMenuItem("Add Edge", 'a');
        addEdgeJMenuItem.setToolTipText("Create a new Edge and add it on the Graph");
        addElementJMenu.add(addEdgeJMenuItem);
        
        removeElementJMenu = new JMenu("Remove Element");
        functionsJMenu.add(removeElementJMenu);
        
        removeVertexJMenuItem = new JMenuItem("Remove Vertex", 'd');
        removeVertexJMenuItem.setToolTipText("Remove a Vertex (and its Edges) from the Graph");
        removeElementJMenu.add(removeVertexJMenuItem);
        
        removeEdgeJMenuItem = new JMenuItem("Remove Edge", 'r');
        removeEdgeJMenuItem.setToolTipText("Remove a Edge from the Graph");
        removeElementJMenu.add(removeEdgeJMenuItem);
        
        hasEdgeJMenuItem = new JMenuItem("Has Edge", 'h');
        hasEdgeJMenuItem.setToolTipText("Shows if the Edge is on the Graph");
        functionsJMenu.add(hasEdgeJMenuItem);
        
        getEdgeCostJMenuItem = new JMenuItem("Get Edge Cost", 'c');
        getEdgeCostJMenuItem.setToolTipText("Shows the Cost of a Edge if it is on the Graph");
        functionsJMenu.add(getEdgeCostJMenuItem);
        
        functionsJMenu.add(new JSeparator());
        
        getNeighbourhoodJMenuItem = new JMenuItem("Neighborhood", 'n');
        getNeighbourhoodJMenuItem.setToolTipText("Shows all Neighbors of a Vertex");
        functionsJMenu.add(getNeighbourhoodJMenuItem);
        
        getReverseNeighbourhoodJMenuItem = new JMenuItem("Reverse Neighborhood", 'g');
        getReverseNeighbourhoodJMenuItem.setToolTipText("Shows all the Reverse Neighbors of a Vertex");
        functionsJMenu.add(getReverseNeighbourhoodJMenuItem);
        
        functionsJMenu.add(new JSeparator());
        
        getTransitiveClosureListJMenuItem = new JMenuItem("Transitive Closure", 't');
        getTransitiveClosureListJMenuItem.setToolTipText("Shos all attainable Vertices from a Vertex");
        functionsJMenu.add(getTransitiveClosureListJMenuItem);
        
        getReverseTransitiveClosureListJMenuItem = new JMenuItem("Reverse Transitive Closure", 'l');
        getReverseTransitiveClosureListJMenuItem.setToolTipText("Show all the Vertices that attaine a reaches");
        functionsJMenu.add(getReverseTransitiveClosureListJMenuItem);
        
        functionsJMenu.add(new JSeparator());
        
        getStronglyConnectedComponentsJMenuItem = new JMenuItem("Strongly Connected Components", 'y');
        getStronglyConnectedComponentsJMenuItem.setToolTipText("Shows all Strongly Connected Components of the Graph");
        functionsJMenu.add(getStronglyConnectedComponentsJMenuItem);
        
        getMinimumTreeJMenuItem = new JMenuItem("Get a Minimum Tree");
        getMinimumTreeJMenuItem.setToolTipText("Get a Minimum Tree using Prim's Algorithm");
        functionsJMenu.add(getMinimumTreeJMenuItem);
        
        algoJMenu = new JMenu("Algorithms");
        functionsJMenu.add(algoJMenu);
        
        extendShortestPathJMenuItem = new JMenuItem("Extend Shortest Paht", 'e');
        algoJMenu.add(extendShortestPathJMenuItem);
        
        floydWarshallJMenuItem = new JMenuItem("Floyd Warshall", 'f');
        algoJMenu.add(floydWarshallJMenuItem);
        
        dijkstraJMenuItem = new JMenuItem("Dijkstra", 'd');
        algoJMenu.add(dijkstraJMenuItem);
        
        johnsonJMenuItem = new JMenuItem("Johnson", 'j');
        algoJMenu.add(johnsonJMenuItem);
        
        drawGraphJMenu = new JMenu("Draw");
        drawGraphJMenu.setMnemonic('d');
        menuBar.add(drawGraphJMenu);
        //drawGraphJMenu.setEnabled(false);
        
        drawGraphJMenuItem = new JMenuItem("Draw Graph", 'd');
        drawGraphJMenu.add(drawGraphJMenuItem);
        
        drawNonDirectedGraphJMenuItem = new JMenuItem("Draw Non Directed Graph", 'n');
        drawNonDirectedGraphJMenuItem.setToolTipText("It does not work correctly yet");
        drawGraphJMenu.add(drawNonDirectedGraphJMenuItem);
        
        toolsJMenu = new JMenu("Tools") ;
        toolsJMenu.setMnemonic('t');
        toolsJMenu.setToolTipText("Set some tools to improve this application");
        menuBar.add(toolsJMenu);
        
        lookAndFeelJMenu = new JMenu("Look and Feel");
        lookAndFeelJMenu.setToolTipText("Choose a apropriate Look And Feel for your window");
        toolsJMenu.add(lookAndFeelJMenu);
        
        LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        
        lookAndFeelJMenuItem = new JMenuItem[installedLookAndFeels.length];
        
        for (int i = 0; i < installedLookAndFeels.length; ++i) {
            lookAndFeelJMenuItem[i] = new JMenuItem(installedLookAndFeels[i].getName(), installedLookAndFeels[i].getName().charAt(0));
            lookAndFeelJMenu.add(lookAndFeelJMenuItem[i]);
            
            lookAndFeelJMenuItem[i].setActionCommand(installedLookAndFeels[i].getClassName());
        }
        
        helpJMenu = new JMenu("Help");
        helpJMenu.setToolTipText("Help, About");
        helpJMenu.setMnemonic('h');
        menuBar.add(helpJMenu);
        
        aboutJMenuItem = new JMenuItem("About", 'b');
        aboutJMenuItem.setToolTipText("About this Application");
        helpJMenu.add(aboutJMenuItem);
        
        // center
        textArea = new JTextArea();
        textArea.setFont(new Font(null, Font.PLAIN, 16));
        textArea.setTabSize(4);
        textArea.setEditable(false);
        
        scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        
        
        popupMenu = new JPopupMenu();

        fullScreenJMenuItem2 = new JMenuItem("Full Screen", 'f');
        fullScreenJMenuItem2.setToolTipText("Change window for full screen mode");
        popupMenu.add(fullScreenJMenuItem2);
        
        popupMenu.add(new JSeparator());
        
        clearTextJMenuItem = new JMenuItem("Clear text", 'c');
        clearTextJMenuItem.setToolTipText("Clear all text of Text Area");
        popupMenu.add(clearTextJMenuItem);
        
        statusJLabel = new JLabel("Ready", JLabel.RIGHT);
        statusJLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(statusJLabel, BorderLayout.SOUTH);
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        super.addKeyListener(l);
        
        textArea.addKeyListener(l);
    }
    
    public void addActionListener(MyActionListener actionForShowFunctions) {
        loadNewGraphJMenuItem.addActionListener(actionForShowFunctions);
        exitJMenuItem.addActionListener(actionForShowFunctions);
        
        fullScreenJMenuItem.addActionListener(actionForShowFunctions);
        
        buildAdjacencyMatrixJMenuItem.addActionListener(actionForShowFunctions);
        destroyAdjacencyMatrixJMenuItem.addActionListener(actionForShowFunctions);
        displayAdjacencyMatrixJMenuItem.addActionListener(actionForShowFunctions);
        getNumEdgesJMenuItem.addActionListener(actionForShowFunctions);
        getNumVerticesJMenuItem.addActionListener(actionForShowFunctions);
        getOutDegreeJMenuItem.addActionListener(actionForShowFunctions);
        getInDegreeJMenuItem.addActionListener(actionForShowFunctions);
        addVertexJMenuItem.addActionListener(actionForShowFunctions);
        removeVertexJMenuItem.addActionListener(actionForShowFunctions);
        addEdgeJMenuItem.addActionListener(actionForShowFunctions);
        removeEdgeJMenuItem.addActionListener(actionForShowFunctions);
        hasEdgeJMenuItem.addActionListener(actionForShowFunctions);
        getEdgeCostJMenuItem.addActionListener(actionForShowFunctions);
        getNeighbourhoodJMenuItem.addActionListener(actionForShowFunctions);
        getReverseNeighbourhoodJMenuItem.addActionListener(actionForShowFunctions);
        getTransitiveClosureListJMenuItem.addActionListener(actionForShowFunctions);
        getReverseTransitiveClosureListJMenuItem.addActionListener(actionForShowFunctions);
        getStronglyConnectedComponentsJMenuItem.addActionListener(actionForShowFunctions);
        getMinimumTreeJMenuItem.addActionListener(actionForShowFunctions);
        
        extendShortestPathJMenuItem.addActionListener(actionForShowFunctions);
        floydWarshallJMenuItem.addActionListener(actionForShowFunctions);
        dijkstraJMenuItem.addActionListener(actionForShowFunctions);
        johnsonJMenuItem.addActionListener(actionForShowFunctions);
        
        drawGraphJMenuItem.addActionListener(actionForShowFunctions);
        drawNonDirectedGraphJMenuItem.addActionListener(actionForShowFunctions);
        
        aboutJMenuItem.addActionListener(actionForShowFunctions);
        
        fullScreenJMenuItem2.addActionListener(actionForShowFunctions);
        clearTextJMenuItem.addActionListener(actionForShowFunctions);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
    
    public void setStatus(final String text) {
        statusJLabel.setText(text);
    }

    public void showMenuPopup(int x, int y) {
        popupMenu.show(textArea, x, y);
    }

    public void addActionListenerForLookAndFeel(ActionForLookAndFeel actionForLookAndFeel) {
        for (int i = 0; i < lookAndFeelJMenuItem.length; ++i) {
            
            lookAndFeelJMenuItem[i].addActionListener(actionForLookAndFeel);
        }
    }

    public void disableFullScreen() {
        fullScreenJMenuItem.setEnabled(false);
    }

    public void removeScrollPane() {
        remove(this.scrollPane);
    }
    
}
