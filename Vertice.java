
package mapa_rpg;
/**
 * @author JoÃo
 */
public class Vertice {
     private String nome;
     private String cor;

    public Vertice(String nome){
        this.nome=nome;
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
