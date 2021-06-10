package git.dimitrikvirik.anychat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDTO {
    public long id;
    public String text;
    public LocalDateTime createDate;
}
