package util;

public class Util {
	
	private static int indentSpace = 2;
	
	public static int lineLength = 80;
	public static int expSize = 60;
	
	public static String indentStringOf(int indent) {
		String atomIndentStr = "";
		for (int i = 0; i < indentSpace; i++)
			atomIndentStr = atomIndentStr + " ";

		String indentStr = "";
		for (int i = 0; i < indent; i++)
			indentStr = indentStr + atomIndentStr;

		return indentStr;
	}

	public static boolean hasNewLine(String string) {
		return string.contains("\n");
	}
}
