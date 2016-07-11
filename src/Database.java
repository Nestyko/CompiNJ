
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author clases
 */
public class Database {
    
    private String host, username, password;
    private Connection connection;
    
    public Database(){
        this.host = "jdbc:mysql://localhost:3306/compiler";
        this.username = "nestyko";
        this.password = "31081994"; //Hay que cambiarlo a "" para la pc de urbe
        connect();
    }
    
    /**
     * Creates a conection to the database with the specified parameters
     * @param database_name
     * @param username ex. "root"
     * @param password leave "" for urbe
     * @param host ex. "127.0.0.1"
     * @param port ex. "3306" for mysql
     */
    public Database(String database_name, 
            String username, 
            String password, 
            String host, 
            String port){
        this.host = "jdbc:mysql://" + host + ":" + port + "/" + database_name;
        this.username = username;
        this.password = password;
        connect();
    }
    
    private void connect(){
        try {
            this.connection = (Connection) DriverManager.getConnection(host, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error conectando a la base de datos");
        }
    }
    
    /**
     * Gets all the data from a table as an ArrayList
     * @param table is the name of the table
     * @return An ArrayList containing the rows and nested ones containing the cols.
     */
    public ArrayList getTable(String table){
        ArrayList result = new ArrayList<ArrayList>();
        Statement statement = this.getStatement();
        String sql = "SELECT * FROM " + table + ";";
        ResultSet data = this.getResultSet(statement, sql); 
        try {
            while(data.next()){
                int counter = 1;
                ArrayList row = new ArrayList<Object>();
                while(true){
                    Object col = null;
                    try{
                       col = data.getObject(counter);
                       counter++;
                       //System.out.println(col);
                    }catch (Exception e){
                        //System.out.println("End of Row");
                        break;
                    } 
                    row.add(col);
                }
                result.add(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    private Statement getStatement(){
        Statement statement = null;
        try{
            statement = (Statement) this.connection.createStatement();
        }catch (SQLException statement_exception){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, statement_exception);
            System.out.println("Error creating the statement object");
        }
        return statement;
    }
    
    private ResultSet getResultSet(Statement statement, String sql){
        ResultSet data = null;
        try{
           data = statement.executeQuery(sql); 
        }catch(SQLException data_exception){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, data_exception);
            System.out.println("Error getting the resultset");
        }
        return data;
    }
    
    public Map<String,? extends Object> getMap(){
        Statement statement = getStatement();
        String sql = "SHOW TABLES;";
        ResultSet data = getResultSet(statement, sql);
        HashMap<String,HashMap> result = new HashMap<>();
        try {
            while(data.next()){
                String table_name = data.getString(1);
                
                HashMap<String, ArrayList<String>> clasifications = new HashMap<>();
                ArrayList<? extends ArrayList> table_data = this.getTable(table_name);
                for(ArrayList<String> row : table_data){
                    String value = row.get(0);
                    String type = row.get(1);
                    String description = row.get(2);
                    if(clasifications.containsKey(type)){
                        clasifications.get(type).add(value);
                    }else{
                        ArrayList<String> arr = new ArrayList<>();
                        arr.add(value);
                        clasifications.put(type, arr);
                    }
                    
                }
                result.put(table_name, clasifications);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
