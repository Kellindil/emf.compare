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
package org.eclipse.emf.compare.diff.merge.api;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.diff.merge.service.MergeService;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;

/**
 * Defines here a new copier to alter the way references are copied when an EObject is. References
 * corresponding to unmatched object need specific handling.
 * <p>
 * <b>This map's content should be cleared when all differences of {@link #diffModel} are merged.</b>
 * </p>
 * 
 * @author Laurent Goubet <a href="mailto:laurent.goubet@obeo.fr">laurent.goubet@obeo.fr</a>
 */
public final class EMFCompareEObjectCopier extends Copier {
	/** This class' serial version UID. */
	private static final long serialVersionUID = 2701874812215174395L;

	/** The DiffModel on which differences this copier will be used. */
	private final DiffModel diffModel;

	/**
	 * Creates a Copier given the DiffModel it will be used for.
	 * 
	 * @param diff
	 *            The DiffModel Which elements will be merged using this copier.
	 */
	public EMFCompareEObjectCopier(DiffModel diff) {
		super();
		diffModel = diff;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.util.EcoreUtil$Copier#copyReferences()
	 */
	@Override
	public void copyReferences() {
		final Set<Map.Entry<EObject, EObject>> entrySetCopy = new HashSet<Map.Entry<EObject, EObject>>(
				entrySet());
		for (Map.Entry<EObject, EObject> entry : entrySetCopy) {
			final EObject eObject = entry.getKey();
			final EObject copyEObject = entry.getValue();
			final EClass eClass = eObject.eClass();
			for (int j = 0; j < eClass.getFeatureCount(); ++j) {
				final EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(j);
				if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived()) {
					if (eStructuralFeature instanceof EReference) {
						final EReference eReference = (EReference)eStructuralFeature;
						if (!eReference.isContainment() && !eReference.isContainer()) {
							copyReference(eReference, eObject, copyEObject);
						}
					} else if (FeatureMapUtil.isFeatureMap(eStructuralFeature)) {
						copyFeatureMap(eObject, eStructuralFeature);
					}
				}
			}
		}
	}

	/**
	 * This will copy the given <tt>value</tt> to the reference <tt>targetReference</tt> of
	 * <tt>target</tt>.
	 * 
	 * @param targetReference
	 *            The reference to add a value to.
	 * @param target
	 *            The object to copy to.
	 * @param value
	 *            The value that is to be copied.
	 */
	@SuppressWarnings("unchecked")
	public void copyReferenceValue(EReference targetReference, EObject target, EObject value) {
		final EObject targetValue = get(value);
		if (targetValue != null)
			((List<Object>)target.eGet(targetReference)).add(targetValue);
		else if (mergeLinkedDiff(value))
			// referenced object was an unmatched one and we managed to merge its corresponding diff
			((List<Object>)target.eGet(targetReference)).add(get(value));
		else
			((List<Object>)target.eGet(targetReference)).add(value);
	}

	/**
	 * Ensures the original and copied objects all share the same XMI ID.
	 */
	public void copyXMIIDs() {
		for (Map.Entry<EObject, EObject> entry : entrySet()) {
			final EObject original = entry.getKey();
			final EObject copy = entry.getValue();
			if (original.eResource() instanceof XMIResource && copy.eResource() instanceof XMIResource) {
				final XMIResource originResource = (XMIResource)original.eResource();
				final XMIResource copyResource = (XMIResource)copy.eResource();
				if (originResource.getID(original) != null)
					copyResource.setID(copy, originResource.getID(original));
			}
		}
	}

