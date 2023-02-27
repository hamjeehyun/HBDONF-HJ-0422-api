package UHDBONF.store.logic;

import UHDBONF.domain.dto.SealDto;
import UHDBONF.domain.entity.Seal;
import UHDBONF.store.SealStore;
import UHDBONF.store.repository.SealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SealStoreLogic implements SealStore {

    @Autowired
    private SealRepository sealRepository;


    @Override
    public String create(SealDto sealDto) {
        Seal seal = new Seal(sealDto);
        sealRepository.save(seal);
        return seal.getId();
    }

    @Override
    public SealDto readById(String id) {
        Seal seal = sealRepository.findById(id).orElse(null);
        if (seal==null){
            return null;
        }
        return seal.toDto();
    }

    @Override
    public List<SealDto> readAll() {
        List<Seal> seals = sealRepository.findAll();
        return seals.stream().map(Seal::toDto).collect(Collectors.toList());
    }
}
