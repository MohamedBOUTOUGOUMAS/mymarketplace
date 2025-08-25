package com.example.mymarketplace.hexa.infrastructure.persistence.entities.client;

import com.example.mymarketplace.hexa.infrastructure.persistence.entities.TurnoverEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("PROFESSIONAL")
public class ProfessionalClientEntity extends ClientEntity {

    @Column(name = "REASON", length = 200)
    private String reason;

    @Column(name = "TVA", length = 200)
    private String tva;

    @Column(name = "SIRET", length = 200)
    private String siret;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurnoverEntity> turnovers;

    @Column(name = "ENABLEDISCOUNTMAXAMOUNT")
    private Integer enableDiscountMaxAmount;

}
