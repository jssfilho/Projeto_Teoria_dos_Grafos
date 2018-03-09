/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa_rpg;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jarde
 */
public class PercorrerGrafo extends Application{
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage palco) throws Exception {
        StackPane raiz = new StackPane();
        Label lblMensagem = new Label();
        
        lblMensagem.setText("Estou aprendendo javaFX!");
        raiz.getChildren().add(lblMensagem);
        
        Scene cena = new Scene(raiz, 250, 100);
        palco.setTitle("javaFX");
        palco.setScene(cena);
        palco.show();
    }
    
}
