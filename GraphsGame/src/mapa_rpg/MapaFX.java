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
import javafx.scene.control.Spinner;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * @author JoÃo
 */
public class MapaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //botão
        Button btn = new Button();
        btn.setText("Gerar Mapa");
        btn.setTranslateX(50);
        btn.setTranslateY(25);
        //seletor
        Spinner seletor = new Spinner(1,4,1);
        seletor.setTranslateX(25);
        seletor.setTranslateY(70);
        //texto do seletor
        Text mTexto = new Text("N° minino:");
        mTexto.setTranslateX(25);
        mTexto.setTranslateY(65);
        mTexto.setFill(Color.WHITE);
        
        Group componentes1 = new Group();
        componentes1.getChildren().addAll(btn,seletor,mTexto);
        Scene cenaBtn = new Scene(componentes1, 200, 100);
        cenaBtn.setFill(Color.BLACK);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(Grafo(btn,seletor));
            }
        });
        
        
        primaryStage.setTitle("teste 1");
        primaryStage.setScene(cenaBtn);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch();
    }
    
    public Scene Grafo(Button btn, Spinner minimo){
        int min = (int) minimo.getValue();
        GeradorMapa mapaJogo = new GeradorMapa(min);
        mapaJogo.gerarCaminhos();
        Group componentes = new Group(); 
        componentes.getChildren().addAll(btn, minimo);
        int n = mapaJogo.mapa.size();
        int n_maior = mapaJogo.maiorNumeroVertice;
        int largura, altura, v;
        int lastX = 110, lastY = 110;
        
        altura = n_maior*20 + (n_maior-1)*60+lastX+lastY;
        
        largura = 20+ (n-1)*100 + lastX+lastY;
        
     
        for(int cont= 0;cont<n;cont++){
            
            v = mapaJogo.mapa.get(cont).length;
            if (v==1){
              lastY = altura/2;
            }else {
              lastY = (altura/2)-((v-1)*60)/2;
            }
            for(int i = 0; i<v;i++){
                Vertice vertice = mapaJogo.mapa.get(cont)[i];
                if (v==1){
                    Circle verticeX = new Circle(10);
                    verticeX.setTranslateX(lastX);
                    verticeX.setTranslateY(lastY);
                    verticeX.setFill(Color.BROWN);
                    Text text = new Text(vertice.getNome());
                    text.relocate(lastX, lastY);
                    componentes.getChildren().addAll(verticeX, text);
                    mapaJogo.mapa.get(cont)[i].v=verticeX;
                }else{
                    Circle verticeX = new Circle(10);
                    verticeX.setTranslateX(lastX);
                    verticeX.setTranslateY(lastY);
                    verticeX.setFill(Color.BROWN);
                    Text text = new Text(vertice.getNome());
                    text.relocate(lastX, lastY);
                    componentes.getChildren().addAll(verticeX, text);
                    mapaJogo.mapa.get(cont)[i].v=verticeX;
                }  
                lastY+=60;
            }
            lastX+=100;
        }
        
        int n_caminhos = mapaJogo.caminhos.size();
        for(int w=0;w<n_caminhos;w++){
            for(int i=0;i<mapaJogo.caminhos.get(w).length;i++){
                for(int j=0;j<mapaJogo.caminhos.get(w)[i].length;j++){
                    if (mapaJogo.caminhos.get(w)[i][j]==1){
                        Line arestaX = new Line();
                        arestaX.setStartX(mapaJogo.mapa.get(w)[i].v.getTranslateX()+10);
                        arestaX.setStartY(mapaJogo.mapa.get(w)[i].v.getTranslateY());
                        arestaX.setEndX(mapaJogo.mapa.get(w+1)[j].v.getTranslateX()-10);
                        arestaX.setEndY(mapaJogo.mapa.get(w+1)[j].v.getTranslateY());
                        arestaX.setFill(Color.CHOCOLATE);
                        componentes.getChildren().add(arestaX);
                    }
                }
            }
        }
        
        Scene cena = new Scene(componentes, largura, altura);
        cena.setFill(Color.LIGHTBLUE);
        return cena;
    }
}
