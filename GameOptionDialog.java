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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOptionDialog {
    //Create the JDialog and its components
    private JDialog dialog;
    private JLabel computerAiLbl;
    private JLabel shipLayoutLbl;
    private JButton saveBtn;
    private JButton canxBtn;
    private JComboBox computerAi;
    private JComboBox shipLayout;
    private JPanel buttonPanel;
    private JPanel optionsPanel;
    
    //Initialize strings for the JComboBoxes
    String[] level = {"Normal", "Ridiculously Hard"};
    String[] Layout = {"Manual", "Automatic"};
    
    //Create array of Player objects
    Player[] playerArray;
    
    SaveListener saveListener;
    CancelListener cancelListener;
    
    //Call the init components method
    public GameOptionDialog(JFrame parent, Player[] inPlayers) {
        
        playerArray = inPlayers;
        initComponents(parent);
    }
    
    private void initComponents(JFrame parent) {
        //Initialize JDialog
        dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        //Initialize JDialog components
        computerAiLbl =  new JLabel("Computer AI");
        shipLayoutLbl = new JLabel("Ship Layout");
        computerAi = new JComboBox(level);
        shipLayout = new JComboBox(Layout);
        saveBtn = new JButton("Save");
        canxBtn = new JButton("Cancel");
        buttonPanel = new JPanel(new GridLayout(1,2));
        optionsPanel = new JPanel(new GridLayout(2,2));
        saveListener = new SaveListener();
        cancelListener = new CancelListener();
        
        //Set border of the options panel and add its components
        optionsPanel.setBorder(BorderFactory.
                createTitledBorder("Game Options"));
        optionsPanel.add(computerAiLbl);
        optionsPanel.add(computerAi);
        optionsPanel.add(shipLayoutLbl);
        optionsPanel.add(shipLayout);
        
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
    
    //Action listener that exits the Jdialog
    private class SaveListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            dialog.dispose();
        }
    }
    
    //Action listener that exits the JDialog
    private class CancelListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            dialog.dispose();
        }
    }
}
