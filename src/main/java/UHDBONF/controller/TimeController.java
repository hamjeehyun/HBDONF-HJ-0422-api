package UHDBONF.controller;

import UHDBONF.util.ExceptionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("hj/time")
public class TimeController {
    @GetMapping("kor")
    public LocalDateTime getKoreaLocalTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.plusHours(9);
    }
    @GetMapping
    public LocalDateTime getServerLocalTime() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    @GetMapping ("error")
    public String getErrorMessage() {
        throw ExceptionUtil.createOnfBizException("에러메세지 테스트");
    }
}
