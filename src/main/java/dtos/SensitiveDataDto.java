package dtos;

import entities.SensitiveDataEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SensitiveDataDto {
    private Long id;
    private String UserDocument;
    private String creditCardToken;
    private Long value;

    public SensitiveDataDto(SensitiveDataEntity entity) {
        id = entity.getId();
        UserDocument = entity.getUserDocument();
        creditCardToken = entity.getCreditCardToken();
        value = entity.getValue();
    }
}
