package edu.baylor.ecs.csi5324.decorator;

public interface DataSource {
	void writeData(String data);

	String readData();
}