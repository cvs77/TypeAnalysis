package ast;

import type.AbstrType;
import type.FunType;
import type.TVarType;
import util.Util;

import java.util.HashSet;

public class FunNamedExp extends AbstrExp {
	
	public VarExp name;
	public VarExp arg;
	public AbstrExp body;
	public Annot annot;
	
	public FunNamedExp(VarExp name, VarExp arg, AbstrExp body, Annot annot) {
		super();
		this.name = name;
		this.arg = arg;
		this.body = body;
		this.annot = annot;
	}

	@Override
	public boolean isComplexExp() {
		return true;
	}
    public VarExp getArg(){
        return arg;
    }
	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String str = Util.indentStringOf(indent);
		str = str + "fun" + (printAnnot ? annot.toString() : "");
		str = str + " "+ name.toString(printAnnot, printType);
        //System.out.println(name.getClass().toString());
		
		String argStr = arg.toString(printAnnot, false);
		if (printType) {
			AbstrType varType = arg.getType();
			String typeStr = varType != null? varType.toString() : "unk";
			argStr = "[" + argStr + ":" + typeStr + "]";
		}
		
		String funTypeStr = "";
		if (printType) {
			AbstrType funType = this.getType();
			funTypeStr = " : " + (funType != null ? funType.toString() : "unk");
		}

		str = str + " " + argStr + funTypeStr + " =>";

		String bodyStr = body.toString(printAnnot, printType).trim();
		if (str.trim().length() + bodyStr.length() < Util.expSize)
			str = str + " " + bodyStr;
		else
			str = str + "\n" + body.toString(printAnnot, printType, indent+1);
		return str;
	}

    @Override
    public void typeJudge() {

        //arg.typeJudge();
        AbstrType abArg=new TVarType("Arg_"+arg.toString());
        arg.setType(abArg);
        this.enviroment.put(arg.toString(),abArg);
        AbstrExp bodyFun;
        bodyFun=this.body;
        TVarType abRet=new TVarType("Ret_"+name.toString());

        HashSet<Annot>  fAnno=new HashSet<Annot>();
        fAnno.add(this.annot);
        FunType abFun=new FunType(arg.getType(),abRet);
        AbstrType abFunR;
        abFunR=abFun;
        while(bodyFun instanceof FunNamedExp){
            AbstrType retFunArg;
            retFunArg=new TVarType("Arg_"+((FunNamedExp) bodyFun).arg.toString());
            ((FunType)abFunR).setRetType(new FunType(retFunArg,new TVarType("Ret_"+((FunNamedExp)bodyFun).name.toString())));
            bodyFun=((FunNamedExp)bodyFun).body;
            abFunR=abFun.getRetType();
        }
        while(bodyFun instanceof FunNamelessExp){
            AbstrType retFunArg;
            retFunArg=new TVarType("Arg_"+((FunNamelessExp) bodyFun).arg.toString());
            ((FunType)abFunR).setRetType(new FunType(retFunArg,new TVarType("Ret")));
            bodyFun=((FunNamelessExp)bodyFun).body;
            abFunR=abFun.getRetType();
        }





        this.enviroment.put(name.toString(),abFun);
        name.setType(abFun);
        body.typeJudge();

        arg.typeJudge();
        TVarType tauT;
        tauT =new TVarType("tau");
        if( arg.getType() instanceof  TVarType){
            arg.setType(tauT);
        }
        if(body.getType() instanceof  TVarType){
            body.setType(tauT);
        }
        this.setType(new FunType(arg.getType(), body.getType(),fAnno));
        name.setType(new FunType(arg.getType(), body.getType(),fAnno));
        //System.out.println(name.getType().toString());
        //System.out.println(name.toString());
        this.enviroment.put(name.toString(),this.getType());
        this.enviroment.put(annot.toString(),this.getType());
        this.enviroment.remove(arg.toString());


    }

}
