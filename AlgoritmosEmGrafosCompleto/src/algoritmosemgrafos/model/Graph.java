package algoritmosemgrafos.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import sun.misc.Compare;
import sun.misc.Sort;

/**
 * A class to represent a Graph
 * 
 * @author joseph
 * 
 * History
 * 
 * 04/08/2011 - Creation of the  Graph Class
 * 
 */
public class Graph {
    public static final char INF = '∞'; // Char to indicate that a value is infinity
    
    private File file; // file with a graph
    private BufferedReader in; // helps to read from a file
    
    private double[][] matrix; // Adjacency Matrix
    private boolean updated; // true if matrix is updated
    
    private ArrayList<Vertex> vertices; // Vertices List
    private ArrayList<Edge> edges; // Edges List
    
    private Graph graphNDirected;
    private boolean directed;
    
    /**
     * Initialize a new Graph without parameters
     */
    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        updated = false;
        directed = true;
    }
    
    /**
     * Initialize a new Graph according a file named filename
     * that is a adjacency matrix.
     * 
     * @param filename Name of the file (path)
     */
    public Graph(String filename) {
        this();
        
        file = new File(filename);
        initFromFile();
    }
    
    /**
     * Generate a random Graph from a density (to be stipulated by user)
     * and the vertex quantity
     * 
     * @param n Number of Vertices
     * @param d Density of the Graph, (d%) zero to one hundred (0 - 100)
     */
    public Graph(int n, int d) {
        this();
        initFromNAndD(n, d, false);
    }
    
    public Graph(int n, int d, boolean selfloop, boolean negative) {
        this();
        initFromNAndD(n, d, false, negative);
    }
    
    private void initFromFile() {
        try {
            this.in = new BufferedReader(new FileReader(file));
            
            String line = in.readLine(); // first line from file
            int numVertices = Integer.parseInt(line);
            
            for (int i = 0; i < numVertices; ++i) {
                vertices.add(new Vertex(i));
            }
            
            StringTokenizer tokenizer = null;
            while (in.ready()) {
                line = in.readLine();
                tokenizer = new StringTokenizer(line, " ");
                
                int v1, v2;
                double c;
                
                if (tokenizer.countTokens() == 3) {
                    v1 = Integer.parseInt(tokenizer.nextToken());
                    v2 = Integer.parseInt(tokenizer.nextToken());
                    c  = Double.parseDouble(tokenizer.nextToken());
                    
                    Edge edge = new Edge(vertices.get(v1), vertices.get(v2), c);
                    edges.add(edge);
                    
                    vertices.get(v1).addOrigin(edge);
                    vertices.get(v2).addDestiny(edge);
                }
            }
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Arquivo não localizado.\n", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar ler dados do arquivo.\n", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid File.\n", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex.getMessage());
        }
    }
    
    private void initFromNAndD(int n, int d, boolean selfloop, boolean negative) {
        //int numEdges = n * d * (n - 1) / 100; // number of edges
        //int numEdges = n * d * (n - 0) / 100; // number of edges
        int numEdges = n * d * (n - (selfloop? 0 : 1)) / 100; // number of edges
        int numVertices = n;
            
        for (int i = 0; i < numVertices; ++i) {
            vertices.add(new Vertex(i));
        }
        
        int createdEdges = 0;
        while (createdEdges < numEdges) {
            
            int v1 =   (int)    random(0, numVertices); //(numVertices * Math.random());
            int v2 =   (int)    random(0, numVertices); //(numVertices * Math.random());
            double c = (int)    random((negative? -25 : 0), 25); //(25          * Math.random());
            
            if (!this.hasEdge(vertices.get(v1), vertices.get(v2)) && (selfloop || v1 != v2)) {
                Edge edge = new Edge(vertices.get(v1), vertices.get(v2), c);
                edges.add(edge);

                ++createdEdges;

                vertices.get(v1).addOrigin(edge);
                vertices.get(v2).addDestiny(edge);
            }
        }
    }

    private void initFromNAndD(int n, int d, boolean selfloop) {
        initFromNAndD(n, d, selfloop, false);
    }
    
    double random(double min, double max) {
        return (min + (max-min) * Math.random());
    }
    
    private void initFromNAndD(int n, int d) {
        if (n > 0 && d > 0 && (JOptionPane.showConfirmDialog(null, "With Self-Loop?", "Confirm", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION)) {
            initFromNAndD(n, d, true);
        } else {
            initFromNAndD(n, d, false);
        }
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    /**
     * Returns the Vertices of this Graph
     * @return the Set of Vertices
     */
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Returns the Edges of this Graph
     * @return the Set of Edges
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }
    
    public void buildAdjacencyMatrix() {
        destroyAdjacencyMatrix();
        
        matrix = new double[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); ++i) {
            for (int j = 0; j < vertices.size(); ++j) {
                matrix[i][j] = -1.0;
            }
        }
        
        Iterator<Edge> it = edges.iterator();
        
        while (it.hasNext()) {
            Edge edge = it.next();
            
            matrix[vertices.indexOf(edge.getOrigin())][vertices.indexOf(edge.getDestiny())] = edge.getCost();
            //matrix[edge.getOrigin().getLabel()][edge.getDestiny().getLabel()] = edge.getCost();
            
        }
        
        updated = true; // Adjacency Matrix is updated
    }
    
    /**
     * Destroy a Adjacency Matrix
     */
    public void destroyAdjacencyMatrix() {
        matrix = null;
        updated = false;
    }
    
    /**
     * Display this Graph in Adjacency Matrix form
     */
    public void displayAdjacencyMatrix() {
        if (updated) {
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix.length; ++j) {
                    System.out.print("[" + matrix[i][j] + "]" + " ");
                }
                System.out.println();
            }
        } else {
            if (JOptionPane.showConfirmDialog(null, "Adjacency Matrix is not updated. Would you like update it now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                buildAdjacencyMatrix();
                displayAdjacencyMatrix();
            }
        }
    }
    
    /**
     * Display the Adjacency Matrix if it is updated and append to textArea
     * @param textArea TextArea where Matrix will appear
     */
    public void displayAdjacencyMatrix(JTextArea textArea) {
        if (updated) {
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix.length; ++j) {
                    if (matrix[i][j] != -1) {
                        textArea.append(matrix[i][j] + "\t");
                    } else {
                        textArea.append("  " + INF + "\t");
                    }
                }
                textArea.append("\n");
            }
            //textArea.append("\n");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Adjacency Matrix is not updated. Would you like update it now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                buildAdjacencyMatrix();
                displayAdjacencyMatrix(textArea);
            }
        }
    }
    
    /**
     * Returns the quantity of edges of this graph
     * @return the quantity of edges
     */
    public int getNumEdges() {
        return edges.size();
    }
    
    /**
     * Returns the quantity of vertices of this graph
     * @return the quantity of vertices
     */
    public int getNumVertices() {
        return vertices.size();
    }
    
    /**
     * Returns the number of vertices when it's a complete graph
     * @param directed true if it's a directed graph. false otherwise
     * @return Max number of Edges
     */
    private double maxNumEdges(boolean directed) {
        if (directed)
            return getNumVertices() * (getNumVertices() - 1);
        else
            return getNumVertices() * (getNumVertices() - 1) / 2;
    }
    
    /**
     * Returns the density of the graph
     * @return Density of the graph
     */
    public double getDensity() {
        return getNumEdges() / (maxNumEdges(true));
    }
    
    /**
     * Returns the degree that out of this vertex
     * @param vertex vertex
     * @return the degree of the vertex
     */
    public int getOutDegree(Vertex vertex) {
        return vertex.getOriginSize();
    }
    
    /**
     * Returns the degree that in to this vertex
     * @param vertex vertex
     * @return the degree of the vertex
     */
    public int getInDegree(Vertex vertex) {
        return vertex.getDestinySize();
    }
    
    /**
     * Add a new Vertex in the Graph
     * @param label The label of the new Vertex
     * @return 0 if Vertex added (It means that there's no a Vertex labeled "label"
     * 1 if there's a Vertex labeled "label"
     * 2 if label informed is non positive value
     */
    public int addVertex(int label) {
        if (label >= 0) {
             if (!this.hasVertex(label)) {
                 vertices.add(new Vertex(label));
                 updated = false;
                 return 0;
             }else {
                 return 1;
             }
        } else
            return 2;
    }
    
    public int removeVertex(int label) {
        if (label >= 0) {
            if (this.hasVertex(label)) {
                
                Vertex vertex = getVertex(label);
                
                if (vertex != null) {

                    Edge edge = null;
                    for (int i = 0; i < edges.size(); ++i) {
                        edge = edges.get(i);
                        
                        if (edge.getOrigin().equals(vertex)) {
                            removeEdge(vertex, edge.getDestiny());
                            System.out.println("Removido " + vertex.getLabel() + ", " + edge.getDestiny().getLabel());
                            --i;
                        } else if (edge.getDestiny().equals(vertex)) {
                            removeEdge(edge.getOrigin(), vertex);
                            System.out.println("Removido " + edge.getOrigin().getLabel() + ", " + vertex.getLabel());
                            --i;
                        }
                    }

                    vertices.remove(vertex);
                    updated = false;
                    return 0;
                }
            }
            return 1;
        } else
            return 2;
    }
    
    /**
     * Check if a Vertex, given a label, exists
     * @param label Label to see the Vertex exists
     * @return true if there is a Vertex labeled "label"
     */
    public boolean hasVertex(int label) {
        Iterator<Vertex> it = vertices.iterator();
        Vertex vertex = null;
        
        while (it.hasNext()) {
            vertex = it.next();
            
            if (vertex.getLabel() == label)
                return true;
        }
        return false;
    }
    
    /**
     * Add a new edge in the graph, if it does not exists
     * @param origin origin vertex
     * @param destiny destiny vertex
     * @param cost cost of this edge
     * @return 0 if cost is great then zero or equals, 1 if cost less then zero, 2 if edge already exists
     */
    public int addEdge(Vertex origin, Vertex destiny, double cost) {
        if (!this.hasEdge(origin, destiny)) {
            if (true /*cost >= 0*/) {
                Edge edge = new Edge(origin, destiny, cost);
                edges.add(edge);

                origin.addOrigin(edge);
                destiny.addDestiny(edge);

                updated = false;
                return 0;
            } else {
                // JOptionPane.showMessageDialog(null, "Cost must be a positive value", "Error", JOptionPane.ERROR_MESSAGE);
                return 1;
            }
        } else {
            // JOptionPane.showMessageDialog(null, "You cannot add an Edge in this position", "Error", JOptionPane.ERROR_MESSAGE);
            return 2;
        }
    }
    
    /**
     * Removes a edge
     * @param origin origin vertex
     * @param destiny destiny vertex
     * @return true if any edge was removed
     */
    public boolean removeEdge(Vertex origin, Vertex destiny) {
        if (this.hasEdge(origin, destiny)) {
            
            int indexOfEdge = getIndexOfEdge(origin, destiny);
            System.out.println(indexOfEdge);
            Edge edge = edges.get(indexOfEdge);
            
            origin.removeOrigin(edge);
            destiny.removeDestiny(edge);
            
            edges.remove(indexOfEdge);
            
            updated = false;
            return true;
        } else {
            return false;
        }
    }
    
    private int getIndexOfEdge(Vertex origin, Vertex destiny) {
        int i = 0;
        Iterator<Edge> it = edges.iterator();
        
        while (it.hasNext()) {
            Edge edge = it.next();
            
            if (edge.getOrigin().getLabel() == origin.getLabel() && edge.getDestiny().getLabel() == destiny.getLabel())
                return i;
            ++i;
        }
        return -1;
    }
    
    public Vertex getVertex(int label) {
        Iterator<Vertex> it = vertices.iterator();
        
        while (it.hasNext()) {
            Vertex v = it.next();
            if (v.getLabel() == label)
                return v;
        }
        return null;
    }
    
    /**
     * Returns if there is this edge
     * @param origin origin vertex
     * @param destiny destiny vertex
     * @return true if the edge exists. false otherwise
     */
    public boolean hasEdge(Vertex origin, Vertex destiny) {
        Iterator<Edge> it = edges.iterator();
        
        while (it.hasNext()) {
            Edge edge = it.next();
            
            if (edge.getOrigin().equals(origin) &&
                    edge.getDestiny().equals(destiny) &&
                    edge.getCost() != -1) {
                return true;
            }
        }
        return false;
    }
    
    public Edge getEdge(Vertex origin, Vertex destiny) {
        Iterator<Edge> it = edges.iterator();
        
        while (it.hasNext()) {
            Edge edge = it.next();
            
            if (edge.getOrigin().equals(origin) &&
                    edge.getDestiny().equals(destiny) &&
                    edge.getCost() != -1) {
                return edge;
            }
        }
        return null;
    }
    
    /**
     * Returns the weight of this edge
     * @param origin origin vertex
     * @param destiny destiny vertex
     * @return 
     */
    public double getEdgeCost(Vertex origin, Vertex destiny) {
        if (this.hasEdge(origin, destiny)) {
            return edges.get(getIndexOfEdge(origin, destiny)).getCost();
        } else {
            return -1;
        }
    }
    
    /**
     * Returns a transitiveClosure list (transitive closure) of a vertex R+(v)
     * @param v a label that identify a vertex
     * @return the transitiveClosure list
     */
    public ArrayList<Vertex> transitiveClosure(int v) {
        return transitiveClosure(getVertex(v));
    }
    
    /**
     * Returns a transitiveClosure list (transitive closure) of a vertex R+(v)
     * @param v the vertex
     * @return the transitiveClosure list
     */
    public ArrayList<Vertex> transitiveClosure(Vertex v) {
        if (v != null) {
            ArrayList<Vertex> transitiveClosure = new ArrayList<Vertex>();
            Stack<Vertex> stack = new Stack<Vertex>();

            ArrayList<Vertex> visited = new ArrayList<Vertex>();

            Iterator<Edge> it;
            Vertex aux = v;
            stack.add(aux);

            do {
                do {
                    aux = stack.remove(0);
                } while (visited.contains(aux));

                it = edges.iterator();

                Edge edge = null;
                while (it.hasNext()) {
                    edge = it.next();

                    Vertex destiny = null;
                    if (edge.getOrigin().equals(aux)) {
                        destiny = edge.getDestiny();

                        if (destiny != aux && destiny != v) {
                            if (!transitiveClosure.contains(destiny))
                                transitiveClosure.add(destiny);

                            if (!stack.contains(destiny) && !visited.contains(destiny))
                                stack.add(destiny);
                        }
                    }
                }

                visited.add(aux);

            } while (!stack.empty());

            return transitiveClosure;
        } else
            return null;
    }
    
    /**
     * Returns a reverseTransitiveClosure list (transitive closure) of a vertex R+(v)
     * @param v a label that identify a vertex
     * @return the transitiveClosure list
     */
    public ArrayList<Vertex> reverseTransitiveClosure(int v) {
        return reverseTransitiveClosure(getVertex(v));
    }
    
    /**
     * Returns a reverseTransitiveClosure list (transitive closure) of a vertex R+(v)
     * @param the vertex
     * @return the transitiveClosure list
     */
    public ArrayList<Vertex> reverseTransitiveClosure(Vertex v) {
        if (v != null) {
            ArrayList<Vertex> predecessorList = new ArrayList<Vertex>();
            Stack<Vertex> stack = new Stack<Vertex>();

            ArrayList<Vertex> visited = new ArrayList<Vertex>();

            Iterator<Edge> it;
            Vertex aux = v;
            stack.add(aux);

            do {
                do {
                    aux = stack.remove(0);
                } while (visited.contains(aux));

                it = edges.iterator();

                Edge edge = null;
                while (it.hasNext()) {
                    edge = it.next();

                    Vertex origin = null;
                    if (edge.getDestiny().equals(aux)) {
                        origin = edge.getOrigin();

                        if (origin != aux && origin != v) {
                            if (!predecessorList.contains(origin))
                                predecessorList.add(origin);

                            if (!stack.contains(origin) && !visited.contains(origin))
                                stack.add(origin);
                        }
                    }
                }

                visited.add(aux);

            } while (!stack.empty());

            return predecessorList;
        } else
            return null;
    }
    
    /**
     * Returns the Neighborhood of a Vertex - N+(v)
     * @param v The Vertex
     * @return The Neighborhood
     */
    public ArrayList<Vertex> neighbourhood(Vertex v, boolean enclosed, ArrayList<Vertex> ver, ArrayList<Edge> ed) {
        if (ed != null) {
            Iterator<Edge> it = ed.iterator();
            ArrayList<Vertex> neighbor = new ArrayList<Vertex>();

            Edge edge = null;
            while (it.hasNext()) {
                edge = it.next();
                
                if (edge.getOrigin() == v)
                    neighbor.add(edge.getDestiny());
            }
            
            if (enclosed && !neighbor.contains(v))
                neighbor.add(0, v);
            
            return neighbor;
        } else
            return new ArrayList<Vertex>();
    }
    
    /**
     * Returns the Neighborhood of a Vertex - N+(v)
     * @param v The Vertex
     * @return The Neighborhood
     */
    public ArrayList<Vertex> neighbourhood(Vertex v, boolean enclosed) {
        return neighbourhood(v, enclosed, vertices, edges);
    }
    
    /**
     * Returns the Neighborhood of a Vertex - N+(v)
     * @param v The Vertex
     * @return The Neighborhood
     */
    public ArrayList<Vertex> neighbourhood(Vertex v) {
        return neighbourhood(v, false);
    }
    
    /**
     * Returns the Neighborhood of a Vertex - N-(v)
     * @param v The Vertex
     * @return The Neighborhood
     */
    public ArrayList<Vertex> reverseNeighbourhood(Vertex v, boolean enclosed, ArrayList<Vertex> ver, ArrayList<Edge> ed) {
        if (ed != null) {
            Iterator<Edge> it = ed.iterator();
            ArrayList<Vertex> reverseNeighbor = new ArrayList<Vertex>();

            Edge edge = null;
            while (it.hasNext()) {
                edge = it.next();
                
                if (edge.getDestiny() == v)
                    reverseNeighbor.add(edge.getOrigin());
            }

            if (enclosed && !reverseNeighbor.contains(v))
                reverseNeighbor.add(0, v);

            return reverseNeighbor;
        } else
            return new ArrayList<Vertex>();
    }
    
    /**
     * Returns the Neighborhood of a Vertex - N-(v)
     * @param v The Vertex
     * @return The Neighborhood
     */
    public ArrayList<Vertex> reverseNeighbourhood(Vertex v, boolean enclosed) {
        return reverseNeighbourhood(v, enclosed, vertices, edges);
    }
    
    /**
     * Returns the Neighborhood of a Vertex - N-(v)
     * @param v The Vertex
     * @return The Neighborhood
     */
    public ArrayList<Vertex> reverseNeighbourhood(Vertex v) {
        return reverseNeighbourhood(v, false);
    }
    
    /**
     * Returns a Set of Strongly Connected Components
     * @param s0 First Vertex to start
     * @param ver Set of Vertices
     * @param ed Set of Edges
     * @return Set of Strongly Connected Components
     */
    public ArrayList<ArrayList<Vertex>> FCONEX(Vertex s0, ArrayList<Vertex> ver, ArrayList<Edge> ed) {
        ArrayList<ArrayList<Vertex>> allComponents = new ArrayList<ArrayList<Vertex>>();
        
        if (ver == null)
            ver = vertices;
        if (ed == null)
            ed = edges;
        
        // Copy of the Graph
        ArrayList<Vertex> V = new ArrayList<Vertex>();
        ArrayList<Edge> E = new ArrayList<Edge>();
        
        Iterator<Vertex> itVer = ver.iterator();
        while (itVer.hasNext()) {
            V.add(itVer.next());
        }
        
        Iterator<Edge> itEd = ed.iterator();
        while (itEd.hasNext()) {
            E.add(itEd.next());
        }
        
        Vertex v = s0; // Vertex v = s0;
        V.remove(v);
        
        ArrayList<Vertex> r = new ArrayList<Vertex>();
        r.add(v); // R+(v) <- {v}
        
        ArrayList<Vertex> r_ = new ArrayList<Vertex>();
        r_.add(v); // R-(v) <- {v}
        
        ArrayList<Vertex> W = new ArrayList<Vertex>(); // W <- { };
        
        ArrayList<Vertex> dif = dif(n(r, V, E), r);
        
        // Enquanto ( ( N+[R+(v)] - R+(v) ) != { } ) {
        while (!dif.isEmpty()) {
            W = dif;
            r = union(r, W);
            
            dif = dif(n(r, V, E), r);
        }
        
        dif = dif(n_(r_, V, E), r_);
        while (!dif.isEmpty()) {
            W = dif;
            r_ = union(r_, W);
            
            dif = dif(n_(r_, V, E), r_);
        }
        
        W = intersec(r, r_);
        allComponents.add(W);
        
        V = dif(V, W);
        
        for (int i = 0; i < E.size() && !E.isEmpty(); ++i) {
            if (E.get(i).getOrigin().equals(v) || E.get(i).getDestiny().equals(v)) {
                E.remove(0);
                --i;
            }
        }
        
        ArrayList<ArrayList<Vertex>> tempAllComponents = null;
        if (!V.isEmpty()) {
            tempAllComponents = FCONEX(V.get(0), V, E);
            Iterator<ArrayList<Vertex>> tempAllComponentsIterator = tempAllComponents.iterator();
            while (tempAllComponentsIterator.hasNext())
                allComponents.add(tempAllComponentsIterator.next());
        }
        
        return allComponents;
    }
    
    
    public ArrayList<Vertex> n(ArrayList<Vertex> r, ArrayList<Vertex> ver, ArrayList<Edge> ed) {
        ArrayList<Vertex> nList = new ArrayList<Vertex>();
        
        Iterator<Vertex> it = r.iterator();
        Vertex vertex = null;
        while (it.hasNext()) {
            vertex = it.next();
            
            nList = union(nList, neighbourhood(vertex, true, ver, ed));
        }
        
        return nList;
    }
    
    public ArrayList<Vertex> n_(ArrayList<Vertex> r, ArrayList<Vertex> ver, ArrayList<Edge> ed) {
        ArrayList<Vertex> n_List = new ArrayList<Vertex>();
        
        Iterator<Vertex> it = r.iterator();
        Vertex vertex = null;
        while (it.hasNext()) {
            vertex = it.next();
            
            n_List = union(n_List, reverseNeighbourhood(vertex, true, ver, ed));
        }
        
        return n_List;
    }
    
    /**
     * Returns a Set containing the difference between two Set of Vertices
     * e. g. The vertices that exists in array1 but doesn't in array2
     * @param array1 Set of Vertices 1
     * @param array2 Set of Vertices 2
     * @return Intersection
     */
    public ArrayList<Vertex> dif(ArrayList<Vertex> array1, ArrayList<Vertex> array2) {
        ArrayList<Vertex> intersec = new ArrayList<Vertex>();
        
        Iterator<Vertex> it = array1.iterator();
        Vertex vertex;
        while (it.hasNext()) {
            vertex = it.next();
            if (!array2.contains(vertex)) {
                intersec.add(vertex);
            }
        }
        
        return intersec;
    }
    
    /**
     * Returns a Set containing the union between two Set of Vertices
     * @param array1 Set of Vertices 1
     * @param array2 Set of Vertices 2
     * @return Intersection
     */
    public ArrayList<Vertex> union(ArrayList<Vertex> array1, ArrayList<Vertex> array2) {
        ArrayList<Vertex> union = new ArrayList<Vertex>();
        
        Iterator<Vertex> it = array1.iterator();
        Vertex vertex = null;
        while (it.hasNext()) {
            vertex = it.next();
            if (!union.contains(vertex)) {
                union.add(vertex);
            }
        }
        it = array2.iterator();
        while (it.hasNext()) {
            vertex = it.next();
            if (!union.contains(vertex)) {
                union.add(vertex);
            }
        }
        
        return union;
    }
    
    /**
     * Returns a Set containing the intersection between two Set of Vertices
     * @param array1 Set of Vertices 1
     * @param array2 Set of Vertices 2
     * @return Intersection
     */
    public ArrayList<Vertex> intersec(ArrayList<Vertex> array1, ArrayList<Vertex> array2) {
        ArrayList<Vertex> intersec = new ArrayList<Vertex>();
        
        Iterator<Vertex> it = array1.iterator();
        Vertex vertex;
        while (it.hasNext()) {
            vertex = it.next();
            if (array2.contains(vertex)) {
                intersec.add(vertex);
            }
        }
        
        return intersec;
    }
    
    /**
     * Returns the Vertex that has x and y as its coordinate
     * @param x X coordinate
     * @param y Y coordinate
     * @return The Vertex
     */
    public Vertex getVertexByXY(int x, int y) {
        Iterator<Vertex> it = vertices.iterator();
        Vertex vertex = null;
        while (it.hasNext()) {
            vertex = it.next();
            
            if (vertex.isOnVertex(x, y))
                return vertex;
        }
        return null;
    }
    
    private int time;
    
    public void DFS() {
        Iterator<Vertex> it = vertices.iterator();
        Vertex vertex = null;
        while (it.hasNext()) {
            vertex = it.next();
            vertex.setColor(Vertex.COLOR_WHITE);
            vertex.setP(null);
        }
        time = 0;
        
        it = vertices.iterator();
        while (it.hasNext()) {
            vertex = it.next();
            
            if (vertex.getColor() == Vertex.COLOR_WHITE) {
                DFS_VISIT(vertex);
            }
        }
    }
    public void DFS_VISIT(Vertex vertex) {
        //System.out.print(vertex.getLabel() + " ");
        
        vertex.setColor(Vertex.COLOR_GRAY);
        vertex.setD(++time);
        Iterator<Edge> it = vertex.getEdgesOrigin().iterator();
        Edge edge = null;
        while (it.hasNext()) {
            edge = it.next();
            if (edge.getDestiny().getColor() == Vertex.COLOR_WHITE) {
                edge.getDestiny().setP(vertex);
                edge.setKind(Edge.EDGE_TREE);
                DFS_VISIT(edge.getDestiny());
            } else if (edge.getDestiny().getColor() == Vertex.COLOR_GRAY) {
                edge.setKind(Edge.EDGE_BACK);
            } else if (edge.getDestiny().getColor() == Vertex.COLOR_BLACK) {
                edge.setKind(Edge.EDGE_CROSS);
            }
        }
        vertex.setColor(Vertex.COLOR_BLACK);
        vertex.setF(++time);
        //System.out.print(vertex.getLabel() + " ");
    }

