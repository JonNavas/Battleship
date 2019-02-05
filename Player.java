/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.Battleship;
import core.Carrier;
import core.PatrolBoat;
import core.Destroyer;
import core.Submarine;
import javax.swing.JOptionPane;

import java.util.Random;

public class Player {
    
    //Create objects corresponding to the current player's ship
    private Color shipColor;
    private String userName;
    private boolean isFirst;
    private int currentShip;
    private int currentShipLength;
    private int currentDirection;
    
    //Create objects to check whether a ship has been previously placed
    private boolean carrierWasPlaced = false;
    private boolean battleshipWasPlaced = false;
    private boolean patrolBoatWasPlaced = false;
    private boolean submarineWasPlaced = false;
    private boolean destroyerWasPlaced = false;
    
    //Create objects to record previous ship placement diretions
    private int carrierDirection;
    private int battleshipDirection;
    private int patrolBoatDirection;
    private int submarineDirection;
    private int destroyerDirection;
    
    //Create objects to record previous ship coordinates
    private int[] carrierCoordinates = new int[2];
    private int[] battleshipCoordinates = new int[2];
    private int[] patrolBoatCoordinates = new int[2];
    private int[] submarineCoordinates = new int[2];
    private int[] destroyerCoordinates = new int[2];
    
    //Create JButton 2D array
    public JButton[][] buttonBoard;
    
    public JButton deploy;
    
    private final static int rows = 10;
    private final static int cols = 10;
    
    //Create ship objects
    public Carrier carrier;
    public Battleship battleShip;
    public PatrolBoat patrolBoat;
    public Submarine submarine;
    public Destroyer destroyer;
    
    public BoardListener boardListener;
    
    private int playMode;
    
    //Call the init components method and return the button array
    public JButton[][] player(String s) {
        
        initObjects();
        
        initComponents();
        
        return getBoard();
    }
    
    //Initialize the ship objects and action listener
    private void initObjects() {
      
        battleShip = new Battleship();
        carrier = new Carrier();
        destroyer = new Destroyer();
        patrolBoat = new PatrolBoat();
        submarine = new Submarine();
        boardListener = new BoardListener();
    }
    
    //Initialize and fill the JButton 2D array, and add client properties
    private void initComponents() {
        
        buttonBoard = new JButton[cols][rows];
        int i, j;
        
        for (i=0;i<cols; i++) {
            for(j=0; j<rows; j++)  {
                buttonBoard[i][j] = new JButton();
                buttonBoard[i][j].addActionListener(boardListener);
                buttonBoard[i][j].putClientProperty("col", i);
                buttonBoard[i][j].putClientProperty("row", j);
                buttonBoard[i][j].putClientProperty("filled", Boolean.FALSE);
            }
        }
        
    }
    
    //Check if the selected ship will fit in the selected area
    private boolean willFit(int col, int row, int shipLength, boolean isVert) {
        
        int i;
         
        if (isVert == false) {
            
            for (i=0; i<shipLength; i++) {
                if (col+i>cols-1||row>rows-1||buttonBoard[col+i][row].
                        getClientProperty("filled") == Boolean.TRUE) 
                    return false;
            }
            
            //If the selected buttons are not filled or null, return true
            return true;
        }
        
        else {
            for (i=0; i<shipLength; i++) {
                if (col>cols-1||row+i>rows-1||buttonBoard[col][row+i].
                        getClientProperty("filled") == Boolean.TRUE) 
                    return false;    
            }
            
            return true;
        }
    }
    
    //Check whether the currentShip has already been placed
    private boolean isPlaced() {
        
        if (currentShip == 1) {
            if (carrierWasPlaced == false)
                return false;
            
            return true;
        }
        
        else if (currentShip == 2) {
            if (battleshipWasPlaced == false)
                return false;
            
            return true;
        }
        
        else if (currentShip == 3) {
            if (patrolBoatWasPlaced == false)
                return false;
            
            return true;
        }
        
        else if (currentShip == 4) {
            if (submarineWasPlaced == false)
                return false;
            
            return true;
        }
        
        else {
            if (destroyerWasPlaced == false)
                return false;
            
            return true;
        }
    }
    
