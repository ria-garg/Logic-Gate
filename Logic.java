import java.util.*;

public interface Logic {
	public abstract void feed(List<Signal> inSignals); // gives signals to the input wires

	public abstract void feed(String inSignals); // accepts a string representation of signals

	public abstract boolean propagate(); // performs the logic and generates outputs

	public abstract List<Signal> read(); // reads the output signals and creates a new List

	public abstract List<Signal> inspect(List<Signal> inputs); // combination of feeding, propagating, and reading

	public abstract String inspect(String input); // combination of feeding, propagating, and reading
}
