import java.util.*;

public class GateXor extends Gate{
//---------------------------------------------------------------------------------------------------------------------------

   // constructor
   public GateXor(List<Wire> ins, Wire output){
      super("Xor",ins,output);
   }
   
   // equality check 
   @Override public boolean equals(Object other){
      boolean check = false;
      if (other instanceof GateXor){
         GateXor g = (GateXor) other;
         check = super.equals(g);
      }
   return check;
   }
  
   // HI if exactly one input is high, LO otherwise
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
      if (countX > 0){
         sigVal = Signal.X;
      }
      else if (countHI == 1 && countX == 0){
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
