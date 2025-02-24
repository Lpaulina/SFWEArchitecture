package edu.baylor.cs.csi3471;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Tester {

	private static final int VALUE = 3;
	private static final int COLUMN_NAME = 2;
	private static final int FILE_NAME = 1;
	private static final int OPTION = 0;
	private static Set<Make> makes = new HashSet<>();
	private static int idTracker = 0;

	public static void main(String[] args) {
		int option = readOption(args);

		try {
			makes = loadCSV(args[FILE_NAME]);
			if (option == 1) {
				optionOne();
			} else if (option == 2) {
				if (args.length < 4) {
					System.out.println("Please enter <option #> <fileName> <columnName> <value>");
					System.exit(1);
				}

				optionTwo(args[COLUMN_NAME], args[VALUE]);
			} else if (option == 3) {
				optionThree();
			} else if (option == 4) {
				optionFour();
			} else {
				System.out.println("Invalid option please select a number between 1 - 4");
			}

		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}
	}

	private static int readOption(String[] args) {
		System.out.println(args.length);
		Integer option = null;
		if (args.length == 0) {
			System.err.println("USAGE: java Tester <filename>");
			System.exit(1);
		} else {
			try {
				option = Integer.parseInt(args[OPTION]);
			} catch (NumberFormatException e) {
				System.err.println("call as java Tester <filename>");
				System.exit(1);
			}
		}
		return option;
	}

	/*
	 * public static Collection<Make> populateSet(Collection<Make> set, String[]
	 * line){ //check the colleciton for existing make }
	 */

	private static Set<Make> loadCSV(String file) throws FileNotFoundException {
		BufferedReader reader = null;
		try {
			// ok, this is much faster than scanner :)
			reader = new BufferedReader(new FileReader(new File("src/main/resources/" + file)));

			// Skip the first 2 lines (Headers)
			reader.readLine();
			reader.readLine();

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				// just to debug
				// System.out.println(split[6] + " : " + split[7]);

				makes = creatorPattern(split, makes);

			}
			return makes;

		} catch (IOException e) {
			String hint = "";
			try {
				hint = "Current dir is: " + new File(".").getCanonicalPath();
			} catch (Exception local) {
				hint = local.getLocalizedMessage();
			}
			throw new FileNotFoundException(e.getLocalizedMessage() + "\n" + hint);

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println(e.getLocalizedMessage());
				}
			}
		}

	}

	public static Set<Make> creatorPattern(String[] line, Set<Make> makes) {
		String makeName = line[6]; 
		ModelSettings newModelSettings = new ModelSettings(line);

		for (Make make : makes) {
			if (make.getMakeName().equals(makeName)) {
				make.getModelSettingSet().add(newModelSettings);
				return makes; 
			}
		}

		Make newMake = new Make(line, idTracker++);
		makes.add(newMake);

		return makes;
	}

	public static void optionOne() {
		System.out.println("Total number of Makes: " + makes.size());
		System.out.println("===============");

		LinkedList<Make> sortedMakes = new LinkedList<>(makes);

		sortedMakes.sort(Comparator.comparing(Make::getMakeName, String.CASE_INSENSITIVE_ORDER).reversed());

		for (Make currMake : sortedMakes) {
			System.out.println("Make: " + currMake.getMakeName() + " # of Model Settings: "
					+ currMake.getModelSettingSet().size());

			System.out.println("===============");
		}

	}

	public static void optionTwo(String column, String value) {
		LinkedList<Make> filteredMakes = new LinkedList<Make>();

		if (column.equals("makeName")) {
			filteredMakes = makes.stream()
					.filter(make -> make.getMakeName().toLowerCase().contains(value.toLowerCase()))
					.sorted(Comparator.comparing(Make::getMakeName))
					.collect(Collectors.toCollection(LinkedList::new));
		} else if (column.equals("model")) {
			filteredMakes = makes.stream()
					.filter(make -> make.getModelSettingSet()
							.stream()
							.anyMatch(model -> model.getModelName()
									.toLowerCase().contains(value.toLowerCase())))
					.sorted(Comparator.comparing(Make::getMakeName))
					.collect(Collectors.toCollection(LinkedList::new));
		} else {
			System.out.println("Invalid column name, please choose between: makeName OR model");
		}

		System.out.println("Matches:");
		for (Make make : filteredMakes) {
			System.out.println(make.getMakeName());
		}

	}

	public static void optionThree() {
		Map<String, Double> map = new TreeMap<>();
		Map<String, Integer> epaCount = new Hashtable<>();

		for (Make make : makes) {
			for (ModelSettings setting : make.getModelSettingSet()) {

				if (map.containsKey(setting.getEpa())) {

					String currEpa = setting.getEpa();
					double currMpg = map.get(currEpa);
					Integer currCount = epaCount.get(currEpa) + 1;

					map.replace(currEpa, currMpg + setting.getMpg().getAvgMpg());
					epaCount.replace(currEpa, currCount);
				}

				else {
					map.put(setting.getEpa(), setting.getMpg().getAvgMpg());
					epaCount.put(setting.getEpa(), 1);
				}
			}
		}

		map.forEach((key, value) -> System.out.println("EPA: " + key + " | Combined Avg MPG: " + (value / epaCount.get(key))));

	}

	public static void optionFour() {
		Map<Pair<String, String>, Integer> map = new TreeMap<>(            Comparator.comparing(Pair<String, String>::getLeft)
		.thenComparing(Pair::getRight));

		for (Make make : makes) {
			for (ModelSettings setting : make.getModelSettingSet()) {

				Pair<String, String> key = new Pair<String, String>(make.getMakeName(), setting.getYear());

				if (map.containsKey(key)) {
					Integer newTotal = map.get(key) + 1;
					map.replace(key, newTotal);
				} else {
					map.put(key, 1);
				}
			}
		} 

		map.forEach((key, value) -> System.out.println("<" + key.getLeft() + ";" + key.getRight() + ";" + value + ">"));
	}

}


