/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author patty
 */
public class Ship implements IShip {
    
    private boolean shipPlaced;
    private boolean shipSunk;
    private int hitsLeft;
    private int maxHits;
    private int shipDirection;
    private int shipLength;
    String shipName;  

    public boolean isShipPlaced() {
        return true;
    }
    
    public void setShipLocation(int row, int column) {
        
    }
    
    public int getShipLength() {
        return shipLength;
    }
    
    public void setShipLength(int inLength) {
        shipLength = inLength;
    }
    
    public boolean isShipSunk() {
        return shipSunk;
    }
    
    public void setShipSunk() {
        shipSunk = true;
    }
    
    public String getShipName() {
        return shipName;
    }
    
    public void setShipName(String inName) {
        shipName = inName;
    }
    
    public int getHitsLeft() {
        return hitsLeft;
    }
    
    public int getMaxHits() {
        return maxHits;
    }
    
    public void setMaxHits(int inHits) {
        maxHits = inHits;
    }
    
    public void setHitsLeft(int inHits) {
        hitsLeft = inHits;
    }
    public int getShipDirection() {
        return 0;
    }
    
    public void setShipDirection(int inDirection) {
        
    }
}
