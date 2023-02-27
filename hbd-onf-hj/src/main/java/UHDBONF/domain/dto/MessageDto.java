package UHDBONF.domain.dto;

import UHDBONF.domain.common.BaseTimeDto;
import lombok.Data;

@Data
public class MessageDto extends BaseTimeDto<MessageDto> {
    private String uid;
    private String title;
    private String content;
    private boolean expose;
    private String colorCode;
    private int keyNumber;
    private SealDto seal;
    private UserDto user;

    public MessageDto(String uid){
        this.uid = uid;
    }

    public MessageDto() {
    }
}
