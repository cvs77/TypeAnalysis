package type;

public class IntType extends AbstrType {
	
	private static IntType instance = null;

	@Override
	public String toString() {
		return "Int";
	}

    @Override
    public AbstrType unit(AbstrType typeX) {
        assert typeX!=null;
       if(typeX instanceof  IntType){
            return getInstance();
        }
        else if(typeX instanceof TVarType){
            return getInstance();
        }
        return null;

    }

    @Override
    public AbstrType getTau(AbstrType Arg) {
        return null;
    }

    @Override
    public AbstrType setTau(AbstrType tauType) {
        return getInstance();
    }

    @Override
	public boolean isFunType() {
		return false;
	}
	
	public static IntType getInstance() {
		if (instance == null)
			instance = new IntType();
		
		return instance;
	}

}
