/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa_rpg;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @author JoÃo
 */
public class MapaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Gerar Mapa");
        
        GeradorMapa mapaJogo = new GeradorMapa();
        mapaJogo.gerarCaminhos();
        Group componentes = new Group(); 
        
        int n = mapaJogo.mapa.size();
        int n_maior = mapaJogo.maiorNumeroVertice;
        int largura, altura, v;
        int lastX = 110, lastY = 110;
        
        altura = n_maior*20 + (n_maior-1)*60+lastX+lastY;
        
        largura = n*20 + (n-1)*100 + lastX+lastY;
        
        
        int pInicial; //não está usando
        for(int cont= 0;cont<n;cont++){
            
            v = mapaJogo.mapa.get(cont).length;
            if (v==1){
              lastY = altura/2;
            }else {
              lastY = (altura/2)-((v-1)*60)/2;
            }
            for(int i = 0; i<v;i++){
                if (v==1){
                    Circle verticeX = new Circle(10);
                    verticeX.setTranslateX(lastX);
                    verticeX.setTranslateY(lastY);
                    verticeX.setFill(Color.BROWN);
                    componentes.getChildren().add(verticeX);
                }else{
                    Circle verticeX = new Circle(10);
                    verticeX.setTranslateX(lastX);
                    verticeX.setTranslateY(lastY);
                    verticeX.setFill(Color.BROWN);
                    componentes.getChildren().add(verticeX);
                }  
                lastY+=60;
            }
            lastX+=100;
        }
       
        
        
        
       
        
        Scene cena = new Scene(componentes, largura, altura);
        cena.setFill(Color.LIGHTBLUE);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(cena);
            }
        });
        
     
        
        StackPane btnClicks = new StackPane();
        btnClicks.getChildren().add(btn);
        Scene cenaBtn = new Scene(btnClicks, 300, 250);
        
        primaryStage.setTitle("teste 1");
        primaryStage.setScene(cenaBtn);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch();
    }
    
}
