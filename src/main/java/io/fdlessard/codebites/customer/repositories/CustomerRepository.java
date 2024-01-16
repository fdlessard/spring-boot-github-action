package io.fdlessard.codebites.customer.repositories;

import io.fdlessard.codebites.customer.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>,
        CrudRepository<Customer, Long>
{

    Page<Customer> findByLastName(@Param("lastName") String lastName, Pageable p);

    Page<Customer> findByFirstName(@Param("firstName") String firstName, Pageable p);
}
