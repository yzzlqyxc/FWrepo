package dev.coms4156.project;

import java.util.ArrayList;
import java.util.List;

public abstract class OrganizationComposite implements OrganizationComponent {
  protected final HRDatabaseFacade db;
  protected final long id;
  protected final String name;
  protected final List<OrganizationComponent> children;

  /**
   * Constructs an organization composite with the given ID and name.
   * @param db the HR database facade
   * @param id the ID of the organization composite
   * @param name the name of the organization composite
   */
  public OrganizationComposite(HRDatabaseFacade db, long id, String name) {
    this.db = db;
    this.id = id;
    this.name = name;
    this.children = new ArrayList<>();
  }

  @Override
  public long getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<OrganizationComponent> getChildren() {
    return this.children;
  }

  /**
   * Adds a child component to the organization.
   * @param organizationComponent the child component to add
   * @return true if the child component was added, false otherwise
   */
  boolean add(OrganizationComponent organizationComponent) {
    return this.children.add(organizationComponent);
  }

  /**
   * Removes a child component from the organization.
   * @param organizationComponent the child component to remove
   * @return true if the child component was removed, false otherwise
   */
  boolean remove(OrganizationComponent organizationComponent) {
    return this.children.remove(organizationComponent);
  }

  /**
   * Returns the number of children of the organization component.
   * @return the number of children of the organization component
   */
  int getNumChildren() {
    return this.children.size();
  }

}
