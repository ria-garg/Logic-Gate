import java.util.*;

public class GateNor extends Gate{
//-------------------------------------------------------------------------------------------------------------------------------
   
   // constuctor
   public GateNor(List<Wire> ins, Wire output){
     super("Nor",ins,output); // gate constuctor
   }
  
   // equality check
   @Override public boolean equals(Object other){
   
     if (other instanceof GateNor){
       GateNor norGate = (GateNor) other;
       return (super.equals(norGate));
     }
     else{
        return false;
     }
   }
   
   // LO is any inputs are high, otherwise HI
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
      if (countHI > 0){
         check = Signal.HI;
      }
      else if (countHI == 0 && countX > 0){
         check = Signal.X;
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
