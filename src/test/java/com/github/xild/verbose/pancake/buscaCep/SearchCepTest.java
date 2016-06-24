/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 24, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.buscaCep;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Luis Vieira 
 *
 */
@RunWith( SpringJUnit4ClassRunner.class )
public class SearchCepTest {
	
	@Before
	public void setUp() {
	}
	
	public void givenAValidCepReturnAddressInformation(){
		fail("not implemented yet");
	}
	
	public void givenAInvalidCepReturnCepInvalidMessage(){
		fail("not implemented yet");
	}
	
	public void givenACepWithoutAddressRemoveADigitFromRightToLeftUntilFound(){
		fail("not implemented yet");
	}

}
