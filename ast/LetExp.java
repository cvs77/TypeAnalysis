package ast;

import type.AbstrType;
import type.TVarType;import util.Util;
public class LetExp extends AbstrExp {

	public VarExp var;
	public AbstrExp val;
	public AbstrExp body;
	
	public LetExp(VarExp var, AbstrExp val, AbstrExp body) {
		super();
		this.var = var;
		this.val = val;
		this.body = body;
	}

	@Override
	public boolean isComplexExp() {
		return true;
	}
    @Override
    public void typeJudge(){
        //var.typeJudge();

        //var.setType(valT);
        val.typeJudge();

        /*There should be a unit function, I have two types one from the Environment     another from the val you should
         * write a uinit function unit them together one side is null is ok */
       // System.out.println(val.getType().toString());

        if(val.getType()==null){
            System.out.println("Can not find the type of " + val.toString() + ": "+this.toString());
            System.exit(0);
        }
        else{
            assert val.getType()!=null;
            // If val do have a type, I should unit the two types :1. from the environment 1. from the val.

            var.setType(val.getType());



            assert var.getType()!=null;
            //Add var type information to the environment
            if(!(val.getType() instanceof TVarType)){
            this.enviroment.put(var.toString(),val.getType());
            }
        }

        body.typeJudge();
    }
	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String str = Util.indentStringOf(indent);
		String varStr = var.toString(printAnnot, false);
		if (printType) {
			AbstrType varType = var.getType();
			String typeStr = varType != null? varType.toString() : "unk";
			varStr = "[" + varStr + ":" + typeStr + "]";
		}
		str = str + "let " + varStr + " =";
		
		String valStr = val.toString(printAnnot, printType).trim();
		if (valStr.length() <= Util.expSize) {
			str = str + " " + valStr + " in \n";
            //str=str+"test1";
			str = str + body.toString(printAnnot, printType, indent);
			return str;
		}
		else {
			str = str + "\n" + val.toString(printAnnot, printType, indent+1) + "\n";
			str = str + "in\n";
			str = str + body.toString(printAnnot, printType, indent);
			return str;
		}
	}

}
