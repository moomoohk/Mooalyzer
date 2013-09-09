package com.moomoohk.Mooalyzer.CSharp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.print.attribute.standard.Finishings;

import com.moomoohk.Mooalyzer.CSharp.CSharpComment.CSharpCommentType;
import com.moomoohk.Mooalyzer.CSharp.CSharpConstructor.CSharpConstructorType;

public class CSharpParser
{
	public static ArrayList<CSharpElement> elements = new ArrayList<CSharpElement>();

	public static final int METHOD_LEVEL = 1;

	public static void parse(ArrayList<String> lines)
	{
		long start = System.currentTimeMillis();
		//		Scanner scanner = new Scanner(System.in);
		//
		//		ArrayList<String> lines = new ArrayList<String>();
		//		while (scanner.hasNextLine())
		//			lines.add(scanner.nextLine());
		//		for (int i = 0; i < lines.size(); i++)
		//		{
		//			String line = lines.get(i);
		//			//if(line.contains("/*"))
		//
		//		}
		System.out.print("Parsing comments...");
		parseComments(lines);
		System.out.println(" Done");
		System.out.print("Parsing methods...");
		parseMethods(lines);
		System.out.println(" Done");
		parseVariables(lines);
		System.out.println("Finished (" + (System.currentTimeMillis() - start) + "ms)");
	}

	public static ArrayList<String> splitLine(String line)
	{
		//		Scanner scanner = new Scanner(line);
		//		scanner.useDelimiter(" |,|\\(|\\)|:");
		//		String temp = line;
		//		//		scanner.useDelimiter("\\s,\\s");
		//		while (scanner.hasNext())
		//		{
		//			String next = scanner.next().trim();
		//			System.out.println(next);
		//			temp = temp.substring(next.length()).trim();
		//			System.out.println("temp: " + temp);
		//		}
		ArrayList<String> split = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		Scanner scanner = new Scanner(line.substring(0, line.indexOf("(")));
		while (scanner.hasNext())
			split.add(scanner.next());
		String params = line.substring(line.indexOf("("));
		ArrayList<String> temp = new ArrayList<String>();
		for (char c : params.toCharArray())
		{
			if (c == ',' || c == ':' || c == '(' || c == ')' || c == ';')
			{
				if (builder.length() > 0)
					temp.add(builder.toString().trim());
				temp.add("" + c);
				builder = new StringBuilder();
			}
			else
				builder.append(c);
		}
		builder = new StringBuilder();
		for (String param : temp)
		{
			if (param.equals(",") || param.equals(":") || param.equals("(") || param.equals(")") || param.equals(";"))
			{
				if (builder.length() > 0)
					split.add(builder.toString().trim());
				split.add(param);
				builder = new StringBuilder();
			}
			else
				builder.append(param + " ");
		}
		return split;
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

	public static int indexOfString(ArrayList<String> lines, String s)
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
				if (lines.get(i).contains(s) && (curlyLevel >= 0 ? cl == curlyLevel : true) && (bracketLevel >= 0 ? bl == bracketLevel : true))
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
		int commentBlockCount = 0, inlineCommentCount = 0, position = 1;
		for (int i = indexOfString(save, "/*"); i != -1; i = indexOfString(save, "/*"))
		{
			position += i;
			int add = 0;
			ArrayList<String> commentLines = new ArrayList<String>();
			for (int j = i; j <= indexOfString(save, "*/"); j++)
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
			save = cutList(save, indexOfString(save, "*/") + 1, save.size() - 1);
		}
		save = lines;
		position = 0;
		for (int i = indexOfString(save, "//"); indexOfString(save, "//") != -1; i = indexOfString(save, "//"))
		{
			position += i;
			if (!save.get(i).trim().startsWith("*") && !save.get(i).trim().startsWith("/*") && save.get(i).substring(save.get(i).indexOf("//")).replace("//", "").trim().length() != 0)
			{
				position++;
				inlineCommentCount++;
				ArrayList<String> line = new ArrayList<String>();
				line.add(save.get(i).substring(save.get(i).indexOf("//")).replace("//", "").trim());
				elements.add(new CSharpComment(line, CSharpCommentType.INLINE, position));
			}
			save = cutList(save, i + 1, save.size() - 1);
		}
	}

