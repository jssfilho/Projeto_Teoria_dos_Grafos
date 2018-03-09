/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosemgrafos;

import algoritmosemgrafos.controller.DrawGraphController;
import algoritmosemgrafos.model.Graph;
import algoritmosemgrafos.model.Vertex;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultHeatMapDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author joseph
 */
public class Main2 {
    public static void main(String[] args) {
        long ini, end;
        
        for (int n = 10; n <= 100; n += 10) {
            for (int d = 30; d <= 100; d += 10) {
                System.out.println("n = " + n + ", d = " + d);
                
                Graph graph = new Graph(100, 40, false, false);
                
                ini = System.currentTimeMillis();
                Iterator<Vertex> iv = graph.getVertices().iterator();
                Vertex vertex = null;
                while (iv.hasNext()) {
                    vertex = iv.next();
                    graph.dijkstra(vertex);
                }
                end = System.currentTimeMillis();

                //graph.printPath(graph.getVertices().get(0), graph.getVertices().get(graph.getNumVertices()-1));
                //new DrawGraphController(graph);

                System.out.println("Time: " + (end - ini) + " miliseconds");
                System.out.println();
            }
        }
        
        System.exit(0);
        
        int a = 0;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        
        
        
        for (int i = 0; i < 100000; i++) {
            a = (int) (-26 + 52 * Math.random());
            System.out.println(a);
            if (a < min)
                min = a;
            if (a > max)
                max = a;
        }
        System.out.println(min);
        System.out.println(max);
        System.exit(0);
        
        /* teste */
        DefaultPieDataset data = new DefaultPieDataset();
        
        data.setValue("Categoria 1", 25);
        data.setValue("Categoria 2", 25);
        data.setValue("Categoria 3", 100);
        
        JFreeChart chart = ChartFactory.createPieChart("Sample Pie Chart", data, true, true, false);
        
        ChartFrame frame = new ChartFrame("Firts", chart);
        frame.pack();
        frame.setVisible(true);
        
        
        /*
        System.exit(0);
        Graph graph = new Graph("/media/Documents/Joseph/Documents/Estudos/Faculdade/Engenharia da Computação/Semestre 07/Algoritmos em Grafos (Andréa)/Trabalhos/grafo9.txt");
        
        System.out.println("\n" + graph.bellmanFord(graph.getVertex(0)));
        */
        //new DrawGraphController(graph);
        
//        graph.dijkstra(graph.getVertex(0));
//        
//        for (Vertex v : graph.getVertices()) {
//            System.out.println(v.getD() + " " + (v.getP() != null ? v.getP().getLabel() : null));
//        }
//        
//        System.out.println();
        
        //graph.printPath(graph.getVertex(0), graph.getVertex(6));
        
        
    }
}
