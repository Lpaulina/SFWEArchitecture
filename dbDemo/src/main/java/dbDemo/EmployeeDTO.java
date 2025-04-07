package dbDemo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class EmployeeDTO {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:employee;create=true";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private Statement statement;
    private Connection dbConnection;

    public void createEmployeeTable() throws SQLException {
        dbConnection = null;
        statement = null;

        String createTableSQL = "CREATE TABLE EMPLOYEE(" +
                "ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                "NAME VARCHAR(255) NOT NULL, " +
                "EMAIL VARCHAR(255) NOT NULL, " +
                "AGE INT NOT NULL, " +
                "GENDER VARCHAR(255) NOT NULL," +
                "SALARY BIGINT NOT NULL" +
                ")";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            DatabaseMetaData dbMeta = dbConnection.getMetaData();
            ResultSet tables = dbMeta.getTables(null, null, "EMPLOYEE", null);

            if (tables.next()) {
                statement.execute("Drop table EMPLOYEE");
            }
            tables.close();
            statement.execute(createTableSQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (dbConnection != null)
                dbConnection.close();
        }
    }

    public Connection getDBConnection() {

        System.out.println("Derby JDBC Driver Registered!");
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Employee e) throws SQLException {
        dbConnection = null;
        statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            if (e.getId() != null) {
                String query = "UPDATE EMPLOYEE SET " +
                        "NAME = '" + e.getName() + "', " +
                        "EMAIL = '" + e.getEmail() + "', " +
                        "AGE = " + e.getAge() + ", " +
                        "GENDER = '" + e.getGender() + "', " +
                        "SALARY = " + e.getSalary() +
                        " WHERE ID = " + e.getId();

                statement.executeUpdate(query);
                System.out.println("Employee updated");
            } else {
                statement.executeUpdate(
                        "INSERT INTO EMPLOYEE (NAME, EMAIL, AGE, GENDER, SALARY) VALUES (" +
                                "'" + e.getName() + "', " +
                                "'" + e.getEmail() + "', " +
                                e.getAge() + ", " +
                                "'" + e.getGender() + "', " +
                                e.getSalary() +
                                ")");
                System.out.println("Employee created");
            }
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }

    public Employee findById(Long id) throws SQLException {
        dbConnection = null;
        statement = null;

        Employee e = new Employee();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            String select = "SELECT ID, NAME, EMAIL, AGE, GENDER FROM EMPLOYEE WHERE ID=" + id;

            ResultSet rs = statement.executeQuery(select);

            if (rs.next()) {
                e.setId(rs.getLong("ID"));
                e.setName(rs.getString("NAME"));
                e.setEmail(rs.getString("EMAIL"));
                e.setAge(rs.getInt("AGE"));
                e.setGender(rs.getString("GENDER"));
                e.setSalary(rs.getLong("SALARY"));
                System.out.println("Employee found");
            } else {
                System.out.println("Employee not found");
            }
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return e;
    }

    public void delete(Long id) throws SQLException {
        dbConnection = null;
        statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            statement.executeUpdate("DELETE FROM EMPLOYEE WHERE ID = " + id);
            System.out.println("Employee removed");
        } catch (Exception error) {
            error.printStackTrace();
            System.out.println("Error, employee not removed");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public Vector<Employee> findAll() throws SQLException {
        dbConnection = null;
        statement = null;

        Vector<Employee> employees = new Vector<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT ID, NAME, EMAIL," + "AGE, GENDER, SALARY FROM EMPLOYEE");

            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getLong("ID"));
                e.setName(rs.getString("NAME"));
                e.setEmail(rs.getString("EMAIL"));
                e.setAge(rs.getInt("AGE"));
                e.setGender(rs.getString("GENDER"));
                e.setSalary(rs.getLong("SALARY"));
                employees.add(e);
            }

            System.out.println("Found employees");

        } catch (Exception error) {
            error.printStackTrace();
            System.out.println("Error, could not find all employees");
        }
        return employees;
    }

    public int count() throws SQLException {
        dbConnection = null;
        statement = null;
        Integer count = 0;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println("Employees counted");
            ResultSet rs = statement.executeQuery("SELECT COUNT(DISTINCT ID) AS total FROM EMPLOYEE");

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return count;
    }

    public Set<Employee> find(String query) throws SQLException {
        dbConnection = null;
        statement = null;

        Set<Employee> employees = new HashSet<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            ResultSet rs = statement
                    .executeQuery("SELECT ID, NAME, EMAIL, AGE, GENDER, SALARY FROM EMPLOYEE WHERE " + query);

            if (rs != null) {
                while (rs.next()) {
                    Employee e = new Employee();
                    e.setId(rs.getLong("ID"));
                    e.setName(rs.getString("NAME"));
                    e.setEmail(rs.getString("EMAIL"));
                    e.setAge(rs.getInt("AGE"));
                    e.setGender(rs.getString("GENDER"));
                    e.setSalary(rs.getLong("SALARY"));
                    employees.add(e);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return employees;
    }

}
