package hiFes.hiFes.dto.fcmDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FCMForGroupDto {
    private Long groupId;
    private String description;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
