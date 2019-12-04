package br.edu.utfpr.pb.oo24s.converter;

import br.edu.utfpr.pb.oo24s.model.ETipoQuarto;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class TipoQuartoConverter implements 
                    AttributeConverter<ETipoQuarto, String>{

    @Override
    public String convertToDatabaseColumn(ETipoQuarto value) {
        return value.getId();
    }

    @Override
    public ETipoQuarto convertToEntityAttribute(String value) {
        return ETipoQuarto.findById(value);
    }
    
}
