/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacaoDeDados;

import connection.ConnectionFactory;
import dados.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Breno
 */
public class DAOUsuario {

    private Connection con = null;

    /**
     * Construtor do DAOUsuario que inicia a conexao com o Banco de Dados
     */
    public DAOUsuario() {
        con = ConnectionFactory.getConnection();
    }

    /**
     * Metodo para inserir dados de usuario, INSERRT
     *
     * @param usuario
     * @return boolean true ou boolean false
     */
    public boolean insert(Usuario usuario) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO usuarios (nome, usuario, senha, iniciais)"
                    + "VALUES (?, ?, ?, ?)");
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getIniciais());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de busca dos usuarios, SELECT
     *
     * @return List usuarios
     */
    public List<Usuario> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuarios");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNomeCompleto(rs.getString("nome"));
                usuario.setIniciais(rs.getString("iniciais"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));

                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar usuários!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return usuarios;
    }

    /**
     * Metodo de atualizar dados do usuário, UPTADE
     *
     * @param usuario
     * @return boolean true ou false
     */
    public boolean update(Usuario usuario) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuarios SET nome = ?, usuario = ?, senha = ?, iniciais = ? "
                    + "WHERE id = ?");
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getIniciais());
            stmt.setInt(5, usuario.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de deletar usuario, DELETE
     *
     * @param usuario
     * @return boolean true ou false
     */
    public boolean delete(Usuario usuario) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM usuarios WHERE id = ?");

            stmt.setInt(1, usuario.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario excluído com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     * Metodo de busca do usuario para login, SELECT
     *
     * @param user
     * @param senha
     * @return List usuarios
     */
    public List<Usuario> buscarLogin(String user, String senha) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuarios "
                    + "WHERE usuario = ? and senha = ?");
            
            stmt.setString(1, user);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNomeCompleto(rs.getString("nome"));
                usuario.setIniciais(rs.getString("iniciais"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));

                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar usuário!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return usuarios;
    }
    
    /**
     * Metodo de busca dos usuarios pelo Id, SELECT
     *
     * @return List usuarios
     */
    public List<Usuario> readId(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNomeCompleto(rs.getString("nome"));
                usuario.setIniciais(rs.getString("iniciais"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));

                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar usuários!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return usuarios;
    }
}
