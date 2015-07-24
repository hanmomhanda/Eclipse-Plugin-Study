package com.packtpub.e4.clock.ui.providers;

import java.util.Map;
import java.util.TimeZone;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class TimeZoneLabelProvider extends LabelProvider implements IStyledLabelProvider, IFontProvider {
	
	private final ImageRegistry ir;
	private final FontRegistry fr;

	public TimeZoneLabelProvider(ImageRegistry ir, FontRegistry fr) {
		this.ir = ir;
		this.fr = fr;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Map) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		} else if (element instanceof Map.Entry){
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
//			return ir.get("sample");
		} else if (element instanceof TimeZone) {
			return ir.get("sample");
		} else {
			return super.getImage(element);
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Map) {
			return "Time Zones";
		} else if (element instanceof Map.Entry){
			return ((Map.Entry)element).getKey().toString();
		} else if (element instanceof TimeZone) {
			return ((TimeZone)element).getID().split("/")[1];
		} else {
			return "Unkown Type: " + element.getClass();
		}
	}

	@Override
	public StyledString getStyledText(Object element) {
		String text = getText(element);
		StyledString ss = new StyledString(text);
		
		if (element instanceof TimeZone) {
			int offset = -((TimeZone)element).getOffset(0);
			ss.append(" (" + offset / 360000 + "h)", StyledString.DECORATIONS_STYLER);			
		}
		return ss;
	}

	@Override
	public Font getFont(Object element) {
		Font italic = fr.getItalic(JFaceResources.DEFAULT_FONT);
		return italic;
	}

}
