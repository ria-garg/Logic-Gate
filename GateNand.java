import java.util.*;

public class GateNand extends Gate{
//---------------------------------------------------------------------------------------------------------------------------

   // constructor
   public GateNand(List<Wire> ins, Wire output){
      super("Nand",ins,output);
   }
   
   // equality check 
   @Override public boolean equals(Object other){
      boolean check = false;
      if (other instanceof GateNand){
         GateNand g = (GateNand) other;
         check = super.equals(g);
      }
   return check;
   }
  
   // LO if all inputs are high, HI otherwise
   // true if signal change, false otherwise
   @Override public boolean propagate(){
     
      // inializing values to use throughout
      Signal sigVal = Signal.X;
      
      // acdding up total values of signals
      int countHI = 0; 
      int countX = 0;
      int countLO = 0;
      
      for (Wire i : getInputs()){
         if (i.getSignal() == Signal.HI){
            countHI++;
         }
         else if (i.getSignal() == Signal.X){
            countX++;
         }
         else if (i.getSignal() == Signal.X){
            countLO++;
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
