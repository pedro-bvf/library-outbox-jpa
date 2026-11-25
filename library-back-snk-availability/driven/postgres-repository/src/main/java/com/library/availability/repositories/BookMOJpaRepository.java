package com.library.availability.repositories;

import com.library.availability.repositories.models.BookMO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMOJpaRepository extends JpaRepository<BookMO, UUID> {
  Optional<BookMO> findByPublicId(Long publicId);

  void deleteByPublicId(Long publicId);
}
