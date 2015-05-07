package ast;

import type.*;
import util.Util;

public class BinaryExp extends AbstrExp {
	public String op;
	public AbstrExp e1;
	public AbstrExp e2;
	
	public BinaryExp(String op, AbstrExp e1, AbstrExp e2) {
		super();
		this.op = op;
		this.e1 = e1;
		this.e2 = e2;
	}
	
	@Override
	public boolean isComplexExp() {
		return true;
	}

	@Override
	public String toString(boolean printAnnot, boolean printType, int indent) {
		String e1Str = e1.toString(printAnnot, printType);
		String e2Str = e2.toString(printAnnot, printType);
		String str = Util.indentStringOf(indent);
		str = str + e1Str + " " + op + " " + e2Str;
		return str;
	}

    @Override
    public void typeJudge() {
        //Only thing you want guarantee is the two Expression on two side of e1
        e1.typeJudge();
        assert e1.getType()!=null;
        e2.typeJudge();
        assert e2.getType()!=null;

        AbstrType abTunity;
        AbstrType abResult;
        switch(this.op){
            case "+":
                abTunity=new IntType();
                abResult=new IntType();
                break;
            case "-":
                abTunity=new IntType();
                abResult=new IntType();
                break;
            case "&&":
                abTunity=new BoolType();
                abResult=new BoolType();
                break;
            case "||":
                abTunity=new BoolType();
                abResult=new BoolType();
                break;
            case "*":
                abTunity=new IntType();
                abResult=new IntType();
                break;
            case "/":
                abTunity=new IntType();
                abResult=new IntType();
                break;
            case "<":
                abTunity=new IntType();
                abResult=new BoolType();
                break;
            case ">":
                abTunity=new IntType();
                abResult=new BoolType();
                break;
            case "<=":
                abTunity=new IntType();
                abResult=new BoolType();
                break;
            case ">=":
                abTunity=new IntType();
                abResult=new BoolType();
                break;
            case "==":
                abTunity=new IntType();
                abResult=new BoolType();
                break;
            case "!=":
                abTunity=new IntType();
                abResult=new BoolType();
                break;
            default:
                abTunity=new TVarType("Alpha_default"+op);
                abResult=new TVarType("Alpha_default"+op);
                break;
        }

        AbstrType typeUnit=null;

            typeUnit=abTunity.unit(e2.getType());

            if(typeUnit==null){
                System.out.println("Incompatible types."+"  ");
                System.out.println("Required: "+ abTunity.toString());
                System.out.println("Found : "+e2.getType().toString());
                System.out.println(this.toString());

                System.exit(0);

            }
            else{
                if(e2.getType() instanceof  TVarType){
                    if(!(typeUnit instanceof TVarType)){
                        this.enviroment.put(e2.getType().toString(),typeUnit);
                    }
                }
            }


        typeUnit=null;

            typeUnit= abTunity.unit(e1.getType());

            if(typeUnit==null){
                System.out.println("Incompatible types.");
                System.out.println("Required "+ abTunity.toString());
                System.out.println("Found: "+e2.getType().toString());
                System.exit(0);

            }
            else{
                if(e1.getType() instanceof TVarType){
                    if(!(typeUnit instanceof TVarType)){
                        this.enviroment.put(e1.getType().toString(),typeUnit);
                    }
                }
            }


        //If both units function are not failed we can make sure of that the two e1 and e2 must have the same type
        this.setType(abResult);

    }

}
