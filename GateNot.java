import java.util.List;

public class GateNot extends Gate {

	public GateNot(String name, List<Wire> ins, Wire out) throws RuntimeException {
		super("NOT", ins, out);
	}

	@Override
	public boolean propagate() {
		boolean gate = true;
		Wire w = (Wire) getInputs().get(0);
		Signal s = w.getSignal();
		s = s.invert();
		if(getOutput().getSignal().equals(s)) {
			gate = false;
		}
		getOutput().setSignal(s);
		return gate;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean path = false;
		if (other instanceof GateNot) {
			GateNot g = (GateNot) other;
			path = super.equals(g);
		}
		return path;
	}

}
