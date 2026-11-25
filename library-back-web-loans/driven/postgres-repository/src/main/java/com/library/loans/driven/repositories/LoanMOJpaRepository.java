package com.library.loans.driven.repositories;

import com.library.loans.driven.repositories.models.LoanMO;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanMOJpaRepository extends JpaRepository<LoanMO, UUID> {}
