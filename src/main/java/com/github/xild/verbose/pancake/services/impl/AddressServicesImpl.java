/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.services.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.xild.verbose.pancake.client.ZipCodeClient;
import com.github.xild.verbose.pancake.exception.AddressResourceException;
import com.github.xild.verbose.pancake.model.Address;
import com.github.xild.verbose.pancake.model.to.AddressTO;
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
	public Address saveAddress(AddressTO input) {
		Address address = new Address(input);
		validateZipCode(input);
		return repository.save(address);  
	}

	private void validateZipCode(AddressTO input) {
		client.findAddress(input.getCep());
	}

	@Override
	public Address updateAddress(AddressTO input) throws AddressResourceException {
		Optional<Address> optional = findById(input.getId());
		if (optional.isPresent()) {
			//check if cep is valid
			validateZipCode(input);
			
			return repository.save(new Address(input));
		} else {
			throw new AddressResourceException("Address not found for update");
		}
	}

}
