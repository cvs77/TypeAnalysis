package ast;

import type.AbstrType;
import type.FunType;
import type.ListType;
import type.TVarType;
import util.Util;

import java.util.HashSet;

public class CaseExp extends AbstrExp {
	public AbstrExp e;
	public ConsExp consPattern;
	public AbstrExp consBody;
	public AbstrExp nilBody;
	
	public CaseExp(AbstrExp e, ConsExp consPattern, AbstrExp consBody, AbstrExp nilBody) {
		super();
		this.e = e;
		this.consPattern = consPattern;
		this.consBody = consBody;
		this.nilBody = nilBody;
	}

	@Override
	public boolean isComplexExp() {
		return true;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String str = Util.indentStringOf(indent);
		str = str + "case " + e.toString(printAnnot, printType) + " of \n";

		AbstrExp condE1 = consPattern.e1;
		String condE1Str = condE1.toString(printAnnot, printType);
		if (printType) {
			AbstrType type = condE1.getType();
			String typeStr = type != null? type.toString() : "unk";
			condE1Str = "[" + condE1Str + ":" + typeStr + "]";
		}
		AbstrExp condE2 = consPattern.e2;
		String condE2Str = condE2.toString(printAnnot, printType);
		if (printType) {
			AbstrType type = condE2.getType();
			String typeStr = type != null? type.toString() : "unk";
			condE2Str = "[" + condE2Str + ":" + typeStr + "]";
		}
		String consPatternStr = "Cons(" + condE1Str + ", " + condE2Str + ") =>";
		consPatternStr = Util.indentStringOf(indent+1) + consPatternStr;

		String consBodyStr = consBody.toString(printAnnot, printType).trim();
		str = str + consPatternStr;
		if (consPatternStr.length() + consBodyStr.length() < Util.expSize)
			str = str + " " + consBodyStr + "\n";
		else
			str = str + "\n" + consBody.toString(printAnnot, printType, indent+2) + "\n";

		String nilPatternStr = Util.indentStringOf(indent+1) + "or Nil =>";
		String nilBodyStr = nilBody.toString(printAnnot, printType).trim();
		str = str + nilPatternStr;
		if (nilPatternStr.length() + nilBodyStr.length() < Util.expSize)
			str = str + " " + nilBodyStr;
		else
			str = str + "\n" + nilBody.toString(printAnnot, printType, indent+2);
		return str;
	}

