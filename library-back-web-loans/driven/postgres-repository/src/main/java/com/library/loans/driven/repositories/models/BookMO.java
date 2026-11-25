package com.library.loans.driven.repositories.models;

import jakarta.persistence.*;
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
@Entity
@Table(name = "book")
public class BookMO {
  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "public_id")
  private Long publicId;

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
    final var entity = (BookMO) o;
    return getId() != null && Objects.equals(getId(), entity.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy proxy
        ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }
}
