package com.packtpub.e4.clock.ui.providers;

import java.util.Collection;
import java.util.Map;
import java.util.TimeZone;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TimeZoneContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Object[]) {
			return (Object[])inputElement;
//			Object[] objArr = new Object[2];
//			objArr[0] = (Object[])inputElement;
//			objArr[1] = (Object[])inputElement;
//			return objArr;
		} else {
			return new Object[0];
		}
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Map) {
			return ((Map)parentElement).entrySet().toArray();
		} else if (parentElement instanceof Map.Entry) {
			return getChildren(((Map.Entry)parentElement).getValue());
		} else if (parentElement instanceof Collection) {
			return ((Collection)parentElement).toArray();
		} else {
			return new Object[0];
		}
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Map) {
			return !((Map)element).isEmpty();
		} else if (element instanceof Map.Entry) {
			return hasChildren(((Map.Entry)element).getValue());
		} else if (element instanceof Collection) {
			return !((Collection)element).isEmpty();
		} else {
			return false;
		}
	}

}
