/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.emf.compare.epatch;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Copy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.compare.epatch.ObjectCopy#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.emf.compare.epatch.ObjectCopy#getFragment <em>Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.compare.epatch.EpatchPackage#getObjectCopy()
 * @model
 * @generated
 */
public interface ObjectCopy extends CreatedObject
{
  /**
	 * Returns the value of the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' reference.
	 * @see #setResource(NamedResource)
	 * @see org.eclipse.emf.compare.epatch.EpatchPackage#getObjectCopy_Resource()
	 * @model
	 * @generated
	 */
  NamedResource getResource();

  /**
	 * Sets the value of the '{@link org.eclipse.emf.compare.epatch.ObjectCopy#getResource <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' reference.
	 * @see #getResource()
	 * @generated
	 */
  void setResource(NamedResource value);

  /**
	 * Returns the value of the '<em><b>Fragment</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fragment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragment</em>' attribute.
	 * @see #setFragment(String)
	 * @see org.eclipse.emf.compare.epatch.EpatchPackage#getObjectCopy_Fragment()
	 * @model
	 * @generated
	 */
  String getFragment();

  /**
	 * Sets the value of the '{@link org.eclipse.emf.compare.epatch.ObjectCopy#getFragment <em>Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fragment</em>' attribute.
	 * @see #getFragment()
	 * @generated
	 */
  void setFragment(String value);

} // ObjectCopy
