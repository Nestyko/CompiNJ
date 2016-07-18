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
public class ErrorSemantico {
    public int line;
    public int col;
    public String description;
    
    public ErrorSemantico(int line, int col, String description){
        this.line = line;
        this.col = col;
        this.description = description;
    }
    
    public String getString(){
        String line = "";
        String colum = "";
        if(this.line != -1){
            line = "linea " + this.line + ", ";
        }
        if(this.col != -1){
            colum = "columna " + this.col + ", "; 
        }
        return "Error: " + line + colum + this.description;
    }
       
}
