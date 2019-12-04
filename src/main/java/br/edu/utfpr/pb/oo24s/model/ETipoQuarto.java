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
    ECONOMICO("Economico"),
    SUPERIOR("Superior"),
    LUXO("Luxo");
    
    private final String id;

    ETipoQuarto(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public static ETipoQuarto findById(String id) {
        for (ETipoQuarto tipoContato : ETipoQuarto.values()) {
            if (tipoContato.getId().equals(id)) return tipoContato;
        }
        throw new IllegalArgumentException("Tipo de quarto inválido!");
    }
}
