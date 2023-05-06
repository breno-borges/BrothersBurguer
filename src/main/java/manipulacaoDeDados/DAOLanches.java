/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manipulacaoDeDados;

import connection.ConnectionFactory;
import dados.Lanches;
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
public class DAOLanches {

    private Connection con = null;

    /**
     * Construtor do DAOLanches que inicia a conexao com o Banco de Dados
     */
    public DAOLanches() {
        con = ConnectionFactory.getConnection();
    }

    /**
     * Metodo para inserir Lanches, INSERRT
     *
     * @param lanche
     * @return boolean true ou boolean false
     */
    public boolean insert(Lanches lanche) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO lanches (nome, preco)"
                    + "VALUES (?, ?)");
            stmt.setString(1, lanche.getNome());
            stmt.setDouble(2, lanche.getPreco());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Lanche inserido com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de busca dos Lanches, SELECT
     *
     * @return List lanches
     */
    public List<Lanches> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Lanches> lanches = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM lanches");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Lanches lanche = new Lanches();
                lanche.setId(rs.getInt("id"));
                lanche.setNome(rs.getString("nome"));
                lanche.setPreco(rs.getDouble("preco"));

                lanches.add(lanche);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lanches!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return lanches;
    }

    /**
     * Metodo de atualizar dados dos Lanches, UPTADE
     *
     * @param lanche
     * @return boolean true ou false
     */
    public boolean update(Lanches lanche) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE lanches SET nome = ?, preco = ? "
                    + "WHERE id = ?");
            stmt.setString(1, lanche.getNome());
            stmt.setDouble(2, lanche.getPreco());
            stmt.setInt(3, lanche.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Lanche atualizado com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de deletar Componente de Lanche, DELETE
     *
     * @param lanche
     * @return boolean true ou false
     */
    public boolean delete(Lanches lanche) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM lanches WHERE id = ?");

            stmt.setInt(1, lanche.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Lanche excluído com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
