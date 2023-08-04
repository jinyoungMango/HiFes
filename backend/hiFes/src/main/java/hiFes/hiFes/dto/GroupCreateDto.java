package hiFes.hiFes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GroupCreateDto {
    private String groupName;
    private String groupPic;
    private LocalDateTime createdAt;
    private Integer maxPop;
    private String content;
    private BigDecimal getterLatitude;
    private BigDecimal getterLongitude;
    private String getterOutline;
    private Integer groupPassword;
}
