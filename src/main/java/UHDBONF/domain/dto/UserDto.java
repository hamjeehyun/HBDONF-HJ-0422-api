package UHDBONF.domain.dto;

import UHDBONF.domain.common.BaseTimeDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto extends BaseTimeDto<UserDto>{
    private String uid;
    private String tid;
    private String region;
    private String tnickName;

    private List<MessageDto> message;
    private List<LuckyDrawDto> luckyDraws;

    public UserDto(String tid){
        this.tid = tid;
    }
    public UserDto() {

    }

}
