package UHDBONF.store;

import UHDBONF.domain.dto.LuckyDrawDto;

import java.util.List;

public interface LuckyDrawStore {
    String create(LuckyDrawDto luckyDrawDto);
    LuckyDrawDto readByUid(String uid);
    int readMaxSequence();
    List<LuckyDrawDto> readAllByTid(String tid);
    List<LuckyDrawDto> readAllByUserUid(String userUid);
    List<LuckyDrawDto> realAllOpenLuckyDrawByTid(String tid);
    List<LuckyDrawDto> realAllOpenLuckyDrawByUserUid(String userUid);
}
