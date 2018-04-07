import java.util.*;

public class GateNand extends Gate{
//-------------------------------------------------------------------------------------------------------------------------------
   
   // constuctor
   public GateNand(List<Wire> ins, Wire output){
     super("Nand",ins,output); // gate constuctor
   }
  
   // equality check
   @Override public boolean equals(Object other){
   
     if (other instanceof GateNand){
       GateNand nAndGate = (GateNand) other;
       return (super.equals(nAndGate));
     }
     else{
        return false;
     }
   }
   
   // LO if all inputs are high, otherwise HI
   // propagate method, seeing how it respinds to different input signals
   @Override public boolean propagate(){
     
      Signal x = Signal.LO;
     
      int countHI = 0; 
      int countX = 0;
      int countLO = 0;
     
      // adding the number of times the HI or LO is signaled, total number at the end of loop
      for (Wire i : getInputs()){
        if (i.getSignal() == Signal.HI){
           countHI++;
        }
        else if (i.getSignal() == Signal.X){
           countX++;
        }
        else if(i.getSignal() == Signal.LO){
           countLO++;
        }
      }
      
      // logic for the specfic gate
      if (countLO > 0){
         x = Signal.LO;
      }
      else if (countHI>0 && countX == 0){
         x = Signal.HI;
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
