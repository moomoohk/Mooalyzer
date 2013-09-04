package com.moomoohk.Mooalyzer.CSharp;

import java.util.ArrayList;

public class CSharpComment
{
	public static enum CSharpCommentType
	{
		BLOCK, INLINE;
	}

	private CSharpCommentType type;
	private ArrayList<String> lines;

	public CSharpComment(ArrayList<String> lines, CSharpCommentType type)
	{
		this.lines = lines;
		this.type = type;
	}

	public CSharpCommentType getType()
	{
		return this.type;
	}

	public ArrayList<String> getLines()
	{
		return this.lines;
	}

	public String toString()
	{
		String st = "";
		switch (this.type)
		{
		case BLOCK:
			st += "/*";
			for (String line : this.lines)
				st += "/* " + line;
			st += "*/";
			break;
		case INLINE:
			st = "// " + this.lines.get(0);
			break;
		}
		return st;
	}
}
