/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.xild.verbose.pancake.model.Address;

/**
 * @author Luis Vieira 
 *
 */
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

	/**
	 * @param zipCode
	 * @return Optional of Address
	 */
	Optional<Address> findByZipCode(String zipCode);
	
	/**
	 * @param zipCode
	 * @return Optional of Address
	 */
	Optional<Address> findById(Long id);

}
