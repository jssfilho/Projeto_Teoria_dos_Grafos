
package mapa_rpg;

import java.awt.Image;

/**
 * @author JoÃo
 */
public class Jogador {
    private String nome;
    private Image foto;
    private int poder;
    private int habilidade;
    private int resistencia;
    private int armadura;
    private String classe;
    public Vertice posicao;
    /*array de skills*/
    public Jogador(String nome,Image foto,int poder, int habilidade, int resistencia, int armadura, String classe){
        this.nome=nome;
        this.foto=foto;
        this.poder=poder;
        this.habilidade=habilidade;
        this.resistencia=resistencia;
        this.armadura=armadura;
        this.classe=classe;
    }
    
    public void movimento(){
        
    }
    
}