//    public Graph getNonDirectedVersion() {
//        graphNDirected = new Graph(0, 0);
//        graphNDirected.setDirected(false);
//        
//        graphNDirected.setVertices(vertices);
//        
//        Iterator<Edge> itE = edges.iterator();
//        Edge edge = null;
//        while (itE.hasNext()) {
//            edge = itE.next();
//            
//            if (!graphNDirected.hasEdge(edge.getDestiny(), edge.getOrigin()) && !edge.getOrigin().equals(edge.getDestiny()))
//                graphNDirected.addEdge(edge.getOrigin(), edge.getDestiny(), 1);
//        }
//        
//        return graphNDirected;
//    }
    

    private void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }
    
    public Graph getUndirectedVersion() {
        Graph newGraph = new Graph();
        
        Iterator<Vertex> itV = vertices.iterator();
        Vertex vertex = null;
        while (itV.hasNext()) {
            vertex = itV.next();
            newGraph.addVertex(vertex.getLabel());
        }
        
        Iterator<Edge> itE = edges.iterator();
        Edge edge = null;
        while (itE.hasNext()) {
            edge = itE.next();
            
            Vertex origim;
            Vertex destiny;
            double cost;
            
            origim = newGraph.getVertex(edge.getOrigin().getLabel());
            destiny = newGraph.getVertex(edge.getDestiny().getLabel());
            cost = edge.getCost();
            
            if (!newGraph.hasEdge(destiny, origim) && !origim.equals(destiny)) {
                newGraph.addEdge(origim, destiny, cost);
                newGraph.addEdge(destiny, origim, cost);
            } else if (newGraph.hasEdge(destiny, origim)) {
                Edge e = newGraph.getEdge(destiny, origim);
                e.modCost(cost);
                e = newGraph.getEdge(origim, destiny);
                e.modCost(cost);
            }
            
        }
        newGraph.setDirected(false);
        //new DrawGraphController(newGraph);
        
        return newGraph;
    }
    
    /**
     * Returns a Set of Edges that create a minimum spanning tree
     * @return The Set of Edges of the Tree
     */
    public ArrayList<Edge> prim() {
        
        // Get a undirected version of the Graph
        Graph undirectedGraph = getUndirectedVersion();
        
        // Variable vt Statement
        ArrayList<Vertex> vt = new ArrayList<Vertex>();
        vt.add(undirectedGraph.getVertices().get(0));

        // Variable et Statement
        ArrayList<Edge> et = new ArrayList<Edge>();

        // creating a Set of Priority Queues
        ArrayList<ArrayList<Edge>> lists = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i < undirectedGraph.getNumVertices(); ++i) {
            lists.add(new ArrayList<Edge>());

            for (int j = 0; j < undirectedGraph.getVertices().get(i).getOriginSize(); ++j) {
                lists.get(i).add(undirectedGraph.getVertices().get(i).getEdgesOrigin().get(j));
            }

            Object[] array = lists.get(i).toArray();
            Sort.quicksort(array, new Compare() {

                @Override
                public int doCompare(Object o, Object o1) {
                    Edge e1 = (Edge) o;
                    Edge e2 = (Edge) o1;

                    if (e1.getCost() < e2.getCost())
                        return -1;
                    else if (e1.getCost() > e2.getCost())
                        return 1;
                    else
                        return 0;
                }
            });

            lists.set(i, new ArrayList(Arrays.asList(array)));
        }
        
        // Prim's Algorithm Loop
        for (int i = 0; i < undirectedGraph.getVertices().size() - 1; ++i) {

            Edge minEdge = null;

            // Loop for the minimum-weith edge
            for (ArrayList<Edge> adj : lists) {
                if (!adj.isEmpty() && vt.contains(adj.get(0).getOrigin()) && !vt.contains(adj.get(0).getDestiny())) {
                    if (minEdge == null) {
                        minEdge = adj.get(0);
                        continue;
                    } else {
                        if (adj.get(0).getCost() < minEdge.getCost()) {
                            minEdge = adj.get(0);
                        }
                    }
                }
            }

            vt.add(minEdge.getDestiny());
            et.add(minEdge);

            // Removing vt's vertices from set of Priority Queues
            for (ArrayList<Edge> adj : lists) {
                if (!adj.isEmpty() && vt.contains(adj.get(0).getOrigin())) {
                    for (int j = 0; j < adj.size(); ++j) {
                        if (vt.contains(adj.get(j).getDestiny())) {
                            adj.remove(j);
                            --j;
                        }
                    }
                }
            }

        }
        
        return et;
    }
    
    /**
     * Initialize all vertex with the value
     * @param s initial vertex
     * @param initialValue Initial value
     * -1 = -∞
     *  0 =  0
     *  1 =  ∞
     */
    private void initializeSingleSource(Vertex s, int initialValue) {
        for (Vertex vertex : this.getVertices()) {
            switch (initialValue) {
                case -1:
                    vertex.setD(Integer.MIN_VALUE);
                    break;
                case 0:
                    vertex.setD(0);
                    break;
                case 1:
                    vertex.setD(Integer.MAX_VALUE);
                    break;
                default:
                    System.exit(1);
            }
            vertex.setP(null);
        }
        if (this.getVertices().contains(s))
            s.setD(0);
        else
            JOptionPane.showMessageDialog(null, "S Vertex is not in V[G]", "Warning!", JOptionPane.WARNING_MESSAGE);
    }
    
    private boolean relax(Vertex u, Vertex v, double w) {
        if (v.getD() > u.getD() + w) {
            v.setD(u.getD() + (int) w);
            v.setP(u);
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param L
     * @param W
     * @return 
     */
    public double[][] extendShortestPath(double[][] L, double[][] W) {
        
        int n = L.length;
        double[][] L_ = new double[n][n];
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                L_[i][j] = Double.MAX_VALUE; // infinity
                for (int k = 0; k < n; ++k) {
                    L_[i][j] = Math.min(L_[i][j], L[i][k] + W[k][j]);
                }
            }
        }
        
        return L_;
    }
    
    /**
     * Dynamic Programming
     */
    public double[][] floydWarshall(double[][] W) {
        int n = W.length;
        double[][] D;
        double[][] D_ = W.clone();
        
        for (int k = 0; k < n; ++k) {
            D = D_.clone();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    D_[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }
        
        return D_;
    }
    
    /**
     * 
     * @param s 
     */
    public void dijkstra(Vertex s) {
        initializeSingleSource(s, 1);
        
        ArrayList<Vertex> S = new ArrayList<Vertex>();
        ArrayList<Vertex> Q = new ArrayList<Vertex>();
        
        for (Vertex vertex : this.getVertices()) {
            Q.add(vertex);
        }
        
        Vertex u = null;
        while (Q.size() > 1) {
            Q = sortByVertex_getD(Q);
            
            u = Q.remove(0);
            S.add(u);
            
            for (Edge e : u.getEdgesOrigin()) {
                relax(u, e.getDestiny(), e.getCost());
            }
        }
    }
    
    public Graph copy() {
        Graph newGraph = new Graph();
        
        Iterator<Vertex> itV = getVertices().iterator();
        Vertex vertex = null;
        while (itV.hasNext()) {
            vertex = itV.next();
            newGraph.addVertex(vertex.getLabel());
            
            newGraph.getVertex(newGraph.getNumVertices() - 1).setD(vertex.getD());
            newGraph.getVertex(newGraph.getNumVertices() - 1).setF(vertex.getF());
            newGraph.getVertex(newGraph.getNumVertices() - 1).setP(vertex.getP());
        }
        
        Iterator<Edge> itE = getEdges().iterator();
        Edge edge = null;
        while (itE.hasNext()) {
            edge = itE.next();
            
            newGraph.addEdge(
                newGraph.getVertex(edge.getOrigin().getLabel()), 
                newGraph.getVertex(edge.getDestiny().getLabel()),
                edge.getCost());
        }
        
        return newGraph;
    }
    
    public double[][] johnson() {
        Graph g_ = copy(); // create a copy of original graph
        double[][] delta = null;
        
        if (bellmanFord(getVertex(0), 0) != false) { // see if there's no cycle
            int[] h = new int[getNumVertices()]; // create h to store h values of the vertices
            
            int i = 0;
            
            Iterator<Vertex> itV = getVertices().iterator();
            while (itV.hasNext()) {
                h[i++] = itV.next().getD();
            }
            
            Iterator<Edge> itE = getEdges().iterator();
            Edge edge = null;
            while (itE.hasNext()) {
                edge = itE.next();
                
                if (!edge.getOrigin().equals(edge.getDestiny())) { // there's no self loop
                    g_.getEdge(
                        g_.getVertex(edge.getOrigin().getLabel()), // origin
                        g_.getVertex(edge.getDestiny().getLabel())) // destiny
                            .setCost(
                                edge.getCost() + h[edge.getOrigin().getLabel()] - h[edge.getDestiny().getLabel()]
                            );
                }
                
                
            } // new graph created with positive costs
            
            delta = new double[g_.getNumVertices()][g_.getNumVertices()];
            
            for (i = 0; i < g_.getNumVertices(); ++i) {
                g_.dijkstra(g_.getVertex(i));
                
                for (int j = 0; j < g_.getNumVertices(); ++j) {
                    if (i != j) {
                        delta[i][j] = g_.getVertex(j).getD() + h[j] - h[i];
                    }
                }
            }
            
        } else {
            
        }
        
        return delta;
    }
    
    /**
     * Return the ArrayList sorted by D
     * @param list The ArrayList
     * @return The ArrayList sorted
     */
    private ArrayList sortByVertex_getD(ArrayList list) {
        Object[] array = list.toArray();
        
        Sort.quicksort(array, new Compare() {

            @Override
            public int doCompare(Object o, Object o1) {
                Vertex a = (Vertex) o;
                Vertex b = (Vertex) o1;
                
                if (a.getD() < b.getD())
                    return -1;
                else
                    return 1;
            }
        });
        
        list = new ArrayList(Arrays.asList(array));
        
        return list;
    }
    
    public void printPath(Vertex s, Vertex v, JTextArea textArea) {
        Stack<Vertex> stack = new Stack<Vertex>();
        
        Iterator<Vertex> it = getVertices().iterator();
        
        Vertex vertex = null;
        while (it.hasNext()) {
            vertex = it.next();
            if (vertex.equals(v))
                break;
        }
        
        Vertex aux = v.getP();
        while (aux != null && !aux.equals(s)) {
            stack.add(aux);
            aux = aux.getP();
        }
        
        if (textArea != null) {
            textArea.append(s.getLabel() + " to " + v.getLabel() + "\n");
        } else {
            System.out.println(s.getLabel() + " to " + v.getLabel());
        }
        
        if (v.getP() != null) {
            
            if (textArea != null) {
                textArea.append(s.getLabel() + " ");
                while (!stack.isEmpty()) {
                    textArea.append(stack.pop().getLabel() + " ");
                }
                textArea.append(v.getLabel() + "\n");
            } else {
                System.out.print(s.getLabel() + " ");
                while (!stack.isEmpty()) {
                    System.out.print(stack.pop().getLabel() + " ");
                }
                System.out.println(v.getLabel());
            }
            
        } else {
            if (textArea != null) {
                textArea.append("TNW " + s.getLabel() + " to " + v.getLabel() + "\n");
            } else {
                System.out.println("TNW " + s.getLabel() + " to " + v.getLabel());
            }
        }
        if (textArea != null) {
            textArea.append("\n");
        } else {
            System.out.println();
        }
    }
    
    public boolean bellmanFord(Vertex s) {
        return bellmanFord(s, 1);
    }
    
    /**
     * Algorithm to detect if there is solution
     * @param s
     * @param initialValue Initial value:
     * -1 = -∞
     *  0 =  0
     *  1 =  ∞
     * @return 
     */
    public boolean bellmanFord(Vertex s, int initialValue) {
        initializeSingleSource(s, initialValue);
        int k = 0;
        boolean relaxed = true;
        for (int i = 0; (i < (getVertices().size() - 1)) && relaxed; ++i) {
            relaxed = false;
            for (Edge edge: getEdges()) {
                relaxed = relax(edge.getOrigin(), edge.getDestiny(), edge.getCost()) || relaxed;
            }
            //System.out.println(relaxed);
        }
        
        for (Edge edge: getEdges()) {
            if (edge.getDestiny().getD() > edge.getOrigin().getD() + edge.getCost())
                return false;
        }
        
        return true;
    }
    
    private int[][] tclosure() {
        int n = getNumVertices();
        
        int[][] T = new int[n][n];
        int[][] T_ = new int[n][n];
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j || (hasEdge(getVertex(i), getVertex(j))))
                    T[i][j] = 1;
                else
                    T[i][j] = 0;
            }
        }
        
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    T_[i][j] = T[i][j] | (T[i][k] & T[k][j]);
                }
            }
        }
        
        return T_;
    }

    /**
     * Build a W matrix of weights of graph
     * 
     * W[i][j] = 0  if i == j
     * W[i][j] = Edge.cost() if i != j and (i, j) is in E
     * W[i][j] = ∞ (infinity) if i != j and (i, j) is not in E
     * 
     * @return Weight Matrix | Adjacency Matrix
     */
    public double[][] buildWeightMatrix() {
        double[][] W = new double[getNumVertices()][getNumVertices()];
        
        for (int i = 0; i < W.length; ++i) {
            for (int j = 0; j < W[0].length; ++j) {
                W[i][j] = (i == j)
                        ? 0 
                        : (hasEdge(getVertex(i), getVertex(j)) )
                            ? getEdgeCost(getVertex(i), getVertex(j)) 
                            :Double.MAX_VALUE;
            }
        }
        
        return W;
    }

    public void printMatrix(double[][] L) {
        for (int i = 0; i < L.length; ++i) {
            for (int j = 0; j < L[0].length; ++j) {
                System.out.print( (L[i][j] == Double.MAX_VALUE? Character.toString(INF) : L[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public void printMatrix(double[][] L, JTextArea textArea) {
        for (int i = 0; i < L.length; ++i) {
            for (int j = 0; j < L[0].length; ++j) {
                textArea.append((L[i][j] == Double.MAX_VALUE? Character.toString(INF) : L[i][j]) + " ");
            }
            textArea.append("\n");
        }
    }

    public Vertex[][] buildPiMatrix() {
        Vertex[][] pi = new Vertex[getNumVertices()][getNumVertices()];
        
        for (int i = 0; i < pi.length; ++i) {
            for (int j = 0; j < pi[0].length; ++j) {
                if (i == j) {
                    pi[i][j] = null;
                }
            }
        }
        
        return pi;
    }

    public Vertex[][] predecessorMatrix() {
        Vertex[][] predecessorMatrix = new Vertex[getNumVertices()][getNumVertices()];
        
        for (int i = 0; i < predecessorMatrix.length; ++i) {
            for (int j = 0; j < predecessorMatrix[0].length; ++j) {
                if (i == j || !hasEdge(getVertex(i), getVertex(j))) {
                    predecessorMatrix[i][j] = null;
                } else {
                    predecessorMatrix[i][j] = getVertex(0);
                }
            }
        }
        
        return predecessorMatrix;
    }

    public void printAllPairsShortestPath(int[][] predecessorMatrix, int i, int j, JTextArea textArea) {
        if (i == j) {
            textArea.append(Integer.toString(i) + " ");
        } else {
            if (predecessorMatrix[i][j] < -99) {
                textArea.append("No path from " + i + " to " + j + " exists\n");
            } else {
                printAllPairsShortestPath(predecessorMatrix, i, predecessorMatrix[i][j], textArea);
                textArea.append(Integer.toString(j) + "\n");
            }
        }
    }
    
    
    
}
