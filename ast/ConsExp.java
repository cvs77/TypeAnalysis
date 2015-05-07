package ast;

import type.AbstrType;
import type.ListType;
import type.TVarType;
import util.Util;

public class ConsExp extends AbstrExp {

	public AbstrExp e1;
	public AbstrExp e2;
	public Annot annot;
	
	public ConsExp(AbstrExp e1, AbstrExp e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}

	public ConsExp(AbstrExp e1, AbstrExp e2, Annot annot) {
		super();
		this.e1 = e1;
		this.e2 = e2;
		this.annot = annot;
	}

	@Override
	public boolean isComplexExp() {
		return true;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String annStr = "";
		if (annot != null)
			annStr = printAnnot ? annot.toString() : "";
		String e1Str = e1.toString(printAnnot, printType);
		String e2Str = e2.toString(printAnnot, printType);
		String str = Util.indentStringOf(indent);
		str = str + "Cons" + annStr + "("+ e1Str + ", " + e2Str +")";
		return str;
	}

    @Override
    public void typeJudge() {
        e1.typeJudge();
        e2.typeJudge();
        AbstrType jugRes;
        //e2.getType();
        AbstrType conET;
        conET=e2.getType();
        if(conET instanceof ListType){
            if(e2 instanceof NilExp){
               e2.setType(new ListType(e1.getType()));
               jugRes=e1.getType();

            }
            else{
                assert conET instanceof ListType;
                AbstrType newItemT;

                newItemT=((ListType) conET).getItemType().unit(e1.getType());
                if(newItemT!=null){
                    if(((ListType) conET).getItemType() instanceof TVarType){
                        if(!(newItemT instanceof TVarType)){
                            this.enviroment.put(((ListType) conET).getItemType().toString(),newItemT);
                        }
                    }
                    else if(e1.getType() instanceof TVarType){
                        if(!(newItemT instanceof TVarType)){
                            this.enviroment.put(e1.getType().toString(),newItemT);
                        }
                    }
                    jugRes=newItemT;

                }
                else{
                    assert newItemT==null;
                    System.out.println("Incompatible types.");
                    System.out.println("Required "+" : "+((ListType) conET).getItemType().toString());
                    System.out.println("Found"+" : "+e1.getType().toString());
                    System.out.println(" In "+this.toString());
                    System.exit(1);
                    jugRes=null;

                }

            }

        }
        else if(conET instanceof TVarType){
            this.enviroment.put(conET.toString(),new ListType(e1.getType()));
            jugRes=e1.getType();
        }
        else{
            //e2 either is ConsExp or NilExp we can assume some error have already happen
            System.out.println("Error! "+e2.toString()+" is not a NilExp, ConsExp or VarExp");
            jugRes=null;
            System.exit(1);
        }


    this.setType(new ListType(jugRes));



    }

}
