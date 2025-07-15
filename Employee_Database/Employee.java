package Project_2241016145;

public class Employee {
    protected String name;
    protected int empId;
    protected double salary;
    protected Date hireDate;
    protected String jobPosition;
    protected String contactNumber;
    protected Address address;

    Employee(String name, int empId, double salary, Date hiredate, String jobPosition, String contactNumber, Address address) {
        this.name = name;
        this.empId = empId;
        this.salary = salary;
        this.hireDate = hiredate;
        this.jobPosition = jobPosition;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    protected void getData(){
        System.out.println();
        System.out.println("Employee Name : "+name);
        System.out.println("Employee ID : "+empId);
        System.out.println("Salary : "+salary);
        System.out.println("Hire Date : "+hireDate.getDate());
        System.out.println("Job Position : "+jobPosition);
        System.out.println("Contact Number : "+contactNumber);
        System.out.print("Address : "+address.getAddress());
        System.out.println();
    }
}


