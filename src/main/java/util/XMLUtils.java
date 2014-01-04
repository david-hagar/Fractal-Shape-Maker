package util;

import java.awt.Color;

import org.w3c.dom.Node;

public class XMLUtils
{

	public static String toXMLString(Color c)
	{
		if (c == null)
			return "null";

		return String.format("%d,%d,%d,%d", c.getRed(), c.getGreen(), c.getBlue(),
				c.getAlpha());
	}


	public static Color fromXMLString(String xmlColorString)
	{
		if (xmlColorString.equals("null"))
			return null;

		String[] v = xmlColorString.split(",");

		if (v.length != 3 && v.length != 4)
			throw new NullPointerException("Color parse error on: '" + xmlColorString
					+ "'");

		int r = Integer.parseInt(v[0]);
		int g = Integer.parseInt(v[1]);
		int b = Integer.parseInt(v[2]);
		int a = (v.length == 4) ? Integer.parseInt(v[3]) : 255;

		return new Color(r, g, b, a);
	}


	public static void printType(Node node)
	{
		System.out.print(node.getNodeName() + " type=");
		int type = node.getNodeType();
		switch (type)
		{
		case Node.COMMENT_NODE:
			System.out.println("COMMENT_NODE");
			break;
		case Node.DOCUMENT_FRAGMENT_NODE:
			System.out.println("DOCUMENT_FRAGMENT_NODE");
			break;
		case Node.DOCUMENT_TYPE_NODE:
			System.out.println("DOCUMENT_TYPE_NODE");
			break;
		case Node.ELEMENT_NODE:
			System.out.println("ELEMENT_NODE");
			break;
		case Node.ENTITY_NODE:
			System.out.println("ENTITY_NODE");
			break;
		case Node.ENTITY_REFERENCE_NODE:
			System.out.println("ENTITY_REFERENCE_NODE");
			break;
		case Node.NOTATION_NODE:
			System.out.println("NOTATION_NODE");
			break;
		case Node.PROCESSING_INSTRUCTION_NODE:
			System.out.println("PROCESSING_INSTRUCTION_NODE");
			break;
		case Node.TEXT_NODE:
			System.out.println("ENTITY_NODE");
			break;

		default:
			break;
		}
	}


}
