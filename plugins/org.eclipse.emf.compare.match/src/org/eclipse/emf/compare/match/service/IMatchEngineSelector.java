/*******************************************************************************
 * Copyright (c) 2008 Dimitrios Kolovos.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.match.service;

import java.util.Set;

/**
 * Clients can subclass this in order to offer a specific match engine selector to users when more than one
 * are defined on the same file extension.
 * 
 * @author <a href="dkolovos@cs.york.ac.uk">Dimitrios Kolovos</a>
 * @since 0.9
 */
public interface IMatchEngineSelector {
	/**
	 * Implements the actual engine selection algorythm.
	 * 
	 * @param engines
	 *            List of all candidates.
	 * @return The selected engine.
	 */
	EngineDescriptor selectMatchEngine(Set<EngineDescriptor> engines);
}