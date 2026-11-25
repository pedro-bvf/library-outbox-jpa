package com.library.loans.driven.repositories.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.proxy.HibernateProxy;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan")
public class LoanMO extends AuditableEntity {
  @Id
  @UuidGenerator
  @Column(name = "id")
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerMO customer;

  @Column(name = "public_id")
  private Long publicId;

  @Column(name = "booking_date")
  private OffsetDateTime bookingDate;

  @Column(name = "booking_expiration_date")
  private LocalDate bookingExpirationDate;

  @Column(name = "returned_date")
  private OffsetDateTime returnedDate;

  @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
  private List<LoanDetailsMO> loanDetails;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    final var thisEffectiveClass =
        this instanceof HibernateProxy thisProxy
            ? thisProxy.getHibernateLazyInitializer().getPersistentClass()
            : getClass();
    final var oEffectiveClass =
        o instanceof HibernateProxy oProxy
            ? oProxy.getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
    if (getClass() != o.getClass() && thisEffectiveClass != oEffectiveClass) return false;
    final var entity = (LoanMO) o;
    return getId() != null && Objects.equals(getId(), entity.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy proxy
        ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }
}
