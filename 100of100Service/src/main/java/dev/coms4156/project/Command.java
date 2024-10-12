package dev.coms4156.project;

/**
 * Interface for service logics.
 * Designed under the Command Design Pattern.
 */
public interface Command {

  /**
   * Executes the command.
   * @return The result of the command.
   */
  Object execute();
}
