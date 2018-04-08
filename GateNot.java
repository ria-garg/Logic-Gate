import java.util.*;

public class GateNot extends Gate{
//---------------------------------------------------------------------------------------------------------------------------
   
   // constructor
   public GateNot(Wire input, Wire output){
      super("Not",Arrays.asList(new Wire[]{input}),output);
   }
   
   // equality check 
   @Override public boolean equals(Object other){
      boolean check = false;
      if (other instanceof GateAnd){
         GateAnd g = (GateAnd) other;
         check = super.equals(g);
      }
   return check;
   }
  
   // inverts it's one value
   // true if signal change, false otherwise
   @Override public boolean propagate(){
    
      Signal s = w.getSignal();
      Wire w = getInputs().get(0);
      
      s = s.invert(); // inverting the signal, will change unless it's X
      
      // determining if signal has changed
      if (getOutput().getSignal().equals(s)){
         getOutput().setSignal(s); // setting for later testing
         return false;
      }
      
   return true;
   }
      
//---------------------------------------------------------------------------------------------------------------------------
}
