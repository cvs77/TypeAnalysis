package ast;

import type.AbstrType;
import type.FunType;
import type.ListType;
import type.TVarType;
import util.Util;

import java.util.HashSet;

// Function application expression
public class FunAppExp extends AbstrExp {
	
	public AbstrExp fun;
	public AbstrExp arg;
	
	public FunAppExp(AbstrExp fun, AbstrExp arg) {
		super();
		this.fun = fun;
		this.arg = arg;
	}

	@Override
	public boolean isComplexExp() {
		return true;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String funStr = fun.toString(printAnnot, printType);
		String argStr = arg.toString(printAnnot, printType);
        if (fun.isComplexExp())
             funStr = "(" + funStr + ")";
        if (arg.isComplexExp())
             argStr = "(" + argStr + ")";
		String str = Util.indentStringOf(indent) + funStr + " " + argStr;
		return str;
	}

    @Override
    public void typeJudge() {
        fun.typeJudge();
        arg.typeJudge();
        FunType k;
        HashSet<Annot> temp=new HashSet<Annot>();


        if(fun.getType() instanceof FunType){



        k=new FunType(arg.getType(),((FunType)fun.getType()).getRetType());
        assert fun.getType()!=null;
        AbstrType resT=fun.getType().unit(k);
        if(resT==null){
            System.out.println("Incompatible types!: "+"Required:  "+((FunType) fun.getType()).getArgType().toString()+". Found: "+arg.getType().toString()+".");
            System.exit(1);
        }
        else{
            if(fun.getType() instanceof  FunType){
                if(arg.getType() instanceof FunType){
                    if(((FunType) fun.getType()).getArgType() instanceof FunType){
                        if(((FunType) fun.getType()).getRetType() instanceof FunType){
                            for(Annot i:((FunType) ((FunType) fun.getType()).getArgType()).getAnnotSet()){
                                if(((FunType) ((FunType) fun.getType()).getRetType()).getAnnotSet().contains(i)){
                                    temp.addAll(((FunType) arg.getType()).getAnnotSet());
                                    temp.addAll(((FunType) ((FunType) fun.getType()).getRetType()).getAnnotSet());
                                    temp.remove(i);
                                    ((FunType)((FunType)resT).getRetType()).getAnnotSet().remove(i);
                                    ((FunType)((FunType)resT).getRetType()).addAllAnnot(temp);
                                }
                            }
                        }
                        else if(((FunType) fun.getType()).getRetType() instanceof ListType){
                            if(((ListType) ((FunType) fun.getType()).getRetType()).getItemType() instanceof  FunType){
                                for(Annot i:(((FunType) ((FunType) fun.getType()).getArgType()).getAnnotSet())){
                                    if(((FunType) ((ListType) ((FunType) fun.getType()).getRetType()).getItemType()).getAnnotSet().contains(i)){
                                        temp.addAll(((FunType) arg.getType()).getAnnotSet());
                                        temp.addAll(((FunType) ((ListType) ((FunType) fun.getType()).getRetType()).getItemType()).getAnnotSet());
                                        temp.remove(i);
                                        ((FunType) ((ListType) ((FunType) resT).getRetType()).getItemType()).getAnnotSet().remove(i);
                                        ((FunType) ((ListType) ((FunType) resT).getRetType()).getItemType()).addAllAnnot(temp);
                                    }
                                }
                            }
                        }
                    }

                }
            }
            if(fun.getType() instanceof FunType){
                if(arg.getType() instanceof ListType){
                    if(((ListType) arg.getType()).getItemType() instanceof FunType){
                        if(((FunType) fun.getType()).getArgType() instanceof ListType){
                            if(((ListType) ((FunType) fun.getType()).getArgType()).getItemType() instanceof FunType){
                                if(((FunType) fun.getType()).getRetType() instanceof FunType){
                                    for(Annot i:((FunType) ((ListType) ((FunType) fun.getType()).getArgType()).getItemType()).getAnnotSet()){
                                        if(((FunType) ((FunType) fun.getType()).getRetType()).getAnnotSet().contains(i)){
                                            temp.addAll(((FunType) ((ListType) arg.getType()).getItemType()).getAnnotSet());
                                            temp.addAll(((FunType) ((FunType) fun.getType()).getRetType()).getAnnotSet());
                                            temp.remove(i);
                                            //System.out.println(((FunType) ((ListType) arg.getType()).getItemType()).getAnnotSet().toString());
                                            ((FunType)((FunType)resT).getRetType()).getAnnotSet().remove(i);
                                            ((FunType)((FunType)resT).getRetType()).addAllAnnot(temp);
                                        }

                                    }
                                }
                                else if(((FunType) fun.getType()).getRetType() instanceof ListType){
                                    if(((ListType) ((FunType) fun.getType()).getRetType()).getItemType() instanceof FunType){
                                        for(Annot i:(((FunType) ((ListType)((FunType) fun.getType()).getArgType()).getItemType()).getAnnotSet())){
                                            if(((FunType) ((ListType) ((FunType) fun.getType()).getRetType()).getItemType()).getAnnotSet().contains(i)){
                                                temp.addAll(((FunType) ((ListType) arg.getType()).getItemType()).getAnnotSet());
                                                temp.addAll(((FunType) ((ListType) ((FunType) fun.getType()).getRetType()).getItemType()).getAnnotSet());
                                                temp.remove(i);
                                                ((FunType) ((ListType) ((FunType) resT).getRetType()).getItemType()).getAnnotSet().remove(i);
                                                ((FunType) ((ListType) ((FunType) resT).getRetType()).getItemType()).addAllAnnot(temp);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //System.out.println(fun.getType().toString());
            assert resT instanceof FunType;
            fun.setType(resT);
            AbstrType tauType;
            tauType=((FunType) fun.getType()).getArgType().getTau(arg.getType());
            if(tauType!=null){
                AbstrType  argTypeNew;
                AbstrType  retTypeNew;

                retTypeNew=((FunType) resT).getRetType().setTau(tauType);
                argTypeNew=((FunType) fun.getType()).getArgType();
                ((FunType) resT).setArgType(argTypeNew);
                ((FunType) resT).setRetType(retTypeNew);
            }

            if(arg.getType() instanceof  TVarType){
                if(!(((FunType) resT).getArgType() instanceof TVarType)){
                this.enviroment.put(arg.toString(),((FunType) resT).getArgType());
                }
            }

            if(((FunType) fun.getType()).getArgType() instanceof TVarType){
                if(!(((FunType)resT).getArgType() instanceof TVarType)){
                    if(!(((FunType) resT).getArgType().toString().equals("tau"))){
                        this.enviroment.put(((FunType) fun.getType()).getArgType().toString(),((FunType) resT).getArgType());
                    }
                }

            }
            //fun.setType(resT);
            //System.out.println(((FunType) resT).getAnnotSet().toString());
            //System.out.println(fun.getType().toString());
            this.setType(((FunType) resT).getRetType());




        };
    }
    else if(fun.getType() instanceof TVarType){
            TVarType argFun=new TVarType("ArgFun_"+fun.getType().toString());
            AbstrType retFun=new TVarType("RetFun"+fun.getType().toString());
            if(this.arg.getType() instanceof  TVarType){
                System.out.println("Can not unity two Type Variables");
                System.out.println(this.arg.getType().toString()+"  "+fun.getType().toString());
                System.exit(1);
            }
            else{

                FunType funFun=new FunType(this.arg.getType(),retFun,new HashSet<Annot>());
                this.enviroment.put(fun.getType().toString(),funFun);
                this.setType(retFun);
            }
            ;

        }
        else{
            System.out.println("The "+fun.toString()+" is not function type!");
            System.out.println("Type of  "+fun.toString()+" : " +fun.getType().toString());
            System.exit(1);
        }


    }

}
