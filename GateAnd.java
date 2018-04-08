import java.util.*;

public class GateAnd extends Gate{
//---------------------------------------------------------------------------------------------------------------------------

   // constructor
   public GateAnd(List<Wire> ins, Wire output){
      super("And",ins,output);
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
  
   // HI if all inputs are high, LO otherwise
   // true if signal change, false otherwise
   @Override public boolean propagate(){
     
      // inializing values to use throughout
      Signal sigVal = Signal.X;
      
      // acdding up total values of signals
      int countLO = 0; 
      int countHI = 0; 
      int countX = 0;
      
      for (Wire i : getInputs()){
         if (i.getSignal() == Signal.LO){
            countLO++;
         }
         else if (i.getSignal() == Signal.HI){
           countHI++;
         }
         else if (i.getSignal() == Signal.X){
           countX++;
         }
      }
      
      // logic for signal gate
      if (countLO > 0){
         sigVal = Signal.LO;
      }
      else if (countX == 0 && countHI > 0){
         sigVal = Signal.HI;
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
