import java.util.*;

public enum Signal { // declares the signal objects
	HI, LO, X;

	public Signal invert() { // converts to opposite signal
		if (this == Signal.HI) {
			return Signal.LO; // returns low signal
		} else if (this == Signal.LO) {
			return Signal.HI; // returns high signal
		} else {
			return this; // returns the current signal
		}
	}

	public static Signal fromString(char c) throws RuntimeException{ // converts string representation to signal
		switch (c) {

		case 'H': // it's a high signal
			if (c == '1') {
				return HI;
			}

		case 'L': // it's a low signal
			if (c == '0') {
				return LO;
			}

		case 'X': // could be a high or low signal
			if ((c == 'X') || (c == 'x')) {
				return X;
			}

		default:
			return throw ExceptionLogicMalformedSignal();
		}
	}

	public static List<Signal> fromString(String inps) {
		List<Signal> mySignals = new ArrayList<Signal>();
		String noSpaces = inps.replaceAll("\\s", "");
		for (char c : noSpaces.toCharArray()) {
			mySignals.add(Signal.fromString(c));
		}
		return mySignals;

	}

	@Override
	public String toString() {
		if (this == HI) {
			return "1";
		} else if (this == LO) {
			return "0";
		} else {
			return "X";
		}
	}

	public static String toString(List<Signal> sig) {
		String list = "";
		for (Signal s : sig) {
			list += s.toString();
		}
		return list;
	}
}
