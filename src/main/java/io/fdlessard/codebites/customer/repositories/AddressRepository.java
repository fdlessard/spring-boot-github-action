package io.fdlessard.codebites.customer.repositories;

import io.fdlessard.codebites.customer.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "addresses")
public interface AddressRepository extends PagingAndSortingRepository<Address, Long>,
        CrudRepository<Address, Long>
{
}
