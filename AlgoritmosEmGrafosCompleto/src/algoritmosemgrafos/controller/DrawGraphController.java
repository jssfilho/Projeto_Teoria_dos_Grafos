package algoritmosemgrafos.controller;

import algoritmosemgrafos.model.Graph;
import algoritmosemgrafos.model.Vertex;
import algoritmosemgrafos.view.DrawGraphGUI;
import algoritmosemgrafos.view.DrawGraphGUI.DrawPanelGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author joseph
 */
public class DrawGraphController {
    private final Graph graph;
    private DrawGraphGUI drawGraphGUI;
    
    private boolean readFirstVertex;
    private boolean readSecondVertex;
    
    private boolean readNewVertex;
    private boolean removeVertex;
    
    public DrawGraphController(final Graph graph) {
        this.graph = graph;
        init();
    }
    
    private void init() {
        readFirstVertex = false;
        readSecondVertex = false;
        readNewVertex = false;
        removeVertex = false;
        
        drawGraphGUI = new DrawGraphGUI(graph);
        drawGraphGUI.addMouseMotionListenerToDrawPanel(new VertexGUIMovment());
        drawGraphGUI.addMouseListenerForDrawPanel(new MouseListenerForDrawPanel());
        drawGraphGUI.addActionForDrawPanel(new ActionForDrawPanel());
        drawGraphGUI.addKeyListener(new KeyListenerForEvents());
        
        Iterator<Vertex> itV = graph.getVertices().iterator();
        Vertex vertex = null;
        
        while (itV.hasNext()) {
            vertex = itV.next();
            
            int x = (int) (25+Math.random() * (drawGraphGUI.getWidth()-125));
            int y = (int) (25+Math.random() * (drawGraphGUI.getHeight()-225));
            
//            int x = (int) (25+Math.random() * (Toolkit.getDefaultToolkit().getScreenSize().width-125));
//            int y = (int) (25+Math.random() * (Toolkit.getDefaultToolkit().getScreenSize().height-225));
            
            vertex.setXY(x, y);
        }
        
        
        drawGraphGUI.setVisible(true);
    }
    
    public class VertexGUIMovment implements MouseMotionListener {
        Vertex vertex;
        DrawPanelGUI panel;

