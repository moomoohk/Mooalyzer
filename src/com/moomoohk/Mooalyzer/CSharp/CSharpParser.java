package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;
import java.util.Scanner;

import com.moomoohk.Mooalyzer.CSharp.CSharpConstructor.CSharpConstructorType;

public class CSharpParser
{
	public static void parse()
	{
		Scanner scanner = new Scanner(System.in);

		ArrayList<String> lines = new ArrayList<String>();
		while (scanner.hasNextLine())
			lines.add(scanner.nextLine());
		for (int i = 0; i < lines.size(); i++)
		{
			String line = lines.get(i);
			//if(line.contains("/*"))

		}
	}

	public static ArrayList<String> cutList(ArrayList<String> list, int startIndex, int endIndex)
	{
		if (startIndex < 0 || startIndex >= list.size() || endIndex < startIndex || endIndex >= list.size())
			return null;
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = startIndex; i <= endIndex; i++)
			temp.add(list.get(i));
		return temp;
	}

	public static int findIndexOfString(ArrayList<String> lines, String s)
	{
		if (lines != null)
			for (int i = 0; i < lines.size(); i++)
				if (lines.get(i).contains(s))
					return i;
		return -1;
	}

	public static void printList(ArrayList<String> list)
	{
		for (String st : list)
			System.out.print("[" + st + "]");
		System.out.println();
	}

	public static void parseComments(ArrayList<String> lines)
	{
		ArrayList<String> save = lines;
		int commentBlockCount = 0, inlineCommentCount = 0;
		while (findIndexOfString(save, "/*") != -1)
		{
			commentBlockCount++;
			if (findIndexOfString(save, "/*") == findIndexOfString(save, "*/"))
			{
				// Add comment block lines somewhere
			}
			else
				for (int i = findIndexOfString(save, "/*") + 1; i <= findIndexOfString(save, "*/") - 1; i++)
				{
					// Add comment block lines somewhere
				}
			save = cutList(save, findIndexOfString(save, "*/") + 1, save.size() - 1);
		}
		save = lines;
		while (findIndexOfString(save, "//") != -1)
		{
			inlineCommentCount++;
			// Add comment line somewhere
			save = cutList(save, findIndexOfString(save, "//"), save.size() - 1);
		}
		System.out.println(commentBlockCount);
	}

	public static void parseCommentBlock(ArrayList<String> lines)
	{

	}

	public static void main(String[] args)
	{
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("/*");
		lines.add("/* big");
		lines.add("/* comment");
		lines.add("*/");
		lines.add("/* other comment */");
		parseComments(lines);
		ArrayList<String> modifiers = new ArrayList<String>();
		modifiers.add("public");
		modifiers.add("static");
		modifiers.add("void");
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add("int param1");
		parameters.add("String param2");
		CSharpConstructor constructor = new CSharpConstructor(modifiers, "main", parameters, CSharpConstructorType.THIS);
		constructor.addLine("\tsampleLine(param1);");
		System.out.println(constructor.toString() + constructor.lines());
	}
}
