package com.library.loans.driven.repositories;

import com.library.loans.driven.repositories.models.BookMO;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMOJpaRepository extends JpaRepository<BookMO, UUID> {
  @Query("SELECT b.id FROM BookMO b WHERE b.id IN :ids")
  List<UUID> findExistingIds(@Param("ids") List<UUID> ids);
}