        @Override
        public void mouseDragged(MouseEvent e) {
            panel = ((DrawGraphGUI.DrawPanelGUI) e.getComponent());
            //vertex = vertices.get(0);

            vertex = graph.getVertexByXY(e.getX(), e.getY());

            if (vertex != null) {
                vertex.setX(e.getX());
                vertex.setY(e.getY());
                panel.repaint();
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {}
    }
    
    private Vertex firstVertex;
    private Vertex secondVertex;
    private Vertex tempVertex;
    
    public class MouseListenerForDrawPanel implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                Vertex vertex = graph.getVertexByXY(e.getX(), e.getY());
                if (vertex == null) {
                    drawGraphGUI.showPopup(e.getX(), e.getY());
                } else {
                    drawGraphGUI.showPopup(e.getX(), e.getY(), vertex);
                    tempVertex = vertex;
                }
            }
            
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (readFirstVertex) {
                    
                    Vertex vertex = graph.getVertexByXY(e.getX(), e.getY());
                    if (vertex != null) {
                        firstVertex = vertex;
                        readFirstVertex = false;
                        readSecondVertex = true;
                        drawGraphGUI.setStatus("Click in the second vertex | ESC to cancel");
                    }
                } else if (readSecondVertex) {
                    
                    Vertex vertex = graph.getVertexByXY(e.getX(), e.getY());
                    if (vertex != null) {
                        secondVertex = vertex;
                        readSecondVertex = false;
                        
                        if (!graph.hasEdge(firstVertex, secondVertex)) {
                            drawGraphGUI.setStatus("Enter Cost value | ESC to cancel");
                            
                            String costString = JOptionPane.showInputDialog("Enter Cost value");
                            double cost;

                            if (costString != null) {

                                try {
                                    cost = Double.parseDouble(costString.trim());
                                    
                                    int result = graph.addEdge(firstVertex, secondVertex, cost);

                                    if (result == 0) {
                                        drawGraphGUI.setStatus("Ready");
                                        drawGraphGUI.repaint();
                                    } else if (result == 1) {
                                        drawGraphGUI.setStatus("Error");
                                        JOptionPane.showMessageDialog(drawGraphGUI, "Cust must be a positive value", "Error", JOptionPane.ERROR_MESSAGE);
                                        drawGraphGUI.setStatus("Ready");
                                    } else {

                                    }
                                } catch (NumberFormatException ex) {
                                    drawGraphGUI.setStatus("Error");
                                    JOptionPane.showMessageDialog(drawGraphGUI, "You entered a invalid value", "Error", JOptionPane.ERROR_MESSAGE);
                                    drawGraphGUI.setStatus("Ready");
                                }

                            }
                        } else {
                            drawGraphGUI.setStatus("Error");
                            JOptionPane.showMessageDialog(drawGraphGUI, "You cannot add an Edge in this position", "Error", JOptionPane.ERROR_MESSAGE);
                            drawGraphGUI.setStatus("Ready");
                        }
                        
                    }
                } else if (readNewVertex) {
                    readNewVertex = false;
                    String labelString = JOptionPane.showInputDialog("Enter New Vertex Label");
                    drawGraphGUI.setStatus("Ready");
                    if (labelString != null) {
                        try {
                            int label = Integer.parseInt(labelString.trim());
                        
                            if (label >= 0) {
                                int result = graph.addVertex(label);

                                if (result == 0) {
                                    graph.getVertex(label).setXY(e.getX(), e.getY());

                                    drawGraphGUI.setStatus("Ready");
                                    drawGraphGUI.repaint();

                                } else {
                                    drawGraphGUI.setStatus("Error");
                                    JOptionPane.showMessageDialog(drawGraphGUI, "You cannot add an Vertix with this label", "Error", JOptionPane.ERROR_MESSAGE);
                                    drawGraphGUI.setStatus("Ready");
                                }
                            } else {
                                drawGraphGUI.setStatus("Error");
                                JOptionPane.showMessageDialog(drawGraphGUI, "Label must be a positive value", "Error", JOptionPane.ERROR_MESSAGE);
                                drawGraphGUI.setStatus("Ready");
                            }
                        } catch (NumberFormatException ex) {
                            drawGraphGUI.setStatus("Error");
                            JOptionPane.showMessageDialog(drawGraphGUI, "You entered a invalid value", "Error", JOptionPane.ERROR_MESSAGE);
                            drawGraphGUI.setStatus("Ready");
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

    }
    
    public class ActionForDrawPanel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (command.equals("Add Vertex")) {
                resetAddNewEdge();
                readNewVertex = true;
                drawGraphGUI.setStatus("Click where you want create the New Vertex | ESC to cancel");
                JOptionPane.showMessageDialog(drawGraphGUI, "Click where you want create the New Vertex");
            } else if (command.equals("Add Edge")) {
                resetAddNewVertex();
                readFirstVertex = true;
                drawGraphGUI.setStatus("Click in the first vertex | ESC to cancel");
                JOptionPane.showMessageDialog(drawGraphGUI, "Click in the first vertex, then in the second to create de Edge");
            } else if (command.equals("Remove Vertex")) {
                if (tempVertex != null) {
                    
                    if (JOptionPane.showConfirmDialog(drawGraphGUI, "Are you sure you want remove this Vertex?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                        graph.removeVertex(tempVertex.getLabel());
                        drawGraphGUI.setStatus("Ready");
                        drawGraphGUI.repaint();
                    }

                    tempVertex = null;
                }
            }
        }
        
        
    }
    
    public class KeyListenerForEvents implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                resetActions();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
    
    public void resetActions() {
        resetAddNewEdge();
        resetAddNewVertex();
    }
    public void resetAddNewVertex() {
        readNewVertex = false;
        drawGraphGUI.setStatus("Ready");
    }
    public void resetAddNewEdge() {
        readFirstVertex = false;
        readSecondVertex = false;
        drawGraphGUI.setStatus("Ready");
    }
    
    public DrawPanelGUI getPanel() {
        return drawGraphGUI.getPanel();
    }
}
