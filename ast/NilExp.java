package ast;

import type.AbstrType;
import type.ListType;
import type.TVarType;
import util.Util;

public class NilExp extends AbstrExp {
	
	public NilExp() {
		super();
	}

	@Override
	public boolean isComplexExp() {
		return false;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		return Util.indentStringOf(indent) + "Nil";
	}

    @Override
    public void typeJudge() {
        AbstrType nilIteT;
        nilIteT=new TVarType("tau");
        setType(new ListType(nilIteT));

    }
}
