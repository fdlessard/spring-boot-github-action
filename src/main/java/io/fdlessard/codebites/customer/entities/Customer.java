package io.fdlessard.codebites.customer.entities;

import io.fdlessard.codebites.customer.entities.Address;
import io.fdlessard.codebites.customer.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Table(name = "customer", schema = "public")
@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @NotBlank(message = "lastName name cannot be blank")
    @Size(min = 2, message = "lastName must have more than 2 characters")
    private String lastName;

    @NotBlank(message = "firstName name cannot be blank")
    @Size(min = 2, message = "firstName must have more than 2 characters")
    private String firstName;

    @NotBlank(message = "company name cannot be blank")
    @Size(min = 2, message = "company must have more than 2 characters")
    private String company;

    @Valid
    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

}
