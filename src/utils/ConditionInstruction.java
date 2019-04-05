package utils;

/**
 * Class qui créer une instruction conditionnelle.
 * @author
 *
 */
public class ConditionInstruction{
	String operateur;
	Condition condition;
	String commande;
	
	public ConditionInstruction(String inp)throws IllegalArgumentException {
		decodeInstructionConditionelle(inp);
	}
	
	/**
	 * Découpe l'instruction en une condition, un opérateur et une commande.
	 * @param inp Commande à découper.
	 * @throws IllegalArgumentException
	 */
	private void decodeInstructionConditionelle(String inp)throws IllegalArgumentException{
		String[] instToken = DecodeurInstruction.decodeInstructions(inp);
		if(inp.startsWith("if")) {
			operateur = "if";
		}else if(inp.startsWith("while")) {
			operateur = "while";
		}else {
			throw new IllegalArgumentException("instruction erronée");
		}
		condition = new Condition(instToken[0]);
		
		commande = instToken[1];
	}
	
	/**
	 * Execute une instruction conditionnelle.
	 * @param commandManager CommandManager qui vas executer this commande.
	 * @return Resultat de la commande.
	 */
	public String runInstruction(CommandManager commandManager) {
		String result = "nil";
		
		if(isIfInstruction() && isTrueCondition(commandManager)) {
			result = commandManager.execCommand(commande);
		}
		while(isWhileInstruction() && isTrueCondition(commandManager)) {
			result = commandManager.execCommand(commande);
		}
		return result;
	}
	
	/**
	 * Test si une comande est une instruction.
	 * @param commandManager Commande que l'on test.
	 * @return true si instruction, false sinon.
	 */
	public boolean isTrueCondition(CommandManager commandManager) {
		String leftValue = commandManager.execCommand(condition.leftArg);
		String rightValue = commandManager.execCommand(condition.rightArg);
		
		boolean res = false;
		try {
			if("= ".equals(condition.operateur)) {
				res = leftValue.equals(rightValue);
			}else if("!= ".equals(condition.operateur)) {
				res = !leftValue.equals(rightValue);
			}else {
				int left,right;
				left = stringToInt(leftValue);
				right = stringToInt(rightValue);
				
				if("> ".equals(condition.operateur)) {
					res = left > right;
				}else if("< ".equals(condition.operateur)) {
					res = left < right;
				}
			}
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int stringToInt(String str) throws NumberFormatException{
		if(str.equals("nil "))
			return 0;
		return Integer.valueOf(str.trim());
	}
	
	private boolean isIfInstruction() {
		return "if".equals(operateur);
	}
	private boolean isWhileInstruction() {
		return "while".equals(operateur);
	}
	
	public static boolean  isConditionelInstruction(String inp) {
		return inp.startsWith("while [") || inp.startsWith("if [");
	}
}
