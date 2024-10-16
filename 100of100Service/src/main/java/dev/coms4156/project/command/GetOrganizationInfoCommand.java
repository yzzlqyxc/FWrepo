package dev.coms4156.project.command;

import dev.coms4156.project.HrDatabaseFacade;
import dev.coms4156.project.Organization;

/**
 * A command to get the information of an organization.
 */
public class GetOrganizationInfoCommand implements Command {
  private final int clientId;

  public GetOrganizationInfoCommand(int clientId) {
    this.clientId = clientId;
  }

  @Override
  public String execute() {
    HrDatabaseFacade db = HrDatabaseFacade.getInstance(this.clientId);
    Organization organization = db.getOrganization();
    return Organization.displayStructure(organization, 0);
  }
}