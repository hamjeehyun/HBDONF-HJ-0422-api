package UHDBONF.domain.entity;

import UHDBONF.domain.common.BaseTimeEntity;
import UHDBONF.domain.dto.MessageDto;
import UHDBONF.domain.dto.UserDto;
import UHDBONF.util.JsonUtil;
import UHDBONF.util.StringUtil;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@Entity
@Table(name = "message")
public class Message extends BaseTimeEntity<MessageDto> {
    @Id @Column(name = "id")
    @GeneratedValue(generator = "message_uuid")
    @GenericGenerator(name = "message_uuid", strategy = "uuid2")
    private String uid;
    private String title;
    private String content;
    private boolean expose;
    private String colorCode;
    private int keyNumber;
    @Lob
    private String userData;
    private String tid;
    private String userUid;

    public Message() {

    }
    public Message(MessageDto dto) {
        super(dto);
    }

    @Override
    public void update(MessageDto dto) {
        BeanUtils.copyProperties(dto, this);

        if (dto.getUser() != null) {
            userData = JsonUtil.toJson(dto.getUser());
            tid = dto.getUser().getTid();
            userUid = dto.getUser().getUid();
        }
    }

    @Override
    public MessageDto toDto() {
        MessageDto dto = new MessageDto();
        BeanUtils.copyProperties(this, dto);

        if (StringUtil.isNotBlank(userData)) {
            UserDto user = JsonUtil.fromJson(userData, UserDto.class);
            dto.setUser(user);
        }
        return dto;
    }

}
