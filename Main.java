import java.io.FileInputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import ast.AbstrExp;
import parser.FunLaLexer;
import parser.FunLaParser;


public class Main {
	
	private static void printUsage() {
		System.out.println("FunLa annotated type analyzer");
		System.out.println("Usage:");
		System.out.println("    typeanalyzer <filename>");
	}
	
	public static void main(String[] args) {
		String fileName = "";
		if (args.length != 1) {
			printUsage();
			return;
		}
		else fileName = args[0];


       //Try out the error report\
		
		try {
			System.out.println("Analyzing file '" + fileName + "'...");
			System.out.println();
			
			FileInputStream  file = new FileInputStream(fileName);
			ANTLRInputStream input = new ANTLRInputStream(file);
			FunLaLexer lexer = new FunLaLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			FunLaParser parser = new FunLaParser(tokens);
			AbstrExp prog = parser.program().e;
            prog.typeJudge();
//            prog.typeJudge();
			if (parser.getNumberOfSyntaxErrors() > 0) {
				System.out.println("Syntax error!");
				return;
			}
			
			System.out.println("----------------------------------");
			System.out.println("Input program:");
			System.out.println();
			System.out.println(input.toString());
			System.out.println();
			
			System.out.println("----------------------------------");
			System.out.println("Annotated program:");
			System.out.println();
			prog.printlnAnnot();
			System.out.println();

			System.out.println("----------------------------------");
			System.out.println("Annotated typed program:");
			System.out.println();
			// YOUR RESULT WILL BE PRINTED HERE
			prog.printlnType();				// will be replaced by your printing
			System.out.println();
			
			System.out.println("----------------------------------");
			System.out.println("Detail type information:");
			System.out.println();
			// YOUR RESULT WILL BE PRINTED HERE
			prog.printDetailTypeInfo();		// will be replaced by your printing
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
