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
	public void feed(List<Signal> inSignals) {
		if (inSignals.size() == inputs.size()) {
			for (int i = 0; i < inputs.size(); i++) {
				Wire w = inputs.get(i);
				w.setSignal(inSignals.get(i));
				inputs.set(i, w);
			}
		} else {
			throw new ExceptionLogicParameters(true, inputs.size(), inSignals.size()); // Exception is thrown when the
																						// number of inputs are
																						// incorrect
		}
	}

	@Override
	public void feed(String inSignals) {
		List<Signal> iSig = Signal.fromString(inSignals);
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
	public List<Signal> inspect(List<Signal> inputs) {
		feed(inputs);
		propagate();
		return read();
	}

	@Override
	public String inspect(String input) {
		feed(input);
		propagate();
		return read().toString();
	}

}

