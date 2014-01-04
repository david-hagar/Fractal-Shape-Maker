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
import java.util.*;
import java.awt.Rectangle;
import java.awt.Shape;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.XMLUtils;

public class SGeneralPath implements Serializable, BasicShape
{

	static final long serialVersionUID = 1770096373549678845L;


	transient GeneralPath gp = new GeneralPath();
	ArrayList<SPoint> points = new ArrayList<SPoint>();

	private class SPoint implements Serializable
	{
		static final long serialVersionUID = -5722990514610102510L;

		float x = 0f;
		float y = 0f;


		SPoint(float x, float y)
		{
			this.x = x;
			this.y = y;
		}


		SPoint(Node node)
		{
			NamedNodeMap attr = node.getAttributes();
			x = java.lang.Float.parseFloat(attr.getNamedItem("x").getNodeValue());
			y = java.lang.Float.parseFloat(attr.getNamedItem("y").getNodeValue());
		}


		public Node toXML(Document document)
		{
			Element e = document.createElement("Point");
			e.setAttribute("x", Float.toString(x));
			e.setAttribute("y", Float.toString(y));
			return e;
		}

	}


	public SGeneralPath()
	{
	}


	public SGeneralPath(Node gpNode)
	{
		NodeList nodeList = gpNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			XMLUtils.printType(node);

			if (node.getNodeName().equals("Point"))
			{
				points.add(new SPoint(node));
			} else
				System.out.println("unused node " + node);
		}

		rebuildGP();
	}


	public void addPoint(float x, float y)
	{
		if (points.size() == 0)
			gp.moveTo(x, y);
		else
			gp.lineTo(x, y);

		points.add(new SPoint(x, y));
	}


	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeObject(points);
		out.flush();
	}


	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException
	{

		points = (ArrayList<SPoint>) in.readObject();

		rebuildGP();
	}


	void rebuildGP()
	{
		gp = new GeneralPath();

		SPoint p = points.get(0);
		gp.moveTo(p.x, p.y);
		int size = points.size();

		System.out.println("size = " + size);
		for (int i = 1; i < size; i++)
		{
			p = points.get(i);
			gp.lineTo(p.x, p.y);
			System.out.println("p = " + p.x + ',' + p.y);
		}

		gp.closePath();

	}


	public Rectangle getBounds()
	{
		return gp.getBounds();
	}


	public Rectangle2D getBounds2D()
	{
		return gp.getBounds2D();
	}


	public boolean contains(double x, double y)
	{
		return gp.contains(x, y);
	}


	public boolean contains(Point2D p)
	{
		return gp.contains(p);
	}


	public boolean intersects(double x, double y, double w, double h)
	{
		return gp.intersects(x, y, w, h);
	}


	public boolean intersects(Rectangle2D r)
	{
		return gp.intersects(r);
	}


	public boolean contains(double x, double y, double w, double h)
	{
		return gp.contains(x, y, w, h);
	}


	public boolean contains(Rectangle2D r)
	{
		return gp.contains(r);
	}


	public PathIterator getPathIterator(AffineTransform at)
	{
		return gp.getPathIterator(at);
	}


	public PathIterator getPathIterator(AffineTransform at, double flatness)
	{
		return gp.getPathIterator(at, flatness);
	}


	public void moveTo(float x, float y)
	{
		addPoint(x, y);
	}


	public void lineTo(float x, float y)
	{
		addPoint(x, y);
	}


	public void closePath()
	{
		gp.closePath();
	}


	public Element toXML(Document document)
	{
		Element e = document.createElement("GeneralPath");

		for (SPoint p : points)
		{
			e.appendChild(p.toXML(document));
		}

		return e;
	}


}