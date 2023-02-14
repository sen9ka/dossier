package ru.senya.dossier.entity.field;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;
import ru.senya.dossier.entity.enums.ChangeType;
import javax.json.bind.annotation.JsonbProperty;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@Table(name = "status_history")
public class StatusHistory {

    @JsonbProperty
    @Column(name = "status")
    private String status;

    @JsonbProperty
    @Column(name = "time")
    private LocalDateTime time;

    @JsonbProperty
    @Column(name = "change_type")
    private ChangeType changeType;

}
