// enum Signal Class

import java.util.*;

public enum Signal{
//---------------------------------------------------------------------------------------------------------------------------------
   // values
   HI, LO, X;
   
   // invert method
   public Signal invert(){
   
      Signal invert = X;
      
         if (this == HI){
            invert = LO;
         }
         else if (this == LO){
            invert = HI;
         }
         
   return invert;   
   }
   
   // fromString method
   public static Signal fromString(char c) throws RuntimeException{
   
      Signal sig = X;
      
         if (c == '1'){
            sig = HI;
         }
         else if (c == '0'){
            sig = LO;
         }
         else if (c == 'X' || c == 'x'){
            sig = X;
         }
         else{
            throw new ExceptionLogicMalformedSignal(c,"bad characters present");
         }
         
   return sig;
   }
   
   // fromString method (different parameter)
   public static List<Signal> fromString(String inps){
   
      List<Signal> list = new ArrayList<Signal> ();
      String charactersOnly = inps.replaceAll("\\s","");
       
         for (char c : charactersOnly.toCharArray()){
            list.add(Signal.fromString(c));
         }
         
   return list;
   }
  
   // overriding toString
   @Override 
   public String toString(){
  
      String repr = "X";
         if (this == HI){
            repr = "1";
         }
         else if (this == LO) {
            repr = "0";
         }
        
   return repr;
   }
   
   // fromString method (different parameter #2)
   public static String toString(List<Signal> sig){
   
      String listRepr = "";
      for (Signal s : sig){
         listRepr += s.toString();
      }
      
   return listRepr;
   }
   
//---------------------------------------------------------------------------------------------------------------------------------
}
