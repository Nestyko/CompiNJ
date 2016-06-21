/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analyzers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Lexical Analyzer can remove comments, and clasify each word of a string
 * with the data
 * @author Nestor Tobon
 */
public class LexicalAnalyzer {
    
    private final HashMap<String, ? extends Object> data;
    private ArrayList<String> clasifications;
    private HashMap<String,HashMap> analysis_data;
    public static ArrayList<String> comentario;
    /**
     * Creates a new Object with the data provided and generates the corresponding
     * clasifications
     * @param data is the data in the form of map, can have nested maps, lists
     * or string arrays (String arrays not recomended)
     */
    public LexicalAnalyzer(HashMap<String, ?> data){
        this.data = data;
        this.clasifications = new ArrayList<>();
        this.get_clasifications(this.data);
    }
    
    public void gitFunction(){
        System.out.println("Funcion para probar git");
    }
    
    public static Integer countSpaces(String text){
        Integer count = 0;
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == ' '){
                count++;
            }
        }
        return count;
    }
    

    public ArrayList<String> getClasifications(){
        return this.clasifications;
    }
    
    /**
     * Busca una palabra y la clasifica
     * @param word es la palabra a buscar en los datos
     * @return un array con las clasificaciones correspondiente
     */
    public ArrayList<String> find(String word){
        ArrayList<String> result = new ArrayList<>();
        if(find_recursive(word, this.data, result)){
            return result;
        }else{
            System.out.println("Word: \"" + word + "\" not found");
            result.add("None");
            return result;
        }
    }
    
    /**
     * Cuenta el numero de coincidencias para cada clasificacion
     * @param text es el texto a analizar
     * @return un mapa con las clasificaciones anidadas,
     * y cuantas veces se repiten cada una.
     */
    public HashMap<String,HashMap> analisys(String text){
        String[] words = this.getTokens(this.removeComments(text));
        System.out.print("Salida: {");
        for (String word : words) {
            System.out.print(word+" ");
        }
        System.out.print("}");
        HashMap<String,HashMap> result = new HashMap<>();
        result.put("Error", new HashMap<>());
        for (String clasification : this.clasifications) {
            HashMap<String,Integer> word = new HashMap<>();
            result.put(clasification, word);
        }
        for (int i = 0; i < words.length; i++) {
            ArrayList<String> classes = this.find(words[i]);
            if(!classes.get(0).equals("None")){
                for (String c : classes) {
                    Integer old = (Integer) result.get(c).get(words[i]);
                    if(old == null){
                        old = 0;
                    }
                    if(old != null){
                        result.get(c).put(words[i], old+1);
                    }
                }
            }else{
                for(int k = 0; k < words[i].length(); k++){
                    classes = this.find(words[i].charAt(k)+"");
                    if(classes.get(0) != "None"){
                        for (String c : classes) {
                            Integer old = (Integer) result.get(c).get(words[i].charAt(k)+"");
                            if(old == null){
                                old = 0;
                            }
                            result.get(c).put(words[i].charAt(k)+"", old+1);
                        }
                    }else{
                        //Error in the character, the character doesnt have a clasification
                        Integer old = (Integer) result.get("Error").get(words[i].charAt(k)+"");
                        if(old == null){
                            old = 0;
                        }
                        result.get("Error").put(words[i].charAt(k)+"", old+1);
                    }
                }
            }
        }
        this.analysis_data = result;
        return result;
    }
    
    /**
     * Separates the text into words and arrage them into an array String
     * @param text is the text with the words to separate
     * @return the string array, each element is a word
     */
    private String[] getTokens(String text){
        StringTokenizer tokens = new StringTokenizer(text);
        String[] words = new String[tokens.countTokens()];
        int counter = 0;
        while(tokens.hasMoreTokens()){
            words[counter++] = tokens.nextToken().toLowerCase();
            System.out.println(words[counter-1]);
        }
        return words;
    }
    
    /**
     * Converts a string to a one line strings, removing any spaces, tabs, or anything else
     * @param text is the string to convert
     * @return the string in one line
     */
    public String getOneLine(String text){
        String[] words = this.getTokens(this.removeComments(text));
        String result = "";
        for (String word : words) {
            result += word;
        }
        if(this.analysis_data != null){
            System.out.println(this.analysis_data);
            HashMap<String, Integer> errors = (HashMap<String, Integer>)analysis_data.get("Error");
            for (Map.Entry<String, Integer> entry : errors.entrySet()) {
                String key = entry.getKey();
                result = result.replace(key, ""); 
            }
            
        }
        return result;
    }
    
    private String removeComments(String text) {
        String comment_token = "\\\\";
        String start_comment_token = "\\*";
        String end_comment_token = "*\\";
        int offset = 0;
        while(offset != -1){
            offset = text.indexOf(comment_token);
            if(offset != -1){
                int part2_offset = text.indexOf("\n", offset);
                if(part2_offset == -1){
                    text = text.substring(0, offset);
                    continue;
                }
                String part1 = text.substring(0, offset);
                String part2 = text.substring(part2_offset, text.length());
                text = part1 + part2;
                continue;
            }
            offset = text.indexOf(start_comment_token);
            if(offset != -1){
                int part2_offset = text.indexOf(end_comment_token, offset);
                String comen = text.substring(offset+2, part2_offset);
                comentario.add(comen);
                if(part2_offset == -1){
                    text = text.substring(0, offset);
                    continue;
                }else{
                    String part1 = text.substring(0, offset);
                    String part2 = text.substring(part2_offset+2, text.length());
                    text = part1 + part2;
                    continue; 
                }
                
            }
        }
        return text;
      }
    
    private void get_clasifications(Map<String,? extends Object> data){
        for (Map.Entry<String, ? extends Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            this.clasifications.add(key);
            if( value instanceof Map){
                get_clasifications((Map)value);
            }
        }
    }
    
    /**
     * Finds a word inside an ArrayList or a String[] that can be nested in 
     * a Map of multiple levels, and will add each clasification to the result 
     * Array.
     * @param word is the word to find
     * @param data is the Map with the clasifications and the ArrayLists of words
     * @param result is the resultant array in which to add the clasifications 
     * @return true if found and false if not found
     */
    private Boolean find_recursive(String word, Map<String, ?> data, ArrayList<String> result){
        for(Map.Entry<String, ?> entry : data.entrySet()){
            if( entry.getValue() instanceof Map ) {
                if(find_recursive(word,(Map) entry.getValue(), result)){
                    result.add(entry.getKey());
                    return true;
                }
            }else if(entry.getValue() instanceof ArrayList){
                ArrayList<String> list = (ArrayList) entry.getValue();
                if(list.contains(word)){
                   return result.add(entry.getKey());
                }
            }else if(entry.getValue() instanceof String[]){
                String[] arr = (String[]) entry.getValue();
                for (int i = 0; i < arr.length; i++) {
                    if(word.equals(arr[i])){
                        return result.add(entry.getKey());
                    }
                }
            }
        }
        return false;
    }
    
    
    
}
