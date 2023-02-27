package UHDBONF.store;

import UHDBONF.domain.dto.SealDto;

import java.util.List;

public interface SealStore {
    String create(SealDto sealDto);
    SealDto readById(String id);
    List<SealDto> readAll();
}
