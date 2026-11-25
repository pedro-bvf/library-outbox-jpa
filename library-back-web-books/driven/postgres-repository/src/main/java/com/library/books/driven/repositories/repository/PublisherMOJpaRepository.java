package com.library.books.driven.repositories.repository;

import com.library.books.driven.repositories.models.PublisherMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherMOJpaRepository extends JpaRepository<PublisherMO, UUID> {
}
