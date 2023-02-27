package UHDBONF.service;

import UHDBONF.domain.dto.SealDto;

import java.util.List;

public interface SealService {
    List<SealDto> findAllSeal();
    String registerSeal(SealDto sealDto);
    String registerSealList(List<SealDto> sealDtoList);
    SealDto findSealById(String keyNumber);
}
