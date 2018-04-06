public class Wire {
	private Signal signal;
	private String name;

	public Wire(String name) {
		this.name = name; // sets name
		signal = Signal.X; // sets value of signal to an unkown value
	}

	@Override
	public String toString() {
		return name + ":" + signal.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Wire)) {
			return false;
		}
		Wire oldWire = (Wire) other;
		return oldWire.getSignal() == signal && oldWire.getName().equals(name);
	}

	public Signal getSignal() {
		return signal;
	}

	public String getName() {
		return name;
	}

	public void setSignal(Signal signal) {
		this.signal = signal;
	}

	public void setName(String name) {
		this.name = name;
	}

}
