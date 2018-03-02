/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa_rpg;

import java.util.Random;
import java.util.ArrayList;
/**
 * @author JoÃo
 */
public class GeradorMapa {
    public Grafo g;
    public ArrayList <Vertice[]> mapa = new ArrayList();
    
    public GeradorMapa(){
        Random dado = new Random();
        int quant_niveis=2*dado.nextInt(10);
        for(int i=0;i<quant_niveis/2;i++){
            int n_vertice_nivel=dado.nextInt(10);
            String nome = "";
            if(i==0){
                Vertice[] n1_vertices =new Vertice[1];
                n1_vertices[0]=new Vertice("n0_v0");
                nome = "n"+i;
                mapa.add(n1_vertices);
            }else{
                /*isso ta tronxo ainda*/
                Vertice[] nx_vertices =new Vertice[n_vertice_nivel];
                for(int j=0;j<n_vertice_nivel;j++){
                    String nome_v = "n"+i+"-"+"v"+j;
                    nx_vertices[j] = new Vertice(nome_v);
                    mapa.add(nx_vertices);
                }
                nome = "n"+i;
            }
        }
        
        
    }
    
    
    public String toString(){
        int tam = this.mapa.size();
        String n = "";
        for(int i=0;i<tam;i++){
            n += "___n"+i +" ";
            for(Vertice v: this.mapa.get(i)){
                /* imprimir um array de objetos...*/
                n += " "+v.getNome()+" ";
            }
        }
        return n;
    }
    
}
