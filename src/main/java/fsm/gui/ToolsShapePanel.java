/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import fsm.FractalShapeMaker;
import fsm.model.FractalShape;
import fsm.shape.ElementShape;

public class ToolsShapePanel extends JPanel
{

	public ElementShape elementShape = null;
	public FractalShape fractalShape = null;


	public ToolsShapePanel(ElementShape elementShape, FractalShape fractalShape)
	{

		this.elementShape = elementShape;
		this.fractalShape = fractalShape;

		setBackground(Color.lightGray);
		try
		{
			jbInit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public void paint(Graphics g0)
	{
		Graphics2D g = (Graphics2D) g0;
		super.paint(g0);

		if (elementShape == null || fractalShape == null)
			return;

		if (elementShape == FractalShapeMaker.toolsFrame.getCurrentElementShape())
			setBackground(Color.darkGray);
		else
			setBackground(Color.lightGray);

		g.setPaint(Color.white);
		g.setStroke(new BasicStroke(0));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Dimension size = getSize();
		Rectangle2D.Double localBounds = fractalShape.getShapeBounds();

		double maxReach = 0.0;
		maxReach = Math.max(maxReach, Math.abs(localBounds.x));
		maxReach = Math.max(maxReach, Math.abs(localBounds.y));
		maxReach = Math.max(maxReach, Math.abs(localBounds.x + localBounds.width));
		maxReach = Math.max(maxReach, Math.abs(localBounds.y + localBounds.height));
		maxReach *= 1.1;
		double scale = Math.min(size.width / 2, size.height / 2) / maxReach;

		g.translate(size.width / 2, size.height / 2);
		g.scale(1.0, -1.0);
		g.scale(scale, scale);

		elementShape.draw(g, 1);

		// System.out.println( "here" + localBounds + " " + scale + " " + maxReach);

	}


	private void jbInit() throws Exception
	{
		this.setPreferredSize(new Dimension(30, 30));
		this.addMouseListener(new java.awt.event.MouseAdapter()
		{

			public void mousePressed(MouseEvent e)
			{
				this_mousePressed(e);
			}
		});
	}


	void this_mousePressed(MouseEvent e)
	{
		FractalShapeMaker.toolsFrame.setCurrentElementShape(elementShape);
	}


}