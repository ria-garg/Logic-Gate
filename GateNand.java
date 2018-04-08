public class Wire{
//-------------------------------------------------------------------------------------------------------------------------------
	
   // fields
   private Signal signal;
	private String name;

   // constructor
	public Wire(String name){
		this.name = name; // sets name
		this.signal = Signal.X; // sets value of signal to an unkown value
	}

	@Override
	public String toString(){
		return (name + ":" + signal.toString());
	}

   // equality check
	@Override
	public boolean equals(Object other){
		
   boolean result = false; // just to initalize, this value may change later as it goes thru the if else  
      
      if (this == other){
         result = true;
      }
      else if (other instanceof Wire){
         Wire o = (Wire)other;
         result = (o.getSignal() == signal && o.getName().equals(name));
      }
      
   return result;   
	}
   
   // getters
	public Signal getSignal(){
		return signal;
	}
	public String getName(){
		return name;
	}

   // setters
	public void setSignal(Signal signal){
		this.signal = signal;
	}
	public void setName(String name){
		this.name = name;
	}
   
//-------------------------------------------------------------------------------------------------------------------------------
}
