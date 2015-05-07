package ast;

public class Annot {
	private int index;
    private String name;
	public Annot(int index) {
		this.index = index;
	}
    public Annot(String name){
        this.name=name;
    }
	
	public int getAnnotIndex() {
		return index;
	}

	public String toString() {
		return "$" + String.valueOf(index);
	}
}
