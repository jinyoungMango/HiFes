package hiFes.hiFes.service;


import hiFes.hiFes.domain.*;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.repository.*;
import hiFes.hiFes.repository.user.NormalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class GetGiftService {
    @Autowired
    private final GetGiftRepository getGiftRepository;
    private final NormalUserRepository normalUserRepository;
    private final ARItemRepository arItemRepository;

    @Autowired
    public GetGiftService(NormalUserRepository normalUserRepository,
                                   GetGiftRepository getGiftRepository,
                          ARItemRepository arItemRepository) {
        this.normalUserRepository = normalUserRepository;
        this.getGiftRepository = getGiftRepository;
        this.arItemRepository = arItemRepository;
    }

    public GetGift saveGetGift(Long normalUserId, Long itemId){
        NormalUser normalUser = normalUserRepository.findById(normalUserId).orElseThrow(NoSuchElementException::new);
        ARItem arItem = arItemRepository.findById(itemId).orElseThrow(NoSuchElementException::new);

        GetGift getGift = new GetGift();
        getGift.setArItem(arItem);
        getGift.setNormalUser(normalUser);

        return getGiftRepository.save(getGift);

    }
}
