/*******************************************************************************
 * Copyright (c) 2006, 2007 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.diff.generic.merge.impl;

import java.util.Iterator;

import org.eclipse.emf.compare.diff.merge.api.AbstractMerger;
import org.eclipse.emf.compare.diff.merge.api.MergeFactory;
import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;

/**
 * Merger for an {@link DiffExtension} operation.<br/>
 * 
 * @author Cedric Brun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 */
public class DiffExtensionMerger extends DefaultMerger {
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.diff.merge.api.AbstractMerger#applyInOrigin()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void applyInOrigin() {
		final AbstractDiffExtension extension = (AbstractDiffExtension)this.diff;
		if (!extension.providesMerger()) {
			/*
			 * No merger is provided so we'll browse the hidden elements and merge them..
			 */
			for (final Iterator<DiffElement> iterator = extension.getHideElements().iterator(); iterator.hasNext(); ) {
				final DiffElement hidden = iterator.next();
				final AbstractMerger merger = MergeFactory.createMerger(hidden);
				merger.applyInOrigin();
			}
		} else {
			extension.getMerger().applyInOrigin();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.diff.merge.api.AbstractMerger#undoInTarget()
	 */
	@Override
	public void undoInTarget() {
		final AbstractDiffExtension extension = (AbstractDiffExtension)this.diff;
		if (!extension.providesMerger()) {
			/*
			 * No merger is provided so we'll browse the hidden elements and merge them..
			 */
			for (final Iterator<DiffElement> iterator = extension.getHideElements().iterator(); iterator.hasNext(); ) {
				final DiffElement hidden = iterator.next();
				final AbstractMerger merger = MergeFactory.createMerger(hidden);
				merger.undoInTarget();
			}
		} else {
			extension.getMerger().undoInTarget();
		}
	}
}
