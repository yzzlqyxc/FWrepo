package dev.coms4156.project.command;

import dev.coms4156.project.Organization;
import dev.coms4156.project.HRDatabaseFacade;

public class GetOrganizationInfoCommand implements Command {
  private final int clientId;

  public GetOrganizationInfoCommand(int clientId) {
    this.clientId = clientId;
  }

  @Override
  public String execute() {
    HRDatabaseFacade db = HRDatabaseFacade.getInstance(this.clientId);
    Organization organization = db.getOrganization();
    return Organization.displayStructure(organization, 0);
  }
}