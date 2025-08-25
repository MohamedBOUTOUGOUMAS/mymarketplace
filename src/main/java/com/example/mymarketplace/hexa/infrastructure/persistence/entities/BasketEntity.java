package com.example.mymarketplace.hexa.infrastructure.persistence.entities;

import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ClientEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@Entity
@Table(name = "BASKETS")
public class BasketEntity {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "OBJECTID", length = 38, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CLIENTID", unique = true, nullable = false)
    private ClientEntity client;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "basket", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BasketItemEntity> basketItems;

    public BasketEntity() {}

    public BasketEntity(String id) {
        this.id = id;
    }
}
