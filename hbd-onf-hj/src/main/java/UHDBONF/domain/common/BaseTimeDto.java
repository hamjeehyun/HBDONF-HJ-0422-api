package UHDBONF.domain.common;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeDto<T> {
    private LocalDateTime created;
    private LocalDateTime updated;

    public BaseTimeDto() {

    }
}
