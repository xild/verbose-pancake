/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.searchZipCode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.xild.verbose.pancake.client.ZipCodeClient;
import com.github.xild.verbose.pancake.exception.AddressResourceException;
import com.github.xild.verbose.pancake.model.Address;
import com.github.xild.verbose.pancake.model.to.AddressInput;
import com.github.xild.verbose.pancake.repository.AddressRepository;
import com.github.xild.verbose.pancake.services.AddressServices;
import com.github.xild.verbose.pancake.services.impl.AddressServicesImpl;
import com.github.xild.verbose.pancake.utils.TestUtils;

/**
 * @author Luis Vieira
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AddressServicesTest {

	@Mock
	private AddressRepository repository;
	
	@Mock
	private ZipCodeClient client;

	@InjectMocks
	private AddressServices services = new AddressServicesImpl(repository, client);
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenAValidIdRemove() throws AddressResourceException {
		when(repository.findById(anyLong())).thenReturn(getRandomAddress());
		services.deleteAddressById(1L);
	
		verify(repository, times(1)).delete(anyLong());
	}

	@Test(expected=AddressResourceException.class)
	public void givenAIdNonExistentForDeleteThrownException() throws AddressResourceException {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		
		services.deleteAddressById(1L);
		
		Mockito.verify(repository, never()).delete(anyLong());
	}
	
	@Test
	public void givenAValidIdReturnAddress() throws AddressResourceException {
		Optional<Address> address = getRandomAddress();
		when(repository.findById(anyLong())).thenReturn(address);
		
		Optional<Address> expectedReturn = services.findById(1L);
		
		assertThat(address, equalTo(expectedReturn));
		verify(repository, times(1)).findById(anyLong());
	}

	public void givenANonExistentIdForSearchThrownException() throws AddressResourceException {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		
		Optional<Address> address = services.findById(1L);
		assertThat(address, equalTo(Optional.empty()));
		
		verify(repository, times(1)).findById(anyLong());
	}
	
	@Test
	public void givenAValidInputInsertAddress(){
		Address address = getRandomAddress().get();
		AddressInput input = new AddressInput(address);
		when(repository.save(Mockito.any(Address.class))).thenReturn(address);
		
		Address expected = services.saveAddress(input);
		
		assertThat(address, equalTo(expected));
	}
	
	private Optional<Address> getRandomAddress() {
		return Optional.of((Address) TestUtils.generateObject(Address.class));
	}

	private AddressInput getRandomAddressInput() {
		return (AddressInput) TestUtils.generateObject(AddressInput.class);
	}
}
