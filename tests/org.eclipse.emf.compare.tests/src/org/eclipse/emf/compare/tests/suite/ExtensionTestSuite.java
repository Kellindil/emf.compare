/*  
 * Copyright (c) 2006, Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.emf.compare.tests.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.compare.tests.unit.TestEnginesPriority;
import org.eclipse.emf.compare.tests.unit.TestFindAdapterFactory;
import org.eclipse.emf.compare.tests.unit.TestNonRegressionModels;

/**
 * Test suite for extensions contributions.
 * 
 * @author Cedric Brun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * 
 */
public final class ExtensionTestSuite {
	private ExtensionTestSuite() {
		// prevents instantiation.
	}
	
	/**
	 * Creates and returns the test suite.
	 * 
	 * @return
	 * 			The test suite.
	 */
	public static Test suite() {
		final TestSuite suite = new TestSuite(
				"Test for org.eclipse.emf.compare.match.statistic.test.suite"); //$NON-NLS-1$
		// $JUnit-BEGIN$
		suite.addTestSuite(TestEnginesPriority.class);
		suite.addTestSuite(TestFindAdapterFactory.class);
		suite.addTestSuite(TestNonRegressionModels.class);
		// $JUnit-END$
		return suite;
	}
}