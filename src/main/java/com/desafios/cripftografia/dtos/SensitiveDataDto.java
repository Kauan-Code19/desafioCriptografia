package com.desafios.cripftografia.dtos;

import com.desafios.cripftografia.entities.sensitiveData.SensitiveDataEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SensitiveDataDto {
    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long value;

    public SensitiveDataDto(SensitiveDataEntity entity) {
        id = entity.getId();
        userDocument = entity.getUserDocument();
        creditCardToken = entity.getCreditCardToken();
        value = entity.getValue();
    }
}
