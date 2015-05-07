package ast;
import type.BoolType;
import util.Util;

public class BoolExp extends AbstrExp {
	private boolean value;

	public BoolExp(boolean value) {
		super();
		this.value = value;
	}

	@Override
	public boolean isComplexExp() {
		return false;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String str = value ? "true" : "false";
        ///str=str+"test2";
		return Util.indentStringOf(indent) + str;
	}

    @Override
    public void typeJudge() {

       this.setType(new BoolType());
    }

}
