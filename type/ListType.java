package type;

// Type of a list is in the form: a' List
public class ListType extends AbstrType {

	private AbstrType itemType;				// type of each element in List

	public ListType(AbstrType itemType) {
		super();
		this.itemType = itemType;
	}

	@Override
	public String toString() {
		String itemStr = itemType.toString();
		if (itemStr.contains("->"))
			itemStr = "(" + itemStr + ")";
		return  itemStr + " List";
	}
    public AbstrType getItemType(){
        return itemType;
    }
    @Override
    public AbstrType unit(AbstrType Atype) {
        if(Atype instanceof ListType){
            AbstrType listIteT;
            listIteT=((ListType) Atype).getItemType().unit(this.getItemType());
            if(listIteT==null){
                return null;
            }
            return new ListType(listIteT);
        }
        else if(Atype instanceof TVarType){
            return  new ListType(this.getItemType());
        }
        return null;
    }

    @Override
    public AbstrType getTau(AbstrType Arg) {
        if(Arg instanceof ListType){
            if(this.itemType instanceof TVarType){
                return this.itemType.getTau(((ListType) Arg).getItemType());
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    @Override
    public AbstrType setTau(AbstrType tauType) {
        AbstrType listItem;
        listItem=this.getItemType().setTau(tauType);
        return new ListType(listItem);
    }

    @Override
	public boolean isFunType() {
		return false;
	}

}
