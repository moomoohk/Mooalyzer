package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import com.moomoohk.Mooalyzer.CSharp.CSharpComment.CSharpCommentType;
import com.moomoohk.Mooalyzer.CSharp.CSharpConstructor.CSharpConstructorType;

public class CSharpParser
{
	public static ArrayList<CSharpElement> elements = new ArrayList<CSharpElement>();

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
		return indexOfString(lines, s, -1, -1);
	}

	public static int indexOfString(ArrayList<String> lines, String s, int curlyLevel, int bracketLevel)
	{
		int cl = 0, bl = 0;
		if (lines != null)
			for (int i = 0; i < lines.size(); i++)
			{
				if (lines.get(i).contains("{"))
					cl++;
				if (lines.get(i).contains("("))
					bl++;
				if (lines.get(i).contains(s) && (curlyLevel != -1 ? cl == curlyLevel : true) && (bracketLevel != -1 ? bl == bracketLevel : true))
					return i;
				if (lines.get(i).contains("}"))
					cl--;
				if (lines.get(i).contains(")"))
					bl--;
			}
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
		int commentBlockCount = 0, inlineCommentCount = 0, position = 0;
		for (int i = findIndexOfString(save, "/*"); i != -1; i = findIndexOfString(save, "/*"))
		{
			position += i;
			int add = 0;
			ArrayList<String> commentLines = new ArrayList<String>();
			for (int j = i; j <= findIndexOfString(save, "*/"); j++)
				if (save.get(j).replace("/*", "").replace("*/", "").replace("*", "").trim().length() > 0)
					commentLines.add(save.get(j).replace("/*", "").replace("*/", "").replace("*", "").trim());
				else
				{
					if (save.get(j).contains("/*"))
						add++;
					if (save.get(j).contains("*/"))
						add++;
				}
			commentBlockCount++;
			elements.add(new CSharpComment(commentLines, CSharpCommentType.BLOCK, position));
			position += commentLines.size() + add;
			save = cutList(save, findIndexOfString(save, "*/") + 1, save.size() - 1);
		}
		save = lines;
		position = 0;
		for (int i = findIndexOfString(save, "//"); findIndexOfString(save, "//") != -1; i = findIndexOfString(save, "//"))
		{
			position += i;
			if (!save.get(i).trim().startsWith("*") && !save.get(i).trim().startsWith("/*") && save.get(i).substring(save.get(i).indexOf("//")).replace("//", "").trim().length() != 0)
			{
				inlineCommentCount++;
				ArrayList<String> line = new ArrayList<String>();
				line.add(save.get(i).substring(save.get(i).indexOf("//")).replace("//", "").trim());
				elements.add(new CSharpComment(line, CSharpCommentType.INLINE, position));
			}
			position++;
			save = cutList(save, findIndexOfString(save, "//") + 1, save.size() - 1);
		}
	}

	public static void parseMethods(ArrayList<String> lines)
	{

	}

	public static void printElements()
	{
		for (CSharpElement element : elements)
		{
			System.out.println("Line " + element.getLine() + ": " + element.getClass().getSimpleName() + ": " + element.getName());
			//			System.out.println(element.toString() + "\n");
		}
	}

	public static void sortElements()
	{
		Collections.sort(elements, new Comparator<CSharpElement>()
		{
			@Override
			public int compare(CSharpElement arg0, CSharpElement arg1)
			{
				return arg0.getLine() - arg1.getLine();
			}
		});
	}

	public static void main(String[] args)
	{
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("/*"); // 0
		lines.add("* big"); // 1
		lines.add("* comment"); // 2
		lines.add("* yoho"); // 3
		lines.add("*/"); // 4
		lines.add("public void test()"); // 5
		lines.add("/* other comment */"); // 6
		lines.add("public static void main(String[] args) //inline comment"); // 7
		lines.add("//an inline comment"); // 8
		lines.add("/* another"); // 9
		lines.add("* comment //test"); // 10
		lines.add("*/"); // 11
		lines.add("//another inline comment"); // 12
		parseComments(lines);
		ArrayList<String> modifiers = new ArrayList<String>();
		modifiers.add("public");
		modifiers.add("static");
		modifiers.add("void");
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add("int param1");
		parameters.add("String param2");
		CSharpConstructor constructor = new CSharpConstructor(modifiers, "main", parameters, CSharpConstructorType.THIS, 10);
		constructor.addLine("\tsampleLine(param1);");
		elements.add(constructor);
		sortElements();
		printElements();
	}
}