/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.searchZipCode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.xild.verbose.pancake.model.Address;
import com.github.xild.verbose.pancake.repository.AddressRepository;
import com.github.xild.verbose.pancake.services.ZipCodeServices;
import com.github.xild.verbose.pancake.services.impl.ZipCodeServicesImpl;
import com.github.xild.verbose.pancake.utils.TestUtils;

import jersey.repackaged.com.google.common.collect.Lists;

/**
 * @author Luis Vieira
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchZipCodeServicesTest {

	final List<String> zipCodes = Lists.newArrayList("12345678", 
			"12345670",
			"12345600", 
			"12345000",
			"12340000",
			"12300000",
			"12000000", 
			"10000000",
			"00000000");

	@Mock
	private AddressRepository repository;

	@InjectMocks
	private ZipCodeServices services = new ZipCodeServicesImpl(repository);
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenAValidCepReturnAddressInformation() {
		Address address = getRandomAddress();
		mockZipCode(anyString(), Lists.newArrayList(address));

		Optional<Address> expected = services.searchZipCode(address.getZipCode());
		
		assertThat(expected.get(), equalTo(address));
	}

	@Test
	public void givenACepWithoutAddressRemoveADigitFromRightToLeftUntilFound() {
		final String zipCode = "00000000";
		Address address = getRandomAddress();
		address.setZipCode(zipCode);
		mockZipCode(zipCodes, Lists.newArrayList());
		mockZipCode(zipCode, Lists.newArrayList(address));

		Optional<Address> optionalAddress = services.searchZipCode("12345678");
		
		Mockito.verify(repository, times(zipCodes.size())).findByZipCode(anyString());
		assertThat(optionalAddress.get(), equalTo(address));
	}

	@Test
	public void givenACepWithoutAddressRemoveADigitFromRightToLeftUntilNotFound() {
		Optional<Address> address = Optional.empty();
		mockZipCode(zipCodes, Lists.newArrayList());
		
		Optional<Address> optionalAddress = services.searchZipCode("12345678");
		
		Mockito.verify(repository, times(zipCodes.size())).findByZipCode(anyString());
		assertThat(optionalAddress, equalTo(address));
	}

	private Address getRandomAddress() {
		return (Address) TestUtils.generateObject(Address.class);
	}

	private void mockZipCode(List<String> zipCodes, List<Address> expectedReturn) {
		zipCodes.forEach(z -> mockZipCode(z, expectedReturn));
	}
	
	private void mockZipCode(String zipCode, List<Address> expectedReturn) {
		Mockito.when(repository.findByZipCode(zipCode)).thenReturn(Lists.newArrayList(expectedReturn));
	}

}
