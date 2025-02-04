package edu.baylor.cs.junit.demo.app;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.baylor.cs.junit.demo.app.impl.U2MusicBoxRejectingCents;
import edu.baylor.cs.junit.demo.app.objects.Coin;

public class U2BoxTester extends BoxTester {

	/* Do we need this here? */
	private IMusicBox box = null;
	
    @BeforeEach
    void init() {
    	box = new U2MusicBoxRejectingCents();
    }

    /**
     * We are overriding the inherited one as we do not accept cents
     */
    @Test
    void feed() {
    	box.insertCoin(Coin.dollar);
    	assertEquals(1.0f, box.balance(), "Cent expected");
    
    }
    @Test
    void feedWithCentIgnores() {
    	box.insertCoin(Coin.dollar);
    	box.insertCoin(Coin.cent);
    	box.insertCoin(Coin.dime);
    	box.insertCoin(Coin.nicle);
    	box.insertCoin(Coin.quarter);
    	assertEquals(1f, box.balance(), "Cent expected");
    	
    }
    
    

}