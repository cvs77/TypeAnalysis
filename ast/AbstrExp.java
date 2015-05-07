package ast;

import java.util.*;

import org.antlr.v4.parse.ANTLRParser;
import type.AbstrType;
import type.BoolType;
import type.FunType;
import type.IntType;

public abstract class AbstrExp {

	private AbstrType type;
    public static HashMap<String,AbstrType> enviroment=new HashMap<String,AbstrType>();

    //Unit function


    // for pretty printing
	public abstract boolean isComplexExp();

	public abstract String toString(boolean printAnnot, boolean printType, int indent);
    public abstract void typeJudge();
	// getter and setter for type information
	public void setType(AbstrType type) {
		this.type = type;
	}

	public AbstrType getType() {
		return this.type;
	}

	// some printing functions
	public String toString() {
		return this.toString(false, false, 0);
	}

	public String toString(boolean printAnnot, boolean printType) {
		return this.toString(printAnnot, printType, 0);
	}

	protected void println(boolean printAnnot, boolean printType, int indent) {
		System.out.println(this.toString(printAnnot, printType, indent));
	}

	public void println() {
		this.println(false, false, 0);
	}

	// print exp with annotation
	public void printlnAnnot() {
		this.println(true, false, 0);
	}

	// print exp with type information
	public void printlnType() {
		this.println(true, true, 0);
	}

	// get all declared vars and functions inside this exp
	protected ArrayList<AbstrExp> getDeclaredVarsFuns() {
		ArrayList<AbstrExp> declaredVarsFuns = new ArrayList<>();
		if (this instanceof BinaryExp) {
			BinaryExp exp = (BinaryExp) this;
			declaredVarsFuns.addAll(exp.e1.getDeclaredVarsFuns());
			declaredVarsFuns.addAll(exp.e2.getDeclaredVarsFuns());
		}
		else if (this instanceof CaseExp) {
			CaseExp exp = (CaseExp) this;
			declaredVarsFuns.addAll(exp.e.getDeclaredVarsFuns());
			declaredVarsFuns.add(exp.consPattern.e1);
			declaredVarsFuns.add(exp.consPattern.e2);
			declaredVarsFuns.addAll(exp.consBody.getDeclaredVarsFuns());
			declaredVarsFuns.addAll(exp.nilBody.getDeclaredVarsFuns());
		}
		else if (this instanceof FunAppExp) {
			FunAppExp exp = (FunAppExp) this;
			declaredVarsFuns.addAll(exp.fun.getDeclaredVarsFuns());
			declaredVarsFuns.addAll(exp.arg.getDeclaredVarsFuns());
		}
		else if (this instanceof FunNamedExp) {
			FunNamedExp exp = (FunNamedExp) this;
			declaredVarsFuns.add(this);
			declaredVarsFuns.add(exp.arg);
			declaredVarsFuns.addAll(exp.body.getDeclaredVarsFuns());
		}
		else if (this instanceof FunNamelessExp) {
			FunNamelessExp exp = (FunNamelessExp) this;
			declaredVarsFuns.add(this);
			declaredVarsFuns.add(exp.arg);
			declaredVarsFuns.addAll(exp.body.getDeclaredVarsFuns());
		}
		else if (this instanceof IfExp) {
			IfExp exp = (IfExp) this;
			declaredVarsFuns.addAll(exp.cond.getDeclaredVarsFuns());
			declaredVarsFuns.addAll(exp.thenBody.getDeclaredVarsFuns());
			declaredVarsFuns.addAll(exp.elseBody.getDeclaredVarsFuns());
		}
		else if (this instanceof LetExp) {
			LetExp exp = (LetExp) this;
			declaredVarsFuns.add(exp.var);
			declaredVarsFuns.addAll(exp.val.getDeclaredVarsFuns());
			declaredVarsFuns.addAll(exp.body.getDeclaredVarsFuns());
		}
		else if (this instanceof UnaryExp) {
			UnaryExp exp = (UnaryExp) this;
			declaredVarsFuns.addAll(exp.e.getDeclaredVarsFuns());
		}
		return declaredVarsFuns;
	}

	// print detail type info of declared functions and variables
	public void printDetailTypeInfo() {
		ArrayList<AbstrExp> declaredVarsFuns = this.getDeclaredVarsFuns();
		ArrayList<String> typeInfos = new ArrayList<>();
		for (AbstrExp exp : declaredVarsFuns) {
			AbstrType type = exp.getType();
			if (exp instanceof VarExp) {
				VarExp var = (VarExp) exp;
				String typeStr = type != null ? type.toString() : "unk";
				String str = var.toString() + " : " + typeStr;
				typeInfos.add(str);
			}
			else if (exp instanceof FunNamelessExp) {
				FunNamelessExp fun = (FunNamelessExp) exp;
				String str = "fn" + fun.annot.toString();
				String typeStr = type != null ? type.toString() : "unk";
				str = str + " : " + typeStr;
				typeInfos.add(str);
			}
			else if (exp instanceof FunNamedExp) {
				FunNamedExp fun = (FunNamedExp) exp;
				String str = "fun" + fun.annot.toString();
				String typeStr = type != null ? type.toString() : "unk";
				str = str + " : " + typeStr;
				typeInfos.add(str);
			}
            else if(exp instanceof  ConsExp){
                ConsExp cons=(ConsExp)exp;
                String str="Con"+cons.toString();
                String typeStr=type!=null? type.toString() : "unk";
                str=str+" : "+typeStr;
                typeInfos.add(str);
            }

		}
		
		Collections.sort(typeInfos);
		for (String str : typeInfos)
			System.out.println("  " + str);
	}
}
