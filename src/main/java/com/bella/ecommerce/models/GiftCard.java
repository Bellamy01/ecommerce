package com.bella.ecommerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@DiscriminatorValue("GIFT_CARD")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class GiftCard extends OrderItem {
    @Column(name = "recipient_email", nullable = true)
    private String recipientEmail;

    public GiftCard(String productName, double price, int quantity, String recipientEmail) {
        super(productName, price, quantity);
        this.recipientEmail = recipientEmail;
    }

    public void sendGift() {
        // Simulate sending the gift card via email
        System.out.println("Gift card sent to " + recipientEmail);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        GiftCard giftCard = (GiftCard) o;
        return getId() != null && Objects.equals(getId(), giftCard.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}