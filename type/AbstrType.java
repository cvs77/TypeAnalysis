package type;

import ast.Annot;

import java.util.HashMap;
import java.util.HashSet;

public abstract class AbstrType {
	
	public abstract String toString();
	public abstract AbstrType unit(AbstrType Atype);
    public abstract AbstrType getTau(AbstrType Arg);
    public abstract AbstrType setTau(AbstrType tauType);

	public abstract boolean isFunType();
	
	public void println() {
		System.out.println(this.toString());
	}

}
