/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa_rpg;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Teste extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle( 100,100,50);
        circle.setStroke(Color.BLUE);
        circle.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.3));

        Rectangle rectangle = new Rectangle( 0,0,100,100);
        rectangle.relocate(200, 200);
        rectangle.setStroke(Color.GREEN);
        rectangle.setFill(Color.GREEN.deriveColor(1, 1, 1, 0.3));

        Text text = new Text( "Example Text");
        text.relocate(300, 300);

        Pane root = new Pane();
        root.getChildren().addAll(circle, rectangle, text);

        MouseGestures mouseGestures = new MouseGestures();
        mouseGestures.makeDraggable(circle);
        mouseGestures.makeDraggable(rectangle);
        mouseGestures.makeDraggable(text);

        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static class MouseGestures {

        class DragContext {
            double x;
            double y;
        }

        DragContext dragContext = new DragContext();

        public void makeDraggable( Node node) {

            node.setOnMousePressed( onMousePressedEventHandler);
            node.setOnMouseDragged( onMouseDraggedEventHandler);
            node.setOnMouseReleased( onMouseReleasedEventHandler);

        }

        EventHandler<MouseEvent> onMousePressedEventHandler = event -> {

            if( event.getSource() instanceof Circle) {

                Circle circle = (Circle) (event.getSource());

                dragContext.x = circle.getCenterX() - event.getSceneX();
                dragContext.y = circle.getCenterY() - event.getSceneY();

            } else {

                Node node = (Node) (event.getSource());

                dragContext.x = node.getTranslateX() - event.getSceneX();
                dragContext.y = node.getTranslateY() - event.getSceneY();

            }
        };

        EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {

            if( event.getSource() instanceof Circle) {

                Circle circle = (Circle) (event.getSource());

                circle.setCenterX( dragContext.x + event.getSceneX());
                circle.setCenterY( dragContext.y + event.getSceneY());

            } else {

                Node node = (Node) (event.getSource());

                node.setTranslateX( dragContext.x + event.getSceneX());
                node.setTranslateY( dragContext.y + event.getSceneY());

            }

        };

        EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {
        };

    }


    public static void main(String[] args) {
        launch(args);
    }
}
}
/*
 private final String IMG_URL = "http://www.oracle.com/ocom/groups/public/@otn/documents/digitalasset/402460.gif";

 public static void main(String[] args) {
  launch();
 }

 @Override
 public void start(Stage palco) throws Exception {
  Image imagem = new Image(IMG_URL); // 1
  ImageView visualizadorImagem = new ImageView(imagem); // 2
  visualizadorImagem.setTranslateX(80); // 3
  visualizadorImagem.setTranslateY(5); // 4

  Text textoInformativo = new Text(
    "Algumas figuras Geométricas desenhadas com JavaFX");
  textoInformativo.setTranslateX(30);
  textoInformativo.setTranslateY(70);

  Rectangle retangulo = new Rectangle(100, 40); // 5
  retangulo.setTranslateX(5);
  retangulo.setTranslateY(90);
  retangulo.setFill(Color.GREEN); // 6

  Circle circulo = new Circle(30);
  circulo.setTranslateX(160);
  circulo.setTranslateY(110);
  circulo.setFill(Color.YELLOW);

  Circle circuloBranco = new Circle(30);
  circuloBranco.setTranslateX(240);
  circuloBranco.setTranslateY(110);
  circuloBranco.setStroke(Color.BLACK); // 7
  circuloBranco.setStrokeWidth(3.0); // 8
  circuloBranco.setFill(Color.WHITE);

  Ellipse elipse = new Ellipse(30, 10);
  elipse.setTranslateX(320);
  elipse.setTranslateY(110);
  elipse.setFill(Color.BLUE);

  Group componentes = new Group(); // 9
  componentes.getChildren().addAll(visualizadorImagem, textoInformativo,
    retangulo, circulo, circuloBranco, elipse); // 10
  Scene cena = new Scene(componentes, 360, 150);
  palco.setTitle("Gráficos e Imagens em JavaFX");
  palco.setScene(cena);
  palco.show();
 }
}

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


 * @author JoÃo

public class Teste extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
*/