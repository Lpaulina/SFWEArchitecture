package dbDemo;

public class Employee {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String gender;
    private Long salary;

    public Employee() {
        super();
    }

    public Employee(String name, String email, Integer age, String gender, Long salary) {
        super();
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return "Employee [id= " + id + ", name= " + name + ", email=" + email + ", age=" + age + ", salary=" + salary
                + "]";
    }
}