    //Erase the currentShip's previous placement
    private void eraseButtons() {
        
        int i, j;
        //Carry out for carrier       
        if (currentShip == 1) {
            //Carry out if direction is horizontal
            if (carrierDirection == 0) {    
                for (i=0; i<currentShipLength; i++) {
                    //Set the jbutton to no longer be filled
                    buttonBoard[carrierCoordinates[0]+i][carrierCoordinates[1]].
                        putClientProperty("filled", Boolean.FALSE);
                    //Return the jbutton to its default color
                    buttonBoard[carrierCoordinates[0]+i][carrierCoordinates[1]].
                        setBackground(new JButton().getBackground());
                    
                }
            }
            //Carry out if direction is vertical
            else {
                for (i=0; i<currentShipLength; i++) {
                    
                    buttonBoard[carrierCoordinates[0]][carrierCoordinates[1]+i].
                        putClientProperty("filled", Boolean.FALSE);
                    buttonBoard[carrierCoordinates[0]][carrierCoordinates[1]+i].
                        setBackground(new JButton().getBackground());
                    
                }
            }
        }
        //Carry out for battleship
        else if (currentShip == 2) {
            if (battleshipDirection == 0) {    
                for (i=0; i<currentShipLength; i++) {
                    
                    buttonBoard[battleshipCoordinates[0]+i][battleshipCoordinates[1]].
                        putClientProperty("filled", Boolean.FALSE);
                    buttonBoard[battleshipCoordinates[0]+i][battleshipCoordinates[1]].
                        setBackground(new JButton().getBackground());
                }
            }
            
            else {
                for (i=0; i<currentShipLength; i++) {
                    
                    buttonBoard[battleshipCoordinates[0]][battleshipCoordinates[1]+i].
                        putClientProperty("filled", Boolean.FALSE);
                    buttonBoard[battleshipCoordinates[0]][battleshipCoordinates[1]+i].
                        setBackground(new JButton().getBackground());
                    
                }
            }
        }
        //Carry out for patrol boat
        else if (currentShip == 3) {
            if (patrolBoatDirection == 0) {    
                for (i=0; i<currentShipLength; i++) {
                    
                    buttonBoard[patrolBoatCoordinates[0]+i][patrolBoatCoordinates[1]].
                        putClientProperty("filled", Boolean.FALSE);
                    buttonBoard[patrolBoatCoordinates[0]+i][patrolBoatCoordinates[1]].
                        setBackground(new JButton().getBackground());
                }
            }
            
            else {
                for (i=0; i<currentShipLength; i++) {
                    
                    buttonBoard[patrolBoatCoordinates[0]][patrolBoatCoordinates[1]+i].
                        putClientProperty("filled", Boolean.FALSE);
                    buttonBoard[patrolBoatCoordinates[0]][patrolBoatCoordinates[1]+i].
                        setBackground(new JButton().getBackground());
                    
                }
            }
        }
        //Carry out for submarine
        else if (currentShip == 4) {
            if (submarineDirection == 0) {    
                for (i=0; i<currentShipLength; i++) {
                    
                    buttonBoard[submarineCoordinates[0]+i][submarineCoordinates[1]].
                        putClientProperty("filled", Boolean.FALSE);
                    buttonBoard[submarineCoordinates[0]+i][submarineCoordinates[1]].
                        setBackground(new JButton().getBackground());
                }
            }
            
            else {
                for (i=0; i<currentShipLength; i++) {
                    
                    buttonBoard[submarineCoordinates[0]][submarineCoordinates[1]+i].
                        putClientProperty("filled", Boolean.FALSE);
                    buttonBoard[submarineCoordinates[0]][submarineCoordinates[1]+i].
                        setBackground(new JButton().getBackground());
                    
                }
            }
        }
        //Carry out for destroyer
        else {
            if (destroyerDirection == 0) {    
                for (i=0; i<currentShipLength; i++) {
                    
                    buttonBoard[destroyerCoordinates[0]+i][destroyerCoordinates[1]].
                        putClientProperty("filled", Boolean.FALSE);
                    buttonBoard[destroyerCoordinates[0]+i][destroyerCoordinates[1]].
                        setBackground(new JButton().getBackground());
                }
            }
            
            else {
                for (i=0; i<currentShipLength; i++) {
                    
                    buttonBoard[destroyerCoordinates[0]][destroyerCoordinates[1]+i].
                        putClientProperty("filled", Boolean.FALSE);
                    buttonBoard[destroyerCoordinates[0]][destroyerCoordinates[1]+i].
                        setBackground(new JButton().getBackground());
                    
                }
            }
        }
    }
    
