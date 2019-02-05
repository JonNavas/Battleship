/*
 Jonathan Navas
 */
package battleshipgame;

import userInterface.BattleshipUI;

public class BattleshipGame {
    
    public static void main(String[] args) {
       //Create a new instance of BattleshipUI()
        BattleshipUI battleShip = new BattleshipUI();
        
        //Call the BattleshipUI() method to create the JFrame
        battleShip.BattleshipUI(battleShip);
    }
    
}
