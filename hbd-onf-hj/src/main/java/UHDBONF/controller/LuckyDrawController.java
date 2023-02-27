package UHDBONF.controller;

import UHDBONF.service.LuckyDrawService;
import UHDBONF.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hj/lucky-draws")
public class LuckyDrawController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LuckyDrawController.class);
    @Autowired
    private LuckyDrawService luckyDrawService;

    /**
     * 럭드 등록 by user-uid
     * @param userUid
     * @return
     */
    @PostMapping("user-uid/{user-uid}")
    public String registerLuckyDrawByUserUid(@PathVariable(name = "user-uid") String userUid) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("효렌치파이 전시가 마감되었습니다.");
        }
        throw ExceptionUtil.createOnfBizException("효렌치파이 전시가 마감되었습니다.");
//        return luckyDrawService.registerLuckyDrawByUserUid(userUid);
    }

    /**
     * 럭드 비공개 설정
     * @param uid
     * @return
     */
    @PutMapping("uid/{uid}/close")
    public String modifyLuckyDrawCloseByUid(@PathVariable(name = "uid") String uid){
        return luckyDrawService.modifyLuckyDrawCloseByUid(uid);
    }
}
