package edu.baylor.cs.junit.demo.app;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import edu.baylor.cs.junit.demo.app.impl.U2MusicBoxRejectingCents;
import edu.baylor.cs.junit.demo.app.objects.Coin;

public class U2BoxTester extends BoxTester {

	/* Do we need this here? */
	// private IMusicBox box = null;
	
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

    @Test
    void correctDeduction() {
    	box.insertCoin(Coin.cent);
    	box.insertCoin(Coin.dollar);
    	box.playSong(1);
    	assertEquals(.0f,box.balance(), 0.001f);
    }
    
    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => coins={0}, total={1}")
    @MethodSource("coins")
    void feedParams(Coin[] coins, float total) {
    	for (Coin coin : coins) {
    		box.insertCoin(coin);
		}
    	assertEquals(total, box.balance(), "Different total expected");
    
    }
    
    @SuppressWarnings("unused")
	private static Stream<Arguments> coins() {
    	return Stream.of(
                Arguments.of(new Coin[]{Coin.dollar,Coin.dime,Coin.cent}, 1.0f),
                Arguments.of(new Coin[]{Coin.dollar,Coin.nicle,Coin.cent,Coin.quarter}, 1.0f),
                Arguments.of(new Coin[]{Coin.dollar,Coin.dime, Coin.nicle,Coin.cent,Coin.quarter}, 1.0f)
        );
    }
    

}