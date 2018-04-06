import java.util.*;

public enum Signal{ 
//------------------------------------------------------------------------------------------------------------------------------

   // declaring the signal objects
	HI, LO, X;

   // returning inversion of "this" signal
	public Signal invert(){
		
      if (this == Signal.HI){
			return Signal.LO; // returns low signal
		} 
      else if (this == Signal.LO){
			return Signal.HI; // returns high signal
		} 
      else{
			return Signal.X; // returns the current signal
		}
	}

   // converts string representation to signal
	public static Signal fromString(char c) throws RuntimeException{ 

	   if (c == '1'){ // it's a high signal
	      return HI;
	   }
      else if (c == '0'){ // it's a low signal
         return LO;
      }
      else if (c == 'X'){ // could be a high or low signal
         return X;
      }
      else if (c == 'x'){
         return X;
      }   
      else{
    	   throw new ExceptionLogicMalformedSignal(c, "<error>");
      }
	}
   
   // returning list of signal values found from string input
	public static List<Signal> fromString(String inps){
   
		List<Signal> mySignals = new ArrayList<Signal>(); // creating list and using the class as it's type
		
      String noSpaces = inps.replaceAll("\\s", "");
      
		for (char c : noSpaces.toCharArray()){
		   mySignals.add(Signal.fromString(c));
	   }
         
   return mySignals;
	}

   // overriding method under this one
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

   // converting each singal in mySignals List to a String
	public static String toString(List<Signal> sig) {
		
      String list = "";
      
		for (Signal s : sig) {
			list += s.toString();
		}
      
   return list;
	}
   
//------------------------------------------------------------------------------------------------------------------------------
}
