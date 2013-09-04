package com.moomoohk.Mooalyzer.CSharp;

public class CSharpAttribute
{
	private CSharpElement child;
	private String attributeText;

	public CSharpAttribute(String attributeText)
	{
		this.attributeText = attributeText;
	}

	public void setChild(CSharpElement child)
	{
		this.child = child;
	}

	public CSharpElement getChild()
	{
		return this.child;
	}

	public String getAttributeText()
	{
		return this.attributeText;
	}

	public String toString()
	{
		return "[" + this.attributeText + "]";
	}
}
