/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst;

/**
 *
 * @author nestyko
 */


// Code for popping up a window that displays a custom component
// in this case we are displaying a Binary Search tree  
// reference problem 4.38 of Weiss to compute tree node x,y positions

// input is a text file name that will form the Binary Search Tree

//     java DisplaySimpleTree textfile


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class BST_draw extends JFrame {
  JScrollPane scrollpane;
  DisplaySimpleTree panel;
  

  public BST_draw(BstMathComponent t) {
    panel = new DisplaySimpleTree(t);
    panel.setPreferredSize(new Dimension(1000, 1000));
    scrollpane = new JScrollPane(panel);
    scrollpane.setPreferredSize(new Dimension(800,600));
    getContentPane().add(scrollpane, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setSize(800, 600);

    pack();  // cleans up the window panel
  }
}
/*
  public static void main(String[] args) {

    BstMathComponent t = new BstMathComponent(); // t is Binary tree we are displaying
    BufferedReader diskInput;
    String word;
    // read in the words to create the Binary Search Tree
    if(args.length!=1){
	System.out.println("usage: java DisplayTree textfile");
        System.exit(0);
    }
    try { //reads in words from a file
      diskInput = new BufferedReader(new InputStreamReader(
          new FileInputStream(
	      new File(args[0]))));// file name is on command line
      Scanner input=new Scanner(diskInput);
      while (input.hasNext()) { 
        word=input.next();
        word=word.toLowerCase(); // use lower case only
        t.root = t.insert(t.root, word);  //insert word into Binary Search Tree
        t.inputString= t.inputString + " " + word; // add word to input string
      }
    }
    catch (IOException e) {
        System.out.println("io exception");
      }

    t.computeNodePositions(); //finds x,y positions of the tree nodes
    t.maxheight=t.treeHeight(t.root); //finds tree height for scaling y axis
    DisplaySimpleTree dt = new DisplaySimpleTree(t);//get a display panel
    dt.setVisible(true); //show the display
  }
}
*/
  
