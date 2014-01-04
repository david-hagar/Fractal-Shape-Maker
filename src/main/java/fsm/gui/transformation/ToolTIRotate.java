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
import fsm.transformation.TransformationRotate;

import java.awt.*;
import java.awt.event.*;


public class ToolTIRotate extends JPanel
{

	TransformationRotate t;
	JTextField angleTextField = new JTextField();
	JLabel jLabel1 = new JLabel();
	BorderLayout borderLayout1 = new BorderLayout();


	public ToolTIRotate(TransformationRotate t)
	{
		this.t = t;

		angleTextField.setText(Double.toString(t.angle));

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
		jLabel1.setText("Rotate");
		jLabel1.addMouseListener(new java.awt.event.MouseAdapter()
		{

			public void mousePressed(MouseEvent e)
			{
				jLabel1_mousePressed(e);
			}
		});
		this.setLayout(borderLayout1);
		angleTextField.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				angleTextField_actionPerformed(e);
			}
		});
		this.add(jLabel1, BorderLayout.CENTER);
		this.add(angleTextField, BorderLayout.SOUTH);
	}


	void angleTextField_actionPerformed(ActionEvent e)
	{
		t.setRotate(Double.parseDouble(((JTextField) e.getSource()).getText()));
		FractalShapeMaker.viewer.redraw();

	}


	void jLabel1_mousePressed(MouseEvent e)
	{
		FractalShapeMaker.toolsFrame.setCurrentTransformItem(t);
	}
}