    //Record the placement of the currentShip
    private void setPlaced(int col, int row) {
        
        //Record carrier coordinates and direction
        if (currentShip == 1) {
            carrier = new Carrier();
            carrierDirection = currentDirection;
            carrierWasPlaced = true;
            carrierCoordinates[0] = col;
            carrierCoordinates[1] = row;
        }
        
        //Record battleship coordinates and direction 
        else if (currentShip == 2) {
            battleShip = new Battleship();
            battleshipDirection = currentDirection;
            battleshipWasPlaced = true;
            battleshipCoordinates[0] = col;
            battleshipCoordinates[1] = row;
        }
        
        //Record patrol boat coordinates and direction
        else if (currentShip == 3) {
            patrolBoat = new PatrolBoat();
            patrolBoatDirection = currentDirection;
            patrolBoatWasPlaced = true;
            patrolBoatCoordinates[0] = col;
            patrolBoatCoordinates[1] = row;
        }
        
        //Record submarine coordinates and direction
        else if (currentShip == 4) {
            submarine = new Submarine();
            submarineDirection = currentDirection;
            submarineWasPlaced = true;
            submarineCoordinates[0] = col;
            submarineCoordinates[1] = row;
        }
        
        //Record destroyer coordinates and direction
        else {
            destroyer = new Destroyer();
            destroyerDirection = currentDirection;
            destroyerWasPlaced = true;
            destroyerCoordinates[0] = col;
            destroyerCoordinates[1] = row;
        }
        
    } 
    
    //If all ships have been placed, enable the deploy JButton
    private void setDeploy() {
        
        if (carrierWasPlaced == true&&battleshipWasPlaced == true&&patrolBoatWasPlaced
                == true&&submarineWasPlaced == true&&destroyerWasPlaced == true) 
            deploy.setEnabled(true);
        
    }
    
    //Return the JButton 2D array
    private JButton[][] getBoard() {
        
        return buttonBoard;
    }
    
    //Return a pointer to the isFirst variable
    public boolean getisFirst() {
        
        return this.isFirst;
    }
    
    //Return a pointer to the shipColor variable
    public Color getShipColor() {
        
        return this.shipColor;
    }
    
    //Return a pointer to the currentShip variable
    public int getcurrentShip() {
        
        return this.currentShip;
    }
    
    //Return a pointer to the currentShipLength variable
    public int getcurrentShipLength() {
        
        return this.currentShipLength;
    }    
    
    //Return a pointer to the currentDirection variable
    public int getcurrentDirection() {
        
        return this.currentDirection;
    }
    
    public int getPlayMode() {
        
        return this.playMode;
    }
    
    //Pass a value to the isFirst variable
    public void setisFirst(boolean value) {
        
        this.isFirst = value;
    }
    
    //Pass a value to the shioColor variable
    public void setshipColor(Color value) {
        
        this.shipColor = value;
    }
    
    //Pass a value to the currentShip variable
    public void setcurrentShip(int value) {
        
        this.currentShip = value;
        
        if (value ==1) 
             setcurrentShipLength(5);
            
        if (value == 2)
            setcurrentShipLength(4);
            
        else if (value == 3)
            setcurrentShipLength(2);
           
        else if (value == 4)
            setcurrentShipLength(3);
            
        else if (value == 5)
            setcurrentShipLength(3);
    }
    
    //Pass a value to the currentShipLength variable
    public void setcurrentShipLength(int value) {
        
        this.currentShipLength = value;
    }
    
    //Pass a value to the currentDirection variable
    public void setcurrentDirection(int value) {
        
        this.currentDirection = value;
    }
    
    public void setPlayMode(int mode) {
        
        playMode = mode;
    }
    
