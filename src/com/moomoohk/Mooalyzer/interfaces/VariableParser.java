
package com.moomoohk.Mooalyzer.interfaces;

import java.util.ArrayList;

import com.moomoohk.Mooalyzer.Variable;

public interface VariableParser
{
	public Variable parseVariable(String variable);
	
	public ArrayList<Variable> parseVariables(ArrayList<String> variables);
	
	public String getTerminator();
}