 package type;

 import ast.ConsExp;

 // Type Variables
public class TVarType extends AbstrType {
	
	private String tvarName;				// name of type variable
	public TVarType(String tvarName) {
		super();
		this.tvarName = tvarName;
	}

	@Override
	public String toString() {
		return tvarName;
	}

    @Override
    public AbstrType unit(AbstrType typeX) {
        assert typeX!=null;
        if(typeX instanceof IntType){
            return IntType.getInstance();
        }
        else if(typeX instanceof BoolType){
            return  BoolType.getInstance();
        }
        else if(typeX instanceof  FunType){


            return new FunType(((FunType) typeX).getArgType(),((FunType) typeX).getRetType(),((FunType) typeX).getAnnotSet());

        }
        else if(typeX instanceof TVarType){
            //fial it
            if(typeX.toString().equals(this.toString())){
                return this;
            }
            else{
            System.out.println("Cannot Unity two Variable types: "+this.toString()+"  "+typeX.toString() );
                System.exit(1);
            return null;
            }
        }
        else if(typeX instanceof ListType){
            return new ListType(((ListType) typeX).getItemType());

        }
        return null;
    }

     @Override
     public AbstrType getTau(AbstrType Arg) {
         if(this.tvarName.equals("tau")){
             return Arg;
         }
         else{
            return null;
         }
     }

     @Override
     public AbstrType setTau(AbstrType tauType) {
         if(this.tvarName.equals("tau")){
             return  tauType;
         }
         else{
         return this;
         }
     }

     @Override
	public boolean isFunType() {
		return false;
	}

}
