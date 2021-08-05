 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.BorderLayout;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;
import java.awt.Color;
import javafx.geometry.Insets;
import java.util.Random;
import java.util.Set;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author antho
 */
public class BattleShip extends Application {
    
    private static final int MAXSHIPS = 14;
    private static final int GRIDSIZE  = 16;
    private GridPane pnlPlayer = new GridPane();
    private FlowPane pnlControls = new FlowPane();
    private Label[][] lblPlayer = new Label[GRIDSIZE][GRIDSIZE];
    private Label[][] lblHidden = new Label[GRIDSIZE][GRIDSIZE];
    private Image[] imgShips = new Image[10];
    private Ship[] shipInfo = new Ship[8];
    private char[][] ocean = new char[16][16];   
   
    private Button resetBtn = new Button("Reset");
    private Button showAllBtn = new Button("Show All");
    private Label misses = new Label("Misses: ");
    private int numMisses = 0;
    private Label lblMisses = new Label("0");
    
    @Override 
    public void start(Stage primaryStage) {
        
        resetBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                initOcean();
                createPlayerPanel();
                createShips();
                placeShips();
                numMisses = 0;
                lblMisses.setText("" + numMisses);
                
              }   
            });
        
        showAllBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

           for(int row = 0; row < GRIDSIZE; row++)
            {
                for(int col = 0; col < GRIDSIZE; col++)
                {
                    pnlPlayer.add(lblPlayer[row][col], col, row);
                    lblPlayer[row][col].setVisible(true);
                }
            }
                
           }   
         });

        
        BorderPane root = new BorderPane();
                
        Scene scene = new Scene(root, 290, 380);
        
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.initOcean();
        this.createPlayerPanel();
        createShips();
        root.setCenter(pnlPlayer); 
        pnlControls.getChildren().addAll(resetBtn, showAllBtn, misses,lblMisses);
        root.setBottom(pnlControls);
        
        placeShips();
        
    }
 
    private void addGridEvent() {
        pnlPlayer.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                       
                        //This makes it clickable
                        Node source = (Node)event.getSource();
                        Integer colIndex = pnlPlayer.getRowIndex(source);
                        Integer rowIndex = pnlPlayer.getColumnIndex(source);
                        //System.out.println("Col: " + colIndex);
                        //System.out.println("Row: " + rowIndex);
                        //This makes it clickable
                        
                        //System.out.println(ocean[colIndex][rowIndex]);

                        if(ocean[colIndex][rowIndex] == 'O'){
                            lblPlayer[colIndex][rowIndex].setVisible(true);
                            Image imgMiss = new Image("file:Images\\batt102.gif");
                            lblHidden[colIndex][rowIndex].setGraphic(new ImageView(imgMiss));
                            lblPlayer[colIndex][rowIndex].setGraphic(new ImageView(imgMiss));
                            numMisses++;
                            lblMisses.setText("" + numMisses);
                         }else{
                            Image imgHit = new Image("file:Images\\batt103.gif");
                            lblHidden[colIndex][rowIndex].setGraphic(new ImageView(imgHit));
                            lblPlayer[colIndex][rowIndex].setGraphic(new ImageView(imgHit));
                            
                        }

                    }

                }
            });

        });
    }
    
    
    private void createPlayerPanel()
    {
       pnlPlayer.setStyle("-fx-background-color:#0000FF;");
       for(int row = 0; row < GRIDSIZE; row++)
       {
           for(int col = 0; col < GRIDSIZE; col++)
           {
               lblPlayer[row][col] = new Label();
               lblHidden[row][col] = new Label();
               Image imgShip = new Image("file:Images\\batt100.gif");
               lblPlayer[row][col].setGraphic(new ImageView(imgShip));
               lblHidden[row][col].setGraphic(new ImageView(imgShip));
               lblPlayer[row][col].setMaxSize(16.0, 16.0);
               lblHidden[row][col].setMaxSize(16.0, 16.0);
               lblPlayer[row][col].setStyle("-fx-border-width:1;-fx-border-color:black;");
               lblHidden[row][col].setStyle("-fx-border-width:1;-fx-border-color:black;");
               pnlPlayer.add(lblHidden[row][col], col, row);
               addGridEvent();
           }
       }
      
    }
    private void createShips()
    {
        this.loadShipImages();
        this.createShipInfo();
    }
    private void loadShipImages()
    {
        for(int i = 0; i < 10 ; i++)
        {
            imgShips[i] = new Image("file:Images\\batt" + (i + 1) + ".gif");
        }
    }
    private void createShipInfo()
    {
          //Creating ships using inheritance
                shipInfo[0] = new Frigate('H');
		
                shipInfo[1] = new Frigate('V');
		
                shipInfo[2] = new Minesweep('H');
		
                shipInfo[3] = new Minesweep('V');
		
                shipInfo[4] = new Cruiser('H');
		
                shipInfo[5] = new Cruiser('V');
		
                shipInfo[6] = new battle_ship('H');
		
                shipInfo[7] = new battle_ship('V');
    }
    private void initOcean()
    {
        for(int row = 0; row < 16; row++)
        {
            for(int col = 0; col < 16; col++)
            {
                    ocean[row][col] = 'O';
            }
        }
    }
    private void placeShips()
    {
       // Create a Random object to select ships
        Random r = new Random();

        // Create random objects to place the ship at a row and a column
        Random randCol = new Random();
        Random randRow = new Random();

        //Place the ships, typically there are 14
        for(int ships = 0; ships < MAXSHIPS; ships++)
        {
                //Get a random ship
                Ship si = shipInfo[r.nextInt(8)];

                int row = randRow.nextInt(16);
                int col = randCol.nextInt(16);
                int direction = checkDirection(si, row, col);
                while(direction == 0) // 0 direction says that we can not place the ship
                {
                        row = randRow.nextInt(16);
                        col = randCol.nextInt(16);
                        direction = checkDirection(si, row, col);
                }
                // got a clear path, let put the ship on the ocean
                int shipPieces[] = si.getShipPieces();
                if(si.Direction == 'H')  // place horizontal
                {
                        if(direction == 1)
                        {
                            for(int i = col, j = 0; i < col + si.length(); i++, j++)
                            {                                                          
                                lblPlayer[row][i].setGraphic(new ImageView(imgShips[shipPieces[j]]));
                                //lblPlayer[row][i].setVisible(false);
                                String name = si.getName();
                                ocean[row][i] = name.charAt(0);
                            }
                        }
                        else
                        {
                            for(int i = col + si.length(), j = 0 ; i > col; i--, j++)
                            {
                                lblPlayer[row][i].setGraphic(new ImageView(imgShips[shipPieces[j]]));
                                //lblPlayer[row][i].setVisible(false);
                                String name = si.getName();
                                ocean[row][i] = name.charAt(0);
                            }
                        }
                }
                else // Must be vertical direction
                {
                        if(direction == 1) // place pieces in positive direction
                        {
                            for(int i = row, j = 0; i < row + si.length(); i++, j++)
                            {
                                lblPlayer[i][col].setGraphic(new ImageView(imgShips[shipPieces[j]]));
                                //lblPlayer[row][i].setVisible(false);
                                String name = si.getName();
                                ocean[i][col] = name.charAt(0);
                            }
                        }
                        else
                        {
                                for(int i = row + si.length(), j = 0; i > row; i--, j++)
                                {
                                    lblPlayer[i][col].setGraphic(new ImageView(imgShips[shipPieces[j]]));
                                    //lblPlayer[row][i].setVisible(false);
                                    String name = si.getName();
                                    ocean[i][col] = name.charAt(0);
                                }
                        }
                }			
        } 
    }
    private int checkDirection(Ship si, int row, int col)
    {
        if(si.Direction == 'H')
            return checkHorizontal(si, row, col);
        else
            return checkVertical(si, row, col);
    }
    int checkHorizontal(Ship si,int row, int col)
    {
            boolean clearPath = true;

            int len = si.length();
            System.out.println(len);
            for(int i = col; i < (col + si.length()); i++)
            {
                    if(i >= GRIDSIZE) //This would put us outside the ocean
                    {
                            clearPath = false;
                            break;
                    }
                    if(ocean[row][i] != 'O') // Ship already exists in this spot
                    {
                            clearPath = false;
                            break;
                    }
            }
            if(clearPath == true) // ok to move in the positive direction
                    return 1; 

            //Next Chec the negative direction
            for(int i = col; i > (col - si.length()); i--)
            {
                    if(i < 0) //This would put us outside the ocean
                    {
                            clearPath = false;
                            break;
                    }
                    if(ocean[row][i] != 'O') // Ship already exists in this spot
                    {
                            clearPath = false;
                            break;
                    }			

            }
            if(clearPath == true) //Ok to move in negative direction
                    return -1;
            else
                    return 0;   // No place to move			

    }
	
    int checkVertical(Ship si,int row, int col)
    {
            boolean clearPath = true;
            int len = si.length();
            System.out.println(len);

            for(int i = row; i < (row + si.length()); i++)
            {
                if(i >= GRIDSIZE) //This would put us outside the ocean
                {
                        clearPath = false;
                        break;
                }
                if(ocean[i][col] != 'O') // Ship already exists in this spot
                {
                        clearPath = false;
                        break;
                }
            }
            if(clearPath == true) // ok to move in the positive direction
                    return 1; 

            //Next Check the negative direction
            for(int i = row; i > (row - si.length() ); i--)
            {
                if(i < 0) //This would put us outside the ocean
                {
                        clearPath = false;
                        break;
                }
                if(ocean[i][col] != 'O') // Ship already exists in this spot
                {
                        clearPath = false;
                        break;
                }			

            }
            if(clearPath == true) //Ok to move in negative direction
                return -1;
            else
                return 0;   // No place to move			

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
