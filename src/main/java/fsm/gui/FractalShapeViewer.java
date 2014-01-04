/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.gui;

import javax.swing.*;

import fsm.model.FractalRenderState;
import fsm.model.FractalShape;
import fsm.model.TransformationSequence;
import fsm.shape.ElementShape;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;

public class FractalShapeViewer extends JPanel implements Runnable, Printable
{

	private FractalShape fractalShape = null;
	// public boolean drawAxes = true;
	static double smallestScale = 1.0;
	final static double smallestFeature = 1.0;
	Thread renderThread = new Thread(this);
	boolean isThumbnail = false;
	BufferedImage image = null;


	public FractalShapeViewer()
	{
		// this.setDoubleBuffered(false);
	}


	public FractalShapeViewer(FractalShape fractalShape, boolean isThumbnail)
	{
		setFractalShape(fractalShape);
		this.isThumbnail = isThumbnail;
	}


	void setFractalShape(FractalShape fractalShape)
	{
		this.fractalShape = fractalShape;
		this.setBackground(fractalShape.background);
	}


	FractalShape getFractalShape()
	{
		return fractalShape;
	}


	void repaintImage()
	{
		image = null;
		repaint();
	}


	public void paint(Graphics g0)
	{
		// ( (Graphics2D) g0).setPaint(Color.red);
		// ( (Graphics2D) g0).draw3DRect(20,20,50,70, true) ;
		// return;
		super.paint(g0);

		if (isThumbnail)
		{
			// super.paint(g0);
			if (image == null)
				makeThumbnail();

			// paintIt( (Graphics2D) g0);
			((Graphics2D) g0).drawImage(image, new AffineTransform(), null);
			return;
		}

		// ((Graphics2D)g0).setBackground(fractalShape.background);
		// super.paint(g0);
		if (renderThread.isAlive())
		{
			// System.out.println("interupting");
			renderThread.interrupt();
			try
			{
				renderThread.join();
			} catch (InterruptedException e)
			{
				return;
			}
		}
		renderThread = new Thread(this);
		renderThread.start();
		// System.out.println("paint call done");
	}


	public void makeThumbnail()
	{
		Dimension size = getSize();
		BufferedImage newImage = new BufferedImage(size.width, size.height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) newImage.getGraphics();
		paintIt(g, size);
		image = newImage;
	}


	public void run()
	{
		// if(threadRenderGraphics == null)
		// return;

		// System.out.println("thread run");
		Dimension size = getSize();

		paintIt((Graphics2D) getGraphics(), size);
	}


	public void paintIt(Graphics2D g, Dimension size)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// g.setPaint(Color.red);
		// g.draw3DRect(20,20,50,70, true) ;

		if (fractalShape == null)
			return;

		if (fractalShape.autoBounds)
			calcFractalBounds(size);

		g.setStroke(new BasicStroke(0));
		// this.setBackground(fractalShape.background);
		// super.paint(g);
		g.setBackground(fractalShape.background);
		g.clearRect(0, 0, size.width, size.height);

		// paintTestPattern(g, size);
		// g.setPaint(Color.red);
		// g.draw3DRect(120,120,50,70, true) ;


		double scale = getScale(size);
		Rectangle2D.Double bounds = fractalShape.bounds;
		double centerX = bounds.x + bounds.width / 2.0;
		double centerY = bounds.y + bounds.height / 2.0;

		g.translate(size.width / 2, size.height / 2);
		g.scale(1.0, -1.0);
		g.scale(scale, scale);
		g.translate(-centerX, -centerY);

		// g.setPaint(Color.red);
		// GeneralPath b = new GeneralPath(bounds);
		// System.out.println( "bounds = " + bounds );
		// g.draw(b) ;
		{
			Iterator i = fractalShape.transforms.iterator();
			while (i.hasNext())
			{
				TransformationSequence t = (TransformationSequence) i.next();
				t.recalcTotal();
			}
		}

		/*
		 * // get initial color if( fractalShape.elementShapes.size() != 0 && (
		 * (ElementShape)(fractalShape.elementShapes.get(0)) ).color == null &&
		 * fractalShape.transforms.size() != 0 && (
		 * (TransformationSequence)(fractalShape.transforms.get(0)) ).color != null )
		 * g.setPaint( ((TransformationSequence)(fractalShape.transforms.get(0))
		 * ).color);
		 */
		TransformationSequence itf = fractalShape.getInitialTF();
		if (itf != null)
		{
			itf.recalcTotal();
			g.transform(itf.totalTransform);
			// System.out.println( fractalShape.initialTF.totalTransform );
		}

