/*******************************************************************************
 * Copyright (c) 2006, 2007, 2008 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.util;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Utility class for model loading/saving and serialization within Eclipse.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @since 0.8
 */
public final class EclipseModelUtils {
	/**
	 * Utility classes don't need to (and shouldn't) be instantiated.
	 */
	private EclipseModelUtils() {
		// prevents instantiation
	}

	/**
	 * Loads a model from an {@link org.eclipse.core.resources.IFile IFile} in a given {@link ResourceSet}.
	 * <p>
	 * This will return the first root of the loaded model, other roots can be accessed via the resource's
	 * content.
	 * </p>
	 * 
	 * @param file
	 *            {@link org.eclipse.core.resources.IFile IFile} containing the model to be loaded.
	 * @param resourceSet
	 *            The {@link ResourceSet} to load the model in.
	 * @return The model loaded from the file.
	 * @throws IOException
	 *             If the given file does not exist.
	 */
	public static EObject load(IFile file, ResourceSet resourceSet) throws IOException {
		EObject result = null;

		// First tries to load the IFile assuming it is in the workspace
		Resource modelResource = ModelUtils.createResource(URI.createPlatformResourceURI(file.getFullPath()
				.toOSString(), true), resourceSet);
		try {
			modelResource.load(Collections.emptyMap());
		} catch (IOException e) {
			// If it failed, load the file assuming it is in the plugins
			resourceSet.getResources().remove(modelResource);
			modelResource = ModelUtils.createResource(URI.createPlatformPluginURI(file.getFullPath()
					.toOSString(), true), resourceSet);
			try {
				modelResource.load(Collections.emptyMap());
			} catch (IOException ee) {
				// If it fails anew, throws the first IOException
				throw e;
			}
		}
		// Returns the first root of the loaded model
		if (modelResource.getContents().size() > 0)
			result = modelResource.getContents().get(0);
		return result;
	}

	/**
	 * Loads a model from an {@link IPath} in a given {@link ResourceSet}.
	 * <p>
	 * This will return the first root of the loaded model, other roots can be accessed via the resource's
	 * content.
	 * </p>
	 * 
	 * @param path
	 *            {@link IPath} where the model lies.
	 * @param resourceSet
	 *            The {@link ResourceSet} to load the model in.
	 * @return The model loaded from the path.
	 * @throws IOException
	 *             If the given file does not exist.
	 */
	public static EObject load(IPath path, ResourceSet resourceSet) throws IOException {
		return load(ResourcesPlugin.getWorkspace().getRoot().getFile(path), resourceSet);
	}
}