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
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.uml2.diff.UML2DiffEngine;
import org.eclipse.emf.compare.uml2.diff.internal.extension.AbstractDiffExtensionFactory;
import org.eclipse.emf.compare.uml2diff.UML2DiffFactory;
import org.eclipse.emf.compare.uml2diff.UMLIntervalConstraintChangeLeftTarget;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Interval;
import org.eclipse.uml2.uml.IntervalConstraint;
import org.eclipse.uml2.uml.ValueSpecification;

public class UMLIntervalConstraintChangeLeftTargetFactory extends AbstractDiffExtensionFactory {

	public UMLIntervalConstraintChangeLeftTargetFactory(UML2DiffEngine engine,
			EcoreUtil.CrossReferencer crossReferencer) {
		super(engine, crossReferencer);
	}

	public boolean handles(DiffElement input) {
		return input instanceof ModelElementChangeLeftTarget
				&& ((ModelElementChangeLeftTarget)input).getLeftElement() instanceof IntervalConstraint;
	}

	public AbstractDiffExtension create(DiffElement input) {
		ModelElementChangeLeftTarget changeLeftTarget = (ModelElementChangeLeftTarget)input;
		final IntervalConstraint intervalConstraint = (IntervalConstraint)changeLeftTarget.getLeftElement();

		UMLIntervalConstraintChangeLeftTarget ret = UML2DiffFactory.eINSTANCE
				.createUMLIntervalConstraintChangeLeftTarget();

		ValueSpecification valueSpecification = intervalConstraint.getSpecification();

		if (valueSpecification instanceof Interval) {
			ValueSpecification min = ((Interval)valueSpecification).getMin();
			hideCrossReferences(min, DiffPackage.Literals.MODEL_ELEMENT_CHANGE_LEFT_TARGET__LEFT_ELEMENT, ret);
			ValueSpecification max = ((Interval)valueSpecification).getMax();
			hideCrossReferences(max, DiffPackage.Literals.MODEL_ELEMENT_CHANGE_LEFT_TARGET__LEFT_ELEMENT, ret);
		}

		ret.getHideElements().add(changeLeftTarget);

		ret.setRemote(changeLeftTarget.isRemote());
		ret.setRightParent(changeLeftTarget.getRightParent());
		ret.setLeftElement(changeLeftTarget.getLeftElement());

		return ret;
	}
}
