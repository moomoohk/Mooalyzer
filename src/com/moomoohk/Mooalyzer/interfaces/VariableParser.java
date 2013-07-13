
package com.moomoohk.Mooalyzer.interfaces;

import java.util.ArrayList;

import com.moomoohk.Mooalyzer.Variable;

public interface VariableParser
{
	public Variable parseVariable(Configuration config, String variable);
	
	public ArrayList<Variable> parseVariables(Configuration config, ArrayList<String> variables);
	
	public ArrayList<Variable> parseVariables(Configuration config, String ... variables);
}