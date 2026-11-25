package com.library.books.driven.repositories.repository;

import com.library.books.driven.repositories.models.BookMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookMOJpaRepository extends JpaRepository<BookMO, UUID> {
  List<BookMO> findByPublicIdIn(List<Long> publicIds);
}
