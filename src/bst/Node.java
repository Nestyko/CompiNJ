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
public class Node<T>
   {
      public T data;
      public Node<T> left, right;

      public Node(T data, Node<T> l, Node<T> r)
      {
         left = l; right = r;
         this.data = data;
      }

      public Node(T data)
      {
         this(data, null, null);
      }

      public String toString()
      {
         return data.toString();
      }
   } //end of Node
