package algoritmosemgrafos.view;

import algoritmosemgrafos.controller.DrawGraphController;
import algoritmosemgrafos.controller.DrawGraphController.ActionForDrawPanel;
import algoritmosemgrafos.controller.DrawGraphController.MouseListenerForDrawPanel;
import algoritmosemgrafos.model.Edge;
import algoritmosemgrafos.model.Graph;
import javax.swing.*;
import algoritmosemgrafos.model.Vertex;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author joseph
 */
public class DrawGraphGUI extends JDialog {
    private DrawPanelGUI drawPanelGUI;
    
    private final Graph graph;
    
    private JPopupMenu popupMenu;
    private JMenuItem addNewVertexJMenuItem;
    private JMenuItem addNewEdgeJMenuItem;
    
    private JPopupMenu vertexPopupMenu;
    private JMenuItem removeVertexJMenuItem;
    
    private JLabel statusJLabel;
    
    public DrawGraphGUI(Graph graph) {
        this.graph = graph;
        
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setTitle("Draw Graph");
        setSize(
            Toolkit.getDefaultToolkit().getScreenSize().width * 2 / 3,
            Toolkit.getDefaultToolkit().getScreenSize().height * 2 / 3
        );
        
        setLocationRelativeTo(null);
        
        drawPanelGUI = new DrawPanelGUI();
        
        add(drawPanelGUI);
        
        popupMenu = new JPopupMenu();
        addNewVertexJMenuItem = new JMenuItem("Add Vertex", 'v');
        popupMenu.add(addNewVertexJMenuItem);
        
        addNewEdgeJMenuItem = new JMenuItem("Add Edge", 'e');
        popupMenu.add(addNewEdgeJMenuItem);
        
        vertexPopupMenu = new JPopupMenu();
        removeVertexJMenuItem = new JMenuItem("Remove Vertex", 'r');
        vertexPopupMenu.add(removeVertexJMenuItem);
        
        statusJLabel = new JLabel("Ready", JLabel.RIGHT);
        statusJLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(statusJLabel, BorderLayout.SOUTH);
    }
    
    public void setStatus(final String text) {
        statusJLabel.setText(text);
    }

    public void addMouseMotionListenerToDrawPanel(DrawGraphController.VertexGUIMovment vertexGUIMovment) {
        drawPanelGUI.addMouseMotionListener(vertexGUIMovment);
    }
    
    public void showPopup(int x, int y) {
        popupMenu.show(drawPanelGUI, x, y);
    }
    
    public void showPopup(int x, int y, Vertex vertex) {
        vertexPopupMenu.show(drawPanelGUI, x, y);
    }

    public void addMouseListenerForDrawPanel(MouseListenerForDrawPanel mouseListenerForDrawPanel) {
        drawPanelGUI.addMouseListener(mouseListenerForDrawPanel);
    }

    public void addActionForDrawPanel(ActionForDrawPanel actionForDrawPanel) {
        addNewVertexJMenuItem.addActionListener(actionForDrawPanel);
        addNewEdgeJMenuItem.addActionListener(actionForDrawPanel);
        removeVertexJMenuItem.addActionListener(actionForDrawPanel);
    }

    public DrawPanelGUI getPanel() {
        return this.drawPanelGUI;
    }
    
    public class DrawPanelGUI extends JPanel {

