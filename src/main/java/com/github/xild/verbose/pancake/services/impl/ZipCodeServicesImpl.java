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

import com.github.xild.verbose.pancake.model.Address;
import com.github.xild.verbose.pancake.repository.AddressRepository;
import com.github.xild.verbose.pancake.services.ZipCodeServices;

/**
 * @author Luis Vieira
 *
 */
@Service
public class ZipCodeServicesImpl implements ZipCodeServices {

	private AddressRepository addressRepository;

	@Inject
	public ZipCodeServicesImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public Optional<Address> searchZipCode(String zipCode) {
		Optional<Address> address = Optional.empty();
		int zipLength = zipCode.length() - 1;

		for (int position = zipLength; position >= -0; position--) {
			address = addressRepository.findByZipCode(zipCode).stream().findFirst();
			if (address.isPresent()) {
				return address;
			}

			StringBuilder sb = new StringBuilder(String.valueOf(zipCode));
			sb.replace(position, position + 1, "0");
			zipCode = sb.toString();
		}

		return address;
	}
}