	/**
	 * Returns the DiffModel associated to this copier.
	 * 
	 * @return The DiffModel associated to this copier.
	 */
	public DiffModel getDiffModel() {
		return diffModel;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.util.EcoreUtil$Copier#copyReference(org.eclipse.emf.ecore.EReference,
	 *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject) {
		// No use trying to copy the reference if it isn't set in the origin
		if (!eObject.eIsSet(eReference))
			return;
		if (eReference.isMany()) {
			final List<?> referencedObjectsList = (List<?>)eObject.eGet(eReference, resolveProxies);
			if (referencedObjectsList == null)
				copyEObject.eSet(getTarget(eReference), null);
			else if (referencedObjectsList.size() == 0)
				copyEObject.eSet(getTarget(eReference), referencedObjectsList);
			else {
				for (Object referencedEObject : referencedObjectsList) {
					final Object copyReferencedEObject = get(referencedEObject);
					if (copyReferencedEObject != null)
						// The referenced object has been copied via this Copier
						((List<Object>)copyEObject.eGet(getTarget(eReference))).add(copyReferencedEObject);
					else if (mergeLinkedDiff((EObject)referencedEObject))
						// referenced object was an unmatched one and we managed to merge its corresponding
						// diff
						((List<Object>)copyEObject.eGet(getTarget(eReference))).add(get(referencedEObject));
					else
						((List<Object>)copyEObject.eGet(getTarget(eReference))).add(referencedEObject);
				}
			}
		} else {
			final Object referencedEObject = eObject.eGet(eReference, resolveProxies);
			if (referencedEObject == null)
				copyEObject.eSet(getTarget(eReference), null);
			else {
				final Object copyReferencedEObject = get(referencedEObject);
				if (copyReferencedEObject != null)
					// The referenced object has been copied via this Copier
					copyEObject.eSet(getTarget(eReference), copyReferencedEObject);
				else if (mergeLinkedDiff((EObject)referencedEObject))
					// referenced object was an unmatched one and we managed to merge its corresponding diff
					copyEObject.eSet(getTarget(eReference), get(referencedEObject));
				else
					copyEObject.eSet(getTarget(eReference), referencedEObject);
			}
		}
	}

	/**
	 * Copies the feature as a FeatureMap.
	 * 
	 * @param eObject
	 *            The EObject from which the feature is copied
	 * @param eStructuralFeature
	 *            The feature which needs to be copied.
	 */
	private void copyFeatureMap(EObject eObject, EStructuralFeature eStructuralFeature) {
		final FeatureMap featureMap = (FeatureMap)eObject.eGet(eStructuralFeature);
		final FeatureMap copyFeatureMap = (FeatureMap)get(eObject).eGet(getTarget(eStructuralFeature));
		int copyFeatureMapSize = copyFeatureMap.size();
		for (int k = 0; k < featureMap.size(); ++k) {
			final EStructuralFeature feature = featureMap.getEStructuralFeature(k);
			if (feature instanceof EReference) {
				final Object referencedEObject = featureMap.getValue(k);
				Object copyReferencedEObject = get(referencedEObject);
				if (copyReferencedEObject == null && referencedEObject != null) {
					final EReference reference = (EReference)feature;
					if (!useOriginalReferences || reference.isContainment()
							|| reference.getEOpposite() != null) {
						continue;
					}
					copyReferencedEObject = referencedEObject;
				}
				// If we can't add it, it must already be in the list so find it and move it
				// to the end.
				//
				if (!copyFeatureMap.add(feature, copyReferencedEObject)) {
					for (int l = 0; l < copyFeatureMapSize; ++l) {
						if (copyFeatureMap.getEStructuralFeature(l) == feature
								&& copyFeatureMap.getValue(l) == copyReferencedEObject) {
							copyFeatureMap.move(copyFeatureMap.size() - 1, l);
							--copyFeatureMapSize;
							break;
						}
					}
				}
			} else {
				copyFeatureMap.add(featureMap.get(k));
			}
		}
	}

	/**
	 * This will merge the DiffElement corresponding to the given element if it was an unmatched element.
	 * 
	 * @param element
	 *            The element we need a diff for.
	 * @return <code>True</code> if an element needed merging, <code>False</code> otherwise.
	 */
	private boolean mergeLinkedDiff(EObject element) {
		boolean hasMerged = false;
		// TODO We should probably cache the concerned differences, keep in mind some can be removed
		final TreeIterator<EObject> diffIterator = diffModel.eAllContents();
		while (diffIterator.hasNext()) {
			final EObject next = diffIterator.next();
			if (next instanceof ModelElementChangeLeftTarget) {
				if (((ModelElementChangeLeftTarget)next).getLeftElement() == element) {
					MergeService.merge((ModelElementChangeLeftTarget)next, true);
					hasMerged = true;
					break;
				}
			} else if (next instanceof ModelElementChangeRightTarget) {
				if (((ModelElementChangeRightTarget)next).getRightElement() == element) {
					MergeService.merge((ModelElementChangeRightTarget)next, false);
					hasMerged = true;
					break;
				}
			}
		}
		return hasMerged;
	}
}