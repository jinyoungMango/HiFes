package hiFes.hiFes.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupListDto {
    private String groupName;
    private String groupPic;
    private LocalDateTime createdAt;
    private Integer maxPop;
    private String content;
    private Integer groupPassword;
}
