/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.model;

/**
 *
 * @author Rafael Mello
 */
public enum ECategoria {
    PRODUTOS ("Produtos"),
    SERVICOS("Serviços");
    
    private final String id;

    ECategoria(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public static ECategoria findById(String id) {
        for (ECategoria ECategoria : ECategoria.values()) {
            if (ECategoria.getId().equals(id)) return ECategoria;
        }
        throw new IllegalArgumentException("Categoria do produto inválida!");
    }
}
