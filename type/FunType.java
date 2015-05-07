package type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import ast.Annot;

// Type of function is in the form:  argType -> retType

public class FunType extends AbstrType {
	private AbstrType argType;			// type of argument
	private AbstrType retType;			// type of return value
	private HashSet<Annot>	annotSet;
	
	public FunType(AbstrType argType, AbstrType retType) {
		super();
		this.argType = argType;
		this.retType = retType;
		this.annotSet = new HashSet<>();
	}

	public FunType(AbstrType argType, AbstrType retType, HashSet<Annot> annotSet) {
		super();
		this.argType = argType;
		this.retType = retType;
		this.annotSet = annotSet;

	}

	// add annotation to func type
	public void addAnnot(Annot annot) {
		annotSet.add(annot);
	}
    public void addAllAnnot(HashSet<Annot> otherAnnotSet){
        annotSet.addAll(otherAnnotSet);
    }
    public HashSet<Annot> getAnnotSet(){
        return annotSet;
    }
    public AbstrType getArgType(){
        return argType;
    }
    public AbstrType getRetType(){
        return retType;
    }
    public void setArgType(AbstrType newArg){this.argType=newArg;}

    public void setRetType(AbstrType aRenT){
        this.retType=aRenT;
    }

	@Override
	public String toString() {
		String argStr = argType.toString();
		if (argType.isFunType())
			argStr = "(" + argStr + ")";
		
		String retStr = retType.toString();
		if (retType.isFunType())
			retStr = "(" + retStr + ")";
		
		String annotStr = "";
		if (annotSet.size() > 0) {
			ArrayList<Annot> annotList = new ArrayList<>();
			annotList.addAll(annotSet);
			Collections.sort(annotList, new Comparator<Annot>() {
				@Override
				public int compare(Annot ann1, Annot ann2) {
					int val1 = ann1.getAnnotIndex();
					int val2 = ann2.getAnnotIndex();
					if (val1 > val2)
						return 1;
					else if (val1 == val2)
						return 0;
					else
						return -1;
				}
			});
			
			for (Annot annot: annotList) {
				int annotIndex = annot.getAnnotIndex();
				annotStr = annotStr + "," + String.valueOf(annotIndex);
			}
			if (annotStr.length() > 0)
				annotStr = annotStr.substring(1);
			
			annotStr = "-$(" + annotStr + ")->";
		}
		else
			annotStr = "->";
		
		return argStr + annotStr + retStr;
	}

    @Override
    public AbstrType unit(AbstrType typeX) {
        if(typeX instanceof FunType){
            assert ((FunType) typeX).getArgType()!=null;
            assert ((FunType) typeX).getRetType()!=null;
            //todo check the correctness: I new a function type, with both annot
            HashSet<Annot> newAnnSet=new HashSet<Annot>();
            newAnnSet.addAll(this.getAnnotSet());
            newAnnSet.addAll(((FunType) typeX).getAnnotSet());
           // this.addAllAnnot(((FunType) typeX).getAnnotSet());
            //((FunType) typeX).addAllAnnot(this.getAnnotSet());
            AbstrType tauArg;
            AbstrType tauRet;
            tauArg=this.argType.getTau(((FunType) typeX).getArgType());
            tauRet=this.retType.getTau(((FunType) typeX).getRetType());
            if(tauArg!=null){
                if(tauRet!=null){
                    if(tauArg.unit(tauRet)==null){
                        return null;
                    }
                }
            }
            AbstrType newArg;
            AbstrType newRet;
            newArg=argType.unit(((FunType) typeX).getArgType());
            newRet=retType.unit(((FunType) typeX).getRetType());
            if(newArg==null){
                return  null;

            }
            if(newRet==null){
                return null;
            }


            FunType newFunT=new FunType(newArg,newRet,newAnnSet);
            return newFunT;
        }
        else if(typeX instanceof TVarType){
            HashSet<Annot> newAnnSet=new HashSet<Annot>();
            newAnnSet.addAll(this.getAnnotSet());

            // this.addAllAnnot(((FunType) typeX).getAnnotSet());
            //((FunType) typeX).addAllAnnot(this.getAnnotSet());
            FunType newFunT=new FunType(argType,retType,newAnnSet);
            return newFunT;
        }
        else{
            return null;
        }
    }

    @Override
    public AbstrType getTau(AbstrType Arg) {
        if(Arg instanceof FunType){
            AbstrType tauArg;
            AbstrType tauRet;
            tauArg=this.argType.getTau(((FunType) Arg).getArgType());
            tauRet=this.retType.getTau(((FunType) Arg).getRetType());
            if(tauArg==null){
                return tauRet;
            }
            else if(tauRet==null){
                return tauArg;
            }
            else{
                assert tauArg!=null;
                assert tauRet!=null;
                return tauArg.unit(tauRet);
            }

        }
        else{
            return null;
        }
    }

    @Override
    public AbstrType setTau(AbstrType tauType) {
        AbstrType argTypeNew;
        AbstrType retTypeNew;
        argTypeNew=this.argType.setTau(tauType);
        retTypeNew=this.retType.setTau(tauType);
        return new FunType(argTypeNew,retTypeNew,this.annotSet);
    }

    @Override
	public boolean isFunType() {
		return true;
	}

}
