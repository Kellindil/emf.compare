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
package org.eclipse.emf.compare.tests.unit.core.util.modelutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.tests.EMFCompareTestPlugin;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Tests the behavior of
 * {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.EObject)} and
 * {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.resource.ResourceSet, org.eclipse.emf.ecore.EObject)}.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
@SuppressWarnings("nls")
public class AttachResourceTest extends TestCase {
	/** Full path to the directory containing the models. */
	private static final String INPUT_DIRECTORY = "/inputs/attribute/attributeChange";

	/** This contains the models loaded from {@link INPUT_DIRECTORY}. */
	private static final List<EObject> INPUT_MODELS;

	/** These URIs will be used as the new resources' URIs. */
	private static final String[] TEST_URIS = {"data", "\u00E9", "\r\n", ""};

	static {
		List<EObject> temp = new ArrayList<EObject>();
		try {
			temp = ModelUtils.getModelsFrom(new File(FileLocator.toFileURL(
					EMFCompareTestPlugin.getDefault().getBundle().getEntry(INPUT_DIRECTORY)).getFile()),
					new ResourceSetImpl());
		} catch (IOException e) {
			// no operation, continues with an empty list
		}
		INPUT_MODELS = Collections.unmodifiableList(temp);
	}

	/**
	 * tests
	 * {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.resource.ResourceSet, org.eclipse.emf.ecore.EObject)}.
	 * Expects all models to be attached to a non-<code>null</code> resource in the same resourceSet.
	 */
	public void testAttachResource() {
		final ResourceSet resourceSet = new ResourceSetImpl();
		for (EObject model : INPUT_MODELS) {
			for (String uriPath : TEST_URIS) {
				final EObject test = EcoreUtil.copy(model);
				assertNull("A copy should have no resource, EMF's API has been changed.", test.eResource());
				ModelUtils.attachResource(URI.createFileURI(uriPath), resourceSet, test);
				assertNotNull("attachResource() did not attach a resource as expected.", test.eResource());
				assertEquals("attachResource() did not attach a resource with the expected URI.", test
						.eResource().getURI(), URI.createFileURI(uriPath));
				assertEquals("attachResource() did not attach a resource of the expected resourceSet.",
						resourceSet, test.eResource().getResourceSet());
			}
		}
	}

	/**
	 * tests {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.EObject)}.
	 * Expects all models to be attached to a non-<code>null</code> resource.
	 */
	public void testAttachResourceNoResourceSet() {
		for (EObject model : INPUT_MODELS) {
			for (String uriPath : TEST_URIS) {
				final EObject test = EcoreUtil.copy(model);
				assertNull("A copy should have no resource, EMF's API has been changed.", test.eResource());
				ModelUtils.attachResource(URI.createFileURI(uriPath), test);
				assertNotNull("attachResource() did not attach a resource as expected.", test.eResource());
				assertEquals("attachResource() did not attach a resource with the expected URI.", test
						.eResource().getURI(), URI.createFileURI(uriPath));
			}
		}
	}

	/**
	 * tests {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.EObject)}
	 * and
	 * {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.resource.ResourceSet, org.eclipse.emf.ecore.EObject)}
	 * with <code>null</code> as the object to attach. Expects a {@link NullPointerException} to be thrown.
	 */
	public void testAttachResourceNullEObject() {
		final ResourceSet resourceSet = new ResourceSetImpl();
		for (String uriPath : TEST_URIS) {
			try {
				ModelUtils.attachResource(URI.createFileURI(uriPath), null);
			} catch (NullPointerException e) {
				// This is expected behavior
			}
			try {
				ModelUtils.attachResource(URI.createFileURI(uriPath), resourceSet, null);
			} catch (NullPointerException e) {
				// This is expected behavior
			}
		}
	}

	/**
	 * tests {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.EObject)}
	 * and
	 * {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.resource.ResourceSet, org.eclipse.emf.ecore.EObject)}
	 * with <code>null</code> as the resource's resourceSet. Expects a {@link NullPointerException} to be
	 * thrown.
	 */
	public void testAttachResourceNullResourceSet() {
		for (EObject model : INPUT_MODELS) {
			for (String uriPath : TEST_URIS) {
				final EObject test = EcoreUtil.copy(model);
				assertNull("A copy should have no resource, EMF's API changed.", test.eResource());
				try {
					ModelUtils.attachResource(URI.createFileURI(uriPath), null, test);
				} catch (NullPointerException e) {
					// This is expected behavior
				}
			}
		}
	}

	/**
	 * tests {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.EObject)}
	 * and
	 * {@link ModelUtils#attachResource(org.eclipse.emf.common.util.URI, org.eclipse.emf.ecore.resource.ResourceSet, org.eclipse.emf.ecore.EObject)}
	 * with <code>null</code> as the resource URI. Expects a {@link NullPointerException} to be thrown.
	 */
	public void testAttachResourceNullURI() {
		final ResourceSet resourceSet = new ResourceSetImpl();
		for (EObject model : INPUT_MODELS) {
			final EObject test = EcoreUtil.copy(model);
			assertNull("A copy should have no resource, EMF's API changed.", test.eResource());
			try {
				ModelUtils.attachResource(null, test);
			} catch (NullPointerException e) {
				// This is expected behavior
			}
			try {
				ModelUtils.attachResource(null, resourceSet, test);
			} catch (NullPointerException e) {
				// This is expected behavior
			}
		}
	}
}
