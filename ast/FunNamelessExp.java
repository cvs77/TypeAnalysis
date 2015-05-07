package ast;

import type.AbstrType;
import type.FunType;
import type.TVarType;
import util.Util;

import java.util.HashSet;

public class FunNamelessExp extends AbstrExp {
	public VarExp arg;
	public AbstrExp body;
	public Annot annot;

	public FunNamelessExp(VarExp arg, AbstrExp body, Annot annot) {
		super();
		this.arg = arg;
		this.body = body;
		this.annot = annot;
        //I change the construct function here,note ---mgao 02/04
       // this.annotEnviroment.put(annot.toString(),new HashSet<Annot>());
	}
    public VarExp getArg(){
        return arg;
    }

	@Override
	public boolean isComplexExp() {
		return true;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String str = Util.indentStringOf(indent);
		str = str + "fn" + (printAnnot ? annot.toString() : "");
		
		String argStr = arg.toString(printAnnot, false);
		if (printType) {
			AbstrType varType = arg.getType();
			String typeStr = varType != null? varType.toString() : "unk";
			argStr = "[" + argStr + ":" + typeStr + "]";
		}

		str = str + " " + argStr + " =>";

		String bodyStr = body.toString(printAnnot, printType).trim();
		if (str.trim().length() + bodyStr.length() < Util.expSize)
			str = str + " " + bodyStr;
		else
			str = str + "\n" + body.toString(printAnnot, printType, indent+1);

		return str;
	}

    @Override
    public void typeJudge() {
        HashSet<Annot> temp=new HashSet<Annot>();
        temp.add(this.annot);

        //arg.typeJudge();
        AbstrType abArg=new TVarType("Arg_"+arg.toString());
        arg.setType(abArg);
        this.enviroment.put(arg.toString(),arg.getType());
        body.typeJudge();
        //judge the type of argument again
        arg.typeJudge();


        if(arg.getType() instanceof  FunType){

            AbstrType MatchtoGroundArg;
            AbstrType MatchtoGroundRet;
            MatchtoGroundRet=((FunType) arg.getType()).getRetType();
            MatchtoGroundArg=((FunType) arg.getType()).getArgType();
            if(((FunType) arg.getType()).getArgType() instanceof  TVarType){
                AbstrType MatchtoGround;
                MatchtoGround=this.enviroment.get(((FunType) arg.getType()).getArgType().toString());
                if(MatchtoGround!=null){

                    while(this.enviroment.get(MatchtoGround.toString())!=null){
                        MatchtoGround=this.enviroment.get(MatchtoGround.toString());
                    }
                    assert MatchtoGround!=null;
                }
                if(MatchtoGround==null){

                    MatchtoGroundArg=new TVarType("tau");
                }
                else if(MatchtoGround instanceof TVarType){
                    MatchtoGroundArg=new TVarType("tau");
                }
                else{
                    MatchtoGroundArg=MatchtoGround;
                }

            }
            if(((FunType) arg.getType()).getRetType() instanceof TVarType){
                AbstrType MatchtoGround;
                MatchtoGround=this.enviroment.get(((FunType) arg.getType()).getRetType().toString());
                if(MatchtoGround!=null){

                    while(this.enviroment.get(MatchtoGround.toString())!=null){
                        MatchtoGround=this.enviroment.get(MatchtoGround.toString());
                    }
                    assert MatchtoGround!=null;
                }
                if(MatchtoGround==null){

                    MatchtoGroundRet=new TVarType("tau");
                }
                else if(MatchtoGround instanceof TVarType){
                    MatchtoGroundRet=new TVarType("tau");
                }
                else{
                    MatchtoGroundRet=MatchtoGround;
                }

            }
            ((FunType) arg.getType()).setArgType(MatchtoGroundArg);
            ((FunType) arg.getType()).setRetType(MatchtoGroundRet);

        }
        TVarType tauT;
        tauT =new TVarType("tau");
        if( arg.getType() instanceof  TVarType){
            arg.setType(tauT);
        }
        if(body.getType() instanceof  TVarType){
            body.setType(tauT);
        }
        this.setType(new FunType(arg.getType(), body.getType(), temp));

       this.enviroment.put(annot.toString(),this.getType());
        this.enviroment.remove(this.arg.toString());


    }

}
