package com.packtpub.e4.clock.ui.internal;

import java.util.TimeZone;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

public class TimeZoneAdapterFactory implements IAdapterFactory {

	public TimeZoneAdapterFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IPropertySource.class && adaptableObject instanceof TimeZone) {
			return new TimeZonePropertySource((TimeZone)adaptableObject);
		} else {
			return null;
		}
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] { IPropertySource.class };
	}

}
