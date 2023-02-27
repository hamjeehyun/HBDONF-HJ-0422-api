package UHDBONF.service.logic;

import UHDBONF.domain.dto.SealDto;
import UHDBONF.service.SealService;
import UHDBONF.store.SealStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SealServiceLogic implements SealService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SealServiceLogic.class);

    @Autowired
    private SealStore sealStore;

    @Override
    public List<SealDto> findAllSeal() {
        return sealStore.readAll();
    }

    @Override
    public String registerSeal(SealDto sealDto) {
        return sealStore.create(sealDto);
    }

    @Override
    public String registerSealList(List<SealDto> sealDtoList) {
        for(SealDto sealDto : sealDtoList) {
            sealStore.create(sealDto);
        }
        return "OK";
    }

    @Override
    public SealDto findSealById(String id) {
        return sealStore.readById(id);
    }

}
