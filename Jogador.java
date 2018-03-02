
package mapa_rpg;



/**
 * @author JoÃo
 */
public class Jogador {
    private String nome;
    private int poder;
    private int habilidade;
    private int resistencia;
    private int armadura;
    private String classe;
    public Vertice posicao;
    /*array de skills*/
    public Jogador(String nome,int poder, int habilidade, int resistencia, int armadura, String classe){
        this.nome=nome;
        this.poder=poder;
        this.habilidade=habilidade;
        this.resistencia=resistencia;
        this.armadura=armadura;
        this.classe=classe;
    }
    
    public void movimento(){
        
    }
    
}
