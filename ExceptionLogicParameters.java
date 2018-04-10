public class ExceptionLogicParameters{
//---------------------------------------------------------------------------------------------------------------------------
  
   // fields
   private boolean inputsRelated;
   private int expected;
   private int found;
  
   // constuctor
   public ExceptionLogicParameters(boolean inputsRelated, int expected, int found){
     this.inputsRelated = inputsRelated;
     this.expected = expected;
     this.found = found;
   }
   
   // for formatting
   @Override public String toString(){
     String toString = String.format("expected %d, but found %d",expected,found);
     return toString;
   }
  
   // getters
   public boolean getInputsRelated(){
      return this.inputsRelated;
   }
   public int getExpected(){
      return this.expected;
   }
   public int getFound(){
      return this.found;
   }

   // setters
   public void setInputsRelated(boolean inputsRelated){
      this.inputsRelated = inputsRelated;
   }
   public void setExpected(int expected){
      this.expected = expected;
   }
   public void setFound(int found){
      this.found = found;
   }
  
//---------------------------------------------------------------------------------------------------------------------------
}
