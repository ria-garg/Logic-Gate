import java.util.*;
import java.io.*;

public class Circuit implements Logic {
	// ---------------------------------------------------------------------------------------------------------------------------------

	// fields
	private List<Logic> components;
	private List<Contact> inputs;
	private List<Contact> outputs;
	private List<Wire> innerWires;
	private List<String> importables;
	private String name;

	// constructor
	public Circuit(String circuitName, List<Logic> components, List<Contact> inputs, List<Contact> outputs,
			List<Wire> innerWires, List<String> importables) {
		this.name = circuitName;
		this.components = components;
		this.inputs = inputs;
		this.outputs = outputs;
		this.innerWires = innerWires;
		this.importables = importables;
	}

	// involved constructor
	public Circuit(String circuitName) throws IOException {

		// initializing fields
		components = new ArrayList<Logic>();
		inputs = new ArrayList<Contact>();
		outputs = new ArrayList<Contact>();
		innerWires = new ArrayList<Wire>();
		importables = new ArrayList<String>();
		this.name = circuitName;

		List<String> inputList = new ArrayList<String>(); // inputList list, taking in from file

		String myString; // used to compare, remove spaces, etc.

		Scanner myScanner = getCircuitScanner(circuitName); // scanning

		// a list containing the lines in the file
		while (myScanner.hasNextLine()) {
			myString = myScanner.nextLine();
			if (!myString.equals("")) {
				inputList.add(myString);
			}
		}

		String[] first = inputList.get(0).split("\\s"); // splitting the first line of the list made

		if (first[0].equals("IMPORT")) { // checking if it needs to be imported
			parseImportLine(inputList.get(0));
			parseContactsLine(inputList.get(1));
			for (int i = 2; i < inputList.size(); i++) {
				parseComponentLine(inputList.get(i));
			}
		} else { // dealing with importables
			importables = new ArrayList<String>(0);
			parseContactsLine(inputList.get(0));
			for (int i = 1; i < inputList.size(); i++) {
				parseComponentLine(inputList.get(i));
			}
		}
	}

	// making a file and opening it
	public Scanner getCircuitScanner(String circuitName) throws IOException {
		Scanner myScanner = new Scanner(new File("samples/" + circuitName + ".txt"));
		return myScanner;
	}

	// updating the importables field
	public void parseImportLine(String line) {
		importables = new ArrayList<String>();
		String[] a = line.split("\\s"); // whitespace character - splitting at this
		for (int i = 1; i < a.length; i++) {
			importables.add(a[i]);
		}
	}

	// helps parseConctactsLine shift - organization
	public List<Wire> parseString(String str) {
		List<Wire> w = new ArrayList<Wire>();
		if (str.length() > 0) {
			String[] stringArray = str.split("\\s"); // splitting at the whitespace
			for (String i : stringArray) {
				w.add(new Wire(i));
			}
		}
		return w;
	}

	// list of wires for both sides
	public void parseContactsLine(String line) {

		// initalizing fields
		inputs = new ArrayList<Contact>();
		outputs = new ArrayList<Contact>();

		// riding of whitespaces
		String start = "";
		String end = "";
		String[] rid = line.split("\\->");
		start = rid[0].trim();
		end = rid[1].trim();

		// innerWires contain previous wires
		this.innerWires = parseString(start);

		// attaching contacts to wires
		for (Wire i : innerWires) {
			inputs.add(new Contact(i, i, true));
		}

		List<Wire> newWire = parseString(end);
		for (Wire i : newWire) {
			innerWires.add(i);
			outputs.add(new Contact(i, i, false));
		}

	}

	// helper
	// finding any wire in the circuit
	public Wire findWire(String name) {
		Wire found = null;
		for (Wire i : innerWires) {
			if (i.getName().equals(name)) {
				found = i;
			}
		}
		return found;
	}

	// comparing two lists, replacing outer wires
	public void hookUp(List<Wire> inWires, List<Wire> outWires) {
		if (inWires.size() == inputs.size() && outWires.size() == outputs.size()) {
			for (int i = 0; i < inputs.size(); i++) {
				inputs.get(i).setIn(inWires.get(i));
			}
			for (int j = 0; j < outputs.size(); j++) {
				outputs.get(j).setOut(outWires.get(j));
			}
		} else {
			throw new ExceptionLogicParameters(true, inputs.size(), inWires.size());
		}
	}

