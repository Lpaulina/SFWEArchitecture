package edu.baylor.cs.junit.demo.app.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (index < 0) {
			throw new RuntimeException("Unknown option");
		}
		Song song = mapSongs.get(index);
		if (song.getCost() <= total) {
			total =- song.getCost();
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
