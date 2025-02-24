package edu.baylor.cs.csi3471;

import java.util.Objects;

public class ModelSettings {
	// city MPG: 0
	// combined MPG: 1
	// engine cylinders: 2
	// engine displacement in liters: 3
	// fuel type:4
	// highway MPG: 5
	// manufacturer: 6
	// model name: 7
	// transmission descriptor: 8
	// EPA vehicle size class: 9
	// model year: 10
	private MPG mpg = null;
	private String modelName = null;
	private String modelYear;
	private String transmissionDesc;
	private String engineDisp;
	private String fuelType;
	private String manufacturer;
	private String epa;
	private int uniqueId;
	private static int id = 0;

	public ModelSettings(String[] line) {
		this.modelName = line[7];
		this.modelYear = line[10];
		this.transmissionDesc = line[8];
		this.engineDisp = line[3];
		this.fuelType = line[4];
		this.manufacturer = line[6];
		this.epa = line[9];
		this.uniqueId = id++;
		this.mpg = new MPG(line);
	}

	public class MPG {
		private String cityMpg;
		private double avgMpg;
		private String hwyMpg;

		public MPG(String[] line) {
			this.cityMpg = line[0]; // line[0]
			this.avgMpg = Double.parseDouble(line[1]);
			this.hwyMpg = line[5]; // line[5]
		}

		public double getAvgMpg(){
			return avgMpg;
		}

		// TODO
		@Override
		public String toString() {
			return   " City:" + cityMpg + " Highway: " + hwyMpg+" Average: "+avgMpg+" ";
		}
	}
	public String getYear(){
		return modelYear;
	}
	public String getEpa(){
		return epa;
	}
	public String getModelName() {
		return modelName;
	}
	public MPG getMpg() {
		return mpg;
	}

	public void setMpg(MPG mpg) {
		this.mpg = mpg;
	}

	// TODO

	@Override
	public int hashCode() {
		return Objects.hash
		( 
			this.modelName,
			this.modelYear,
			this.transmissionDesc,
			this.engineDisp,
			this.fuelType,
			this.manufacturer,
			this.epa
		);
	}

	@Override
	public String toString() {
		return "ModelSettings:[ MPG: [" + mpg + "] Brand: " + modelName + "]";
	}

	@Override
	public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
        if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
        ModelSettings other = (ModelSettings) obj;

    return this.modelName.equals(other.modelName) &&
           this.modelYear == other.modelYear &&
           this.transmissionDesc.equals(other.transmissionDesc) &&
           this.engineDisp.equals(other.engineDisp) &&
           this.fuelType.equals(other.fuelType) &&
           this.manufacturer.equals(other.manufacturer) &&
           this.epa.equals(other.epa);
	}

}
