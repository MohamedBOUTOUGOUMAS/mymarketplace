package com.example.mymarketplace.hexa.infrastructure.persistence.repositories;

import com.example.mymarketplace.hexa.infrastructure.persistence.entities.client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, String>{
}