	public static void parseMethods(ArrayList<String> lines)
	{
		ArrayList<String> save = lines;
		int position = 1; //TODO: Same line curlies
		for (int i = indexOfString(save, "(", METHOD_LEVEL - 1, 1); indexOfString(save, "(", METHOD_LEVEL - 1, 1) != -1; i = indexOfString(save, "(", METHOD_LEVEL - 1, 1))
		{
			position += i;
			String line = save.get(i);
			ArrayList<String> split = splitLine(line);
			ArrayList<String> modifiers = new ArrayList<String>();
			ArrayList<String> parameters = parseParameters(split);
			String name = split.get(split.indexOf("(") - 1);
			for (int j = 0; j < split.indexOf("(") - 1; j++)
				modifiers.add(split.get(j));
			if (split.get(split.size() - 1).equals(";"))
			{
				elements.add(new CSharpAbstractMethod(modifiers, name, parameters, position));
				position += 1;
			}
			else
				if (split.contains(":"))
				{
					CSharpConstructor constructor = null;
					if (split.get(split.indexOf(":") + 1).equals("base"))
						constructor = new CSharpConstructor(modifiers, name, parameters, CSharpConstructorType.BASE, position);
					if (split.get(split.indexOf(":") + 1).equals("this"))
						constructor = new CSharpConstructor(modifiers, name, parameters, CSharpConstructorType.THIS, position); //TODO: Fix base and this params
					ArrayList<String> methodLines = cutList(save, indexOfString(save, "}", METHOD_LEVEL, -1), indexOfString(save, "}", METHOD_LEVEL, -1));
					int add = 0;
					System.out.println(line);
					printList(methodLines);
					for (String s : methodLines)
					{
						constructor.addLine(s);
						add++;
					}
					elements.add(constructor);
					position += 1 + add;
				}
				else
				{
					CSharpMethod method = new CSharpMethod(modifiers, name, parameters, position);
					ArrayList<String> methodLines = cutList(save, indexOfString(save, "{", METHOD_LEVEL, -1), indexOfString(save, "}", METHOD_LEVEL, -1));
					int add = 0;
					for (String s : methodLines)
					{
						method.addLine(s);
						add++;
					}
					elements.add(method);
					position += 1 + add;
				}
			save = cutList(save, i + 1, save.size() - 1);
		}
	}

	public static ArrayList<String> parseParameters(ArrayList<String> split)
	{
		ArrayList<String> parameters = new ArrayList<String>();
		StringBuilder param = new StringBuilder();
		for (int j = split.indexOf("(") + 1; j < split.indexOf(")"); j++)
		{
			if (!split.get(j).equals(","))
				param.append(split.get(j) + " ");
			else
			{
				parameters.add(param.toString().trim());
				param = new StringBuilder();
			}
		}
		parameters.add(param.toString().trim());
		return parameters;
	}

	public static void parseVariables(ArrayList<String> lines)
	{
		ArrayList<String> save = lines;
		for (int i = indexOfString(save, " ", METHOD_LEVEL-1, -1); indexOfString(save, " ", METHOD_LEVEL-1, -1) != -1; i = indexOfString(save, "", METHOD_LEVEL-1, -1))
		{
			System.out.println(lines.get(i));
			save = cutList(save, i + 1, save.size() - 1);
		}
	}

	public static void printElements()
	{
		for (CSharpElement element : elements)
		{
			System.out.println("Line " + element.getLine() + ": " + element.getClass().getSimpleName() + ": " + element.getName());
			System.out.println(element.toString() + "\n-");
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
		Scanner scanner;
		try
		{
			scanner = new Scanner(new File("Test CSharp File"));
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				lines.add(line);
			}
			printList(lines);
			parse(lines);
			sortElements();
			printElements();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}