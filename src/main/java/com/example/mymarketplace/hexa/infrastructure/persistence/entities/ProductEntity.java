package com.example.mymarketplace.hexa.infrastructure.persistence.entities;

import com.example.mymarketplace.hexa.domain.model.Product;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "OBJECTID", length = 38, nullable = false)
    private String id;

    @Column(name = "NAME", length = 200, nullable = false, unique = true)
    private String name;

    @Column(name = "CATEGORY", length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private Product.CategoryEnum category;

    @Column(name = "PRICEFORPARTICULAR", nullable = false)
    private Integer priceForParticular;

    @Column(name = "PRICEFORPROFESSIONAL", nullable = false)
    private Integer priceForProfessional;

    @Column(name = "PRICEFORPROFESSIONALAFTERDISCOUNT", nullable = false)
    private Integer priceForProfessionalAfterDiscount;
}
