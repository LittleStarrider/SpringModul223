package com.example.demo.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "BOOKING")
public class BookingEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id = UUID.randomUUID();

    @Column(name = "checkin", nullable = false)
    private Date checkin;

    @Column(name = "checkout", nullable = false)
    private Date checkout;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "approved", nullable = false)
    private boolean approved;

    @Column(name = "workspace", nullable = true)
    private String workspace;

    @ManyToOne
    @JoinColumn(name = "fk_person", nullable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private PersonEntity person;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    private UUID personid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookingEntity other = (BookingEntity) o;
        return id != null && Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public void setPersonid(UUID personid) {
        this.personid = personid;
    }

    public UUID getId() {
        return id;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public UUID getPersonid() {
        return personid;
    }
}
