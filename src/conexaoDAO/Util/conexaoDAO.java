
package conexaoDAO.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class conexaoDAO {
 
    
    public static Connection getConnection() {
    	Connection conexao = null;
    	try
    	{
	    	Class.forName("com.mysql.jdbc.Driver"); 
	    	System.out.println("Driver carregado");
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Erro ao carregar o driver");
    	}

       try{
           conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/consultorio", "root", "1q2w3e4r5t");
           System.out.println("Conexão ok");
       }catch(Exception e){
          System.out.println(e.getMessage());
          conexao = null;
       }
       
       return conexao;
       
   }



   public static void close(Connection conn, Statement stmt, ResultSet rs) {
       try{
           if (rs != null) rs.close();
           if (stmt != null) stmt.close();
           if (conn != null) conn.close();
       }catch(Exception e){
    	   System.out.println(e.getMessage());
       }
   }
   
   
   public static void close(Connection conn, Statement stmt) {
       try{
           if (stmt != null) stmt.close();
           if (conn != null) conn.close();
       }catch(Exception e){
    	   System.out.println(e.getMessage());
       }
   }


}