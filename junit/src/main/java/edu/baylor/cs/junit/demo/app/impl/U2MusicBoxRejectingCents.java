package edu.baylor.cs.junit.demo.app.impl;


import java.util.Arrays;

import edu.baylor.cs.junit.demo.app.objects.Coin;
import edu.baylor.cs.junit.demo.app.objects.Song;

/**
 * Enhanced tweaked {@link DefaultMusicBox}
 * @author cerny
 *
 */
public class U2MusicBoxRejectingCents extends DefaultMusicBox {
	
	public U2MusicBoxRejectingCents() {
		super();
	}
	
	protected void loadSongs() {
		list = Arrays.asList(
				new Song(1, "With or Without You", 1f),
				new Song(2, "War", null),
				new Song(3, "One", 1f),
				new Song(4, "Elevation", 1f),
				new Song(5, "Exit", 1f),
				new Song(6, "The fly", 1f),
				new Song(7, "The Fool", 1f)
			);
	}
	
	public void insertCoin(Coin coin) {
		if (coin.equals(Coin.dollar)) {
			super.insertCoin(coin);
		}
	}
}
