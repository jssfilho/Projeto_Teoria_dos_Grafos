
package mapa_rpg;

import javafx.scene.shape.Circle;

/**
 * @author JoÃo
 */
public class Vertice {
     private String nome;
     private String cor;
     public boolean entrada;
     public boolean saida;
     public Circle v;

    public Vertice(String nome){
        this.nome=nome;
        this.saida=false;
        this.entrada=false;
    }
     
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public String toString(){
        return this.nome;
    }

}
