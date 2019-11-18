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
public enum ETipoQuarto {
    ECONOMICO(11),
    SUPERIOR(22),
    LUXO(33);
    
    private final Integer id;

    ETipoQuarto(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public static ETipoQuarto findById(Integer id) {
        for (ETipoQuarto tipoContato : ETipoQuarto.values()) {
            if (tipoContato.getId().equals(id)) return tipoContato;
        }
        throw new IllegalArgumentException("Tipo de quarto inválido!");
    }
}
