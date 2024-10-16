package com.bella.ecommerce.services;

import com.bella.ecommerce.models.GiftCard;
import org.springframework.stereotype.Service;

@Service
public class GiftCardService {
    public void sendGift(GiftCard giftCard) {
        giftCard.sendGift();
    }
}
