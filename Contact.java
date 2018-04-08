import java.util.*;

public /*abstract*/ class Contact /*implements Logic*/{
//--------------------------------------------------------------------------------------------------------------------------------
  
   // fields
   private Wire in;
   private Wire out;
   private boolean inbound;
  
   // constructor
   public Contact(Wire in, Wire out, boolean inbound){
     this.in = in;
     this.out = out;
     this.inbound = inbound;
   }
   
   // printing representation -- https://www.javatpoint.com/java-string-format
   @Override public String toString(){
     
      if (in.getName().equals(out.getName())){
         return (String.format("%s:%s",in.getName(),in.getSignal()));
      }
      else{
         return (String.format(inbound ? "%s(%s):%s" : "(%s)%s:%s",in.getName(),out.getName(),out.getSignal()));
      }
   }
  
   // equality check
   @Override public boolean equals(Object o){
     
     if (o instanceof Contact){
        Contact c = (Contact) o;
           if (c.getIn().equals(in) && c.getOut().equals(out) && c.getInbound()==inbound){
              return true;
           }
     }
     
   return false;
   }
  
   // getters
   public Wire getIn(){
      return this.in;
   }
   public Wire getOut(){
      return this.out;
   }
   public boolean getInbound(){
      return this.inbound;
   }

   // setters
   public void setIn(Wire in){
      this.in = in;
   }
   public void setOut(Wire out){
      this.out = out;
   }
   public void setInbound(boolean inbound){
      this.inbound = inbound;
   }
   
   /*
   // abstract methods
   @Override public abstract void feed (List<Signal> inputSignals);
   @Override public abstract void feed (String inputSignals);
   @Override public abstract List<Signal> read();
   @Override public abstract List<Signal> inspect(List<Signal> inSigs);
   @Override public abstract String inspect(String str);
   @Override public abstract boolean propagate();
   */
    
//--------------------------------------------------------------------------------------------------------------------------------
}
