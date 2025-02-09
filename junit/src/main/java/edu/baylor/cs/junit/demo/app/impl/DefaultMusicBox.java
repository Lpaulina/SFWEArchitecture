package edu.baylor.cs.junit.demo.app.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import edu.baylor.cs.junit.demo.app.IMusicBox;
import edu.baylor.cs.junit.demo.app.objects.Coin;
import edu.baylor.cs.junit.demo.app.objects.Song;

/**
 * Default implementation of {@link IMusicBox}
 * 
 * @author cerny
 *
 */
public class DefaultMusicBox implements IMusicBox {

	protected List<Song> list = null;
	private Map<Integer, Song> mapSongs = null;
	
	private Float total = null;
	
	public DefaultMusicBox() {
		loadSongs();
		mapSongs();
	}
	
	/*
	 * meant to populate list of songs
	 */
	protected void loadSongs() {
		list = Arrays.asList(new Song(1,"Love song", 1f));
		for (Song song: list){
			System.out.println(song.getIndex() + " " + song.getName() + " " + song.getCost());
		}
	}
	
	/*
	 * map of songs by index
	 */
	protected void mapSongs() {
		mapSongs = new HashMap<Integer, Song>();
		for (Song song : list) {
			mapSongs.put(song.getIndex(), song);
		}
	}

	/* (non-Javadoc)
	 * @see edu.baylor.cs.junit.demo.app.IMusicBox#insertCoin(edu.baylor.cs.junit.demo.app.objects.Coin)
	 */
	@Override
	public void insertCoin(Coin coin) {
		if(total == null) {
			total = 0f;
		}
		total += coin.value;
	}

	/* (non-Javadoc)
	 * @see edu.baylor.cs.junit.demo.app.IMusicBox#playSong(java.lang.Integer)
	 */
	@Override
	public String playSong(Integer index) {
		if (index == null || index < 0) {
			throw new RuntimeException("Unknown option");
		}

		if (!mapSongs.containsKey(index)){
			throw new NoSuchElementException("Unknown option");
		}

		Song song = mapSongs.get(index);

		if (total != null && (song.getCost() <= total)) {
			total = new BigDecimal(total - song.getCost())
			.setScale(2, RoundingMode.HALF_UP)
			.floatValue();

			System.out.println("1 " + total);

			return "Playing "+song.getName();
		} else {
			return "Not enough credit";
		}
	}

	/* (non-Javadoc)
	 * @see edu.baylor.cs.junit.demo.app.IMusicBox#listSongs()
	 */
	@Override
	public List<Song> listSongs() {
		return list;
	}

	/* (non-Javadoc)
	 * @see edu.baylor.cs.junit.demo.app.IMusicBox#balance()
	 */
	@Override
	public float balance() {
		return total;
	}

	/* (non-Javadoc)
	 * @see edu.baylor.cs.junit.demo.app.IMusicBox#cancel()
	 */
	@Override
	public float cancel() {
		float tmp = total;
		total = null;
		return tmp;
	}
}
