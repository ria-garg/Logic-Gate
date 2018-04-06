import java.util.ArrayList;
import java.util.List;

public abstract class Gate implements Logic {
	private List<Wire> inputs;
	private Wire output;
	private String name;

	// default constructor
	public Gate(String name, List<Wire> ins, Wire out) throws RuntimeException {
		this.name = name;
		this.output = out;
		if (ins.size() > 0) {
			this.inputs = ins;
		} else {
			throw new ExceptionLogicParameters(true, 1, 0);
		}
	}

	// input a list of signals and feed the signals
	@Override
	public void feed(List<Signal> inSigs) {
		if (inSigs.size() == inputs.size()) {
			for (int i = 0; i < inputs.size(); i++) {
				Wire w = inputs.get(i);
				w.setSignal(inSigs.get(i));
				inputs.set(i, w);
			}
		} else {
			throw new ExceptionLogicParameters(true, inputs.size(), inSignals.size()); // Exception is thrown when the
																						// number of inputs are
																						// incorrect
		}
	}

	@Override
	public void feed(String signalsStr) {
		List<Signal> iSig = Signal.fromString(signalsStr);
		feed(iSig);
	}

	@Override
	public abstract boolean propagate();

	@Override
	public List<Signal> read() {
		List<Signal> mySigs = new ArrayList<Signal>();
		mySigs.add(output.getSignal());
		return mySigs;
	}

	@Override
	public List<Signal> inspect(List<Signal> inSigs) {
		feed(inSigs);
		propagate();
		return read();
	}

	@Override
	public String inspect(String inStr) { // looks at the
		feed(inStr);
		propagate();
		return read().toString();
	}

	@Override
	public String toString() {
		return "\"" + name + "( [" + inputs + "] | " + output + " )";
	}

	@Override
	public boolean equals(Object other) { // compares two gate objects to each other
		boolean path = false;
		if (other instanceof Gate) {
			Gate g = (Gate) other;
			if (g.getName().equals(name) && g.getOutput().equals(output) && g.getInputs().equals(inputs)) {
				path = true;
			}
		}
		return path;
	}

	// getter and setter for the variables
	public List<Wire> getInputs() {
		return inputs;
	}

	public Wire getOutput() {
		return output;
	}

	public String getName() {
		return name;
	}

	public void setInputs(List<Wire> inputs) {
		this.inputs = inputs;
	}

	public void setOutput(Wire output) {
		this.output = output;
	}

	public void setName(String name) {
		this.name = name;
	}
}
