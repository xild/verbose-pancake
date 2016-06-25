/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.searchZipCode;

import static org.junit.Assert.fail;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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

/**
 * @author Luis Vieira 
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchCepTest {
	
	@Mock
	private AddressRepository repository;
	
	@InjectMocks
	private ZipCodeServices services = new ZipCodeServicesImpl(repository);

	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenAValidCepReturnAddressInformation(){
		Address address = (Address) TestUtils.generateObject(Address.class);
		Mockito.when(repository.findByZipCode(Mockito.anyString())).thenReturn(address);
		
		Address returnObject  = services.searchZipCode(address.getZipCode());
		
		MatcherAssert.assertThat(returnObject, Matchers.equalTo(address));
	}
	
	@Test
	public void givenAInvalidCepReturnCepInvalidMessage(){
		fail("not implemented yet");
	}
	
	@Test
	public void givenACepWithoutAddressRemoveADigitFromRightToLeftUntilFound(){
		fail("not implemented yet");
	}

}
