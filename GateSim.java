import java.util.*;
import java.io.*;

public class GateSim{
//---------------------------------------------------------------------------------------------------------------------------
  
  public static void main(String[] args) throws Exception{
    
    try{
      Circuit myCircuit = new Circuit(args[0]);
      myCircuit.feed(args[1]);
      myCircuit.propagate();
      String str = Signal.toString(myCircuit.read());
      System.out.println(str);
    }
    catch (Exception e){
      System.out.println("couldn't perform simulation");
    }
  }
  
//---------------------------------------------------------------------------------------------------------------------------
}
