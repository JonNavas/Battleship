/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import userInterface.BattleshipUI;


public class PlayerOptionDialog {
    //Create JDialog and its components
    private JDialog dialog;
    private JLabel shipColorLbl;
    private JLabel firstPlayerLbl;
    private JComboBox shipColor;
    private JComboBox firstPlayer;
    private JButton saveBtn;
    private JButton canxBtn;
    private JRadioButton player1;
    private JRadioButton player2;
    private ButtonGroup playerOptions;
    private JPanel buttonPanel;
    private JPanel optionsPanel;
    
    private int selectPlayer;
    
    public boolean isCanceled = false;
    
    //Initialize strings for the JDialog
    String[] colors = {"Cyan", "Green", "Yellow",
        "Magenta", "Pink", "Red", "White"};
    String[] players = {"Player 1", "Player 2", "Random"};
    Player[] playerArray;
    
    PlayerListener playerListener;
    SaveListener saveListener;
    CancelListener cancelListener;
    
    //Call the init components method
    public PlayerOptionDialog(JFrame parent, Player[] inPlayers) {
        
        playerArray = inPlayers;
        initComponents(parent);
    }
    
    private void initComponents(JFrame parent) {
        //Initialize the JDialog
        dialog = new JDialog(parent, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        //Initialize components for the JDialog
        shipColorLbl = new JLabel("Ship Color");
        firstPlayerLbl = new JLabel("Who Plays first?");
        shipColor = new JComboBox(colors);
        firstPlayer = new JComboBox(players);
        saveBtn = new JButton("Save");
        canxBtn = new JButton("Cancel");
        player1 = new JRadioButton("Player 1");
        player2 = new JRadioButton("Player 2");
        playerOptions = new ButtonGroup();
        buttonPanel = new JPanel(new GridLayout(1, 2));
        optionsPanel = new JPanel(new GridLayout(3, 2));
        playerListener = new PlayerListener();
        saveListener = new SaveListener();
        cancelListener = new CancelListener();
        
        //Add action listeners to the radion buttons
        player1.addActionListener(playerListener);
        player2.addActionListener(playerListener);
        
        //JButtons to the player options button group
        playerOptions.add(player1);
        playerOptions.add(player2);    
        
        //Add components to the options panel
        optionsPanel.setBorder(BorderFactory.
            createTitledBorder("Player Options"));
        optionsPanel.add(player1);
        optionsPanel.add(player2);
        optionsPanel.add(shipColorLbl);
        optionsPanel.add(shipColor);
        optionsPanel.add(firstPlayerLbl);
        optionsPanel.add(firstPlayer);
        
        //Add action listeners to the save and cancel buttons
        saveBtn.addActionListener(saveListener);
        canxBtn.addActionListener(cancelListener);
        
        //Add components to the button panel
        buttonPanel.add(saveBtn);
        buttonPanel.add(canxBtn);
        
        //Add components to the JDialog
        dialog.setTitle("Options");
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().add(optionsPanel, BorderLayout.CENTER);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        dialog.setMinimumSize(new Dimension(300, 190));
        dialog.setVisible(true);
    }
    
    public int getSelectPlayer() {
        
        return this.selectPlayer;
    }
    
    public void makeVisible() {
        
        dialog.setVisible(true);
    }
    
    //Action listener that records the chosen player radio button
    public class PlayerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            if (e.getActionCommand().equals("Player 1"))
                selectPlayer = 1;
            
            else if (e.getActionCommand().equals("Player 2"))
                selectPlayer = 2;
        }
    }
    
    //Action listener that saves chosen values in the JDialog
    private class SaveListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            Random rnd = new Random();
            
            if (selectPlayer == 1) {
                
                if (shipColor.getSelectedItem().equals("Cyan")) 
                    playerArray[0].setshipColor(Color.cyan);
                
                else if (shipColor.getSelectedItem().equals("Green"))
                    playerArray[0].setshipColor(Color.green);
                
                else if (shipColor.getSelectedItem().equals("Yellow"))
                    playerArray[0].setshipColor(Color.yellow);
                
                else if (shipColor.getSelectedItem().equals("Magenta"))
                    playerArray[0].setshipColor(Color.magenta);
                
                else if (shipColor.getSelectedItem().equals("Pink"))
                    playerArray[0].setshipColor(Color.pink);
                
                else if (shipColor.getSelectedItem().equals("Red"))
                    playerArray[0].setshipColor(Color.red);
                
                else if (shipColor.getSelectedItem().equals("White"))
                    playerArray[0].setshipColor(Color.white);
                
                //Player 1 goes first
                if (firstPlayer.getSelectedItem().equals("Player 1")) {
                    playerArray[0].setisFirst(true);
                    playerArray[1].setisFirst(false);
                }
                
                //Player 2 goes first
                else if (firstPlayer.equals("Player 2")) {
                    playerArray[0].setisFirst(false);
                    playerArray[1].setisFirst(true);
                }
                
                //Random player goes first
                else if (firstPlayer.getSelectedItem().equals("Random")) {
                    playerArray[0].setisFirst(rnd.nextBoolean());
                    playerArray[1].setisFirst(!playerArray[0].getisFirst());
                }
                    
                //Close JDialog
                dialog.dispose();
           }
           
            else if (selectPlayer == 2) {
                
                if (shipColor.getSelectedItem().equals("Cyan"))
                    playerArray[1].setshipColor(Color.cyan);
                
                else if (shipColor.getSelectedItem().equals("Green"))
                    playerArray[1].setshipColor(Color.green);
                
                else if (shipColor.getSelectedItem().equals("Yellow"))
                    playerArray[1].setshipColor(Color.yellow);
                
                else if (shipColor.getSelectedItem().equals("Magenta"))
                    playerArray[1].setshipColor(Color.magenta);
                
                else if (shipColor.getSelectedItem().equals("Pink"))
                    playerArray[1].setshipColor(Color.pink);
                
                else if (shipColor.getSelectedItem().equals("Red"))
                    playerArray[1].setshipColor(Color.red);
                
                else if (shipColor.getSelectedItem().equals("White"))
                    playerArray[1].setshipColor(Color.white);
               
                //Player 1 goes first
                if (firstPlayer.getSelectedItem().equals("Player 1")) {
                    playerArray[0].setisFirst(true);
                    playerArray[1].setisFirst(false);
                }
                
                //Player 2 goes first
                else if (firstPlayer.getSelectedItem().equals("Player 2")) {
                    playerArray[0].setisFirst(false);
                    playerArray[1].setisFirst(true);
                } 
                
                //Random player goes first
                else if (firstPlayer.getSelectedItem().equals("Random")) {
                    playerArray[0].setisFirst(rnd.nextBoolean());
                    playerArray[1].setisFirst(!playerArray[0].getisFirst());
                }
                
                //Close JDialog
                dialog.dispose();
            }
            
            //Display message if user has not chosen a player radio button
            else {
               
                JOptionPane.showMessageDialog(null, "You must select a player"); 
            }
        }
    }
    
    //Action listener that closes the JDialog
    private class CancelListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            isCanceled = true;
            dialog.dispose();
        }
    }
}


