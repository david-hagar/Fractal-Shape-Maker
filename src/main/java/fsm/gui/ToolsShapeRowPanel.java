/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import fsm.FractalShapeMaker;
import fsm.model.FractalShape;
import fsm.shape.ElementShape;

import java.awt.event.*;

public class ToolsShapeRowPanel extends JPanel
{
	ElementShape elementShape;
	public FractalShape fractalShape = null;
	Color noColor = Color.lightGray;

	BorderLayout borderLayout1 = new BorderLayout();
	JButton colorButton = new JButton();
	ToolsShapePanel shapePanel;


	public ToolsShapeRowPanel(ElementShape elementShape, FractalShape fractalShape)
	{

		this.elementShape = elementShape;
		this.fractalShape = fractalShape;
		shapePanel = new ToolsShapePanel(elementShape, fractalShape);

		try
		{
			jbInit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception
	{
		this.setLayout(borderLayout1);
		colorButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				colorButton_actionPerformed(e);
			}
		});
		colorButton.setPreferredSize(new Dimension(20, 20));
        colorButton.setOpaque(true);
        colorButton.setBorder(new LineBorder(Color.black, 1));

    	this.add(colorButton, BorderLayout.WEST);
		this.add(shapePanel, BorderLayout.CENTER);
		noColor = colorButton.getBackground();
		colorButton.setBackground(elementShape.color);
	}


	void colorButton_actionPerformed(ActionEvent e)
	{
		Color colorToUse = elementShape.color == null ? Color.blue
				: elementShape.color;

		Color c = JColorChooser.showDialog(this,
				"Pick Color (Cancel sets no color)", colorToUse);

		if( c == null )
		{
			elementShape.color = null;
		}
		else
		{
		
		String ans = (String) JOptionPane.showInputDialog(this,
				"Enter tranparency ( 0.0 to 1.0 ) ", "Tranparency",
				JOptionPane.PLAIN_MESSAGE, (Icon) null, (Object[]) null, new Float(
						colorToUse.getAlpha()/255.0f));
		if (ans == null || ans.equals(""))
			return;

		int transparency = Math.round(Float.parseFloat(ans) * 255) ;
		
		//System.out.println("transparency=" + transparency );
		
		elementShape.color = new Color(c.getRed(), c.getGreen(), c.getBlue(), transparency );

		}
		
			
		// System.out.println( c );

		if (elementShape.color == null)
			colorButton.setBackground(noColor);
		else
			colorButton.setBackground(elementShape.color);
		repaint();
		FractalShapeMaker.viewer.redraw();
	}


	public void setFractalShape(FractalShape fractalShape)
	{
		this.fractalShape = fractalShape;
		shapePanel.fractalShape = fractalShape;
		shapePanel.repaint();
	}


}