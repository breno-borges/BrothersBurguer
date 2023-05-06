/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Breno
 */
public class ConnectionFactory {
    
    private final static String URL = "jdbc:mysql://localhost:3306/Hamburgueria";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    
    
    /**
     * Inicia a conexão com o banco de dados
     * @return DriverManager.getConnection(URL, USER, PASSWORD) 
     */
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexao! " + ex);
        }
    }
    
    /**
     * Fecha a conexao com o banco de dados
     * @param con 
     */
    public static void closeConnection(Connection con){
        
        if(con != null){
            try {
                con.close();
            } 
            catch (SQLException ex) {
                System.err.println("Erro! " + ex);
            }
        }
    }
    
    /**
     * Fecha a conexão com o banco de dados e o Statement
     * @param con
     * @param stmt 
     */
    public static void closeConnection(Connection con, PreparedStatement stmt){
        
        if(stmt != null){
            try {
                stmt.close();
            }
            catch (SQLException ex) {
                System.err.println("Erro! " + ex);
            }
        }
        
        closeConnection(con);
    }
    
    /**
     * Fecha a conexão com o banco de dados, o Statement e o ResultSet
     * @param con
     * @param stmt
     * @param rs 
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        
        if(rs != null){
            try {
                rs.close();
            } 
            catch (SQLException ex) {
                System.err.println("Erro! " + ex);
            }
        }
        
        closeConnection(con, stmt);
    }
}
