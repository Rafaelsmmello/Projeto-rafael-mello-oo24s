/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.dao;

import br.edu.utfpr.pb.oo24s.model.Cliente;

public class ClienteDao  extends GenericDao<Cliente, Long> {

    public ClienteDao() {
        super(Cliente.class);
    }
}
