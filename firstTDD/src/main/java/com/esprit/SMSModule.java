package com.esprit;

public interface SMSModule {
	void send(String number,String message);
    Boolean isReady();

}
