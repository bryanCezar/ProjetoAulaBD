/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;
import br.com.DTO.UsuarioDTO;
import br.com.bios.TelaLogin;
import br.com.bios.TelaPrincipal;
import br.com.bios.TelaUsuarios;
import static br.com.bios.TelaUsuarios.txtIdUsuario;
import static br.com.bios.TelaUsuarios.txtNomeUsuario;
import static br.com.bios.TelaUsuarios.txtSenha;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno.saolucas
 */
public class UsuarioDAO {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    
    
    
    
    
    public void inserirUsuario(UsuarioDTO objUsuarioDTO){
        String sql = "Insert into tb_usuarios(id, usuario, login, senha) values(?, ?, ?, ?)";
        conexao = ConexaoDAO.connector();
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getIdUsuario());
            pst.setString(2, objUsuarioDTO.getNomeUsuario());
            pst.setString(3, objUsuarioDTO.getLoginUsuario());
            pst.setString(4, objUsuarioDTO.getSenhaUsuario());
              
            
              int add = pst.executeUpdate();
              if(add > 0){
                  JOptionPane.showMessageDialog(null, "Adicionado com sucesso");
              }
              pst.close();
        } catch(Exception e){
             if(e.getMessage().contains("tb_usuarios.PRIMARY")){
           JOptionPane.showMessageDialog(null, "Id já está em uso");
             }else if(e.getMessage().contains("tb_usuarios.login_UNIQUE")){
                   JOptionPane.showMessageDialog(null, "Login já está em uso");
       }else{
            JOptionPane.showMessageDialog(null, "Erro no método inserirUsuario "+ e);
             }
            
        }
    }
    
    
     public void logar(UsuarioDTO objUsuarioDTO){
       String sql = "select * from tb_usuarios where usuario = ? and senha = ?";
       conexao = ConexaoDAO.connector();
       try{
           // preparar a consulta no banco, em função ao que foi inserido nas caixas de texto
           pst = conexao.prepareStatement(sql);
           pst.setString(1, objUsuarioDTO.getNomeUsuario());
           pst.setString(2, objUsuarioDTO.getSenhaUsuario());
           
           //executa a query
           rs = pst.executeQuery();
           //verifica se existe usuario
           if(rs.next()){
               TelaPrincipal principal = new TelaPrincipal();
               principal.setVisible(true);
           }else{
               JOptionPane.showMessageDialog(null, "Usuário e/ou senha invalidos");
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Método Login" + e);
       }
    }
    
    
    
     public void pesquisar(UsuarioDTO objUsuarioDTO) {
        String sql = "select * from tb_usuarios where id = ?";
        conexao= ConexaoDAO.connector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getIdUsuario());
            rs = pst.executeQuery();

            if (rs.next()) {
                TelaUsuarios.txtNomeUsuario.setText(rs.getString(1));
                TelaUsuarios.txtSenha.setText(rs.getString(2));
                TelaUsuarios.txtLogin.setText(rs.getString(3));
                
            } else {
                limpar();
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método Pesquisar" + e.getMessage());
        }
    }
     
     
     
     public void editar(UsuarioDTO objUsuarioDTO){
         String sql = "update tb_usuarios set usuario = ?, login = ?, senha = ? where id = ?";
         conexao = ConexaoDAO.connector();
         
         try{
             pst = conexao.prepareStatement(sql);
             pst.setString(1, objUsuarioDTO.getNomeUsuario());
             pst.setString(2, objUsuarioDTO.getLoginUsuario());
             pst.setString(3, objUsuarioDTO.getSenhaUsuario());
             pst.setInt(4, objUsuarioDTO.getIdUsuario());
             
            int add = pst.executeUpdate();
            if(add>0){
                JOptionPane.showMessageDialog(null, "Editado com sucesso");
            }
             
             
             
         }catch(Exception e){
            JOptionPane.showMessageDialog(null," Método editar "+ e);
                 
         
     }  
     }
         public void apagar(UsuarioDTO objUsuarioDTO){
            String sql = "delete from tb_usuarios where id = ?";
            conexao = ConexaoDAO.connector();
            
            try{
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, objUsuarioDTO.getIdUsuario());
                int add = pst.executeUpdate();
                if(add > 0){
                    conexao.close();
                    JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso");
                
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null," Método apagar "+ e);
            }
         }
         
          public void limpar() {
        TelaUsuarios.txtIdUsuario.setText(null);
        TelaUsuarios.txtNomeUsuario.setText(null);
         TelaUsuarios.txtSenha.setText(null);
         TelaUsuarios.txtLogin.setText(null);

    }
    
}
