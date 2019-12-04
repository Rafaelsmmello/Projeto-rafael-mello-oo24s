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
public enum EMotivo {
    
    TRABALHO("Trabalho"),
    PASSEIO("Passeio"),
    TURISMO("Turismo");
    
    private final String id;

    EMotivo(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public static EMotivo findById(String id) {
        for (EMotivo EMotivo : EMotivo.values()) {
            if (EMotivo.getId().equals(id)) return EMotivo;
        }
        throw new IllegalArgumentException("Motivo inválido!");
    }
}
