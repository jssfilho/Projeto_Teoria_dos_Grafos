
package mapa_rpg;

import javafx.scene.shape.Line;

/**
 * @author JoÃo
 */
public class Aresta {
    
    private String nome;
    private float peso;
    private Vertice[] vertices_ligado = new Vertice[2];
    public Line a;
    
    public Aresta(){
        
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public String getNome() {
        return nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Vertice[] getVertices_ligado() {
        return vertices_ligado;
    }
    public void setVertices_ligado(Vertice[] vertices){
        this.vertices_ligado=vertices;
    }
    
}
