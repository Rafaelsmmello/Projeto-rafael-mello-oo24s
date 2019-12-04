/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.dao;

import br.edu.utfpr.pb.oo24s.model.Produto;
import javax.persistence.Query;

public class ProdutoDao extends GenericDao<Produto, Long> {

    public ProdutoDao() {
        super(Produto.class);
    }
    
    public Produto findByDescricaoQuery(String descricao){
        Query query = em.createNamedQuery(
                Produto.FIND_BY_DESCRICAO);
        query.setParameter("descricao", descricao);
        
        return (Produto) query.getSingleResult();
    }
}
