package edu.baylor.cs.junit.demo.app;

import java.util.List;

import edu.baylor.cs.junit.demo.app.objects.Coin;
import edu.baylor.cs.junit.demo.app.objects.Song;

/**
 * Just a custom MusicBox Interface
 * @author cerny
 *
 */
public interface IMusicBox {
	
	/**
	 * Push credit to pay for songs
	 * 
	 * @param coin
	 */
	void insertCoin(Coin coin);

	/**
	 * Play selected song if you have enough credit
	 * 
	 * @param index
	 * @return
	 */
	String playSong(Integer index);
	/**
	 * Listing of songs
	 * 
	 * @return
	 */
	List<Song> listSongs();

	/**
	 * Current credit balance
	 * 
	 * @return
	 */
	float balance();

	/**
	 * Request cash back
	 * 
	 * @return
	 */
	float cancel();
}