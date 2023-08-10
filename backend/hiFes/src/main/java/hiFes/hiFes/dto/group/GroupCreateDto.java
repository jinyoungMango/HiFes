package hiFes.hiFes.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GroupCreateDto {
    private String groupName;
    private LocalDateTime createdAt;
    private Integer maxPop;
    private String content;
    private Integer groupPassword;

    private String[] hashtags;
}
