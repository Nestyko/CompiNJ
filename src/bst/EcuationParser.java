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
        this.evaluar(this.replaceForTokens(this.ecuacion.data));
    }
    
    private String replaceForTokens(String ecuacion){
        Point posicion = null;
        TipoSeparador ts = null;
        for(TipoSeparador tipo: tipo_separadores){
            ts = tipo;
            posicion = buscarIndex(ecuacion,ts);
            if(posicion.x != -1 && posicion.y != -1){
                break;
            }
        }
        if(posicion.x == -1 && posicion.y == -1){
            return ecuacion;
        }
        Separador separador = new Separador(ts, posicion, this.extraer(ecuacion, posicion));
        this.separadores.add(separador);
        return replaceForTokens(separador.replaceForToken(ecuacion));
    }
    
    
    private Point buscarIndex(TipoSeparador tipo_separador){
        int index_inicial = this.ecuacion.data.indexOf(tipo_separador.inicial);
        int index_final = this.ecuacion.data.indexOf(tipo_separador.fin);
        if(!(index_inicial != -1 && index_final != -1)){
            System.out.println("Error en la ecuacion:" + this.ecuacion);
        }
        return new Point(index_inicial, index_final);
    }
    
    private Point buscarIndex(String ecuacion, TipoSeparador tipo_separador){
        int index_inicial = ecuacion.indexOf(tipo_separador.inicial);
        int index_final = ecuacion.indexOf(tipo_separador.fin);
        if(!(index_inicial != -1 && index_final != -1)){
            System.out.println("Error en la ecuacion:" + ecuacion);
        }
        return new Point(index_inicial, index_final);
    }
    
    private String extraer(Point posicion){
        return this.ecuacion.data.substring(posicion.x, posicion.y+1);
    }
    
    private String extraer(String ecuacion, Point posicion){
        return ecuacion.substring(posicion.x, posicion.y+1);
    }
    
    private String[] separar(int index, String tokenized){
        String[] result = new String[2];
        tokenized = this.replaceForOriginal(tokenized);
        result[0] = tokenized.substring(0, index);
        result[1] = tokenized.substring(index+1, tokenized.length());
        return result;
    }   
    
    private int indexReal(int index, String tokenized){
        String operador = tokenized.charAt(index)+"";
        int initial_index = index;
        while(this.isTokenized(tokenized, index)){
            for(Separador separador : separadores){
                if(index > tokenized.indexOf(separador.token())){
                    tokenized = separador.replaceForOriginal(tokenized);
                    index += separador.length();
                }
            }
        }
        
        return index;
    }
    
    private boolean isTokenized(String ecuacion){
        for(Separador separador: this.separadores){
            if(ecuacion.indexOf(separador.token()) != -1){
                return true;
            }
        }
        return false;
    }
    
    private boolean isTokenized(String ecuacion,int index){
        for(Separador separador: this.separadores){
            int position = ecuacion.indexOf(separador.token());
            if(position < index && position != -1){
                return true;
            }
        }
        return false;
    }
    
    private String replaceForOriginal(String tokenized){
        while(this.isTokenized(tokenized)){
            for(Separador separador: this.separadores){
                tokenized = separador.replaceForOriginal(tokenized);
            }
        }
        return tokenized;
    }
    
    private boolean evaluar(String tokenized){
        for (String tipo_operador : tipo_operadores) {
            int index = tokenized.indexOf(tipo_operador);
            if(index != -1){
                //TODO:
                //Agregar al nodo actual
                //Separar
                //Agregar al nodo izquierda y derecha
                //rewrite
                String[] partes = this.separar(indexReal(index, tokenized), tokenized);
                for(int i = 0; i < partes.length;i++){
                    partes[i] = removerEnvoltorio(partes[i]);
                }
                this.ecuacion.left = new Node<String>(partes[0]);
                this.ecuacion.right = new Node<String>(partes[1]);
                EcuationParser left = new EcuationParser(this.ecuacion.left);
                EcuationParser right = new EcuationParser(this.ecuacion.right);
                this.ecuacion.data = tipo_operador;
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
            int inicio = ecuacion.indexOf(tipo_separador.inicial);
            int fin = ecuacion.indexOf(tipo_separador.fin);
            if(
                    inicio == 0 && 
                    fin == ecuacion.length()-1){
                
                //Remueve los separadores que envuelven toda la ecuacion
                return ecuacion.substring(1, ecuacion.length()-1); 
            }/*else if(inicio == 0){
                return ecuacion.substring(1, ecuacion.length());
            }else if(fin == ecuacion.length()-1){
                return ecuacion.substring(0, ecuacion.length()-1);
            }*/
        }
        return ecuacion;
    }
    
    
    
}
