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
    public Grafo g = new Grafo();
    public ArrayList <Vertice[]> mapa = new ArrayList();
    public ArrayList <int[][]> caminhos = new ArrayList();
    
    public GeradorMapa(){
        //valor variavel
        int x = 5;
        Random dado = new Random();
        int quant_niveis=2*(dado.nextInt(x)+1);
        int n_vertice_nivel = 0;
        String nome_v = "";
        for(int i=0;i<quant_niveis/2;i++){
            n_vertice_nivel=(dado.nextInt(x)+1);
            if(i==0){
                Vertice[] n1_vertices =new Vertice[1];
                n1_vertices[0]=new Vertice("n0-v0");
                mapa.add(n1_vertices);
            }else{
                /*isso ta tronxo ainda*/
                Vertice[] nx_vertices =new Vertice[n_vertice_nivel];
                for(int j=0;j<n_vertice_nivel;j++){
                    nome_v = "n"+i+"-"+"v"+j;
                    nx_vertices[j] = new Vertice(nome_v);
                }
                mapa.add(nx_vertices);
            }
        }
        nome_v = ""; 
        int  n_nivel=this.mapa.size();
        for(int a = this.mapa.size(); a>0;a--){
            n_vertice_nivel = this.mapa.get(a-1).length;
            Vertice[] nx_vertices = new Vertice[n_vertice_nivel];
            for(int b = 0;b<n_vertice_nivel;b++){
                nome_v = "n"+n_nivel+"_"+"v"+b;
                nx_vertices[b] = new Vertice(nome_v);
            }
            mapa.add(nx_vertices);
            n_nivel +=1;
        }
    }
    
    public void gerarCaminhos(){
    /*
     *Um caminho é um conjunto de arestas que ligam dois conjuntos de vertices(nivel)
    */
    int quant_caminhos = this.mapa.size()-1;
    int nivel, nivel_p, nivel_sorteado;
    Random dado = new Random();
    for(int a=0; a<quant_caminhos-1;a++){
        nivel=this.mapa.get(a).length;
        nivel_p=this.mapa.get(a+1).length;
        int [][] caminho_x = new int[nivel][nivel_p];
        
        for(int i = 0;i<nivel;i++){
            for(int j = 0;j<nivel_p;j++){
                /*condição para sortear se existe ou não aresta*/
                if (i==0){
                    nivel_sorteado = dado.nextInt(nivel_p);
                    caminho_x[i][nivel_sorteado]=1;
                    this.mapa.get(a)[i].saida=true;
                    this.mapa.get(a+1)[nivel_sorteado].entrada=true;
                }else if(dado.nextInt(2)==1 && this.mapa.get(a)[i].saida==false && this.mapa.get(a+1)[j].entrada==false){
                    caminho_x[i][j]=1;
                    this.mapa.get(a)[i].saida=true;
                    this.mapa.get(a+1)[j].entrada=true;
                }else{
                    caminho_x[i][j]=0;
                }
            }
        }
    }
    
    }
    
    public Grafo gerarGrafo(){
        for(int i=0;i<this.mapa.size();i++){
            for(int j=0; j<this.mapa.get(i).length;j++){
                this.g.addVertice(this.mapa.get(i)[j]);
            }
        }
        
        return this.g;
    }
    
    //função de teste para imprimir
    public void imprime(){
        int tam = this.mapa.size();
        String n = "";
        for(int i=0;i<tam;i++){
            n = "___n"+i +" ";
            for(Vertice v: this.mapa.get(i)){
                n += " "+v.getNome()+" ";
            }
        System.out.println(n);
        }
    }
    
}
