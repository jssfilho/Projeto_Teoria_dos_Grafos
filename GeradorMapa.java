/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa_rpg;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author JoÃo
 */
public class GeradorMapa {
    public Grafo g;
    public Map mapa;
    
    public GeradorMapa(Grafo g){
        this.g=g;
        Random dado = new Random();
        int quant_niveis=2*dado.nextInt(10);
        
        for(int i=0;i<quant_niveis/2;i++){
            int n_vertice_nivel=dado.nextInt(10);
            if(i==1){
                Vertice[] n1_vertices =new Vertice[1];
                n1_vertices[0]=new Vertice("n1_v1");
                this.mapa.put("n"+i, n1_vertices);
            }else{
                /*isso ta tronxo ainda*/
                int nx_vertice_nivel = dado.nextInt(10);
                Vertice[] nx_vertices =new Vertice[nx_vertice_nivel];
                for(int j=0;j<nx_vertice_nivel;j++){
                    this.mapa.put("n"+i, nx_vertices);
                }
            }
  
        }
        
    }
    public Map getMapa(){
        return this.mapa;
    }
    
}