    //Layout the computer's ships randomly
    public void autoLayout() {
        
        boolean fit, isShipPlaced;
        int i, j, startRowClick, startColumnClick;
        
        Random random = new Random();
        
        //Go through setting a ship 5 times for each ship
        for (i = 1; i < 6; i++) {
            
            setcurrentShip(i);
            
            isShipPlaced = false;
            
            if (i == 1) 
                carrier = new Carrier();
            
            if (i == 2) 
                battleShip = new Battleship();
            
            if (i == 3) 
                patrolBoat = new PatrolBoat();
            
            if (i == 4) 
                submarine = new Submarine();
            
            if (i == 5) 
                destroyer = new Destroyer();
            
            //Repeat this process until a ship is succesfully placed
            while (isShipPlaced == false) {
            
                setcurrentDirection(random.nextInt(2));
        
                startRowClick = random.nextInt(10);
                startColumnClick = random.nextInt(10);
                
                //Execute if ship is horizontal
                if (currentDirection == 0) {
                
                    fit = willFit(startColumnClick, startRowClick, currentShipLength, false);
                    
                    //If the ship will fit, place it on the board
                    if (fit == true) {
                    
                        for (j=0; j<currentShipLength; j++) {
                        
                            buttonBoard[startColumnClick+j][startRowClick].
                                    putClientProperty("filled", Boolean.TRUE);                       
                            
                            //Store which ship is currently occupying the JButton
                            if ( i == 1)
                                buttonBoard[startColumnClick+j][startRowClick].
                                        putClientProperty("ship", "Carrier");
                            if (i == 2)
                                buttonBoard[startColumnClick+j][startRowClick].
                                        putClientProperty("ship", "Battleship");
                            if (i == 3)
                                buttonBoard[startColumnClick+j][startRowClick].
                                        putClientProperty("ship", "Patrol Boat");
                            if (i == 4)
                                buttonBoard[startColumnClick+j][startRowClick].
                                        putClientProperty("ship", "Submarine");
                            if (i == 5)
                                buttonBoard[startColumnClick+j][startRowClick].
                                        putClientProperty("ship", "Destroyer");
                        }
                    
                        isShipPlaced = true;
                    }
                }
                
                //Execute if ship is vertical
                else if (currentDirection == 1) {
                    
                    fit = willFit(startColumnClick, startRowClick, currentShipLength, true);
                
                    if (fit == true) {
                    
                        for (j=0; j<currentShipLength; j++) {
                        
                            buttonBoard[startColumnClick][startRowClick+j].
                                    putClientProperty("filled", Boolean.TRUE);
                             
                            if ( i == 1)
                                buttonBoard[startColumnClick][startRowClick+j].
                                        putClientProperty("ship", "Carrier");
                            if (i == 2)
                                buttonBoard[startColumnClick][startRowClick+j].
                                        putClientProperty("ship", "Battleship");
                            if (i == 3)
                                buttonBoard[startColumnClick][startRowClick+j].
                                        putClientProperty("ship", "Patrol Boat");
                            if (i == 4)
                                buttonBoard[startColumnClick][startRowClick+j].
                                        putClientProperty("ship", "Submarine");
                            if (i == 5)
                                buttonBoard[startColumnClick][startRowClick+j].
                                        putClientProperty("ship", "Destroyer");
                        }
                    
                        isShipPlaced = true;
                    }
                
                }
            
            }
        }
    }
    
    //Action listener that places ships on the board
    public class BoardListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            boolean fit, placed;
            int i;
            
            if (currentShip==0||shipColor==null)
                JOptionPane.showMessageDialog(null, "You must select a ship and color");
            