		AffineTransform defaultTransform = g.getTransform();

		FractalRenderState frs = new FractalRenderState();
		frs.color = itf.color;

		recurse(g, 1, defaultTransform, frs);


		/*
		 * if( drawAxes ) { g.setPaint(Color.blue);
		 * g.setTransform(defaultTransform); drawAxes( g ); }
		 */
	}


	void recurse(Graphics2D g, int level, AffineTransform t,
			FractalRenderState frs)
	{
		// AffineTransform originalTf = g.getTransform();
		// System.out.println("t = " + t);

		if (Thread.currentThread().isInterrupted())
			return;

		boolean lastLevel = level + 1 == fractalShape.level;

		boolean allTooSmall = true;
		{
			Iterator i = fractalShape.elementShapes.iterator();
			g.setColor(frs.color);
			while (i.hasNext())
			{
				ElementShape es = ((ElementShape) i.next());
				boolean rts = rectTooSmall(es.getBounds(t), smallestFeature);
				if (!rts)
					allTooSmall = false;

				if (!es.onlyDrawLast || rts || lastLevel)
				{
					g.setTransform(t);
					es.draw(g, level);
				}

			}
		}


		if (level++ < fractalShape.level && !allTooSmall)
		{
			Iterator i = fractalShape.transforms.iterator();
			while (i.hasNext())
			{
				TransformationSequence ts = (TransformationSequence) i.next();
				if (!ts.recurse)
					continue;
				AffineTransform subT = new AffineTransform(t);
				subT.concatenate(ts.totalTransform);

				FractalRenderState newfrs = new FractalRenderState(frs);
				if (ts.color != null)
					newfrs.setColor(ts.color);
				else if (ts.hueDelta != 0.0f)
					newfrs.incrementColor(ts.hueDelta);

				recurse(g, level, subT, newfrs);
			}
		}
	}


	void drawAxes(Graphics2D g)
	{
		Point2D.Float origin = new Point2D.Float(0.0f, 0.0f);
		Point2D.Float xAxis = new Point2D.Float(1.0f, 0.0f);
		Point2D.Float yAxis = new Point2D.Float(0.0f, 1.0f);


		GeneralPath p = new GeneralPath();
		p.moveTo(xAxis.x, xAxis.y);
		p.lineTo(origin.x, origin.y);
		p.lineTo(yAxis.x, yAxis.y);

		// System.out.println( "" + origin + xAxis + yAxis );

		g.draw(p);

		// AffineTransform normalizedToDeviceCoord = g.getTransform();

		// normalizedToDeviceCoord.transform(origin, origin);
		// normalizedToDeviceCoord.transform(xAxis, xAxis);
		// normalizedToDeviceCoord.transform(yAxis, yAxis);
		// System.out.println( "post" + origin + xAxis + yAxis );

	}


	Rectangle2D.Double getFractalBounds(Dimension size)
	{
		if (fractalShape.elementShapes.size() == 0
				|| fractalShape.transforms.size() == 0)
			return new Rectangle2D.Double(-10.0, -10.0, 20.0, 20.0);


		Iterator j = fractalShape.transforms.iterator();
		while (j.hasNext())
		{
			TransformationSequence ts = (TransformationSequence) j.next();
			ts.recalcTotal();
		}


		AffineTransform t = new AffineTransform();
		TransformationSequence itf = fractalShape.getInitialTF();
		if (itf != null)
		{
			itf.recalcTotal();
			t.concatenate(itf.totalTransform);
			// System.out.println( fractalShape.initialTF.totalTransform );
		}

		smallestScale = getMaxShapeSize(t) / 20.0;

		Rectangle2D r = getBoundsRecurse(1, t);

		if (r instanceof Rectangle2D.Double)
			return (Rectangle2D.Double) r;
		if (r instanceof Rectangle2D.Float)
		{
			Rectangle2D.Float rf = (Rectangle2D.Float) r;
			return new Rectangle2D.Double(rf.x, rf.y, rf.width, rf.height);
		} else
			return null;
	}


	Rectangle2D getBoundsRecurse(int level, AffineTransform t)
	{
		Rectangle2D rUnion = null;
		boolean allTooSmall = true;
		{
			Iterator i = fractalShape.elementShapes.iterator();
			if (i.hasNext())
			{
				rUnion = ((ElementShape) i.next()).getBounds(t);
				if (!rectTooSmall(rUnion, smallestScale))
					allTooSmall = false;
			}
			while (i.hasNext())
			{
				Rectangle2D r = ((ElementShape) i.next()).getBounds(t);
				Rectangle2D.union(rUnion, r, rUnion);
				if (!rectTooSmall(r, smallestScale))
					allTooSmall = false;
			}
		}

		if (level++ < fractalShape.level && !allTooSmall)
		{
			Iterator i = fractalShape.transforms.iterator();
			while (i.hasNext())
			{
				TransformationSequence ts = (TransformationSequence) i.next();
				if (!ts.recurse)
					continue;
				AffineTransform subT = new AffineTransform(t);

				subT.concatenate(ts.totalTransform);

				Rectangle2D r = getBoundsRecurse(level, subT);
				Rectangle2D.union(rUnion, r, rUnion);
			}
		}
		return rUnion;
	}


	boolean rectTooSmall(Rectangle2D r, double size)
	{
		if (r instanceof Rectangle2D.Float)
		{
			Rectangle2D.Float rf = (Rectangle2D.Float) r;
			return Math.max(rf.width, rf.height) < size;
		} else
		{
			Rectangle2D.Double rd = (Rectangle2D.Double) r;
			return Math.max(rd.width, rd.height) < size;
		}
	}


	double getMaxShapeSize(AffineTransform t)
	{
		double maxSize = 0.0;

		Iterator i = fractalShape.elementShapes.iterator();
		while (i.hasNext())
		{
			Rectangle2D r = ((ElementShape) i.next()).getBounds(t);
			double maxAspect = Math.max(r.getWidth(), r.getHeight());
			if (maxAspect > maxSize)
				maxSize = maxAspect;
		}

		return maxSize;
	}


	double getScale(Dimension size)
	{
		// Dimension size = getSize();
		if (fractalShape.bounds == null || (size.width == 0 && size.height == 0))
			return 200.0;

		Rectangle2D.Double bounds = fractalShape.bounds;

		double boundsAspect = bounds.height / bounds.width;
		double windowAspect = (double) size.height / size.width;

		double scale;
		if (windowAspect > boundsAspect)
			scale = size.width / bounds.width;
		else
			scale = size.height / bounds.height;

		return scale;
	}


	void calcFractalBounds(Dimension size)
	{

		Dimension size2 = new Dimension(100, 100);
		Rectangle2D.Double bounds = getFractalBounds(size2);
		double maragin = Math.max(bounds.width, bounds.height) * 0.05;
		bounds.x -= maragin;
		bounds.y -= maragin;
		bounds.width += maragin * 2.0;
		bounds.height += maragin * 2.0;
		fractalShape.bounds = bounds;

	}


	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException
	{
		if (pageIndex >= 1)
			return Printable.NO_SUCH_PAGE;

		Graphics2D g2d = (Graphics2D) graphics;

		double h = pageFormat.getImageableHeight();
		double w = pageFormat.getImageableWidth();
		double x = pageFormat.getImageableX();
		double y = pageFormat.getImageableY();

		// if( pageFormat.getOrientation() == pageFormat.LANDSCAPE )
		// System.out.println("-pageFormat.LANDSCAPE");
		// if( pageFormat.getOrientation() == pageFormat.PORTRAIT )
		// System.out.println("-pageFormat.PORTRAIT");

		System.out.println("pf:" + x + "," + y + "," + w + "," + h);

		// g2d.translate(x,y);
		// g2d.scale();

		// Rectangle r = g2d.getClipBounds();
		// r.x -= 1;
		// r.y -= 1;
		// r.width += 2;
		// r.height += 2;
		// g2d.setClip(r);

		g2d.translate(x, y);
		Dimension size = new Dimension((int) w, (int) h);
		// AffineTransform t = g2d.getTransform();
		paintIt(g2d, size);
		// g2d.setTransform(t);
		// paintTestPattern(g2d, size);

		return Printable.PAGE_EXISTS;
	}


	void paintTestPattern(Graphics2D g, Dimension size)
	{
		Rectangle r = new Rectangle(size);

		g.setPaint(Color.black);
		g.setStroke(new BasicStroke(1));
		r.grow(50, 50);
		for (int i = 0; i < 15; i++)
		{
			r.grow(-10, -10);
			g.draw(r);
			g.drawString("" + r.x + "," + r.y + "," + r.width + "," + r.height,
					r.x + 100, r.y + 10);
		}
		g.setStroke(new BasicStroke(0));
	}


}