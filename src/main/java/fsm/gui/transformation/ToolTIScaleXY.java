/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.gui.transformation;

import javax.swing.*;

import fsm.FractalShapeMaker;
import fsm.transformation.TransformationScaleXY;

import java.awt.*;
import java.awt.event.*;


public class ToolTIScaleXY extends JPanel
{

	TransformationScaleXY t;
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	JTextField scaleXText = new JTextField();
	JTextField scaleYText = new JTextField();
	BorderLayout borderLayout1 = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();


	public ToolTIScaleXY(TransformationScaleXY t)
	{
		this.t = t;
		scaleXText.setText(Double.toString(t.scaleX));
		scaleYText.setText(Double.toString(t.scaleY));
		if (t == FractalShapeMaker.toolsFrame.getCurrentTransformItem())
		{
			setBackground(Color.darkGray);
			jLabel1.setForeground(Color.white);
		}
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
		jLabel1.setText("Scale XY");
		jLabel1.addMouseListener(new java.awt.event.MouseAdapter()
		{

			public void mousePressed(MouseEvent e)
			{
				jLabel1_mousePressed(e);
			}
		});
		this.setLayout(borderLayout1);
		jPanel1.setLayout(borderLayout2);
		scaleXText.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				scaleXText_actionPerformed(e);
			}
		});
		scaleYText.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				scaleYText_actionPerformed(e);
			}
		});
		this.add(jLabel1, BorderLayout.CENTER);
		this.add(jPanel1, BorderLayout.SOUTH);
		jPanel1.add(scaleXText, BorderLayout.NORTH);
		jPanel1.add(scaleYText, BorderLayout.SOUTH);
	}


	void scaleXText_actionPerformed(ActionEvent e)
	{
		t.setScaleX(Double.parseDouble(((JTextField) e.getSource()).getText()));
		FractalShapeMaker.viewer.redraw();

	}


	void scaleYText_actionPerformed(ActionEvent e)
	{
		t.setScaleY(Double.parseDouble(((JTextField) e.getSource()).getText()));
		FractalShapeMaker.viewer.redraw();

	}


	void jLabel1_mousePressed(MouseEvent e)
	{
		FractalShapeMaker.toolsFrame.setCurrentTransformItem(t);
	}
}