	// components in circuit
	public void parseComponentLine(String line) throws IOException {

		Scanner input = new Scanner(line);
		String reading = input.next();

		List<Wire> in = new ArrayList<Wire>();
		List<Wire> out = new ArrayList<Wire>();

		// removing extra spaces
		String newWires = input.nextLine().trim();
		String start = "";
		String end = "";
		String[] stringArray = newWires.split("\\->");
		start = stringArray[0].trim();
		end = stringArray[1].trim();

		in = parseString(start);
		out = parseString(end);

		// checking length of two lists being compared
		if (in.size() == 0) {
			throw new ExceptionLogicParameters(true, 1, 0);
		}
		if (out.size() == 0) {
			throw new ExceptionLogicParameters(false, 1, 0);
		}

		// does this to see if names match up between two wires, replaced if so
		for (int i = 0; i < in.size(); i++) { // inner wire
			if (findWire(in.get(i).getName()) != null) {
				in.set(i, findWire(in.get(i).getName()));
			} else {
				innerWires.add(in.get(i));
			}
		}

		for (int j = 0; j < out.size(); j++) { // outer wire
			if (findWire(out.get(j).getName()) != null) {
				out.set(j, findWire(out.get(j).getName()));
			} else {
				innerWires.add(out.get(j));
			}
		}

		// subcircuit made and connected to two wires
		if (importables.contains(reading)) {
			Circuit newComponent = new Circuit(reading);
			newComponent.hookUp(in, out);
			this.components.add(newComponent);
		}

		// gate created, added to components
		else {
			// NOT gate
			if (reading.equals("NOT")) {
				if (in.size() == 1) {
					components.add(new GateNot(in, out.get(0)));
				} else {
					throw new ExceptionLogicParameters(true, 1, in.size());
				}
			}
			// AND gate
			else if (reading.equals("AND")) {
				components.add(new GateAnd(in, out.get(0)));
			}
			// NAND gate
			else if (reading.equals("NAND")) {
				components.add(new GateNand(in, out.get(0)));
			}
			// OR gate
			else if (reading.equals("OR")) {
				components.add(new GateOr(in, out.get(0)));
			}
			// XOR gate
			else if (reading.equals("XOR")) {
				components.add(new GateXor(in, out.get(0)));
			}
			// NOR gate
			else if (reading.equals("NOR")) {
				components.add(new GateNor(in, out.get(0)));
			}
		}
	}

	// feeding a list of signals into the circuit
	@Override
	public void feed(List<Signal> inputsW) {
		if (inputsW.size() == inputs.size()) {
			for (int i = 0; i < inputs.size(); i++) {
				inputs.get(i).getIn().setSignal(inputsW.get(i));
			}
		} else {
			throw new ExceptionLogicParameters(true, inputs.size(), inputsW.size());
		}
	}

	// feeding a string of signal into the circuit
	@Override
	public void feed(String s) { // recursion
		List<Signal> myList = Signal.fromString(s);
		feed(myList);
	}

	// transferring signals
	@Override
	public boolean propagate() {

		// compared two lists
		List<Signal> one = read();
		List<Signal> two = read();

		for (Contact i : inputs) {
			i.getOut().setSignal(i.getIn().getSignal());
		}
		for (Logic i : components) {
			i.propagate();
		}
		for (Contact i : outputs) {
			if (i.getOut().getSignal() != i.getIn().getSignal()) {
				i.getOut().setSignal(i.getIn().getSignal());
			}
		}
		for (int i = 0; i < one.size(); i++) {
			if (!one.get(i).equals(two.get(i))) {
				return true;
			}
		}

		return false;
	}

	// list of signals from ouputs
	@Override
	public List<Signal> read() {

		List<Signal> myList = new ArrayList<Signal>();
		for (Contact i : outputs) {
			myList.add(i.getOut().getSignal());
		}
		return myList;
	}

	// string representation of circuit - printing purposes
	@Override
	public String toString() {

		StringBuilder a = new StringBuilder(); // https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
		a.append(name + " : " + inputs + " -> " + outputs + "\n");
		for (Logic i : components) {
			a.append(indent(i.toString()));
		}
		return a.toString();
	}

	// 2 spaces in the front
	public static String indent(String s) {

		String newString = "";
		Scanner myScanner = new Scanner(s);

		while (myScanner.hasNextLine()) {
			String aux = myScanner.nextLine();
			newString += ("  " + aux + "\n");
		}
		return newString;
	}

	// inspecting method 1
	@Override
	public List<Signal> inspect(List<Signal> inputs) {
		feed(inputs);
		propagate();
		return read();
	}

	// inspecting method 2
	@Override
	public String inspect(String input) {
		feed(input);
		propagate();
		return read().toString();
	}

	// getters
	public List<Logic> getComponents() {
		return this.components;
	}

	public List<Contact> getInputs() {
		return this.inputs;
	}

	public List<Contact> getOutputs() {
		return this.outputs;
	}

	public List<Wire> getInnerWires() {
		return this.innerWires;
	}

	public List<String> getImportables() {
		return this.importables;
	}

	public String getName() {
		return this.name;
	}

	// setters
	public void setComponents(List<Logic> components) {
		this.components = components;
	}

	public void setInputs(List<Contact> inputs) {
		this.inputs = inputs;
	}

	public void setOutputs(List<Contact> outputs) {
		this.outputs = outputs;
	}

	public void setInnerWires(List<Wire> innerWires) {
		this.innerWires = innerWires;
	}

	public void setImportables(List<String> importables) {
		this.importables = importables;
	}

	public void setName(String name) {
		this.name = name;
	}

	// ---------------------------------------------------------------------------------------------------------------------------------
}
