package UHDBONF.domain.entity;

import UHDBONF.domain.common.BaseTimeEntity;
import UHDBONF.domain.dto.LuckyDrawDto;
import UHDBONF.domain.dto.UserDto;
import UHDBONF.util.JsonUtil;
import UHDBONF.util.StringUtil;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Data
@Table(name = "luckyDraw")
public class LuckyDraw extends BaseTimeEntity<LuckyDrawDto> {
    @Id @Column(name = "id")
    @GeneratedValue(generator = "lucky_draw_uuid")
    @GenericGenerator(name = "lucky_draw_uuid", strategy = "uuid2")
    private String uid;
    private int sequence;
    private String tid;
    private boolean expose;
    @Lob
    private String userData;
    private String userUid;

    public LuckyDraw() {

    }

    public LuckyDraw(LuckyDrawDto dto) {
        super(dto);
    }

    @Override
    public void update(LuckyDrawDto dto) {
        BeanUtils.copyProperties(dto, this);
        if (dto.getUser() != null) {
            userData = JsonUtil.toJson(dto.getUser());
            tid = dto.getUser().getTid();
            userUid = dto.getUser().getUid();
        }
    }

    @Override
    public LuckyDrawDto toDto() {
        LuckyDrawDto dto = new LuckyDrawDto();
        BeanUtils.copyProperties(this, dto);
        if (StringUtil.isNotBlank(userData)) {
            UserDto user = JsonUtil.fromJson(userData, UserDto.class);
            dto.setUser(user);
        }
        return dto;
    }
}
