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
   * Returns the children of the organization component.
   * @return the children of the organization component
   */
  List<OrganizationComponent> getChildren();
}
