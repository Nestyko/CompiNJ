/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bst.BST_math;

/**
 *
 * @author nestyko
 */
public class BTS_math_test {
    
    public static void main(String[] args) {
        String ecuation = "1*[A+B]";
        BST_math test = new BST_math(ecuation);
        test.preOrderTraversal();
    }
}
