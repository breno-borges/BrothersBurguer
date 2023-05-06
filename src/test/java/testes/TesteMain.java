/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dados.*;
import manipulacaoDeDados.*;

/**
 *
 * @author breno
 */
public class TesteMain {

    public static void main(String[] args) {
        
        Usuario u = new Usuario();
        u.setId(6);
        
        DAOUsuario dao = new DAOUsuario();
        dao.delete(u);
        
    }
}
