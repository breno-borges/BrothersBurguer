/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacaoDeDados;

import connection.ConnectionFactory;
import dados.Bebida;
import dados.ComponentesLanche;
import dados.Lanches;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author breno
 */
public class DAOProdutos_Has_Lanches {
    
    private Connection con = null;
    
    /**
     * Construtor do DAOProdutos_Has_Lanches que inicia a conexao com o Banco de Dados
     */
    public DAOProdutos_Has_Lanches() {
        con = ConnectionFactory.getConnection();
    }
    
    /**
     * Metodo para inserir na tabela n para m Produtos_Has_Lanches, INSERRT
     *
     * @param lanche
     * @param produto
     * @return boolean true ou boolean false
     */
    public boolean insertHasB(Lanches lanche, Bebida produto) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO produtos_has_lanches (produto, lanche)"
                    + "VALUES ((select id from produtos where id = ?), ?)");
            stmt.setInt(1, produto.getId());
            stmt.setInt(2, lanche.getId());
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
     * Metodo para inserir na tabela n para m Produtos_Has_Lanches, INSERRT
     *
     * @param lanche
     * @param produto
     * @return boolean true ou boolean false
     */
    public boolean insertHasC(Lanches lanche, ComponentesLanche produto) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO produtos_has_lanches (produto, lanche)"
                    + "VALUES ((select id from produtos where id = ?), ?)");
            stmt.setInt(1, produto.getId());
            stmt.setInt(2, lanche.getId());
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
}
