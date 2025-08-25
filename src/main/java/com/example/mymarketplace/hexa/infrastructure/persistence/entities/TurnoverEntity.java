package com.example.mymarketplace.hexa.infrastructure.persistence.entities;

import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "TURNOVERS")
public class TurnoverEntity {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "OBJECTID", length = 38, nullable = false)
    private String id;

    @Column(name = "REPORTINGYEAR", length = 4, nullable = false)
    private String year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTID")
    private ClientEntity client;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount = 0;
}
