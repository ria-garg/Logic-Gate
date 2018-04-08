public class ExceptionLogicMalformedSignal extends RuntimeException{
//--------------------------------------------------------------------------------------------------------------------------
 
   // fields
   private char bad;
   private String msg;
  
   // constuctor
   public ExceptionLogicMalformedSignal(char bad, String msg){
      this.bad = bad;
      this.msg = msg;
   }
   
   // formatting purposes
   @Override public String toString(){
      return msg;
   }
  
   // getters
   public char getBad(){
      return this.bad;
   }
   public String getMsg(){
      return this.msg;
   }

   // setters
   public void setBad(char bad){
      this.bad = bad;
   }
   public void setMsg(String msg){
      this.msg = msg;
   }

//--------------------------------------------------------------------------------------------------------------------------
}
