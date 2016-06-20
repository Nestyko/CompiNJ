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
public class BST_math extends BST{
    
    public BST_math(String ecuation){
        super();
        this.root = new Node<String>(ecuation);
        EcuationParser parser = new EcuationParser(this.root);
        this.root = parser.getNode();
    }
    
}

