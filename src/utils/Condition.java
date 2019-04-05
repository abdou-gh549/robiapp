package utils;

import java.util.Arrays;
import java.util.List;

/**
 * Class qui permet de créer une condition.
 * @author
 */
public class Condition {
	public String operateur = "";
	public String leftArg= "";
	public String rightArg= "";
	
	public Condition(String condition) throws IllegalArgumentException{
		decodeCondition(condition);
	}
	
	/**
	 * Test si la commande est une condition et la décode.
	 * C'est à dire qu'il sépare l'opération des deux arguments.
	 * @param condition Commande que nous teston si c'est une condition.
	 * @throws IllegalArgumentException
	 */
	private void decodeCondition(String condition) throws IllegalArgumentException{
		List<String> tabOpList = Arrays.<String>asList("= ", "> ", "< ", "!= ");
		boolean inInstruction = true;
		for(int i=0; i<condition.length(); i++) {
			char c = condition.charAt(i);
			if(tabOpList.contains(c+" ")) {
				i++;
				operateur = c + " ";
			}else if(tabOpList.contains(c+"= ")) {
				i+=2;
				operateur = c + "= ";
			}
			else if(inInstruction) {
				if(operateur.isEmpty()) {
					// left
					leftArg+= c;
				}else {
					// right
					rightArg+= c;
				}
			}
		}
		if(! isComplet()) {
			throw new IllegalArgumentException("condition syntaxe error ");
		}
	}
	
	public boolean isComplet() {
		return !operateur.isEmpty()  && !leftArg.isEmpty()  && !rightArg.isEmpty();
	}
}
