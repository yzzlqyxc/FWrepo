package dev.coms4156.project;

public class TestDriver {
  public static void main(String[] args) {
    MysqlConnection conn = MysqlConnection.getInstance();
    conn.getEmployee(1, 2);
  }
}
