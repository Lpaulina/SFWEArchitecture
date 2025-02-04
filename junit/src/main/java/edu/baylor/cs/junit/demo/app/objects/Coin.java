package edu.baylor.cs.junit.demo.app.objects;

/**
 * Coin enum
 * 
 * @author cerny
 *
 */
public enum Coin {
	cent(.01f), dime(.10f), nicle(.05f), quarter(.25f), dollar(1);
	
	public float value;

	Coin(float value) {
		this.value = value;
	};
}
