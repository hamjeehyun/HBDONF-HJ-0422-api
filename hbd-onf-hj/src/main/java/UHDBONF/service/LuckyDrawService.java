package UHDBONF.service;

import UHDBONF.domain.dto.LuckyDrawDto;

import java.util.List;

public interface LuckyDrawService {
    String registerLuckyDrawByUserUid(String userUid);
    LuckyDrawDto findLuckyDrawByUid(String id);
    List<LuckyDrawDto> findAllOpenLuckyDrawByUserUid(String userUid);
    String modifyLuckyDrawCloseByUid(String uid);
}
