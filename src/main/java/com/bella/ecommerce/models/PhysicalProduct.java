package com.bella.ecommerce.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@DiscriminatorValue("PHYSICAL")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PhysicalProduct extends OrderItem {
    private double shippingWeight;

    public PhysicalProduct(String productName, double price, int quantity, double shippingWeight) {
        super(productName, price, quantity);
        this.shippingWeight = shippingWeight;
    }

    public double calculateShippingCost() {
        // Calculation: Shipping cost of 1,500 Rwf per kg
        return shippingWeight * 1500;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PhysicalProduct that = (PhysicalProduct) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}