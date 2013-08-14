package com.moomoohk.Mooalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils
{
	public static ArrayList<String> splitWords(String line)
	{
		ArrayList<String> temp = new ArrayList<String>();
		Scanner scanner = new Scanner(line);
		if (line.contains("("))
		{
			String beforeParams = line.substring(0, line.indexOf('('));
			scanner = new Scanner(beforeParams);
			while (scanner.hasNext())
				temp.add(scanner.next().trim());
			temp.add("(");
			String params = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
			Scanner paramScanner = new Scanner(params);
			paramScanner.useDelimiter(",");
			while (paramScanner.hasNext())
			{
				scanner = new Scanner(paramScanner.next());
				while (scanner.hasNext())
					temp.add(scanner.next().trim());
				if (paramScanner.hasNext())
					temp.add(",");
			}
			scanner = new Scanner(line.substring(line.indexOf(")") + 1));
			while (scanner.hasNext())
				temp.add(scanner.next());
			temp.add(")");
		}
		else
		{
			if (line.trim().contains(" "))
				while (scanner.hasNext())
					temp.add(scanner.next());
			else
				temp.add(line);
		}
		return temp;
	}

	public static String getListAsString(List<?> list, boolean boxes, boolean commas)
	{
		if(list.contains((Object)"("))
				return getFunctionAsString(list);
		String temp = "";
		if (list == null || list.size() == 0)
			return temp;
		for (Object item : list)
			temp += (boxes ? "[" : "") + item.toString() + (boxes ? "]" : "") + (commas ? ", " : " ");
		return temp.substring(0, temp.length() - (commas ? 2 : 1));
	}

	public static String getFunctionAsString(List<?> list)
	{
		String temp = "";
		for (int i = 0; i < list.size() - 1; i++)
		{
			temp += list.get(i).toString().trim();
			if (!list.get(i + 1).toString().equals("(") && !list.get(i + 1).toString().equals(")")&& !list.get(i + 1).toString().equals(",") && !list.get(i).toString().equals("("))
				temp += " ";
		}
		temp += ")";
		return temp.trim();
	}
}
