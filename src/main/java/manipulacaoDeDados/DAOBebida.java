/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manipulacaoDeDados;

import connection.ConnectionFactory;
import dados.Bebida;
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
public class DAOBebida {

    private Connection con = null;

    /**
     * Construtor do DAOBebida que inicia a conexao com o Banco de Dados
     */
    public DAOBebida() {
        con = ConnectionFactory.getConnection();
    }

    /**
     * Metodo para inserir bebidas, INSERRT
     *
     * @param bebida
     * @return boolean true ou boolean false
     */
    public boolean insert(Bebida bebida) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO produtos (nome, precoUnitario, qtdEmEstoque, bebida)"
                    + "VALUES (?, ?, ?, 1)");
            stmt.setString(1, bebida.getNome());
            stmt.setDouble(2, bebida.getValorUnitario());
            stmt.setInt(3, bebida.getQtdEmEstoque());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bebida inserida com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de busca das Bebidas, SELECT
     *
     * @return List bebidas
     */
    public List<Bebida> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Bebida> bebidas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE bebida = 1");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bebida bebida = new Bebida();
                bebida.setId(rs.getInt("id"));
                bebida.setNome(rs.getString("nome"));
                bebida.setValorUnitario(rs.getDouble("precoUnitario"));
                bebida.setQtdEmEstoque(rs.getInt("qtdEmEstoque"));

                bebidas.add(bebida);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar bebidas!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return bebidas;
    }

    /**
     * Metodo de atualizar dados da bebida, UPTADE
     *
     * @param bebida
     * @return boolean true ou false
     */
    public boolean update(Bebida bebida) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos SET nome = ?, precoUnitario = ?, qtdEmEstoque = ? "
                    + "WHERE id = ? and bebida = 1");
            stmt.setString(1, bebida.getNome());
            stmt.setDouble(2, bebida.getValorUnitario());
            stmt.setInt(3, bebida.getQtdEmEstoque());
            stmt.setInt(4, bebida.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bebida atualizada com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de deletar bebida, DELETE
     *
     * @param bebida
     * @return boolean true ou false
     */
    public boolean delete(Bebida bebida) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE id = ? and bebida = 1");

            stmt.setInt(1, bebida.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bebida excluída com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
