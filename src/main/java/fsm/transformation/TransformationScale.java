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


public class TransformationScale extends Transformation implements Serializable
{
	static final long serialVersionUID = -2865711332395576241L;

	public double scale = 1.0;


	public TransformationScale()
	{
	}


	public TransformationScale(Node node)
	{
		NamedNodeMap attr = node.getAttributes();
		scale = Float.parseFloat(attr.getNamedItem("scale").getNodeValue());
		transform.setToScale(scale, scale);
	}


	public String getDisplayString()
	{
		return "Scale " + scale;
	}


	public void setScale(double scale)
	{
		this.scale = scale;
		transform.setToScale(scale, scale);
	}


	@Override
	public Node toXML(Document document)
	{
		Element e = document.createElement("TransformationScale");
		e.setAttribute("scale", Double.toString(scale));
		return e;
	}

}