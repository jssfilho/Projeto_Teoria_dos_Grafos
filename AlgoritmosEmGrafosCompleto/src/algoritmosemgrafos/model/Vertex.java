package algoritmosemgrafos.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to represent a Vertex of a Graph
 * 
 * @author joseph
 * 
 * History
 * 
 * 04/08/2011 - Creation of the  Vertex Class
 * 
 */
public class Vertex {
    // Colors to identify if a Vertex was or not found and if there's or not adjacency vertex not found
    public static final int COLOR_WHITE = 0; // zero to indicate the color is white (non found)
    public static final int COLOR_GRAY  = 1; // one to indicate the color is gray (found)
    public static final int COLOR_BLACK = 2; // two to indicate the color is black (there's no adjacency vertex)
    
    private ArrayList<Edge> edgesOrigin;  // Edges where this Vertex is Origin
    private ArrayList<Edge> edgesDestiny; // Edges where this Vertex is Destiny
    
    private Vertex p; // predecessor
    private int d;    // begin time
    private int f;    // end time
    
    // identify the vertex
    private int label;
    
    // identify if vertex was visited - White, Gray, Black
    private int color;
    
    // location of this vertex in the panel, area where graph is showed
    private Point point;

    /**
     * Initializes this Vertex
     */
    private Vertex() {
        edgesOrigin = new ArrayList<Edge>();
        edgesDestiny = new ArrayList<Edge>();
        point = new Point();
    }
    
    /**
     * Initializes this Vertex
     * @param x The X
     * @param y The Y
     * @param label The label of this Vertex
     */
    public Vertex(int x, int y, int label) {
        this();
        this.label = label;
        this.setPoint(x, y);
    }
    
    public Vertex(Point point, int label) {
        this();
        this.label = label;
        //this.point = point;
        setPoint(point.x, point.y);
    }
    
    public Vertex(int label) {
        this(0, 0, label);
    }

    /**
     * Set the X and Y point of this Vertex
     * @param x The X
     * @param y The Y
     */
    private void setPoint(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
    
    public int getLabel() {
        return this.label;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Point getPoint() {
        return point;
    }
    
    /**
     * Returns X
     * @return the X value
     */
    public int getX() {
        return this.point.x;
    }
    
    /**
     * Set X
     * @param x the new X
     */
    public void setX(int x) {
        this.point.x = x - 25;
    }
    
    /**
     * Returns the Y 
     * @return the Y value
     */
    public int getY() {
        return this.point.y;
    }
    
    /**
     * Set Y
     * @param y the new Y
     */
    public void setY(int y) {
        this.point.y = y - 25;
    }
    
    public void setXY(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
    
    public double distance(Vertex vertex) {
        return this.point.distance(vertex.getPoint());
    }

    /**
     * Add a Edge where this Vertex is Origin in its edgesDestiny
     * @param edge The Edge
     */
    public void addOrigin(Edge edge) {
        this.edgesOrigin.add(edge);
    }
    
    /**
     * Add a Edge where this Vertex is Destiny in its edgesDestiny
     * @param edge The Edge
     */
    public void addDestiny(Edge edge) {
        this.edgesDestiny.add(edge);
    }

    /**
     * Returns the Number of Neighborhood
     * @return the Number of Neighborhood
     */
    public int getOriginSize() {
        return this.edgesOrigin.size();
    }
    
    /**
     * Returns the Number of Reverse Neighborhood
     * @return The Number of Reverse Neighborhood
     */
    public int getDestinySize() {
        return this.edgesDestiny.size();
    }

    /**
     * Removes this vertex where it is Origin
     * @param edge The Edge where it is Origin
     */
    public void removeOrigin(Edge edge) {
        Iterator<Edge> it = this.edgesOrigin.iterator();
        
        int i = 0;
        while (it.hasNext()) {
            Edge e = it.next();
            
            if (e.getOrigin().getLabel() == edge.getOrigin().getLabel()) {
                this.edgesOrigin.remove(i);
                break;
            }
            ++i;
        }
    }

    /**
     * Removes this vertex where it is Destiny
     * @param edge The Edge where it is Destiny
     */
    public void removeDestiny(Edge edge) {
        Iterator<Edge> it = this.edgesDestiny.iterator();
        
        int i = 0;
        while (it.hasNext()) {
            Edge e = it.next();
            
            if (e.getDestiny().getLabel() == edge.getDestiny().getLabel()) {
                this.edgesDestiny.remove(i);
                break;
            }
            ++i;
        }
    }

    boolean isOnVertex(int x, int y) {
        return ((x >= point.getX() && x <= point.getX() + 50) && (y >= point.getY() && y <= point.getY() + 50));
    }
    
    public Vertex getP() {
        return p;
    }

    public void setP(Vertex p) {
        this.p = p;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public ArrayList<Edge> getEdgesOrigin() {
        return edgesOrigin;
    }
    
    
}
