package io.fdlessard.codebites.customer.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "address", schema = "public")
@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity implements Serializable {

    @NotBlank(message = "number cannot be blank")
    @Column(name = "number")
    private String number;

    @NotBlank(message = "street cannot be blank")
    @Size(min = 2, message = "street must have more thant 2 characters")
    @Column(name = "street")
    private String street;

    @NotBlank(message = "city cannot be blank")
    @Size(min = 2, message = "city must have more thant 2 characters")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "postalCode cannot be blank")
    @Size(min = 2, message = "postalCode must have more thant 2 characters")
    @Column(name = "postal_code")
    private String postalCode;

    @NotBlank(message = "province cannot be blank")
    @Size(min = 2, message = "province must have more thant 2 characters")
    @Column(name = "province")
    private String province;

    @NotBlank(message = "country cannot be blank")
    @Size(min = 2, message = "country must have more thant 2 characters")
    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Customer customer;

}