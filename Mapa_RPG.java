/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa_rpg;

/**
 * @author JoÃo
 */
public class Mapa_RPG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //abri janela de login
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
        */
        GeradorMapa gMapa = new GeradorMapa();
        gMapa.gerarGrafo();
        gMapa.gerarCaminhos();
    }
}
    

