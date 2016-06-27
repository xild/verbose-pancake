/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.services;

import java.util.Optional;

import com.github.xild.verbose.pancake.model.Address;

/**
 * @author Luis Vieira 
 *
 */
public interface ZipCodeServices {
	/**
	 * @param zipCode
	 * @return
	 */
	Optional<Address> searchZipCode(String zipCode);
}
