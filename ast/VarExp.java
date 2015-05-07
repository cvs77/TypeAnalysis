package ast;

import type.*;
import util.Util;

public class VarExp extends AbstrExp {
	
	private String name;

	public VarExp(String name) {
		this.name = name;
	}
    public String getVarName(){
        return name;
    }
	@Override
	public boolean isComplexExp() {
		return false;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String varStr = name;
		return Util.indentStringOf(indent) + varStr;
	}

    @Override
    public void typeJudge() {
        AbstrType MatchtoGround;
        MatchtoGround=this.enviroment.get(this.toString());
        if(MatchtoGround!=null){
            assert MatchtoGround!=null;
            while(this.enviroment.get(MatchtoGround.toString())!=null){
                MatchtoGround=this.enviroment.get(MatchtoGround.toString());
            }
        assert MatchtoGround!=null;
        this.setType(MatchtoGround);
        }
        else{
            assert this.enviroment.get(this.toString())==null;
            //Mean the enviroment don't have my type, so I will select a VarType
            System.out.println("Can not find the type of "+this.toString());
            System.exit(1);
        }
    }

}
