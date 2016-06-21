/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author nltob
 */
public class EcuationParser {
    
    final static TipoSeparador[] tipo_separadores = new TipoSeparador[] {
            new TipoSeparador("{", "}", "Llaves", 1),
            new TipoSeparador("[", "]", "Corchetes", 2),
            new TipoSeparador("(", ")", "Parentesis", 3)
        };
    final static String[] tipo_operadores = { "=" , "+", "-", "*", "/" ,"^"};
    ArrayList<Separador> separadores = new ArrayList<>();
    Node<String> ecuacion;
    String parsed;
    
    EcuationParser(Node ecuacion){
        this.ecuacion = ecuacion;
        this.parse();
    }
    
    private void parse(){
        for (int i = 0; i < tipo_separadores.length; i++) {
            int count = 0;
            Point posicion = buscarIndex(tipo_separadores[i]);
            //TODO:
            //Manejar el caso en que los separadores abarquen todo
            while(posicion.x != -1 && posicion.y != -1){           
                Separador separador = new Separador(tipo_separadores[i], posicion, this.extraer(posicion));
                count++;
                separadores.add(separador);
                this.evaluar(separador.replaceForToken(this.ecuacion.data));
                posicion = buscarIndex(tipo_separadores[i]);
            }
        }
    }
    
    private Point buscarIndex(TipoSeparador tipo_separador){
        int index_inicial = this.ecuacion.data.indexOf(tipo_separador.inicial);
        int index_final = this.ecuacion.data.indexOf(tipo_separador.fin);
        if(!(index_inicial != -1 && index_final != -1)){
            System.out.println("Error en la ecuacion:" + this.ecuacion);
        }
        return new Point(index_inicial, index_final);
    }
    
    private String extraer(Point posicion){
        return this.ecuacion.data.substring(posicion.x, posicion.y+1);
    }
    private String[] separar(int index){
        String[] result = new String[2];
        /*
        String tokenized = this.ecuacion.data;
        for(Separador separador : separadores){
            tokenized = separador.replaceForToken(tokenized);
            tokenized = separador.replaceForOriginal(tokenized, true);
        }
                */
        result[0] = this.ecuacion.data.substring(0, index);
        result[1] = this.ecuacion.data.substring(index+1, this.ecuacion.data.length());
        return result;
    }   
    
    private int indexReal(int index, String tokenized){
        String operador = tokenized.charAt(index)+"";
        for(Separador separador : separadores){
            if(index > tokenized.indexOf(separador.token())){
                separador.replaceForOriginal(tokenized);
                index = tokenized.indexOf(operador);
            }
        }
        return index;
    }
    
    private boolean evaluar(String tokenized){
        for (int i = 0; i < tipo_operadores.length; i++) {
            int index = tokenized.indexOf(tipo_operadores[i]);
            if(index != -1){
                //TODO:
                //Agregar al nodo actual
                //Separar
                //Agregar al nodo izquierda y derecha
                //rewrite
                String[] partes = this.separar(indexReal(index, tokenized));
                for(String parte: partes){
                    parte = removerEnvoltorio(parte);
                }
                this.ecuacion.left = new Node<String>(partes[0]);
                this.ecuacion.right = new Node<String>(partes[1]);
                EcuationParser left = new EcuationParser(this.ecuacion.left);
                EcuationParser right = new EcuationParser(this.ecuacion.right);
                this.ecuacion.data = tipo_operadores[i];
                return true; //retornar el nodo
            }
        }
        return false;
    }
    
    public Node<String> getNode(){
        return this.ecuacion;
    }
    
    public static String removerEnvoltorio(String ecuacion){
        for(TipoSeparador tipo_separador: tipo_separadores){
            if(
                    ecuacion.indexOf(tipo_separador.inicial) == 0 && 
                    ecuacion.indexOf(tipo_separador.fin) == ecuacion.length()-1){
                
                //Remueve los separadores que envuelven toda la ecuacion
                return ecuacion.substring(1, ecuacion.length()-1); 
            }
        }
        return ecuacion;
    }
    
    
    
}
