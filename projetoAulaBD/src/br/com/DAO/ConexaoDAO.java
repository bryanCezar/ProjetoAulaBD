
package br.com.DAO;

import br.com.bios.TelaPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class ConexaoDAO {
    public static Connection connector(){
    
    java.sql.Connection conexao = null;
    
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/bdprojetoaula";
    String user = "root";
    String password = "root";
    
   try{
       Class.forName(driver);
       
       conexao = DriverManager.getConnection(url, user, password);
       return conexao;
   } catch (Exception e){
       JOptionPane.showMessageDialog(null, " Classe de Conexão " + e);
       return null;
   }       
    }
      
      }
    
    
