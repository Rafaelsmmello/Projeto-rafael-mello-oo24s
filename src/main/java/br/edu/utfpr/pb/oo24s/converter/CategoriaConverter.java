/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.converter;

import br.edu.utfpr.pb.oo24s.model.ECategoria;
import javax.persistence.AttributeConverter;

public class CategoriaConverter implements
        AttributeConverter<ECategoria, Integer>{

    @Override
    public Integer convertToDatabaseColumn(ECategoria value) {
        return value.getId();
    }

    @Override
    public ECategoria convertToEntityAttribute(Integer value) {
        return ECategoria.findById(value);
    }
    
}
