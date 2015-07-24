package com.packtpub.e4.clock.ui;

import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ClockWidget extends Canvas {
	
	private final Color color;
	
	private int offset;
	
	public void setOffset(int offset) {
		this.offset = offset;
	}

	public ClockWidget(Composite parent, int style, RGB rgb) {
		super(parent, style);
		this.color = new Color(parent.getDisplay(), rgb);
		
		addPaintListener(new PaintListener() {			
			public void paintControl(PaintEvent e) {
				ClockWidget.this.paintControl(e);
			}
		});
		
		addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if (color != null && !color.isDisposed()) {
					color.dispose();
				}
			}
		});
		
		
		new Thread("TickTock") {
			@Override
			public void run() {
				while(!ClockWidget.this.isDisposed()) {
					ClockWidget.this.getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							if (ClockWidget.this != null && !ClockWidget.this.isDisposed()) {
								ClockWidget.this.redraw();
							}
						}					
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						return;
					}
				}
				
			}
		}.start();
	}

	public void paintControl(PaintEvent e) {		
		
		int seconds = Calendar.getInstance().get(Calendar.SECOND);
		int minutes = Calendar.getInstance().get(Calendar.MINUTE);
		int hours = Calendar.getInstance().get(Calendar.HOUR) + offset;
		e.gc.drawArc(e.x, e.y, e.width - 1, e.height - 1, 0, 360);
		
		int arcSec = (15 - seconds) * 6 % 360;
		Color blue = e.display.getSystemColor(SWT.COLOR_BLUE);
		e.gc.setBackground(blue);
		e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arcSec - 1, 2);
		
		
		int arcMin = (15 - minutes) * 6 % 360;
		Color green = e.display.getSystemColor(SWT.COLOR_GREEN);
		e.gc.setBackground(green);
		e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arcMin - 1, 2);
		
		int arcHr = (3 - hours) * 30 % 360;
		Color red = e.display.getSystemColor(SWT.COLOR_RED);
		e.gc.setBackground(red);
		e.gc.fillArc(e.x, e.y, e.width - 3, e.height - 3, arcHr - 2, 4);
		
		
	}
	
	public Point computeSize(int w, int h, boolean changed) {
		int size;
		
		if (w == SWT.DEFAULT) {
			size = h;
		} else if (h == SWT.DEFAULT) {
			size = w;
		} else {
			size = Math.min(w,  h);
		}
		
		if (size == SWT.DEFAULT) {
			size = 50;
		}
		
		return new Point(size, size);
	}
	
//	@Override
//	public void dispose() {
//		if (color != null && !color.isDisposed()) {
//			color.dispose();			
//		}
//		super.dispose();
//	}
}
