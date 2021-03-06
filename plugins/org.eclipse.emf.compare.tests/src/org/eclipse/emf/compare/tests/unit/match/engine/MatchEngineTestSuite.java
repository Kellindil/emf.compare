/*******************************************************************************
 * Copyright (c) 2006, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.tests.unit.match.engine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Launches all the JUnit tests for the {@link DifferencesService}.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
@SuppressWarnings("nls")
public class MatchEngineTestSuite extends TestCase {
	/**
	 * Launches the test with the given arguments.
	 * 
	 * @param args
	 *            Arguments of the testCase.
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
	 * 
	 * @return The test suite containing all the tests
	 */
	public static Test suite() {
		final TestSuite suite = new TestSuite("Tests for the generic match engine behavior");
		suite.addTestSuite(EnginesPriorityTest.class);
		suite.addTestSuite(MatchEngineSelectionTest.class);
		suite.addTestSuite(MatchOptionsTest.class);
		suite.addTestSuite(ThreeWayModelMatchTest.class);
		suite.addTestSuite(ThreeWayContentMatchTest.class);
		suite.addTestSuite(ThreeWayResourceMatchTest.class);
		suite.addTestSuite(TwoWayModelMatchFragments.class);
		suite.addTestSuite(TwoWayModelMatchTest.class);
		suite.addTestSuite(TwoWayContentMatchTest.class);
		suite.addTestSuite(TwoWayResourceMatchTest.class);
		return suite;
	}
}
