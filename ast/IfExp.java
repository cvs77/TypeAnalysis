package ast;

import type.AbstrType;import type.BoolType;
import type.TVarType;import util.Util;

public class IfExp extends AbstrExp {
	
	public AbstrExp cond;
	public AbstrExp thenBody;
	public AbstrExp elseBody;

	public IfExp(AbstrExp cond, AbstrExp thenExp, AbstrExp elseExp) {
		super();
		this.cond = cond;
		this.thenBody = thenExp;
		this.elseBody = elseExp;
	}

	@Override
	public boolean isComplexExp() {
		return true;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String indentStr = Util.indentStringOf(indent);
		
		String condStr = cond.toString(printAnnot, printType).trim();
		String thenStr = thenBody.toString(printAnnot, printType).trim();
		String elseStr = elseBody.toString(printAnnot, printType).trim();
		
		int condLen = condStr.length();
		int thenLen = thenStr.length();
		int elseLen = elseStr.length();
		
		// print if-then-else in 1 line
		if (condLen + thenLen + elseLen <= Util.expSize) {
			String str = indentStr + "if " + condStr + " then " + thenStr;
			str = str + " else " + elseStr;
			return str;
		}
		// print thenExp in 1 line
		else if (condLen + thenLen < Util.expSize) {
			String str = indentStr + "if " + condStr + " then " + thenStr + "\n";
			// elseExp in 1 line
			if (elseStr.length() < Util.expSize) {
				str = str + indentStr + "else " + elseStr; 
			}
			// elseExp in several lines
			else {
				str = str + indentStr + "else\n";
				str = str + elseBody.toString(printAnnot, printType, indent+1);;
			}
			return str;
		}
		// print thenExp in several lines 
		else {
			String str = indentStr + "if " + condStr + " then\n";
			str = str + thenBody.toString(printAnnot, printType, indent+1) + "\n";
			// elseExp in 1 line
			if (elseStr.length() < Util.expSize) {
				str = str + indentStr + "else " + elseStr; 
			}
			// elseExp in several lines
			else {
				str = str + indentStr + "else\n";
				str = str + elseBody.toString(printAnnot, printType, indent+1);;
			}
			return str;
		}
	}

    @Override
    public void typeJudge() {
        BoolType boolT =new BoolType();
        cond.typeJudge();
        assert cond.getType()!=null;
        boolT.unit(cond.getType());
        thenBody.typeJudge();
        elseBody.typeJudge();
        assert thenBody.getType()!=null;
        assert elseBody.getType()!=null;


        AbstrType bodyT;
        bodyT=this.thenBody.getType().unit(this.elseBody.getType());
        if(this.thenBody.getType() instanceof TVarType ){
            if(!(bodyT instanceof TVarType)){
                this.enviroment.put(this.thenBody.getType().toString(),bodyT);
            }
        }
        if(this.elseBody.getType() instanceof  TVarType){
            if(!(bodyT instanceof TVarType)){
                this.enviroment.put(this.elseBody.getType().toString(),bodyT);
            }
        }
        this.setType(bodyT);

    }

}
