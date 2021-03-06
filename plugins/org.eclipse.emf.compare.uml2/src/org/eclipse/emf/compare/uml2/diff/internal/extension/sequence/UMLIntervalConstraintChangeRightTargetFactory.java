/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.uml2.diff.internal.extension.sequence;

import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffPackage;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.compare.uml2.diff.UML2DiffEngine;
import org.eclipse.emf.compare.uml2.diff.internal.extension.AbstractDiffExtensionFactory;
import org.eclipse.emf.compare.uml2diff.UML2DiffFactory;
import org.eclipse.emf.compare.uml2diff.UMLExecutionSpecificationChangeRightTarget;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Interval;
import org.eclipse.uml2.uml.IntervalConstraint;
import org.eclipse.uml2.uml.ValueSpecification;

public class UMLIntervalConstraintChangeRightTargetFactory extends AbstractDiffExtensionFactory {

	public UMLIntervalConstraintChangeRightTargetFactory(UML2DiffEngine engine,
			EcoreUtil.CrossReferencer crossReferencer) {
		super(engine, crossReferencer);
	}

	public boolean handles(DiffElement input) {
		return input instanceof ModelElementChangeRightTarget
				&& ((ModelElementChangeRightTarget)input).getRightElement() instanceof IntervalConstraint;
	}

	public AbstractDiffExtension create(DiffElement input) {
		ModelElementChangeRightTarget changeRightTarget = (ModelElementChangeRightTarget)input;
		final IntervalConstraint intervalConstraint = (IntervalConstraint)changeRightTarget.getRightElement();

		UMLExecutionSpecificationChangeRightTarget ret = UML2DiffFactory.eINSTANCE
				.createUMLExecutionSpecificationChangeRightTarget();

		ValueSpecification valueSpecification = intervalConstraint.getSpecification();

		if (valueSpecification instanceof Interval) {
			ValueSpecification min = ((Interval)valueSpecification).getMin();
			hideCrossReferences(min, DiffPackage.Literals.MODEL_ELEMENT_CHANGE_RIGHT_TARGET__RIGHT_ELEMENT,
					ret);
			ValueSpecification max = ((Interval)valueSpecification).getMax();
			hideCrossReferences(max, DiffPackage.Literals.MODEL_ELEMENT_CHANGE_RIGHT_TARGET__RIGHT_ELEMENT,
					ret);
		}

		ret.getHideElements().add(changeRightTarget);

		ret.setRemote(changeRightTarget.isRemote());
		ret.setRightElement(changeRightTarget.getRightElement());
		ret.setLeftParent(changeRightTarget.getLeftParent());

		return ret;
	}
}
