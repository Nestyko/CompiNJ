/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analyzers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nltob
 */
public class Tests {
    
    public static void main(String[] args){
        HashMap<String, Object> data = new HashMap<>();
        ArrayList<String> letras = new ArrayList<>();
        HashMap<String, Object> simbols = new HashMap<>();
        letras.add("a");
        letras.add("b");
        letras.add("c");
        //letras.add("world");
        simbols.put("Letras", letras);
        data.put("Simbols", simbols);
        LexicalAnalyzer lx = new LexicalAnalyzer(data);
        System.out.println(lx.find("a"));
        System.out.print(lx.analisys("a b hello world"));
    }
}