        public DrawPanelGUI() {
            super(new BorderLayout());
        }

        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        public synchronized void addKeyListener(KeyListener l) {
            super.addKeyListener(l);
            drawPanelGUI.addKeyListener(l);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            Iterator<Edge> itE = graph.getEdges().iterator();

            //ArrayList<Edge> control = new ArrayList<Edge>();
            
            Edge e = null;
            while (itE.hasNext()) {
                e = itE.next();
                
//                if (!graph.isDirected()) {
//                    control.add(e);
//                }

                int xOrigin = e.getOrigin().getX()+25;
                int yOrigin = e.getOrigin().getY()+25;
                int xDestiny = e.getDestiny().getX()+25;
                int yDestiny = e.getDestiny().getY()+25;
                
                Vertex originVertex = e.getOrigin();
                Vertex destinyVertex = e.getDestiny();
                
                if (originVertex.equals(destinyVertex) && graph.isDirected()) {
                    g.drawArc(originVertex.getX()+25, originVertex.getY()+25, 30, 30, 0, 360);
                    
                    g.setColor(Color.red);
                    g.drawString(
                        new Double(e.getCost()).toString(),
                        (destinyVertex.getX()+25 + 30),
                        (destinyVertex.getY()+25 + 30)
                    );
                    g.setColor(Color.black);
                } else {
                    if (true /*graph.isDirected() || (!graph.isDirected() && !controlHasEdge(control, e.getDestiny(), e.getOrigin())) */) {
                        double sina = (originVertex.getY() - destinyVertex.getY()) / originVertex.distance(destinyVertex);
                        double cosa = (destinyVertex.getX() - originVertex.getX()) / originVertex.distance(destinyVertex);

                        double a    = Math.asin(sina); // angle
                        double b    = Math.acos(cosa); // angle

                        int factorx = (int) (cosa*25);
                        int factory = (int) (sina*25);

                        xOrigin += factorx;
                        xDestiny -= factorx;

                        yOrigin -= factory;
                        yDestiny += factory;

                        g.drawLine(xOrigin, yOrigin, xDestiny, yDestiny);
                        //g.fillOval(xDestiny-5, yDestiny-5, 10, 10);

                        Point p1 = new Point();
                        Point p2 = new Point();
                        Point p3 = new Point();

                        p1.x = xDestiny;
                        p1.y = yDestiny;

                        double ang;

                        if ((b*180/Math.PI) >= 0 && (b*180/Math.PI) <= 180 && (a*180/Math.PI) >= 0) {
                            ang = b;
                        } else if ((a*180/Math.PI) < 0 && (b*180/Math.PI) >= 90) {
                            ang = (b*180/Math.PI - 2*a*180/Math.PI)*Math.PI/180;
                        } else {
                            ang = -b;
                        }

                        p2.x = p1.x + (int) (10 * Math.cos(ang + (150) * Math.PI / 180));
                        p2.y = p1.y - (int) (10 * Math.sin(ang + (150) * Math.PI / 180));

                        p3.x = p2.x + (int) (10 * Math.cos(ang + (270) * Math.PI / 180));
                        p3.y = p2.y - (int) (10 * Math.sin(ang + (270) * Math.PI / 180));

                        
                        g.fillPolygon(
                            new int [] {p1.x, p2.x, p3.x},
                            new int [] {p1.y, p2.y, p3.y},
                            3
                        );

                            
                        g.setColor(Color.red);
                        g.drawString(
                            new Double(e.getCost()).toString(),
                            (int)(p3.x - 20 * Math.cos(ang)),
                            (int)(p3.y + 20 * Math.sin(ang))
                        );
                        
                        g.setColor(Color.black);
                    }
                }
                
            }

            Iterator<Vertex> itV = graph.getVertices().iterator();

            Vertex v = null;
            while (itV.hasNext()) {
                v = itV.next();

                g.setColor(Color.white);
                g.fillOval(v.getX(), v.getY(), 50, 50);

                g.setColor(Color.black);
                g.drawOval(v.getX(), v.getY(), 50, 50);


                g.setColor(Color.red);
                g.drawString(Integer.toString(v.getLabel()), v.getX()+22, v.getY()+28);

                g.setColor(Color.black);
            }
        }

        private boolean controlHasEdge(ArrayList<Edge> edges, Vertex origin, Vertex destiny) {
            Iterator<Edge> it = edges.iterator();

            while (it.hasNext()) {
                Edge edge = it.next();

                if (edge.getOrigin().getLabel() == origin.getLabel() &&
                        edge.getDestiny().getLabel() == destiny.getLabel() &&
                        edge.getCost() != -1) {
                    return true;
                }
            }
            return false;
        }
        
    }
    
    
}
