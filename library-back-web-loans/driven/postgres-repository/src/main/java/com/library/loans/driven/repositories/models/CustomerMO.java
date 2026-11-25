package com.library.loans.driven.repositories.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
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
@Table(name = "customer")
public class CustomerMO {
  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "public_id")
  private Long publicId;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Column(name = "phone")
  private Long phone;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "card_image_url")
  private String cardImageUrl;

  @OneToMany(mappedBy = "customer")
  private List<LoanMO> loans;

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
    final var entity = (CustomerMO) o;
    return getId() != null && Objects.equals(getId(), entity.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy proxy
        ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }
}
