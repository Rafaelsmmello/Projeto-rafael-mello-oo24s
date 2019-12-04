/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.converter;

import br.edu.utfpr.pb.oo24s.model.EMotivo;
import javax.persistence.AttributeConverter;

/**
 *
 * @author Rafael Mello
 */
public class MotivoConverter implements 
                    AttributeConverter<EMotivo, String>{

    @Override
    public String convertToDatabaseColumn(EMotivo value) {
        return value.getId();
    }

    @Override
    public EMotivo convertToEntityAttribute(String value) {
        return EMotivo.findById(value);
    }
    
}
