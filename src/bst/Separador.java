/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst;

import java.awt.Point;

/**
 *
 * @author nltob
 */
public class Separador{
    private TipoSeparador separador;
    public String original;
    private int length;
    public Point posicion;

    public Separador(TipoSeparador separador, Point posicion, String original){
        this.separador = separador;
        this.posicion = posicion;
        this.original = original;
    }
    
    public int posicionInicial(){
        return posicion.x;
    }
    
    public void posicionInicial(int inicial){
        this.posicion.x = inicial;
    }
    
    public int posicionFinal(){
        return posicion.y;
    }
    
    public void posicionFinal(int fin){
        this.posicion.y = fin;
    }
    
    public int length(){
        return original.length();
    }
    
    public int lengthReemplazada() {
        return original.length() - this.token().length();
    }
    
    public String token(){
        return "&" + ((int)(Math.random() * 1000) + 1) + "&";
    }
    
    public String replaceForToken(String ecuacion){
        return ecuacion.replace(this.original, this.token());
    }
    
    public String replaceForOriginal(String ecuacion){
        return ecuacion.replace(this.token(), this.original);
    }

}
