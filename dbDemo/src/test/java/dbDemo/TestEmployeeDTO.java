package dbDemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

public class TestEmployeeDTO {
    private EmployeeDTO gateway;

    @Before
    public void setup() throws SQLException {
        gateway = new EmployeeDTO();
        gateway.createEmployeeTable();
    }

    @Test
    public void testCreateEmployeeTable() throws SQLException {

        Connection connection = gateway.getDBConnection();
        ResultSet rs = connection.getMetaData().getTables(null, null, "EMPLOYEE", null);

        assertTrue(rs.next());
    }

    @Test
    public void testSaveAndFindById() throws SQLException {
        Employee emp = new Employee();
        emp.setName("Alice");
        emp.setEmail("alice@example.com");
        emp.setAge(30);
        emp.setGender("Female");
        emp.setSalary(70000L);

        gateway.save(emp);

        Vector<Employee> all = gateway.findAll();
        assertEquals(1, all.size());

        Long id = all.get(0).getId();
        Employee found = gateway.findById(id);

        assertNotNull(found);
        assertEquals("Alice", found.getName());
    }

    @Test
    public void testUpdateEmployee() throws SQLException {
        Employee emp = new Employee();
        emp.setName("Pau");
        emp.setEmail("Pau@example.com");
        emp.setAge(40);
        emp.setGender("Female");
        emp.setSalary(1000L);

        gateway.save(emp);

        Long id = gateway.findAll().get(0).getId();

        emp.setId(id);
        emp.setName("Paulina");
        emp.setEmail("Paulina@example.com");
        emp.setAge(41);
        emp.setGender("Female");
        emp.setSalary(85000L);

        gateway.save(emp);

        Employee updated = gateway.findById(id);
        assertEquals("Paulina", updated.getName());
        assertEquals("Paulina@example.com", updated.getEmail());
    }

    @Test
    public void testUpdateNonExistentEmployee() throws SQLException {
        gateway.createEmployeeTable();

        Employee emp = new Employee();
        emp.setId(999L);
        emp.setName("Ghost");
        emp.setEmail("ghost@example.com");
        emp.setAge(100);
        emp.setGender("Male");
        emp.setSalary(0L);

        gateway.save(emp);

        Employee result = gateway.findById(999L);
        assertNull(result.getName());
    }

    @Test
    public void testDeleteEmployee() throws SQLException {
        Employee emp = new Employee();
        emp.setName("Charlie");
        emp.setEmail("charlie@example.com");
        emp.setAge(35);
        emp.setGender("Male");
        emp.setSalary(75000L);

        gateway.save(emp);
        Long id = gateway.findAll().get(0).getId();

        gateway.delete(id);

        Employee deleted = gateway.findById(id);
        assertNull(deleted.getName());
    }

    @Test
    public void testDeleteNonExistentEmployee() throws SQLException {
        gateway.createEmployeeTable();

        gateway.delete(999L);
        assertEquals(0, gateway.count());
    }

    @Test
    public void testFindAll() throws SQLException {
        for (int i = 0; i < 3; i++) {
            Employee emp = new Employee();
            emp.setName("Test" + i);
            emp.setEmail("test" + i + "@example.com");
            emp.setAge(25 + i);
            emp.setGender("Femail");
            emp.setSalary(60000L + i);
            gateway.save(emp);
        }

        Vector<Employee> all = gateway.findAll();
        assertEquals(3, all.size());
    }

    @Test
    public void testFindAllEmpty() throws SQLException {
        gateway.createEmployeeTable();

        Vector<Employee> employees = gateway.findAll();
        assertTrue(employees.isEmpty());
    }

    @Test
    public void testFindWithQuery() throws SQLException {
        Employee emp = new Employee();
        emp.setName("David");
        emp.setEmail("david@example.com");
        emp.setAge(29);
        emp.setGender("Male");
        emp.setSalary(72000L);
        gateway.save(emp);

        Set<Employee> found = gateway.find("NAME = 'David'");
        assertFalse(found.isEmpty());
    }

    @Test
    public void testFindWithNoMatch() throws SQLException {
        gateway.createEmployeeTable();

        Employee emp = new Employee();
        emp.setName("Mark");
        emp.setEmail("mark@example.com");
        emp.setAge(45);
        emp.setGender("Male");
        emp.setSalary(90000L);
        gateway.save(emp);

        Set<Employee> result = gateway.find("NAME = 'Nonexistent'");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCountEmployees() throws SQLException {
        EmployeeDTO gateway = new EmployeeDTO();
        gateway.createEmployeeTable();

        Employee emp = new Employee();
        emp.setName("Eva");
        emp.setEmail("eva@example.com");
        emp.setAge(28);
        emp.setGender("Female");
        emp.setSalary(65000L);
        gateway.save(emp);

        int count = gateway.count();
        assertEquals(1, count);
    }

    @Test
    public void testCountOnEmptyTable() throws SQLException {
        gateway.createEmployeeTable();

        int count = gateway.count();
        assertEquals(0, count);
    }

}
