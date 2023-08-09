package hiFes.hiFes.Controller;

import hiFes.hiFes.Service.EventNotificationService;
import hiFes.hiFes.Service.GetGiftService;
import hiFes.hiFes.domain.*;
import hiFes.hiFes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class GetGiftApiController {
    @Autowired
    private GetGiftService getGiftService;
    @PostMapping("api/{normalUserId}/get-gift/{itemId}")

    public GetGift saveGetGift(@PathVariable Long normalUserId, @PathVariable Long itemId) {
        return getGiftService.saveGetGift(normalUserId, itemId);
    }
}
