package com.example.mymarketplace.hexa.infrastructure.persistence.entities.client;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("PARTICULAR")
public class ParticularClientEntity extends ClientEntity {
    @Column(name = "FIRSTNAME", length = 200)
    private String firstName;

    @Column(name = "LASTNAME", length = 200)
    private String lastName;
}
