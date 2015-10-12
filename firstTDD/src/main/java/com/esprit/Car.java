package com.esprit;

import java.util.Arrays;
import java.util.List;

import com.esprit.exception.CouldNotStartException;

public class Car {

	
	SMSModule smsModule;
	
	private boolean started=false;
	
	public void turnKey(String direction) {
		
		
		List<String> directionValues=Arrays.asList("RIGHT","LEFT");
		
		
		if(!directionValues.contains(direction)){
			if(smsModule.isReady())
			{
				
			    try{	
				smsModule.send("00216555888", "ALARM");
			    }catch(IllegalArgumentException e){
			    	System.out.println("Num tel obligatoire..");
			    }
				
			}
			
			throw new CouldNotStartException();
			}
		
		if("RIGHT".equals(direction)){started=true;};
		if("LEFT".equals(direction)){started=false;};
		
		
		
	}

	public boolean isStarted() {
		// TODO Auto-generated method stub
		return started;
	}

}
