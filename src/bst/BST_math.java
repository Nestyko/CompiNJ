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
    
    public static void main(String[] args){
        String ecuation = "1*[A+B]=46*[17^(p-78)]";
        BST_math test = new BST_math(ecuation);
        System.out.print("PreOrderTransversal: ");
        test.preOrderTraversal();
        System.out.println();
        System.out.print("InOrderTransversal: ");
        test.inOrderTraversal();
        System.out.println();
        System.out.print("Hojas: " + test.countLeaves());
        System.out.println();
        System.out.print("Anchura: " + test.width());
        System.out.println();
    }
    
}

