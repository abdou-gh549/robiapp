package utils;

public class DecodeurInstruction {
	
	/**
	 * Découpe une commande qui est de type instruction.
	 * @param inp Commande à découper.
	 * @return Commande à découper.
	 */
	static public String[] decodeInstructions(String inp) {
		String[] result = new String[2];
		result[0]= "";
		result[1]= "";
		int g = 0;
		boolean inInstruction = false;
		for(int i=0; i<inp.length() && g < 2; i++) {
			char c = inp.charAt(i);
			if( c == '[') {
				inInstruction = true;
			}else if(c == ']') {
				inInstruction = false;
				g++;
			}
			else if(inInstruction) {
				result[g] += c;
			}
		}
		return result;
	}
	
}

