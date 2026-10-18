/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 *
 * @author
 * @version 1.0
 */
package fsm.transformation;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.awt.geom.AffineTransform;
import java.io.Serializable;

abstract public class Transformation implements Serializable {

    static final long serialVersionUID = -7080059040162915697L;


    public AffineTransform transform = new AffineTransform();


    public Transformation() {
    }


    public abstract String getDisplayString();


    abstract public Node toXML(Document document);

}