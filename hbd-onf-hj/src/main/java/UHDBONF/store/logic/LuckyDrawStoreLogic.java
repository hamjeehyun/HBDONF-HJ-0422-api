package UHDBONF.store.logic;

import UHDBONF.domain.dto.LuckyDrawDto;
import UHDBONF.domain.entity.LuckyDraw;
import UHDBONF.store.LuckyDrawStore;
import UHDBONF.store.criteria.LuckyDrawSearchKeys;
import UHDBONF.store.criteria.LuckyDrawSearchSpecs;
import UHDBONF.store.repository.LuckyDrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LuckyDrawStoreLogic implements LuckyDrawStore {

    @Autowired
    private LuckyDrawRepository luckyDrawRepository;

    @Override
    public String create(LuckyDrawDto luckyDrawDto) {
        LuckyDraw luckyDraw = new LuckyDraw(luckyDrawDto);
        luckyDrawRepository.save(luckyDraw);
        return luckyDraw.getUid();
    }

    @Override
    public LuckyDrawDto readByUid(String uid) {
        LuckyDraw luckyDraw = luckyDrawRepository.findById(uid).orElse(null);
        if (luckyDraw==null){
            return null;
        }
        return luckyDraw.toDto();
    }

    @Override
    public int readMaxSequence() {
        LuckyDraw luckyDraw = luckyDrawRepository.findTopByOrderBySequenceDesc();
        if (luckyDraw== null) {
            return -1;
        }
        return luckyDraw.getSequence();
    }

    @Override
    public List<LuckyDrawDto> readAllByTid(String tid) {
        Map<LuckyDrawSearchKeys, Object> searchKeys = new HashMap<>();

        searchKeys.put(LuckyDrawSearchKeys.TID, tid);
        List<LuckyDraw> luckyDraws = luckyDrawRepository.findAll(LuckyDrawSearchSpecs.searchWith(searchKeys));
        return luckyDraws.stream().map(LuckyDraw::toDto).collect(Collectors.toList());
    }

    @Override
    public List<LuckyDrawDto> readAllByUserUid(String userUid) {
        Map<LuckyDrawSearchKeys, Object> searchKeys = new HashMap<>();

        searchKeys.put(LuckyDrawSearchKeys.USER_UID, userUid);

        List<LuckyDraw> luckyDraws = luckyDrawRepository.findAll(LuckyDrawSearchSpecs.searchWith(searchKeys));
        return luckyDraws.stream().map(LuckyDraw::toDto).collect(Collectors.toList());
    }

    @Override
    public List<LuckyDrawDto> realAllOpenLuckyDrawByTid(String tid) {
        Map<LuckyDrawSearchKeys, Object> searchKeys = new HashMap<>();

        searchKeys.put(LuckyDrawSearchKeys.TID, tid);
        searchKeys.put(LuckyDrawSearchKeys.EXPOSE, true);

        List<LuckyDraw> luckyDraws = luckyDrawRepository.findAll(LuckyDrawSearchSpecs.searchWith(searchKeys));
        return luckyDraws.stream().map(LuckyDraw::toDto).collect(Collectors.toList());
    }

    @Override
    public List<LuckyDrawDto> realAllOpenLuckyDrawByUserUid(String userUid) {
        Map<LuckyDrawSearchKeys, Object> searchKeys = new HashMap<>();

        searchKeys.put(LuckyDrawSearchKeys.USER_UID, userUid);
        searchKeys.put(LuckyDrawSearchKeys.EXPOSE, true);

        List<LuckyDraw> luckyDraws = luckyDrawRepository.findAll(LuckyDrawSearchSpecs.searchWith(searchKeys));
        return luckyDraws.stream().map(LuckyDraw::toDto).collect(Collectors.toList());
    }
}
