package com.moomoohk.Mooalyzer.interfaces;

import java.util.ArrayList;

import com.moomoohk.Mooalyzer.Variable;

public interface MethodParser
{
	public Variable parseMethod(String method);

	public ArrayList<Variable> parseMethods(ArrayList<String> methods);
}
