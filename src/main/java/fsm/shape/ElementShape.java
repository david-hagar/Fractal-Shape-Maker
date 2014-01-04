/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fsm.model.TransformationSequence;

import util.XMLUtils;

public class ElementShape implements Cloneable, Serializable
{

	static final long serialVersionUID = -4483191325965215536L;

	BasicShape shape;

	public TransformationSequence baseTransform = new TransformationSequence();

	public boolean onlyDrawLast = false;
	public Color color = null;


	// public boolean fill = true;


	public ElementShape(BasicShape shape)
	{
		this.shape = shape;
	}


	public ElementShape(Node esNode, ArrayList<TransformationSequence> transforms)
	{
		NamedNodeMap attr = esNode.getAttributes();
		onlyDrawLast = Boolean.parseBoolean(attr.getNamedItem("onlyDrawLast")
				.getNodeValue());
		color = XMLUtils.fromXMLString(attr.getNamedItem("color").getNodeValue());
		// fill = Boolean.parseBoolean(attr.getNamedItem("fill")
		// .getNodeValue());


		int baseTransformIndex = Integer.parseInt(attr
				.getNamedItem("baseTransform").getNodeValue());
		baseTransform = transforms.get(baseTransformIndex);


		// System.out.println(onlyDrawLast + " " + color + fill);


		NodeList nodeList = esNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			XMLUtils.printType(node);

			if (node.getNodeName().equals("TransformationSequence"))
			{
				baseTransform = new TransformationSequence(node);
			} else if (node.getNodeName().equals("Shape"))
			{
				loadShape(node);
			} else
				System.out.println("unused node " + node);
		}

	}


	private void loadShape(Node shapeNode)
	{
		NodeList nodeList = shapeNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			XMLUtils.printType(node);

			if (node.getNodeName().equals("GeneralPath"))
			{
				shape = new SGeneralPath(node);
			} else if (node.getNodeName().equals("Ellipse"))
			{
				shape = new SEllipse2D(node);
			} else
				System.out.println("unused node " + node);
		}

	}


	public void setBaseTF(TransformationSequence t)
	{
		baseTransform = t;
		t.recalcTotal();
	}


	public void draw(Graphics2D g, int level)
	{
		// System.out.println("1 " + shapeDevCoord.getBounds2D() );

		g.transform(baseTransform.totalTransform);

		if (color != null)
			g.setColor(color);

		// if (fill)
		g.fill(shape);
		// else
		// g.draw(shape);

	}


	public Rectangle2D getBounds(AffineTransform t)
	{
		AffineTransform t2 = new AffineTransform(t);
		t2.concatenate(baseTransform.totalTransform);
		Shape ts = t2.createTransformedShape(shape);
		return ts.getBounds2D();
	}


	public Rectangle2D.Double getBounds()
	{
		Shape ts = baseTransform.totalTransform.createTransformedShape(shape);
		return (Rectangle2D.Double) ts.getBounds2D();
	}


	public Object clone()
	{
		try
		{
			ElementShape obj = (ElementShape) super.clone();
			// obj.baseTransform = baseTransform.clone();
			return obj;
		} catch (CloneNotSupportedException e)
		{
			System.out.println("clone failed");
		}

		return null;
	}


	public Element toXML(Document document,
			ArrayList<TransformationSequence> transforms) throws Exception
	{
		Element elementShape = document.createElement("ElementShape");
		elementShape.setAttribute("onlyDrawLast", Boolean.toString(onlyDrawLast));
		// elementShape.setAttribute("fill", Boolean.toString(fill));
		elementShape.setAttribute("color", XMLUtils.toXMLString(color));

		int index = transforms.indexOf(baseTransform);
		if (index == -1)
			throw new Exception("Could not find shape transformation");

		elementShape.setAttribute("baseTransform", Integer.toString(index));

		Element shapeNode = document.createElement("Shape");
		Element se = shape.toXML(document);
		shapeNode.appendChild(se);
		elementShape.appendChild(shapeNode);


		return elementShape;
	}
}