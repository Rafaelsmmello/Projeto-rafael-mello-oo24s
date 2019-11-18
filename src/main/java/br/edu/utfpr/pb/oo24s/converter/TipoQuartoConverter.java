package br.edu.utfpr.pb.oo24s.converter;

import br.edu.utfpr.pb.oo24s.model.ETipoQuarto;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class TipoQuartoConverter implements 
                    AttributeConverter<ETipoQuarto, Integer>{

    @Override
    public Integer convertToDatabaseColumn(ETipoQuarto value) {
        return value.getId();
    }

    @Override
    public ETipoQuarto convertToEntityAttribute(Integer value) {
        return ETipoQuarto.findById(value);
    }
    
}
