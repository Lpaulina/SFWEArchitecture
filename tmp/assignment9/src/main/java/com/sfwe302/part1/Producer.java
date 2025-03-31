package com.sfwe302.part1;

class Producer extends Thread {
	private CubbyHole cubbyhole;
	private int number;

	public Producer(CubbyHole c, int number) {
		cubbyhole = c;
		this.number = number;
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			cubbyhole.put(i);
			System.out.println("Producer #" + this.number + " put: " + i+" :: "+System.currentTimeMillis());
			// try {
			// 	sleep((int) (Math.random() * 100));
			// } catch (InterruptedException e) {
			// }
		}
	}
}