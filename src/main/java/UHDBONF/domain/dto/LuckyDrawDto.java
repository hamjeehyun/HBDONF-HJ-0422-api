package UHDBONF.domain.dto;

import UHDBONF.domain.common.BaseTimeDto;
import lombok.Data;

@Data
public class LuckyDrawDto extends BaseTimeDto<LuckyDrawDto> {
    private String uid;
    private int sequence;
    private boolean expose;
    private UserDto user;


    public LuckyDrawDto(String uid){
        this.uid = uid;
    }

    public LuckyDrawDto() {
    }
}
