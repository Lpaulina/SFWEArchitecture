package edu.baylor.cs.csi3471;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Make {
	//city MPG,combined MPG,engine cylinders,engine displacement in liters,fuel type,highway MPG,manufacturer,model name,transmission descriptor,EPA vehicle size class,model year

	private String makeName;
	private int makeId;
	// private static int ids = 0;
	private Set<ModelSettings> modelSettingSet = new HashSet<>();

	public Make(String[] makeInfo, int id){
		this.makeName = makeInfo[6];
		this.makeId = id;
		this.modelSettingSet.add(new ModelSettings(makeInfo));
	}

	public String getMakeName(){
		return makeName;
	}

	public Set<ModelSettings> getModelSettingSet() {
		return modelSettingSet;
	}

	public void setModelSettingSet(Set<ModelSettings> modelSettingSet) {
		this.modelSettingSet = modelSettingSet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(makeName);
	}

	@Override
	public String toString() {
		StringBuilder set = new StringBuilder();
		set.append(makeName).append("\n");
	
		for (ModelSettings settings : modelSettingSet) {
			set.append(settings).append("\n");
		}
	
		return set.toString();	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
			return true;
		}
        if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
        Make other = (Make) obj;
		return makeName.equals(other.makeName);
	}

	// there are 2 options, do this functionality here(its static),
	// or in your tester.java and call this method from the make object that a 
	// line is. I would suggest number 2. 
	// public Collection<Make> creatorPattern(String[] line, Collection<Make> makes) {
	// 	if (!modelSettingSet.contains(new ModelSettings(line))) {
	// 		// if the make does not exist then create a new one
	// 	} else {
	// 		// if the make does exist, update its modelSettingSet
	// 	}
	// 	return makes;
	// }
}
