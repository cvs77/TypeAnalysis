package ast;

import type.AbstrType;
import type.BoolType;
import type.IntType;
import type.TVarType;
import util.Util;

public class UnaryExp extends AbstrExp {
	
	public String op;
	public AbstrExp e;

	public UnaryExp(String op, AbstrExp e) {
		super();
		this.op = op;
		this.e = e;
	}

	@Override
	public boolean isComplexExp() {
		return true;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String str = e.toString(printAnnot, printType).trim();
		if (e.isComplexExp())
			str = op + "(" + str + ")";
		else
			str = op + str;
		str = Util.indentStringOf(indent) + str;
		return str;
	}

    @Override
    public void typeJudge() {
        e.typeJudge();
        AbstrType abT;
        switch(op){
            case "-":
                abT=new IntType();
                break;
            case "!":
                abT=new BoolType();
                break;
            default:
                abT=new TVarType("Alpha_default"+op);
                break;
        }
        AbstrType typeUnit=null;

        typeUnit=abT.unit(e.getType());
        if(typeUnit==null){
            System.out.println("Incompatible types.");
            System.out.println("Required: "+ abT.toString());
            System.out.println("Found :"+e.getType().toString());

            System.exit(0);

        }
        this.setType(abT);


    }

}
