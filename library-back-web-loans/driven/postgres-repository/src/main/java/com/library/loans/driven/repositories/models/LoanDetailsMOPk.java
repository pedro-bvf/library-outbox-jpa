package com.library.loans.driven.repositories.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LoanDetailsMOPk implements Serializable {

  @Column(name = "loan_id")
  private UUID loanId;

  @Column(name = "book_id")
  private UUID bookId;

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
    final var entity = (LoanDetailsMOPk) o;
    return getLoanId() != null
        && Objects.equals(getLoanId(), entity.getLoanId())
        && getBookId() != null
        && Objects.equals(getBookId(), entity.getBookId());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(getLoanId(), getBookId());
  }
}
