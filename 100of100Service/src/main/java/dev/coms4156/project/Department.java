package dev.coms4156.project;

/**
 * This class represents a department in the organization.
 * Designed under the Composite Design Pattern.
 */
public class Department extends OrganizationComposite {
  private Employee head;

  /**
   * Constructs a department with the given ID and name.
   * @param db the HR database facade that manages the department
   * @param id the ID of the department
   * @param name the name of the department
   */
  public Department(HRDatabaseFacade db, long id, String name) {
    super(db, id, name);
    this.head = null;
  }

  /**
   * Returns the head of the department.
   * @return the head of the department
   */
  public Employee getHead() {
    return this.head;
  }

  /**
   * Sets the head of the department.
   * @param head the head of the department
   * @return true if the validation passes, false otherwise
   */
  public boolean setHead(Employee head) {
    // TODO: Any validation? Manager must in the department?
    this.head = head;
    this.db.updateDepartment(this); // Update the department information to the database
    return true;
  }

  /**
   * Returns the basic information of the Department, including the name, ID, and head.
   * @return the string representation of the department
   */
  @Override
  public String toString() {
    if (this.head != null) {
      return "Department: " + this.name + " (ID: " + this.id + ") Head: " + this.head.getName();
    } else {
      return "Department: " + this.name + " (ID: " + this.id + ")";
    }
  }
}
