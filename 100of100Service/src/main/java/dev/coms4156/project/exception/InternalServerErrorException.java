package dev.coms4156.project.exception;

public class InternalServerErrorException extends RuntimeException {

  public InternalServerErrorException() {
    super("Internal server error.");
  }

  public InternalServerErrorException(String message) {
    super(message);
  }
}
