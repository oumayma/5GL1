package com.esprit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.esprit.exception.CouldNotStartException;


@RunWith(MockitoJUnitRunner.class)
public class CarTest {

	
	@Mock SMSModule smsModule;
	
	@InjectMocks Car car;
	
	@Before
	public void setUp() throws Exception {
		
		
		when(smsModule.isReady()).thenReturn(true);
		doThrow(IllegalArgumentException.class)
		.when(smsModule).send(eq(""), anyString());
	}

	@Test
	public void itShouldStartWhenKeyIsTurnedRight() {
		
		String direction="RIGHT";
		car.turnKey(direction);
		
		
		assertTrue(car.isStarted());
	}
	
	
	@Test
	public void itShouldStopWhenKeyIsTurnedLeft(){
	
		String direction="LEFT";
		car.turnKey(direction);
		assertFalse(car.isStarted());
	}
	
	@Test(expected=CouldNotStartException.class)
	public void itShouldThrowAnCouldNotStartExceptionWhenWrongArgumentIsGiven(){
		
		String direction="WRONG";
		car.turnKey(direction);
		
		
	}
	
	@Test
	 public void itShouldSendSMSNotificationWhenWrongArgumentIsGiven(){
		try {
			
			String direction="WRONG";
			car.turnKey(direction);
		} catch (CouldNotStartException e) {
			
		}
		finally{
			verify(smsModule).send(eq("00216555888"), anyString());
		}
		
		
		
	}
	
	
	

}
