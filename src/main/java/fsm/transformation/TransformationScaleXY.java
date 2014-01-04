/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.transformation;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


public class TransformationScaleXY extends Transformation implements
		Serializable
{

	static final long serialVersionUID = -6293511061809198403L;

	public double scaleX = 1.0;
	public double scaleY = 1.0;


	public TransformationScaleXY()
	{
	}


	public TransformationScaleXY(Node node)
	{
		NamedNodeMap attr = node.getAttributes();
		scaleX = Float.parseFloat(attr.getNamedItem("scaleX").getNodeValue());
		scaleY = Float.parseFloat(attr.getNamedItem("scaleY").getNodeValue());
		transform.setToScale(scaleX, scaleY);
	}


	public String getDisplayString()
	{
		return "Scale XY " + scaleX + "," + scaleY;
	}


	public void setScale(double scaleX, double scaleY)
	{
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		transform.setToScale(scaleX, scaleY);
	}


	public void setScaleX(double scaleX)
	{
		this.scaleX = scaleX;
		transform.setToScale(scaleX, scaleY);
	}


	public void setScaleY(double scaleY)
	{
		this.scaleY = scaleY;
		transform.setToScale(scaleX, scaleY);
	}


	@Override
	public Node toXML(Document document)
	{
		Element e = document.createElement("TransformationScaleXY");
		e.setAttribute("scaleX", Double.toString(scaleX));
		e.setAttribute("scaleY", Double.toString(scaleY));
		return e;
	}

}