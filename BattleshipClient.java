/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import userInterface.Player;
import userInterface.BattleshipUI;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BattleshipClient {
    
   private  Player players[] = new Player[2];
   BattleshipUI shipClass;
   
   private HitListener hitListener = new HitListener();
    
   //Store the array of Player objects and the instance of the BattleshipUI class
    public BattleshipClient(Player[] playerArray, BattleshipUI battleshipClass) {
        players = playerArray;
        shipClass = battleshipClass;
        
    }
    
    //Remove previous action listeners and replace them 
    public void play() {
        
        int i, j;
     
        for (i=0; i<10; i++) {
            
            for (j=0; j<10; j++) {

                players[0].buttonBoard[i][j].removeActionListener(players[0].boardListener);
                players[0].buttonBoard[i][j].putClientProperty("hit", Boolean.FALSE);
                players[1].buttonBoard[i][j].removeActionListener(players[1].boardListener);
                players[1].buttonBoard[i][j].addActionListener(hitListener);
                players[1].buttonBoard[i][j].putClientProperty("hit", Boolean.FALSE);
            }
        }
    }
    
    //Remove new action listeners after the game is over
    private void clearBoard() {
        
        int i, j;
     
        for (i=0; i<10; i++) 
            for (j=0; j<10; j++) 
                players[1].buttonBoard[i][j].removeActionListener(hitListener);
            
    }
    
    //Action listeners for enemy JButton
    private class HitListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            JButton btn = (JButton)e.getSource();
            int i, rowClick, columnClick;
            boolean shotFired = false;
            
            Random random = new Random();
            
            if (btn.getClientProperty("hit") == Boolean.TRUE) 
                JOptionPane.showMessageDialog(null, "You have already fired at this location. Choose another");
            
            //Execute if the player has not previously fired on a location
            else {
               
                btn.putClientProperty("hit", Boolean.TRUE);
                
                //Execute if a ship currently occupies this area
                if (btn.getClientProperty("filled") == Boolean.TRUE) {
                    btn.setBackground(Color.BLACK);
                    
                    //Reduce the numbers of hits left on the ship
                    if (btn.getClientProperty("ship") == "Carrier") {
                        i = players[1].carrier.getHitsLeft();
                        players[1].carrier.setHitsLeft(i-1);
                        
                        //If the ship is completely destroyed, notify the player
                        if (players[1].carrier.getHitsLeft() == 0) {
                            players[1].carrier.setShipSunk();
                            JOptionPane.showMessageDialog(null, "You have sunk the Carrier");
                        }
                    }
                    
                    if (btn.getClientProperty("ship") == "Battleship") {
                        i = players[1].battleShip.getHitsLeft();
                        players[1].battleShip.setHitsLeft(i-1);
                        
                        if (players[1].battleShip.getHitsLeft() == 0) {
                            players[1].battleShip.setShipSunk();
                            JOptionPane.showMessageDialog(null, "You have sunk the Battleship");
                        }
                    }
                     
                    if (btn.getClientProperty("ship") == "Patrol Boat") {
                        i = players[1].patrolBoat.getHitsLeft();
                        players[1].patrolBoat.setHitsLeft(i-1);
                        
                        if (players[1].patrolBoat.getHitsLeft() == 0) {
                            players[1].patrolBoat.setShipSunk();
                            JOptionPane.showMessageDialog(null, "You have sunk the Patrol Boat");
                        }
                    }
                      
                    if (btn.getClientProperty("ship") == "Submarine") {
                        i = players[1].submarine.getHitsLeft();
                        players[1].submarine.setHitsLeft(i-1);
                        
                        if (players[1].submarine.getHitsLeft() == 0) {
                            players[1].submarine.setShipSunk();
                            JOptionPane.showMessageDialog(null, "You have sunk the Submarine");
                        }
                    }
                       
                    if (btn.getClientProperty("ship") == "Destroyer") {
                        i = players[1].destroyer.getHitsLeft();
                        players[1].destroyer.setHitsLeft(i-1);
                        
                        if (players[1].destroyer.getHitsLeft() == 0) {
                            players[1].destroyer.setShipSunk();
                            JOptionPane.showMessageDialog(null, 
                                    "You have sunk the Destroyer");
                        }
                    }
                    
                    //Once all of Player 2's ships are sunk, announce it
                    if (players[1].carrier.isShipSunk() == true&&players[1].
                            battleShip.isShipSunk() == true&&players[1].patrolBoat.
                            isShipSunk() == true&&players[1].submarine.
                                    isShipSunk() == true&&players[1].destroyer.
                                            isShipSunk() == true) {
                        JOptionPane.showMessageDialog(null, "Player 1 Wins!");
                        clearBoard();
                        shipClass.gameOver("\nPlayer One Wins!");
                        return;
                    }
                }
                
                //Execute if the chosen area is empty
                else {
                    btn.setBackground(Color.BLUE);
                }
                
                //Repeat loop until a shot is succesfully made on a new area
                while (shotFired == false) {
                    
                    rowClick = random.nextInt(10);
                    columnClick = random.nextInt(10);
                    
                    if (players[0].buttonBoard[columnClick][rowClick].
                            getClientProperty("hit") == Boolean.FALSE) {
                        
                        //Execute if shot hits target
                        if (players[0].buttonBoard[columnClick][rowClick].
                                getClientProperty("filled") == Boolean.TRUE) {
                            
                            //Change the color of the JButton and store its hit
                            players[0].buttonBoard[columnClick][rowClick].
                                    setBackground(Color.BLACK);
                            players[0].buttonBoard[columnClick][rowClick].
                                    putClientProperty("hit", Boolean.TRUE);
                            
                            if (players[0].buttonBoard[columnClick][rowClick].
                                    getClientProperty("ship") == "Carrier") {
                                i = players[0].carrier.getHitsLeft();
                                players[0].carrier.setHitsLeft(i-1);
                                
                                //If ship is sunk, notify player
                                if (players[0].carrier.getHitsLeft() == 0) {
                                    players[0].carrier.setShipSunk();
                                    JOptionPane.showMessageDialog(null, 
                                            "Your Carrier has been sunk");
                                }
                            }
                    
                            if (players[0].buttonBoard[columnClick][rowClick].
                                    getClientProperty("ship") == "Battleship") {
                                i = players[0].battleShip.getHitsLeft();
                                players[0].battleShip.setHitsLeft(i-1);
                        
                                if (players[0].battleShip.getHitsLeft() == 0) {
                                    players[0].battleShip.setShipSunk();
                                    JOptionPane.showMessageDialog(null, 
                                            "Your Battleship has been sunk");
                                }
                            }
                     
                            if (players[0].buttonBoard[columnClick][rowClick].
                                    getClientProperty("ship") == "Patrol Boat") {
                                i = players[0].patrolBoat.getHitsLeft();
                                players[0].patrolBoat.setHitsLeft(i-1);
                        
                                if (players[0].patrolBoat.getHitsLeft() == 0) {
                                    players[0].patrolBoat.setShipSunk();
                                    JOptionPane.showMessageDialog(null, 
                                            "Your Patrol Boat has been sunk");
                                }
                            }
                      
                            if (players[0].buttonBoard[columnClick][rowClick].
                                    getClientProperty("ship") == "Submarine") {
                                i = players[0].submarine.getHitsLeft();
                                players[0].submarine.setHitsLeft(i-1);
                        
                                if (players[0].submarine.getHitsLeft() == 0) {
                                    players[0].submarine.setShipSunk();
                                    JOptionPane.showMessageDialog(null, 
                                            "Your Submarine has been sunk");
                                }
                            }
                       
                            if (players[0].buttonBoard[columnClick][rowClick].
                                    getClientProperty("ship") == "Destroyer") {
                                i = players[0].destroyer.getHitsLeft();
                                players[0].destroyer.setHitsLeft(i-1);
                        
                                if (players[0].destroyer.getHitsLeft() == 0) {
                                    players[0].destroyer.setShipSunk();
                                    JOptionPane.showMessageDialog(null, 
                                            "Your Destroyer has been sunk");
                                }
                            }
                            
                            //If Player 2 wins, notify player
                            if (players[0].carrier.isShipSunk() == true&&players[0].
                                    battleShip.isShipSunk() == true&&players[0].patrolBoat.
                            isShipSunk() == true&&players[0].submarine.isShipSunk() 
                                    == true&&players[0].destroyer.isShipSunk() == true) {
                                JOptionPane.showMessageDialog(null, "Player 2 Wins!");
                                shipClass.gameOver("\nPlayer Two Wins!");
                                clearBoard();
                            }
                        }  
                        
                        //Execute if chosen area is empty
                        else {
                            players[0].buttonBoard[columnClick][rowClick].
                                    setBackground(Color.BLUE);
                            players[0].buttonBoard[columnClick][rowClick].
                                    putClientProperty("hit", Boolean.TRUE);
                        }
                        
                        shotFired = true;
                        
                    }
                }
            }
            
        }
    
    }

}

