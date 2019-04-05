package commands;

import graphicLayer.Morph;

public abstract class Command{
	Morph target;
	
	public Command (Morph target) {
		this.target = target;
	}
	
	abstract public String run();
	abstract public String run(String [] args);
	
	public String exec(Morph morph, String[] args) {
		target = morph;
		return run(args);
	}
	
	public boolean isCommand() { return true;}
	
	public boolean isMacro() { return false;}
	
	abstract public String getName();
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof String) {
			String c = (String) obj;
			return getName().equals(c);
		}
		return false;
	}

}
