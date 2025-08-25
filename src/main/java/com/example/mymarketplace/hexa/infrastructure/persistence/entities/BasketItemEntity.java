package com.example.mymarketplace.hexa.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "BASKETITEMS")
public class BasketItemEntity {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "OBJECTID", length = 38, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PRODUCTID")
    private ProductEntity product;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BASKETID")
    @JsonBackReference
    private BasketEntity basket;
}
