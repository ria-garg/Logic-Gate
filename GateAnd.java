import java.util.List;

public class GateAnd extends Gate {

	public GateAnd(List<Wire> ins, Wire out) {
		super("AND", ins, out);
	}

	@Override
	public boolean propagate() {
		int numX = 0;
		int numHI = 0;
		int numLO = 0;
		for (Wire w : getInputs()) {
			if (w.getSignal() == Signal.HI) {
				numHI++;
			} else if (w.getSignal() == Signal.LO) {
				numLO++;
			} else if (w.getSignal() == Signal.X) {
				numX++;
			}
		}
		if (numHI == this.getInputs().size()) {
			this.getOutput().setSignal(Signal.HI);
		} else if ((numLO == this.getInputs().size() && numX > 0) || (numX == this.getInputs().size())) {
			this.getOutput().setSignal(Signal.X);
		} else {
			this.getOutput().setSignal(Signal.LO);
		}
		if ((numX > 0) || (numLO > 0)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean equals(Object other) {
		boolean path = false;
		if (other instanceof GateAnd) {
			GateAnd g = (GateAnd) other;
			path = super.equals(g);
		}
		return path;
	}

}
