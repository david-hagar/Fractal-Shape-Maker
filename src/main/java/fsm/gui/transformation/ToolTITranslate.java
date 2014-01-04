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
import fsm.transformation.TransformationTranslate;

import java.awt.*;
import java.awt.event.*;


public class ToolTITranslate extends JPanel
{

	TransformationTranslate t;
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	JTextField xTextField = new JTextField();
	JTextField yTextField = new JTextField();
	BorderLayout borderLayout1 = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();


	public ToolTITranslate(TransformationTranslate t)
	{
		this.t = t;
		xTextField.setText(Double.toString(t.transX));
		yTextField.setText(Double.toString(t.transY));
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
		jLabel1.setText("Translate");
		jLabel1.addMouseListener(new java.awt.event.MouseAdapter()
		{

			public void mousePressed(MouseEvent e)
			{
				jLabel1_mousePressed(e);
			}
		});
		this.setLayout(borderLayout2);
		jPanel1.setLayout(borderLayout1);
		xTextField.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				xTextField_actionPerformed(e);
			}
		});
		yTextField.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				yTextField_actionPerformed(e);
			}
		});
		this.add(jLabel1, BorderLayout.CENTER);
		this.add(jPanel1, BorderLayout.SOUTH);
		jPanel1.add(xTextField, BorderLayout.NORTH);
		jPanel1.add(yTextField, BorderLayout.SOUTH);
	}


	void xTextField_actionPerformed(ActionEvent e)
	{
		t.setTranslateX(Double.parseDouble(((JTextField) e.getSource()).getText()));
		FractalShapeMaker.viewer.redraw();
	}


	void yTextField_actionPerformed(ActionEvent e)
	{
		t.setTranslateY(Double.parseDouble(((JTextField) e.getSource()).getText()));
		FractalShapeMaker.viewer.redraw();
	}


	void jLabel1_mousePressed(MouseEvent e)
	{
		FractalShapeMaker.toolsFrame.setCurrentTransformItem(t);
	}
}