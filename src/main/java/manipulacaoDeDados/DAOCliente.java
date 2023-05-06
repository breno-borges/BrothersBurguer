/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manipulacaoDeDados;

import connection.ConnectionFactory;
import dados.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author breno
 */
public class DAOCliente {

    private Connection con = null;

    /**
     * Construtor do DAOCliente que inicia a conexao com o Banco de Dados
     */
    public DAOCliente() {
        con = ConnectionFactory.getConnection();
    }

    /**
     * Metodo para inserir dados de cliente, INSERRT
     *
     * @param cliente
     * @return boolean true ou boolean false
     */
    public boolean insert(Cliente cliente) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO clientes (nome, CPF, telefone1, telefone2, email)"
                    + "VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getTelefone1());
            stmt.setString(4, cliente.getTelefone2());
            stmt.setString(5, cliente.getEmail());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de busca dos clientes, SELECT
     *
     * @return List clientes
     */
    public List<Cliente> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM clientes");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCPF(rs.getString("CPF"));
                cliente.setTelefone1(rs.getString("telefone1"));
                cliente.setTelefone2(rs.getString("telefone2"));
                cliente.setEmail(rs.getString("email"));

                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar clientes!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    /**
     * Metodo de atualizar dados do cliente, UPTADE
     *
     * @param cliente
     * @return boolean true ou false
     */
    public boolean update(Cliente cliente) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE clientes SET nome = ?, CPF = ?, telefone1 = ?, telefone2 = ?, "
                    + "email = ? WHERE id = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getTelefone1());
            stmt.setString(4, cliente.getTelefone2());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6, cliente.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de deletar cliente, DELETE
     *
     * @param cliente
     * @return boolean true ou false
     */
    public boolean delete(Cliente cliente) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM clientes WHERE id = ?");

            stmt.setInt(1, cliente.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
