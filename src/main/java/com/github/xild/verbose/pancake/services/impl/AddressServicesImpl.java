/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.services.impl;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.xild.verbose.pancake.client.ZipCodeClient;
import com.github.xild.verbose.pancake.exception.AddressResourceException;
import com.github.xild.verbose.pancake.model.Address;
import com.github.xild.verbose.pancake.model.to.AddressInput;
import com.github.xild.verbose.pancake.model.to.AddressOutput;
import com.github.xild.verbose.pancake.repository.AddressRepository;
import com.github.xild.verbose.pancake.services.AddressServices;

/**
 * @author Luis Vieira
 *
 */
@Service
public class AddressServicesImpl implements AddressServices {

	private AddressRepository repository;
	
	private ZipCodeClient client;

	@Inject
	public AddressServicesImpl(AddressRepository repository, ZipCodeClient client) {
		this.repository = repository;
		this.client = client;
	}

	@Override
	public Optional<Address> findById(Long id) {
		Optional<Address> address = repository.findById(id);
		return address;
	}

	@Override
	public void deleteAddressById(Long id) throws AddressResourceException {
		Optional<Address> optional = findById(id);
		if (optional.isPresent()) {
			repository.delete(id);
		} else {
			throw new AddressResourceException("Address not found for remove");
		}
	}

	@Override
	public Address saveAddress(AddressInput input) {
		Address address = new Address(input); 
		return repository.save(address);  
	}

}
