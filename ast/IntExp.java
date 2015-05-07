package ast;

import type.IntType;
import util.Util;

public class IntExp extends AbstrExp {
	private int value;

	public IntExp(int value) {
		super();
		this.value = value;
	}

	@Override
	public boolean isComplexExp() {
		return false;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		return Util.indentStringOf(indent) + String.valueOf(value)+"";
	}

    @Override
    public void typeJudge() {
        this.setType(new IntType());

    }

}
