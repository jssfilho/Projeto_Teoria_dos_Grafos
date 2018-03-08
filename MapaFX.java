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
 *
 * @author JoÃo
 */
public class MapaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Gerar Mapa");
        
        for 
            Circle circulo = new Circle(10);
            circulo.setTranslateX(100);
            circulo.setTranslateY(110);
            circulo.setFill(Color.GREEN);
        
       
        
        
        Line linha = new Line();
        linha.setStartX(110);
        linha.setStartY(110);
        linha.setEndX(150);
        linha.setEndY(110);
      
        
        Group componentes = new Group(); 
        componentes.getChildren().add(circulo);
        componentes.getChildren().add(linha);
        componentes.getChildren().add(circulo2);
        Scene cena = new Scene(componentes, 500, 500);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(cena);
            }
        });
        
     
        
        StackPane btnClicks = new StackPane();
        btnClicks.getChildren().add(btn);
        Scene scene = new Scene(btnClicks, 300, 250);
        
        primaryStage.setTitle("teste 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch();
    }
    
}
