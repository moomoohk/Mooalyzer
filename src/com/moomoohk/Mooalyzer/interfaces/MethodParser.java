package com.moomoohk.Mooalyzer.interfaces;

import java.util.ArrayList;

import com.moomoohk.Mooalyzer.Method;

public interface MethodParser
{
	public Method parseMethod(Configuration config, String method);

	public ArrayList<Method> parseMethods(Configuration config, ArrayList<String> methods);
}
