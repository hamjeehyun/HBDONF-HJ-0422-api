package UHDBONF.domain.dto;

import lombok.Data;

@Data
public class SealDto {
    private String id;
    private LanguageDto name;
    private LanguageDto description;
    private String imgLink;
    private String memeLink;


    public SealDto(String id){
        this.id = id;
    }

    public SealDto() {
    }
}
