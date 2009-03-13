
/*
 * generated by Xtext
 */
package org.eclipse.emf.compare.epatch.dsl.internal;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Map;
import java.util.HashMap;

/**
 * Generated
 */
public class EpatchActivator extends AbstractUIPlugin {

	private Map<String,Injector> injectors = new HashMap<String,Injector>();
	private static EpatchActivator INSTANCE;

	public Injector getInjector(String languageName) {
		return injectors.get(languageName);
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
		
		injectors.put("org.eclipse.emf.compare.epatch.dsl.Epatch",Guice.createInjector(new org.eclipse.emf.compare.epatch.dsl.EpatchRuntimeModule(), new org.eclipse.emf.compare.epatch.dsl.EpatchUiModule()));
		
	}
	
	public static EpatchActivator getInstance() {
		return INSTANCE;
	}
	
}
