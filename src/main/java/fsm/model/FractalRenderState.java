/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.model;

import java.awt.*;

import sun.security.action.GetBooleanAction;

public class FractalRenderState
{

	public Color color = Color.white;


	public FractalRenderState()
	{
	}


	public FractalRenderState(FractalRenderState frs)
	{
		this.color = frs.color;
	}


	// public Object clone( )
	// {
	// try
	// { return super.clone(); }
	// catch( Exception e)
	// { return null; }
	//    
	// }

	public void incrementColor(float hueDelta)
	{
		if (hueDelta == 0.0 || color == null)
			return;
		
		float[] hsbvals = Color.RGBtoHSB(color.getRed(), color.getGreen(), color
				.getBlue(), null);
		hsbvals[0] += hueDelta;
		if (hsbvals[0] < 0.0f)
			hsbvals[0] += 1.0f;
		if (hsbvals[0] > 1.0f)
			hsbvals[0] -= 1.0f;

		int transparency = color.getAlpha();
		color = Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]);
		color = new Color(color.getRed(), color.getGreen(), color.getBlue(),
				transparency );
	}


	public void setColor(Color c)
	{
		color = c;
	}
}