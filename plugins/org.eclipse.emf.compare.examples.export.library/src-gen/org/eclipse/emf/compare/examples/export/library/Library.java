/*******************************************************************************
 * Copyright (c) 2007, 2009 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.examples.export.library;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Library</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.compare.examples.export.library.Library#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.emf.compare.examples.export.library.Library#getBooks <em>Books</em>}</li>
 * <li>{@link org.eclipse.emf.compare.examples.export.library.Library#getWriters <em>Writers</em>}</li>
 * <li>{@link org.eclipse.emf.compare.examples.export.library.Library#getMembers <em>Members</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.emf.compare.examples.export.library.LibraryPackage#getLibrary()
 * @model
 * @generated
 */
public interface Library extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.emf.compare.examples.export.library.LibraryPackage#getLibrary_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.compare.examples.export.library.Library#getName
	 * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Books</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.emf.compare.examples.export.library.Book}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Books</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Books</em>' containment reference list.
	 * @see org.eclipse.emf.compare.examples.export.library.LibraryPackage#getLibrary_Books()
	 * @model containment="true"
	 * @generated
	 */
	EList<Book> getBooks();

	/**
	 * Returns the value of the '<em><b>Writers</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.emf.compare.examples.export.library.Writer}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Writers</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Writers</em>' containment reference list.
	 * @see org.eclipse.emf.compare.examples.export.library.LibraryPackage#getLibrary_Writers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Writer> getWriters();

	/**
	 * Returns the value of the '<em><b>Members</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.emf.compare.examples.export.library.Member}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Members</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Members</em>' containment reference list.
	 * @see org.eclipse.emf.compare.examples.export.library.LibraryPackage#getLibrary_Members()
	 * @model containment="true"
	 * @generated
	 */
	EList<Member> getMembers();

} // Library
