import java.util.*;

public class GateOr extends Gate{
//---------------------------------------------------------------------------------------------------------------------------

   // constructor
   public GateOr(List<Wire> ins, Wire output){
      super("Or",ins,output);
   }
   
   // equality check 
   @Override public boolean equals(Object other){
      boolean check = false;
      if (other instanceof GateOr){
         GateOr g = (GateOr) other;
         check = super.equals(g);
      }
   return check;
   }
  
   // HI if any inputs are high, LO otherwise
   // true if signal change, false otherwise
   @Override public boolean propagate(){
     
      // inializing values to use throughout
      Signal sigVal = Signal.LO;
      
      // acdding up total values of signals
      int countHI = 0; 
      int countX = 0;
      
      for (Wire i : getInputs()){
         if (i.getSignal() == Signal.HI){
            countHI++;
         }
         else if (i.getSignal() == Signal.X){
            countX++;
         }
      }
      
      // logic for signal gate
      if (countHI > 0){
         sigVal = Signal.HI;
      }
      else if (countHI == 0 && countX > 0){
         sigVal = Signal.X;
      }
      
      // determining if signal changed or not
      if (getOutput().getSignal().equals(sigVal)){
         getOutput().setSignal(sigVal);
         return false;
      }
         
   return true;
   }
    
//---------------------------------------------------------------------------------------------------------------------------
}
