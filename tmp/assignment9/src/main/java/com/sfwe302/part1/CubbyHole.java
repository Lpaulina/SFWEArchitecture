package com.sfwe302.part1;

import java.util.LinkedList;
import java.util.Queue;

class CubbyHole {
	private final int CAPACITY = 10;
	private int seq;
	private boolean available = false;
	Queue<Integer> cubies = new LinkedList<>();

	public synchronized int get() {
		// while (available == false) {
		while (cubies.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		// available = false;
		notify();
		return cubies.poll();
		// return seq;
	}

	public synchronized void put(int value) {
		// while (available == true) {
		while(cubies.size() == CAPACITY){
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		// seq = value;
		cubies.add(value);
		// available = true;
		notify();
		
	}
}