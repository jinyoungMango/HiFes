package hiFes.hiFes.controller.festival;
import hiFes.hiFes.domain.festival.GetGift;
import hiFes.hiFes.service.festival.GetGiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
@Tag(name="회원-aritem 컨트롤러")
public class GetGiftApiController {
    @Autowired
    private GetGiftService getGiftService;
    @Operation(summary = "특정 일반회원이 어떤 ar아이템을 획득했는지 등록")
    @PostMapping("/{normalUserId}/get-gift/{itemId}")

    public Boolean saveGetGift(@PathVariable Long normalUserId, @PathVariable Long itemId) {
        return getGiftService.saveGetGift(normalUserId, itemId);
    }
}
