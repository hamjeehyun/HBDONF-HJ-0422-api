package UHDBONF.domain.entity;

import UHDBONF.domain.dto.LanguageDto;
import UHDBONF.domain.dto.SealDto;
import UHDBONF.util.JsonUtil;
import UHDBONF.util.StringUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "seal")
public class Seal {
    @Id
    private String id;
    @Lob
    private String nameData;
    @Lob
    private String descriptionData;
    private String imgLink;
    private String memeLink;

    public Seal() {

    }

    public Seal(SealDto dto) {
        this.update(dto);
    }

    public void update(SealDto dto) {
        BeanUtils.copyProperties(dto, this);
        if (dto.getName() != null) {
            nameData = JsonUtil.toJson(dto.getName());
        }
        if (dto.getDescription() != null) {
            descriptionData = JsonUtil.toJson(dto.getDescription());
        }
    }

    public SealDto toDto() {
        SealDto dto = new SealDto();
        BeanUtils.copyProperties(this, dto);
        if (StringUtil.isNotBlank(nameData)) {
            LanguageDto name = JsonUtil.fromJson(nameData, LanguageDto.class);
            dto.setName(name);
        }
        if (StringUtil.isNotBlank(descriptionData)) {
            LanguageDto description = JsonUtil.fromJson(descriptionData, LanguageDto.class);
            dto.setDescription(description);
        }
        return dto;
    }
}
