/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.model;

import br.edu.utfpr.pb.oo24s.converter.TipoQuartoConverter;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quarto  implements AbstractModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    
    @Convert(converter = TipoQuartoConverter.class)
    @Column(name = "tipo", nullable = false)
    private ETipoQuarto tipoQuarto;
    
    @Column(nullable = false)
    private int qtd_camas;
    
    @Column(nullable = false)
    private int qtd_pessoas;
    
    @Column(nullable = false)
    private double valor;

    public Quarto() {
    }

    public Long getId() {
        return numero;
    }

    public void setId(Long id) {
        this.numero = id;
    }

    public ETipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(ETipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public int getQtd_camas() {
        return qtd_camas;
    }

    public void setQtd_camas(int qtd_camas) {
        this.qtd_camas = qtd_camas;
    }

    public int getQtd_pessoas() {
        return qtd_pessoas;
    }

    public void setQtd_pessoas(int qtd_pessoas) {
        this.qtd_pessoas = qtd_pessoas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.numero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quarto other = (Quarto) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

}
