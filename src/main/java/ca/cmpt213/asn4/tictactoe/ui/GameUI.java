package ca.cmpt213.asn4.tictactoe.ui;
    
import java.util.Optional;

import ca.cmpt213.asn4.tictactoe.game.GameLogic;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameUI extends Application {
    private static final double BUTTON_PADDING = 20;
    private static final int SIZE = 4;
    private Button[][] buttons = new Button[SIZE][SIZE];
    private static Scene scene;
    private GameLogic gameLogic;
    private Image imgX; 
    private Image imgO; 
    private Image imgBack; 
    private Text t;
    
    @Override
    public void start(Stage stage) {

        stage.setMinWidth(470);
        stage.setMinHeight(650);
        stage.setMaxWidth(470);
        stage.setMaxHeight(650);
        stage.setTitle("Tic Tac Toe");

        imgX = new Image(getClass().getResourceAsStream("UI_res/FrontX1.png"));
        imgO = new Image(getClass().getResourceAsStream("UI_res/FrontO1.png"));
        imgBack = new Image(getClass().getResourceAsStream("UI_res/back.png"));
        gameLogic = new GameLogic();
        t = new Text();
        t.getStyleClass().addAll("pane");

        BorderPane bp = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);
        
        String mover = gameLogic.isXTurn() ? "X's turn" : "O's turn";
        t.setText(mover);
        
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {

                  ImageView viewBack = new ImageView(imgBack);
                viewBack.setFitHeight(80);
                viewBack.setPreserveRatio(true);

                Button button = new Button();
                button.setGraphic(viewBack);
                // EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
                //     public void handle(ActionEvent e) 
                //     { 
                //         button.setGraphic(viewFront);
                //     } 
                // }; 
                // button.setOnAction(event); 
                int finalR = r;
                int finalC = c;
                button.setOnAction(event -> handleButtonClick(finalR , finalC, button));
                buttons[r][c] = button;
                grid.add(button, c, r);
                
            }
        }
        scene = new Scene(bp);
        scene.getStylesheets().add( getClass().getResource( "asn4.css" ).toExternalForm() );
        bp.getStyleClass().addAll("pane");
    
        // Text t = new Text("Tic Tac Toe");
        // t.getStyleClass().addAll("pane");

        Button NewGameBut = new Button();
        NewGameBut.setText("New Game");
        NewGameBut.setOnAction(event -> resetGame());

     //   grid.add(NewGameBut, 0, 0);

        // ScrollPane scrollPane = new ScrollPane(grid);
        HBox hb = new HBox(NewGameBut);
        hb.setAlignment(Pos.TOP_CENTER);
        HBox hbTurn = new HBox(t);
        hbTurn.setAlignment(Pos.TOP_CENTER);

        bp.setTop(t);
        bp.setCenter(grid);
        bp.setBottom(hb);
        // bp.setTop(t);

        stage.setScene(scene);
        stage.show();
    }
    private void handleButtonClick(int row, int col, Button button) {
        if (gameLogic.makeMove(row, col)) {
            Image currentPlayerImage = gameLogic.isXTurn() ? imgO : imgX; // Next player's turn
            String mover = gameLogic.isXTurn() ? "X's turn" : "O's turn";
            t.setText(mover);
            ImageView view = new ImageView(currentPlayerImage);
            view.setFitHeight(80); 
            view.setPreserveRatio(true);
            button.setGraphic(view);

            if (gameLogic.checkForWin()) {
                String winner = gameLogic.isXTurn() ? "O" : "X";

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.getDialogPane().setStyle("-fx-font-family: Verdana;");
                alert.setTitle("Game Over");
                alert.setHeaderText(null);
                alert.setContentText("Player " + winner + " wins!");
            
                alert.showAndWait();
                resetGame();
                
        
            } else if (gameLogic.isDraw()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.getDialogPane().setStyle("-fx-font-family: Verdana;");
                alert.setTitle("Game Over");
                alert.setHeaderText(null);
                alert.setContentText("It's a draw!");
            
                alert.showAndWait();
                resetGame();
            }
        }
    }

    private void resetGame() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Button button = buttons[r][c];
                ImageView viewBack = new ImageView(imgBack);
                viewBack.setFitHeight(80); // Set the size as per your requirement
                viewBack.setPreserveRatio(true);
                button.setGraphic(viewBack);
                String mover = "X's turn" ;
                t.setText(mover);
            }
        }
        gameLogic.resetGame();
    }
}