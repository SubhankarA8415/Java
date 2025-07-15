package Project_2241016145;

import java.util.*;

public class Test extends Data {
    public static void arrangeEmployeeBySalary(Employee e[]) {
        double arr[] = new double[500];
        for (int i = 0; i < 500; i++) {
            arr[i] = e[i].salary;
        }
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500 - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("Arranging employees by salary in descending order -: ");
        System.out.println();
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                if (arr[i] == e[j].salary) {
                    e[j].getData();
                    break;
                }
            }
        }

    }

    public static void getEmployeesByJobPosition(Employee e[], String jp) {
        for (int i = 0; i < 500; i++) {
            if (e[i].jobPosition.equalsIgnoreCase(jp)) {
                e[i].getData();
            }
        }
    }

    public static void getEmployeesByHireDate(Employee e[], Date d1, Date d2) {
        System.out.println("Details of employee  within the given dates range -: ");
        String d10 = d1.getDate();
        String d20 = d2.getDate();
        int day1 = Integer.parseInt(d10.substring(0, 2));
        int day2 = Integer.parseInt(d20.substring(0, 2));
        int month1 = Integer.parseInt(d10.substring(3, 5));
        int month2 = Integer.parseInt(d20.substring(3, 5));
        int year1 = Integer.parseInt(d10.substring(6, 10));
        int year2 = Integer.parseInt(d20.substring(6, 10));
        String d30 = "";
        for (int i = 0; i < 500; i++) {
            d30 = e[i].hireDate.getDate();
            int day3 = Integer.parseInt(d30.substring(0, 2));
            int month3 = Integer.parseInt(d30.substring(3, 5));
            int year3 = Integer.parseInt(d30.substring(6, 10));
            if (year2 > year3 && year1 < year3) {
                e[i].getData();
            } else if (year1 == year3) {
                if (month1 < month3) {
                    e[i].getData();
                } else if (month1 == month3) {
                    if (day3 > day1) {
                        e[i].getData();
                    }
                }
            } else if (year3 == year2) {
                if (month2 > month3) {
                    e[i].getData();
                } else if (month2 == month3) {
                    if (day2 > day3)
                        e[i].getData();
                }
            }
        }
    }

    public static int foreignEmployeeCount(Employee e[]) {
        int count = 0;
        for (int i = 0; i < 500; i++) {
            String add = e[i].address.getAddress();
            if (add.endsWith("India")) {
                continue;
            } else {
                count++;
            }
        }

        return count;
    }

    public static void getEmployeesBySalary(Employee e[], double s1, double s2) {
        for (int i = 0; i < 500; i++) {
            if (e[i].salary > s1 && e[i].salary < s2) {
                e[i].getData();
            }
        }
    }


    public static void main(String[] args) {
        Employee e[] = new Employee[500];
        Address a[] = new Address[500];
        Date d[] = new Date[500];
        Random c = new Random();
        int c1 = c.nextInt(350, 421);
        // Data entry
        for (int i = 0; i < 500; i++) {
            Random ran = new Random();
            int date = ran.nextInt(1, 32);
            int month = ran.nextInt(1, 13);
            int year = ran.nextInt(2000, 2024);
            int salary = 0;
            int r = (int) (Math.random() * 50);
            a[i] = new Address(addressList[r]);
            d[i] = new Date(date, month, year);
            r = (int) (Math.random() * 10);
            String jobPos = jobPosition[r];
            if (jobPos.endsWith("Manager")) {
                salary = ran.nextInt(250000, 750001);
            } else if (jobPos.equals("Senior Engineer")) {
                salary = ran.nextInt(150000, 400001);
            } else if (jobPos.equals("Junior Engineer")) {
                salary = ran.nextInt(25000, 120001);
            } else if (jobPos.equals("Data Scientist")) {
                salary = ran.nextInt(200000, 500001);
            } else if (jobPos.equals("Data Analyst")) {
                salary = ran.nextInt(40000, 150001);
            } else if (jobPos.equals("Web Developer")) {
                salary = ran.nextInt(100000, 400001);
            } else {
                salary = ran.nextInt(100000, 250001);
            }
            r = ran.nextInt(0, 50);
            String contact = "" + ((ran.nextLong(3, 7) * 1000000000) + (ran.nextLong(123456789, 999999999)));
            if (i >= c1) {
                r = ran.nextInt(50, 80);
                a[i] = new Address((addressList[r]));
                r = ran.nextInt(50, 100);
                contact = "" + ((ran.nextLong(6, 10) * 1000000000) + (ran.nextLong(123456789, 999999999)));
            }
            e[i] = new Employee(nameList[r], i + 1, salary, d[i], jobPos, contact, a[i]);
            //e[i].getData();
        }
        // Database execution
        Scanner sc = new Scanner(System.in);
        int p = 1;
        int i = 5;
        System.out.println();
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ Welcome to Employee Database ///////////////////////////////////////////////////////////////////////");
        while (true) {
            System.out.println();
            System.out.println("Enter admin password ");
            System.out.print("Example : Password is -: Admin@123 -: ");
            if (sc.next().equals("Admin@123")) {
                System.out.println("Password entered is correct data is now accessible");
                System.out.println();
                break;
            } else {
                i--;
                System.out.println("Incorrect Password try again " + i + " tries left !!");
                System.out.println();
            }
            if (i == 0) {
                System.out.println();
                System.out.println("Suspicious activity detected !!");
                System.out.println("Database locked !!");
                System.out.println();
                p = 0;
                break;
            }
        }

        while (p != 0) {
            System.out.println("Select operations you want to proceed with \n" +
                    "1 -> Display details of all employees int the database \n" +
                    "2 -> Get employees details arranged in descending order by salary \n" +
                    "3 -> Get employees details through their job position \n" +
                    "4 -> Get employees details through their hiring date \n" +
                    "5 -> Get count of foreign employees \n" +
                    "6 -> Get employees details through salary range \n" +
                    "7 -> Exit database -: ");
            String s = sc.next();
            int s1 = 0;
            try {
                s1 = Integer.parseInt(s);
            } catch (NumberFormatException e1) {
                System.out.println("Invalid choice");
                System.out.println("Try again");
                System.out.println();
                continue;
            }
            if (s1 == 1) {
                System.out.println("Details of all employees");
                for (int j = 0; j < 500; j++) {
                    e[j].getData();
                }
                System.out.println();
            }
            else if(s1 == 2){
                System.out.println("Details of all employees in descending order of their salary");
                arrangeEmployeeBySalary(e);
                System.out.println();
            }
            else if(s1 == 3){
                System.out.print("Enter employee's job position to get details -: ");
                sc.nextLine();
                String pos = sc.nextLine();
                int count = 0;
                for(int j = 0; j<500; j++) {
                    if (e[j].jobPosition.equalsIgnoreCase(pos)){
                        e[j].getData();
                        count++;
                    }
                }
                if (count == 0){
                    System.out.println("No such data available with job position as "+pos);
                    System.out.println();
                }
            } else if (s1 == 4) {
                System.out.println("Enter employee's hiring date range to get details -: \n" +
                        "Enter starting hiring date range (format of date -: dd-mm-yyyy) -: ");
                String d1s = sc.next();
                int d1d = Integer.parseInt(d1s.substring(0,2));
                int d1m = Integer.parseInt(d1s.substring(3,5));
                int d1y = Integer.parseInt(d1s.substring(6,10));
                Date d1 = new Date(d1d,d1m,d1y);
                System.out.print("Enter ending hiring date range -: ");
                String d2s = sc.next();
                int d2d = Integer.parseInt(d2s.substring(0,2));
                int d2m = Integer.parseInt(d2s.substring(3,5));
                int d2y = Integer.parseInt(d2s.substring(6,10));
                Date d2 = new Date(d2d,d2m,d2y);
                System.out.println();
                getEmployeesByHireDate(e,d1,d2);
                System.out.println();

            }
            else if (s1 == 5){
                System.out.println("Number of foreign Employees working with us -: "+foreignEmployeeCount(e));
                System.out.println();
            }
            else if (s1 == 6) {
                while (true) {
                    System.out.println("Enter salary range to get details of employees \n" +
                            "Enter starting range salary -: ");
                    String sal10 = sc.next();
                    double sal1;
                    try {
                        sal1 = Double.parseDouble(sal10);
                    } catch (NumberFormatException e1) {
                        System.out.println("Invalid format entered");
                        System.out.println("Try again");
                        System.out.println();
                        continue;
                    }
                    System.out.print("Enter ending salary range -: ");
                    String sal20 = sc.next();
                    double sal2;
                    try {
                        sal2 = Double.parseDouble(sal20);
                    } catch (NumberFormatException e1) {
                        System.out.println("Invalid format entered");
                        System.out.println("Try again");
                        System.out.println();
                        continue;
                    }
                    System.out.println("Details of employees within the salary range -: ");
                    getEmployeesBySalary(e, sal1, sal2);
                    break;
                }
            }
            else if (s1 == 7){
                System.out.println("Code by -: Subhankar Pandit");
                System.out.println("Registration number -: 2241016145");
                System.out.println("Section -: 2241022");
                System.out.println("Branch -: BTech CSE");
                System.out.println("Database Terminated successfully");
                break;
            }
            else {
                System.out.println("Choice invalid !! ");
                System.out.println("Try again");
                continue;
            }
        }
    }
}