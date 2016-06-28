/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.services;

import java.util.Optional;

import com.github.xild.verbose.pancake.exception.AddressResourceException;
import com.github.xild.verbose.pancake.model.Address;
import com.github.xild.verbose.pancake.model.to.AddressTO;

/** 
 * @author Luis Vieira 
 *
 */
public interface AddressServices {
	
	Optional<Address> findById(Long id);

	/**
	 * @param id
	 * @throws AddressResourceException 
	 */
	void deleteAddressById(Long id) throws AddressResourceException;

	/**
	 * @param input
	 * @return a Representation of Address in database
	 */
	Address saveAddress(AddressTO input);

	Address updateAddress(AddressTO input) throws AddressResourceException;
}