    @Override
    public void typeJudge() {
        e.typeJudge();
        if(e.getType() instanceof ListType){
            if(!(e instanceof NilExp)){
                //assert e instanceof ConsExp;
                assert this.consPattern.e1!=null;
                assert this.consPattern.e2!=null;
                this.consPattern.e1.setType(((ListType) e.getType()).getItemType());
                this.consPattern.e2.setType(new ListType(((ListType) e.getType()).getItemType()));
                this.enviroment.put(consPattern.e1.toString(),consPattern.e1.getType());
                this.enviroment.put(consPattern.e2.toString(),consPattern.e2.getType());
            }
            this.consBody.typeJudge();
            this.nilBody.typeJudge();
            assert this.consBody!=null;
            assert this.nilBody!=null;
            if(this.consBody.getType() instanceof FunType){
                if(this.nilBody.getType() instanceof TVarType){
                    HashSet<Annot> temp=new HashSet<Annot>();
                    Annot tempAnnot=new Annot("Bata");
                    temp.add(tempAnnot);
                    FunType nilFun;
                    nilFun=new FunType(((FunType) this.consBody.getType()).getArgType(),((FunType) this.consBody.getType()).getRetType(),temp);
                    this.enviroment.put(this.nilBody.getType().toString(),nilFun);
                    nilBody.setType(nilFun);
                }
            }
            else if(this.nilBody.getType() instanceof FunType){
                if(this.consBody.getType() instanceof  TVarType){
                    HashSet<Annot> temp=new HashSet<Annot>();
                    Annot tempAnnot=new Annot("Bata");
                    temp.add(tempAnnot);
                    FunType conFun;
                    conFun=new FunType(((FunType) this.nilBody.getType()).getArgType(),((FunType) this.nilBody.getType()).getRetType(),temp);
                    this.enviroment.put(this.consBody.getType().toString(),conFun);
                    consBody.setType(conFun);
                }
            }
            AbstrType bodyT;
            bodyT=this.consBody.getType().unit(this.nilBody.getType());
            if(this.consBody.getType() instanceof TVarType ){
                if(!(bodyT instanceof TVarType)){
                    this.enviroment.put(this.consBody.getType().toString(),bodyT);
                }
            }
            if(this.nilBody.getType() instanceof  TVarType){
                if(!(bodyT instanceof TVarType)){
                    this.enviroment.put(this.nilBody.getType().toString(),bodyT);
                }
            }

            this.setType(bodyT);
        }
        else if(e.getType() instanceof TVarType){
            AbstrType eLisT;
            AbstrType eIteT;
            eIteT=new TVarType("tau");
            eLisT=new ListType(eIteT);
            this.enviroment.put(e.getType().toString(),eLisT);
            assert this.consPattern.e1!=null;
            assert this.consPattern.e2!=null;
            this.consPattern.e1.setType(eIteT);
            this.consPattern.e2.setType(eLisT);
            this.enviroment.put(consPattern.e1.toString(),consPattern.e1.getType());
            this.enviroment.put(consPattern.e2.toString(),consPattern.e2.getType());
            this.consBody.typeJudge();
            this.nilBody.typeJudge();
            assert this.consBody!=null;
            assert this.nilBody!=null;
            if(this.consBody.getType() instanceof FunType){
                if(this.nilBody.getType() instanceof TVarType){
                    HashSet<Annot> temp=new HashSet<Annot>();
                    Annot tempAnnot=new Annot("Bata");
                    temp.add(tempAnnot);
                    FunType nilFun;
                    nilFun=new FunType(((FunType) this.consBody.getType()).getArgType(),((FunType) this.consBody.getType()).getRetType(),temp);

                    this.enviroment.put(this.nilBody.getType().toString(),nilFun);
                    nilBody.setType(nilFun);
                }
            }
            if(this.nilBody.getType() instanceof FunType){
                if(this.consBody.getType() instanceof  TVarType){
                    HashSet<Annot> temp=new HashSet<Annot>();
                    Annot tempAnnot=new Annot("Bata");
                    temp.add(tempAnnot);
                    FunType conFun;
                    conFun=new FunType(((FunType) this.nilBody.getType()).getArgType(),((FunType) this.nilBody.getType()).getRetType(),temp);
                    this.enviroment.put(this.consBody.getType().toString(),conFun);
                    consBody.setType(conFun);
                    //System.out.println(this.consBody.getType().toString());
                }
            }
            AbstrType bodyT;
            bodyT=this.consBody.getType().unit(this.nilBody.getType());
            if(this.consBody.getType() instanceof TVarType ){
                if(!(bodyT instanceof TVarType)){
                    this.enviroment.put(this.consBody.getType().toString(),bodyT);
                }
            }
            if(this.nilBody.getType() instanceof  TVarType){
                if(!(bodyT instanceof TVarType)){
                    this.enviroment.put(this.nilBody.getType().toString(),bodyT);
                }
            }
            this.consPattern.e1.typeJudge();
            if(!(this.consPattern.e1.getType() instanceof TVarType)){
                this.consPattern.e2.setType(new ListType(this.consPattern.e1.getType()));
                this.enviroment.put(e.toString(),this.consPattern.e2.getType());
            }
            else{
                this.consPattern.e2.typeJudge();
                if(this.consPattern.e2.getType() instanceof ListType){
                    if(!(((ListType)this.consPattern.e2.getType()).getItemType() instanceof  TVarType)){
                        this.consPattern.e1.setType(((ListType) this.consPattern.e2.getType()).getItemType());
                        this.enviroment.put(e.toString(),this.consPattern.e2.getType());
                    }
                }
            }

            this.setType(bodyT);



        }
        else{
            System.out.println("The "+this.e.toString()+" is not a List Type."+"Required: List type."+"Found: "+e.getType().toString());
            System.exit(1);
        }



    }

}
