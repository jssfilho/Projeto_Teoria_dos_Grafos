
package mapa_rpg;
import java.util.ArrayList;
/**
 * @author JoÃo
 */
public class Grafo {
   private String nome;
   public ArrayList<Vertice> N = new ArrayList<Vertice>();
   public ArrayList<Aresta> A = new ArrayList<Aresta>();
   
   
   public Grafo(String nome){
       this.nome = nome;
   }
   /*Verifica se vertice1-vertice2 existe*/
   public boolean existeAresta(String vertice1, String vertice2){
       for(Aresta a: A){
           if(a.getVertices_ligado()[0].getNome().equals(vertice1) && a.getVertices_ligado()[0].getNome().equals(vertice2))
               return true;
       }
       return false;
   }
   /*verifica se o nome do vertice está de acordo, se sim, adiciona, se não, retorna false*/
   public boolean adicionarVertice(String nome){
       if (nome.contains(" ") || nome.contains("-")){
           System.out.println("Vertice já existe!");
           return false;
       }
       for(Vertice v: N){
           if(v.getNome().equals(nome)){
               System.out.println("Vertice já existe!");
               return false;
           }else{
               Vertice vertice = new Vertice(nome);
               
               this.N.add(vertice);
           }
       }
       return true;
   }
    /*verifica se os vertices existem, se sim, adiciona, se não, retorna false*/
   public boolean adicionarAresta(String nome, String vertice1,String vertice2){
       int cont=0;
       Vertice v1 = null,v2 = null;
       
       for(Vertice v: this.N){
           if(v.getNome().equals(vertice1)){
               cont+=1;
               v1=v;
           }else if(v.getNome().equals(vertice2)){
               cont+=1;
               v2=v;
           }
        }
        for(Aresta a: A){
            if(a.getNome().equals(nome)){
                System.out.println("Aresta já existe!");
                return false;
            }
        }
        
        if(cont!=2){
            System.out.println("Vertices digitados não existem");
            return false;
        }
        else{
            Aresta aresta = new Aresta();
            aresta.setNome(nome);
            Vertice[] vertices = new Vertice[2];
            vertices[0]= v1;
            vertices[1]= v2;
            
        }
        return true;
   }

}
