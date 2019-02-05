/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import core.BattleshipClient;

/**
 *
 * @author patty
 */
public class BattleshipUI extends JFrame {
    //Create menu and items to be used in the menu
    private JMenuBar menuBar;   
    private JMenu gameMenu;
    private JMenu optionMenu;
    private JMenuItem playerPlayer;
    private JMenuItem playerComputer;
    private JMenuItem computerComputer;
    private JMenuItem exit;
    private JMenuItem game;
    private JMenuItem player;
    private JTextArea gameStatus;
    private JScrollPane scrollPane;
   
    private JButton deploy;
    
    //Create panels for the JFrame
    private JPanel shipLayoutPanel;
    private JPanel playerOnePanel;
    private JPanel playerTwoPanel;
    private JPanel gameStatusPanel;
    
    public boolean isOptionDialogOpen = false;
    
    //Initialize string arrays for JPanels and JComboBoxes
    String[] rowLetters = {" ", "A", "B", "C", "D", "E",
        "F", "G", "H", "I", "J"};
    String[] columnNumbers = {" ", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10"};
    String[] ships = {"Carrier", "Battleship", "Patrol Boat",
        "Submarine", "Destroyer"};
    String[] direction = {"Horizontal", "Vertical"};
    
    //Create JButton 2D array to hold JButtons from Player()
    JButton[][] buttonArray;
    JButton[][] buttonArray2;
    
    //Create JComboBoxes for the ship layout JPanel
    private JComboBox shipCb;
    private JComboBox directionCb;
    
    //Create Action Lisenevers for JMenu items
    private GameListener gameListener;
    private OptionsListener optionListener;
    private ExitListener exitListener;
    private DirectionListener directionListener;
    private ShipListener shipListener;
    private DeployListener deployListener;
    
    GameOptionDialog gameDialog;
    PlayerOptionDialog playerDialog;
    
    BattleshipClient client;
    BattleshipUI shipClass;
        
    final int PLAYER_ONE = 0;
    final int PLAYER_TWO = 1;
    final int HUMAN = 0;
    final int COMPUTER = 1;
    //Create instances of Player()
    Player playerOne;
    Player playerTwo; 
    
    //Initialize array to hold Playe() instances
    Player[] players = new Player[2]; 
    
    //Initialize array of colors
    Color[] color = {Color.cyan, Color.green, Color.yellow,
        Color.magenta, Color.pink, Color.red, Color.white};
    
    //Call the initComponents() method
     public void BattleshipUI(BattleshipUI battleShipClass) {
         shipClass = battleShipClass;
         initObjects();
         initComponents();
     }
    
     //Return a reference to this class
    JFrame getThisParent() {
        
        return this;
    }
    
    //Initialize player objects and add them to the Player array
    void initObjects() {
        
        playerOne = new Player();
        playerTwo = new Player();
        
        players[PLAYER_ONE] = playerOne;
        players[PLAYER_TWO] = playerTwo;
         
        client = new BattleshipClient(players, shipClass);
    }
    
    //Initialize items
    void initComponents() {
        
        int i, j;
        
        //Initialize menuBar and menus
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        optionMenu = new JMenu("Options");
        
        //Initialize Action Listeners
        gameListener = new GameListener();
        optionListener = new OptionsListener();
        exitListener = new ExitListener();
        directionListener = new DirectionListener();
        shipListener = new ShipListener();
        deployListener = new DeployListener();
        
        //Add menus to the menu bar
        menuBar.add(gameMenu);
        menuBar.add(optionMenu);
        
        //Initialize menu items
        playerPlayer = new JMenuItem("Player vs. Player");
        playerComputer = new JMenuItem("Player vs. Computer");
        computerComputer = new JMenuItem("Computer vs. Computer");
        exit = new JMenuItem("Exit");
        game = new JMenuItem("Game Options");
        player = new JMenuItem("Player Options");
        
        //Add menu items to the game menu
        gameMenu.add(playerPlayer);
        gameMenu.add(playerComputer);
        gameMenu.add(computerComputer);
        gameMenu.add(exit);
        
        //Disable player vs. player and computer vs. computer
        playerPlayer.setEnabled(false);
        computerComputer.setEnabled(false);
        
        //Add menu items to the options menu
        optionMenu.add(game);
        optionMenu.add(player);
        
        //Add Action Listeners to menu items
        playerPlayer.addActionListener(gameListener);
        playerComputer.addActionListener(gameListener);
        computerComputer.addActionListener(gameListener);
        exit.addActionListener(exitListener);
        game.addActionListener(optionListener);
        player.addActionListener(optionListener);
        
        //Initialize JPanels
        shipLayoutPanel = new JPanel();
        playerOnePanel = new JPanel();  
        
        //Initialize JComboBoxes for the ship layout panel
        shipCb = new JComboBox(ships);
        shipCb.setBorder(BorderFactory.createTitledBorder("Ships"));
        directionCb = new JComboBox(direction);
        directionCb.setBorder(BorderFactory.createTitledBorder("Direction"));
        
        //Initialize JButton for the ship layout panel
        deploy = new JButton("Deploy Ships");
        
        //Add action listeners to the combo boxes
        directionCb.addActionListener(directionListener);
        shipCb.addActionListener(shipListener);
        deploy.addActionListener(deployListener);
        
        //Add JComboBoxes and JButton to the ship layout panel
        shipLayoutPanel.add(shipCb, BorderLayout.WEST);
        shipLayoutPanel.add(directionCb, BorderLayout.CENTER);
        shipLayoutPanel.add(deploy, BorderLayout.EAST);
        
        //Disable the JButton
        deploy.setEnabled(false);
        
        //Set the layout and size of the player one panel
        playerOnePanel.setLayout(new GridLayout(11, 11));
        playerOnePanel.setMinimumSize(new Dimension(200,290));
        playerOnePanel.setBorder(BorderFactory.createTitledBorder("Player 1"));
        
        //Assign a JButton 2D array to buttonArray
        buttonArray = playerOne.player(" ");
        
        //Add JLabels and JButtons to the player one panel
        for (i=0;i<11; i++) {
            for (j=0; j<11; j++) {
                
                if (i==0) {
                    
                    JLabel colLabel = new JLabel(columnNumbers[j], SwingConstants.CENTER);
                    colLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    playerOnePanel.add(colLabel);
                }
                
                else if (j==0) {
                    
                    JLabel rowLabel = new JLabel(rowLetters[i], SwingConstants.CENTER);
                    rowLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    playerOnePanel.add(rowLabel);
                }
                
                else
                   playerOnePanel.add(buttonArray[j-1][i-1]);
            }
        }
        
        //Add items to the JFrame and set its size
        this.setTitle("Battleship"); 
        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.getContentPane().add(shipLayoutPanel, BorderLayout.NORTH);
        this.getContentPane().add(playerOnePanel, BorderLayout.WEST);
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    //Edit JTextArea after a player wins
    public void gameOver(String text) {
        gameStatus.append(text);
    }
    
    //Action Listener that displays JDialogs
    public class OptionsListener implements ActionListener {    
        
        public void actionPerformed(ActionEvent e) {
            
            if (e.getActionCommand().equals("Game Options")) {
                    
                gameDialog = new GameOptionDialog(getThisParent(), players);   
            }
            
            else if (e.getActionCommand().equals("Player Options")) {
                
                if (isOptionDialogOpen == true) {
                    
                    if (playerDialog.isCanceled == false)
                        playerDialog.makeVisible();
                    
                    else 
                        playerDialog = new PlayerOptionDialog(getThisParent(), players);
                    
                }
                
                else  {
                    
                    playerDialog = new PlayerOptionDialog(getThisParent(), players);
                    isOptionDialogOpen = true;
                }
            }
        }
    }
    
    //Action Listener that evaluates the chosen game type
    public class GameListener implements ActionListener {
                
        public void actionPerformed(ActionEvent e) {
            //Evaluate the chosen game type and store it        
            if (e.getActionCommand().equals("Player vs. Player") ) {
               players[PLAYER_ONE].setPlayMode(HUMAN);   
               players[PLAYER_TWO].setPlayMode(HUMAN);
            }
            
            else if (e.getActionCommand().equals("Player vs. Computer")) {
               players[PLAYER_ONE].setPlayMode(HUMAN);   
               players[PLAYER_TWO].setPlayMode(COMPUTER);
            }
                
            else if (e.getActionCommand().equals("Computer vs. Computer")) {
               players[PLAYER_ONE].setPlayMode(COMPUTER);   
               players[PLAYER_TWO].setPlayMode(COMPUTER);
            }
                
        }
    }
    
    //Action listener that exits the application
    public class ExitListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            //Ask user if they are sure they want to exit
            int n = JOptionPane.showConfirmDialog(null,"Are you sure "
                    + "you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION);
            //If user picks yes, exit application
            if (n == JOptionPane.YES_OPTION)
                System.exit(0);
            
        }
    }
    
    //Action listener that sets the selected ship
    public class ShipListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            JComboBox cb;
            
            cb = (JComboBox)e.getSource();
            
            //Pass a reference to the deploy jbutton
            playerOne.deploy = deploy;
            
            if (cb.getSelectedItem().equals("Carrier")) 
                playerOne.setcurrentShip(1);
                         
            else if (cb.getSelectedItem().equals("Battleship")) 
                playerOne.setcurrentShip(2);
                        
            else if (cb.getSelectedItem().equals("Patrol Boat")) 
                playerOne.setcurrentShip(3);
                 
            else if (cb.getSelectedItem().equals("Submarine")) 
                playerOne.setcurrentShip(4);
                 
            else if (cb.getSelectedItem().equals("Destroyer")) 
                playerOne.setcurrentShip(5);  
        }
                  
    }
    
    //Action listener that sets the selected ship direction
    public class DirectionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            JComboBox cb;
            
            cb = (JComboBox)e.getSource();
            
            if (cb.getSelectedItem().equals("Horizontal")) 
                playerOne.setcurrentDirection(0);
            
            else if (cb.getSelectedItem().equals("Vertical")) 
                playerOne.setcurrentDirection(1);
            
        }
    }
    
    //Action Listener that deploys ships and begins the game
    public class DeployListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            int i, j, k, rowClick, columnClick;
            Random random = new Random();
            
            //Disable components no longer needed
            shipCb.setEnabled(false);
            directionCb.setEnabled(false);
            deploy.setEnabled(false);
            
            gameStatusPanel = new JPanel();
            
            //Display which player will play first in the JTextArea
            if (players[0].getisFirst() == false) 
                gameStatus = new JTextArea("Let's play!\nLet the games begin!"
                        + "\nPlayer Two goes first!", 5, 20);
                 
            else 
                gameStatus = new JTextArea("Let's play!\nLet the games begin!"
                        + "\nPlayer One goes first!", 5, 20);
            
            scrollPane = new JScrollPane(gameStatus);
            gameStatus.setEditable(false);
            gameStatus.setLineWrap(true);
            gameStatus.setWrapStyleWord(true);
            
            gameStatusPanel.setLayout(new BorderLayout());
            gameStatusPanel.setMinimumSize(new Dimension(200,290));
            gameStatusPanel.setBorder(BorderFactory.createTitledBorder("Game Status"));
            gameStatusPanel.add(scrollPane, BorderLayout.CENTER);
            
            //Place the JPanel containing the JTextArea on the JFrame
            BattleshipUI.this.getContentPane().add(gameStatusPanel, BorderLayout.CENTER);
            
            playerTwoPanel = new JPanel();
            
            playerTwoPanel.setLayout(new GridLayout(11, 11));
            playerTwoPanel.setMinimumSize(new Dimension(200,290));
            playerTwoPanel.setBorder(BorderFactory.createTitledBorder("Player 2"));
            
            buttonArray2 = playerTwo.player(" ");
            
            //Fill the Player Two JPanel with JButtons
            for (i=0;i<11; i++) {
                for (j=0; j<11; j++) {
                
                    if (i==0) {
                    
                        JLabel colLabel = new JLabel(columnNumbers[j], SwingConstants.CENTER);
                        colLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                        playerTwoPanel.add(colLabel);
                    }
                
                    else if (j==0) {
                    
                        JLabel rowLabel = new JLabel(rowLetters[i], SwingConstants.CENTER);
                        rowLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                        playerTwoPanel.add(rowLabel);
                    }
                
                    else
                    playerTwoPanel.add(buttonArray2[j-1][i-1]);
                }
            }
            
            //Place the Player Two JPanel on the JFrame
            BattleshipUI.this.getContentPane().add(playerTwoPanel, BorderLayout.EAST);
            pack();
            
            //Call the autoLayout method to choose the Computer's ship locations
            players[PLAYER_TWO].autoLayout();
          
            client = new BattleshipClient(players, shipClass);
            
            //Fire enemy's first shot if the computer plays first
            if (players[0].getisFirst() == false) {
                rowClick = random.nextInt(10);
                columnClick = random.nextInt(10);
                
                //Exectue if the chosen JButton is filled
                if (players[0].buttonBoard[columnClick][rowClick].
                        getClientProperty("filled") == Boolean.TRUE) {
                    players[0].buttonBoard[columnClick][rowClick]
                            .setBackground(Color.BLACK);
                    players[0].buttonBoard[columnClick][rowClick]
                            .putClientProperty("hit", Boolean.TRUE);
                    
                    if (players[0].buttonBoard[columnClick][rowClick]
                            .getClientProperty("ship") == "Carrier") {
                         k = players[0].carrier.getHitsLeft();
                         players[0].carrier.setHitsLeft(i-1);
                    }
                    
                    if (players[0].buttonBoard[columnClick][rowClick]
                            .getClientProperty("ship") == "Battleship") {
                         k = players[0].battleShip.getHitsLeft();
                         players[0].battleShip.setHitsLeft(i-1);
                    }
                    
                    if (players[0].buttonBoard[columnClick][rowClick]
                            .getClientProperty("ship") == "Patrol Boat") {
                         k = players[0].patrolBoat.getHitsLeft();
                         players[0].patrolBoat.setHitsLeft(i-1);
                    }
                    
                    if (players[0].buttonBoard[columnClick][rowClick]
                            .getClientProperty("ship") == "Submarine") {
                         k = players[0].submarine.getHitsLeft();
                         players[0].submarine.setHitsLeft(i-1);
                    }
                    
                    if (players[0].buttonBoard[columnClick][rowClick]
                            .getClientProperty("ship") == "Destroyer") {
                         k = players[0].destroyer.getHitsLeft();
                         players[0].destroyer.setHitsLeft(i-1);
                    }
                }
                
                //Execute if the chosen JButton is empty
                else {
                    players[0].buttonBoard[columnClick][rowClick].
                            setBackground(Color.BLUE);
                    players[0].buttonBoard[columnClick][rowClick].
                            putClientProperty("hit", Boolean.TRUE);
                 }
                
            }
            client.play();
        }
    }
}
    
    

