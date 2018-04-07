import java.util.ArrayList;
import java.util.List;

public abstract class Gate implements Logic{
//------------------------------------------------------------------------------------------------------------------------------
	
   // fields
   private List<Wire> inputs;
	private Wire output;
	private String name;

	// default constructor
	public Gate(String name, List<Wire> ins, Wire out) throws RuntimeException{
      this.name = name;
		this.output = out;
		if (ins.size() < 0){
         throw new ExceptionLogicParameters(true, 1, 0);
		} 
      else{
		   this.inputs = ins;	
		}
	}

	// input a list of signals and feed the signals
	@Override
	public void feed(List<Signal> inSigs){
   
   int a = inSigs.size();
   int b = inputs.size();
		
      if (a == b){ // if sizes of two lists are the same then it replaces the values on inputs list from inSigs list
			for (int i = 0; i < b; i++) {
				Wire myWire = inputs.get(i);
				myWire.setSignal(inSigs.get(i));
				inputs.set(i, myWire);
			}
		} 
      else if (a != b){
			throw new ExceptionLogicParameters(true, inputs.size(), inSigs.size()); // Exception is thrown when the number of inputs are incorrect
		}
	}

   // obtaining signal values
	@Override
	public void feed(String signalsStr){
		List<Signal> sig = Signal.fromString(signalsStr);
		feed(sig); // calling method above in here, but this feed is done first, as it is overriding
	}
   
   // keeping this method abstract as it does not need to be implemented right now
	@Override
	public abstract boolean propagate();

   // reading single value from output wire, returning single values in a list
	@Override
	public List<Signal> read(){
		List<Signal> single = new ArrayList<Signal>();
		single.add(output.getSignal());
		return single;
	}
   
   // combining logic methods into one, for use later, which will be easier to call
	@Override
	public List<Signal> inspect(List<Signal> inSigs){
		feed(inSigs);
		propagate();
		return read();
	}

   // string of signals -- not a list
	@Override
	public String inspect(String inStr) { 
		feed(inStr);
		propagate();
		return read().toString();
	}

   // converting everything to string for printing later
	@Override
	public String toString() {
		return ("\"" + name + "( [" + inputs + "] | " + output + " )");
	}

   // equality check
	@Override
	public boolean equals(Object other) { // compares two gate objects to each other
		
		if (other instanceof Gate){
			Gate g = (Gate) other;
			if (g.getName().equals(name) && g.getOutput().equals(output) && g.getInputs().equals(inputs)) {
				return true;
			}
		}
      
   return false;
	}

	// getters
	public List<Wire> getInputs() {
		return inputs;
	}
	public Wire getOutput() {
		return output;
	}
	public String getName() {
		return name;
	}

   // setters
	public void setInputs(List<Wire> inputs) {
		this.inputs = inputs;
	}
	public void setOutput(Wire output) {
		this.output = output;
	}
	public void setName(String name) {
		this.name = name;
	}
   
//------------------------------------------------------------------------------------------------------------------------------
}
