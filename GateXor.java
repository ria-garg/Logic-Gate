import java.util.*;

public class GateXor extends Gate{
//-------------------------------------------------------------------------------------------------------------------------------
   
   // constuctor
   public GateXor(List<Wire> ins, Wire output){
     super("Xor",ins,output); // gate constuctor
   }
  
   // equality check
   @Override public boolean equals(Object other){
   
     if (other instanceof GateXor){
       GateXor orGate = (GateXor) other;
       return (super.equals(orGate));
     }
     else{
        return false;
     }
   }
   
   // HI if exactly one input is high, otherwise it is LO
   // propagate method, seeing how it respinds to different input signals
   @Override public boolean propagate(){
     
      Signal x = Signal.LO;
     
      int countHI = 0; 
      int countX = 0;
     
      // adding the number of times the HI or LO is signaled, total number at the end of loop
      for (Wire i : getInputs()){
        if (i.getSignal() == Signal.HI){
          countHI++;
        }
        else if (i.getSignal() == Signal.X){
          countX++;
        }
      }
      
      // logic for the specfic gate
      if (countHI == 1 && countX == 0){
         x = Signal.HI;
      }
      else if (countX > 0){
         x = Signal.X;
      }
     
      boolean a = true; // intializing value for the returned value, will change varying depending on the statement it goes thru
      
      if (getOutput().getSignal() == (x)){ // comparing signal values
         a = false;
         getOutput().setSignal(x); // setting the value of check as this signal
      } 
      else if (getOutput().getSignal() != (x)){ // comparing signal values
         a= true;
      }
          
   return a;
   }
  
//-------------------------------------------------------------------------------------------------------------------------------
}
