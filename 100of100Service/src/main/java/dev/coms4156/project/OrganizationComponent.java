package dev.coms4156.project;

import java.util.List;

/**
 * This interface is used to represent an organization component.
 * Designed under the Composite Design Pattern.
 */
public interface OrganizationComponent {

  /**
   * Returns the ID of the organization component.
   * @return the ID of the organization component
   */
  long getId();

  /**
   * Returns the name of the organization component.
   * @return the name of the organization component
   */
  String getName();

  /**
   * Adds a child component to the organization.
   * @param organizationComponent the child component to add
   * @return true if the child component was added, false otherwise
   */
  boolean add(OrganizationComponent organizationComponent);

  /**
   * Removes a child component from the organization.
   * @param organizationComponent the child component to remove
   * @return true if the child component was removed, false otherwise
   */
  boolean remove(OrganizationComponent organizationComponent);

  /**
   * Returns the number of children of the organization component.
   * @return the number of children of the organization component
   */
  int getNumChildren();

  /**
   * Returns the children of the organization component.
   * @return the children of the organization component
   */
  List<OrganizationComponent> getChildren();
}