            else {
                //Get reference to the selected jbutton
                JButton btn = (JButton)e.getSource();
                
                //Carry out if the direction is horizontal
                if (currentDirection == 0) {
                
                    //Pass chosen ship to willFit method to check if it will fit
                    fit = willFit((int)btn.getClientProperty("col"),
                        (int)btn.getClientProperty("row"), currentShipLength, false);
                
                    //Display message stating that the ship will not fit
                    if (fit == false)
                        JOptionPane.showMessageDialog(null, "Selected ship will"
                            + " not fit in this location");
                
                    //If ship fits, insert it into the chosen area
                    else {
                        //Check if ship has been previously placed
                        placed = isPlaced();
                    
                        //If ship has been placed, erase its previous placement
                        if (placed == true)  
                            eraseButtons();
                    
                        //Record ship placement
                        setPlaced((int)btn.getClientProperty("col"),
                            (int)btn.getClientProperty("row"));
                    
                        for (i=0; i<currentShipLength; i++) {
                        
                            buttonBoard[(int)btn.getClientProperty("col")+i]
                            [(int)btn.getClientProperty("row")]
                                .putClientProperty("filled", Boolean.TRUE);
                        
                            buttonBoard[(int)btn.getClientProperty("col")+i]
                                [(int)btn.getClientProperty("row")].
                                    setBackground(shipColor);
                            
                            if (currentShip == 1) {
                                buttonBoard[(int)btn.getClientProperty("col")+i]
                                [(int)btn.getClientProperty("row")].
                                        putClientProperty("ship", "Carrier");
                            }
                             
                            if (currentShip == 2) {
                                buttonBoard[(int)btn.getClientProperty("col")+i]
                                [(int)btn.getClientProperty("row")].
                                        putClientProperty("ship", "Battleship");
                            }
                             
                            if (currentShip == 3) {
                                buttonBoard[(int)btn.getClientProperty("col")+i]
                                [(int)btn.getClientProperty("row")].
                                        putClientProperty("ship", "Patrol Boat");
                            }
                             
                            if (currentShip == 4) {
                                buttonBoard[(int)btn.getClientProperty("col")+i]
                                [(int)btn.getClientProperty("row")].
                                        putClientProperty("ship", "Submarine");
                            }
                             
                            if (currentShip == 5) {
                                buttonBoard[(int)btn.getClientProperty("col")+i]
                                [(int)btn.getClientProperty("row")].
                                        putClientProperty("ship", "Destroyer");
                            }
                        }
                        //Check if all ships have been placed
                        setDeploy();
                    }
                }
            
                //Carry out if the direction is vertical
                else if(currentDirection == 1) {
                
                    fit = willFit((int)btn.getClientProperty("col"),
                        (int)btn.getClientProperty("row"), currentShipLength, true);
                
                    if (fit == false)
                        JOptionPane.showMessageDialog(null, "Selected ship will"
                            + " not fit in this location");
                
                    else {
                        //Check if ship was previosuly placed
                        placed = isPlaced();
                    
                        //If ship has been placed, erase its previous placement 
                        if (placed == true) 
                            eraseButtons();
                    
                        //Record ship placement
                        setPlaced((int)btn.getClientProperty("col"),
                            (int)btn.getClientProperty("row"));
                        
                        //Set JButton colors and store which ship occupies the JButton
                        for (i=0; i<currentShipLength; i++) {
                        
                            buttonBoard[(int)btn.getClientProperty("col")]
                                [(int)btn.getClientProperty("row")+i]
                                .putClientProperty("filled", Boolean.TRUE);
                        
                            buttonBoard[(int)btn.getClientProperty("col")]
                                [(int)btn.getClientProperty("row")+i].setBackground(shipColor);
                            
                            if (currentShip == 1) {
                                buttonBoard[(int)btn.getClientProperty("col")]
                                [(int)btn.getClientProperty("row")+i].
                                        putClientProperty("ship", "Carrier");
                            }
                             
                            if (currentShip == 2) {
                                buttonBoard[(int)btn.getClientProperty("col")]
                                [(int)btn.getClientProperty("row")+i].
                                        putClientProperty("ship", "Battleship");
                            }
                             
                            if (currentShip == 3) {
                                buttonBoard[(int)btn.getClientProperty("col")]
                                [(int)btn.getClientProperty("row")+i].
                                        putClientProperty("ship", "Patrol Boat");
                            }
                             
                            if (currentShip == 4) {
                                buttonBoard[(int)btn.getClientProperty("col")]
                                [(int)btn.getClientProperty("row")+i].
                                        putClientProperty("ship", "Submarine");
                            }
                             
                            if (currentShip == 5) {
                                buttonBoard[(int)btn.getClientProperty("col")]
                                [(int)btn.getClientProperty("row")+i].
                                        putClientProperty("ship", "Destroyer");
                            }
                        } 
                        //Check if all ships have been placed
                        setDeploy();
                    }
                }
            }
                
        }
    }
    
}
