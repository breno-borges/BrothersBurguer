/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manipulacaoDeDados;

import connection.ConnectionFactory;
import dados.ComponentesLanche;
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
public class DAOComponentesLanche {

    private Connection con = null;

    /**
     * Construtor do DAOComponentesLanche que inicia a conexao com o Banco de
     * Dados
     */
    public DAOComponentesLanche() {
        con = ConnectionFactory.getConnection();
    }

    /**
     * Metodo para inserir Componentes do Lanche, INSERRT
     *
     * @param componente
     * @return boolean true ou boolean false
     */
    public boolean insert(ComponentesLanche componente) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO produtos (nome, qtdEmEstoque, bebida)"
                    + "VALUES (?, ?, 0)");
            stmt.setString(1, componente.getNome());
            stmt.setInt(2, componente.getQtdEmEstoque());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Componente inserido com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de busca dos Componentes do Lanche, SELECT
     *
     * @return List componentes
     */
    public List<ComponentesLanche> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ComponentesLanche> componentes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE bebida = 0");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ComponentesLanche componente = new ComponentesLanche();
                componente.setId(rs.getInt("id"));
                componente.setNome(rs.getString("nome"));
                componente.setQtdEmEstoque(rs.getInt("qtdEmEstoque"));

                componentes.add(componente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar componentes do lanche!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return componentes;
    }

    /**
     * Metodo de atualizar dados dos Componentes do Lanche, UPTADE
     *
     * @param componente
     * @return boolean true ou false
     */
    public boolean update(ComponentesLanche componente) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos SET nome = ?, qtdEmEstoque = ? "
                    + "WHERE id = ? and bebida = 0");
            stmt.setString(1, componente.getNome());
            stmt.setInt(2, componente.getQtdEmEstoque());
            stmt.setInt(3, componente.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Componente atualizado com sucesso!");
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
     * @param componente
     * @return boolean true ou false
     */
    public boolean delete(ComponentesLanche componente) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE id = ? and bebida = 0");

            stmt.setInt(1, componente.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Componente excluída com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
