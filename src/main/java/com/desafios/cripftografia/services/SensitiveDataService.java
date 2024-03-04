package com.desafios.cripftografia.services;

import com.desafios.cripftografia.dtos.SensitiveDataDto;
import com.desafios.cripftografia.entities.sensitiveData.SensitiveDataEntity;
import com.desafios.cripftografia.repositories.SensitiveDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SensitiveDataService {
    private final SensitiveDataRepository sensitiveDataRepository;
    private final EncryptionService encryptionService;

    @Autowired
    public SensitiveDataService(SensitiveDataRepository sensitiveDataRepository, EncryptionService encryptionService) {
        this.sensitiveDataRepository = sensitiveDataRepository;
        this.encryptionService = encryptionService;
    }

    @Transactional
    public SensitiveDataDto createSensitiveData(SensitiveDataDto sensitiveDataDto) {
        SensitiveDataEntity entity = new SensitiveDataEntity();

        String encryptedUserDocument = encryptionService.encryptString(sensitiveDataDto.getUserDocument());
        String encryptedCreditCardToken = encryptionService.encryptString(sensitiveDataDto.getCreditCardToken());

        entity.setUserDocument(encryptedUserDocument);
        entity.setCreditCardToken(encryptedCreditCardToken);
        entity.setValue(sensitiveDataDto.getValue());

        entity = sensitiveDataRepository.save(entity);

        return new SensitiveDataDto(entity);
    }

    @Transactional
    public SensitiveDataDto getSensitiveData(Long idSensitiveData) {
        SensitiveDataEntity entity = sensitiveDataRepository.getReferenceById(idSensitiveData);

        String decryptedUserDocument = encryptionService.decryptString(entity.getUserDocument());
        String decryptedCreditCardToken = encryptionService.decryptString(entity.getCreditCardToken());

        entity.setUserDocument(decryptedUserDocument);
        entity.setCreditCardToken(decryptedCreditCardToken);

        return new SensitiveDataDto(entity);
    }

    @Transactional
    public SensitiveDataDto updateSensitiveData(Long idSensitiveData, SensitiveDataDto sensitiveDataDto) {
        SensitiveDataEntity entity = sensitiveDataRepository.getReferenceById(idSensitiveData);

        String encryptedUserDocument = encryptionService.encryptString(sensitiveDataDto.getUserDocument());
        String encryptedCreditCardToken = encryptionService.encryptString(sensitiveDataDto.getCreditCardToken());

        entity.setUserDocument(encryptedUserDocument);
        entity.setCreditCardToken(encryptedCreditCardToken);
        entity.setValue(sensitiveDataDto.getValue());

        entity = sensitiveDataRepository.save(entity);

        return new SensitiveDataDto(entity);
    }

    @Transactional
    public void deleteSensitiveData(Long idSensitiveData) {
        sensitiveDataRepository.deleteById(idSensitiveData);
    }

}
