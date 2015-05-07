package type;

public class BoolType extends AbstrType {
	
	private static BoolType instance = null;

	@Override
	public String toString() {
		return "Bool";
	}

    @Override
    public AbstrType unit(AbstrType typeX) {
        assert typeX!=null;
        if(typeX instanceof  BoolType){
            return getInstance();
        }
        else if(typeX instanceof TVarType){
            return  getInstance();
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
	
	public static BoolType getInstance() {
		if (instance == null)
			instance = new BoolType();
		
		return instance;
	}

}
