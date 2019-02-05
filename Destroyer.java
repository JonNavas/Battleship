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
public class Destroyer implements IShip {
    
    private final static int DESTROYER_LENGTH = 3;
    private int shipLength;
    private int maxHits;
    private String shipName;
    private int hitsLeft;
    private boolean shipSunk = false;
    
    public boolean isShipPlaced() {
        return true;
    }
    
    public void setShipLocation(int row, int column) {
        
    }
    
    public int getShipLength() {
        return this.shipLength;
    }
    
    public void setShipLength(int inLength) {
        this.shipLength = inLength;
    }
    
    public boolean isShipSunk() {
        return this.shipSunk;
    }
    
    public void setShipSunk() {
        this.shipSunk = true;
    }
    
    public String getShipName() {
        return this.shipName;
    }
    
    public void setShipName(String inName) {
        this.shipName = inName;
    }
    
    public int getHitsLeft() {
        return this.hitsLeft;
    }
    
    public int getMaxHits() {
        return this.maxHits;
    }
    
    public void setMaxHits(int inHits) {
        this.maxHits = inHits;
    }
    
    public void setHitsLeft(int inHits) {
        this.hitsLeft = inHits;
    }
    
    public int getShipDirection() {
        return 0;
    }
    
    public void setShipDirection(int inDirection) {
        
    }
    
    //Set the values of unique class members
    public Destroyer() {
        
        setShipLength(DESTROYER_LENGTH);
        setShipName("Destroyer");
        setMaxHits(DESTROYER_LENGTH);
        setHitsLeft(DESTROYER_LENGTH);
    }
}
