/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

/**
 *
 * @author nltob
 */
public class Variable {
    public int line;
    String value = null;
    
    public Variable(String value){
        this.value = value;
    }
    
    public Variable(String value, int line){
        this.value = value;
        this.line = line;
    }
    
    public String getValue(){
        return this.value;
    }
    
    public boolean isDefined(){
        if(value.equals(null)){
            return false;
        }
        return true;
    }
}
