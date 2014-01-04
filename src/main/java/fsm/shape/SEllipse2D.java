/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.shape;

import java.awt.geom.*;
import java.io.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


public class SEllipse2D extends Ellipse2D.Double implements Serializable,
		BasicShape
{

	static final long serialVersionUID = 1983849157883672487L;


	public SEllipse2D()
	{
		super();
	}


	public SEllipse2D(double x, double y, double w, double h)
	{
		super(x, y, w, h);
	}


	public SEllipse2D(Node node)
	{
		NamedNodeMap attr = node.getAttributes();
		x = java.lang.Double.parseDouble(attr.getNamedItem("x").getNodeValue());
		y = java.lang.Double.parseDouble(attr.getNamedItem("y").getNodeValue());
		width = java.lang.Double.parseDouble(attr.getNamedItem("width")
				.getNodeValue());
		height = java.lang.Double.parseDouble(attr.getNamedItem("height")
				.getNodeValue());

	}


	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeObject(new java.lang.Double(x));
		out.writeObject(new java.lang.Double(y));
		out.writeObject(new java.lang.Double(width));
		out.writeObject(new java.lang.Double(height));
		out.flush();
	}


	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException
	{
		java.lang.Double dbl = null;
		dbl = (java.lang.Double) in.readObject();
		x = dbl.doubleValue();
		dbl = (java.lang.Double) in.readObject();
		y = dbl.doubleValue();
		dbl = (java.lang.Double) in.readObject();
		width = dbl.doubleValue();
		dbl = (java.lang.Double) in.readObject();
		height = dbl.doubleValue();
	}


	public Element toXML(Document document)
	{
		Element e = document.createElement("Ellipse");
		e.setAttribute("x", java.lang.Double.toString(x));
		e.setAttribute("y", java.lang.Double.toString(y));
		e.setAttribute("width", java.lang.Double.toString(width));
		e.setAttribute("height", java.lang.Double.toString(height));
		return e;
	}


}