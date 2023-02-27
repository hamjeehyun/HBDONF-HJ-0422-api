package UHDBONF.domain.entity;

import UHDBONF.domain.common.BaseTimeEntity;
import UHDBONF.domain.dto.UserDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user")
public class User extends BaseTimeEntity<UserDto> {
    @Id @Column(name = "id")
    private String uid;
    private String tid;
    private String region;
    private String tnickName;

    public User() {
        super();
    }

    public User(UserDto dto) {
        super(dto);
    }

    @Override
    public void update(UserDto userDto) {
        BeanUtils.copyProperties(userDto, this);

    }

    @Override
    public UserDto toDto() {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(this, userDto);

        return userDto;
    }
}
