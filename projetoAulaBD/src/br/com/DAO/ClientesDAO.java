
package br.com.DAO;

import br.com.DTO.ClientesDTO;
import br.com.bios.TelaClientes;
import br.com.bios.TelaUsuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;




public class ClientesDAO {
          Connection conexao = null;
          PreparedStatement pst = null;
          ResultSet rs = null;
          
           public void limpar() {
        TelaClientes.txtId.setText(null);
        TelaClientes.txtNome.setText(null);
        TelaClientes.txtEndereco.setText(null);
        TelaClientes.txtTelefone.setText(null);
        TelaClientes.txtEmail.setText(null);
        TelaClientes.txtCpf.setText(null);

    }
           public void pesquisar(ClientesDTO objClienteDTO) {
        String sql = "select * from tb_clientes where id = ?";
        conexao= ConexaoDAO.connector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objClienteDTO.getId());
            rs = pst.executeQuery();

            if (rs.next()) {
                TelaClientes.txtNome.setText(rs.getString(2));
                TelaClientes.txtEndereco.setText(rs.getString(3));
                TelaClientes.txtTelefone.setText(rs.getString(4));
                TelaClientes.txtEmail.setText(rs.getString(5));
                TelaClientes.txtCpf.setText(rs.getString(6));
            } else {
                limpar();
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método Pesquisar" + e.getMessage());
        }
    }
}
