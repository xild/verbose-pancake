/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.services.impl;

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

	public ZipCodeServicesImpl(AddressRepository addressRepository){
		this.addressRepository = addressRepository;
	}

	@Override
	public Address searchZipCode(String zipCode) {
		return addressRepository.findByZipCode(zipCode);
	}

	
}
