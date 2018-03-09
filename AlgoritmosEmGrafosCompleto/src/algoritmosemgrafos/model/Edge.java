package algoritmosemgrafos.model;

/**
 * A class to represent a Edge of a Graph
 * 
 * @author joseph
 * 
 * History
 * 
 * 04/08/2011 - Creation of the  Edge Class
 * 
 */
public class Edge {
    public static final int EDGE_TREE = 0;
    public static final int EDGE_DIRECT = 1;
    public static final int EDGE_BACK = 2;
    public static final int EDGE_CROSS = 3;
    
    // Origin Vertex
    private Vertex origin;
    
    // Destiny Vertex
    private Vertex destiny;
    
    // Cost of this Edge
    private Double cost;
    
    // One of the four kind of edge
    private int kind;

    /**
     * Initialize a new Edge. Etch Edge object has a Origin Vertex, a Destiny Vertex and a Cost
     * 
     * @param origin    The Origin Vertex
     * @param destiny   The Destiny Vertex
     * @param cost      The Edge Cost
     */
    public Edge(Vertex origin, Vertex destiny, Double cost) {
        this.origin = origin;
        this.destiny = destiny;
        this.cost = cost;
    }
    
    
    /*
     * public Edge(int origin, int destiny, Double cost) {
        this(new Vertex(origin), new Vertex(destiny), cost);
    }
     * 
     */

    public void setEdge(Vertex origin, Vertex destiny, double cost) {
        this.origin = origin;
        this.destiny = destiny;
        this.cost = cost;
    }
    
    /*
     * void setEdge(int origin, int destiny, double cost) {
        setEdge(new Vertex(origin), new Vertex(destiny), cost);
    }
     * 
     */

    public Vertex getOrigin() {
        return origin;
    }

    public Vertex getDestiny() {
        return destiny;
    }

    public String getEdge() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Double getCost() {
        return this.cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
    
    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public void modCost(double cost) {
        this.cost = Math.abs(this.cost - cost);
    }
}
