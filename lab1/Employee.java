package com.mycompany.app;

import java.util.*;

public class Employee implements Comparable<Employee>{
    private String name;
    private String jobTitle;
    private String department;
    private int clearenceLevel;
    private int salary;
    private Set<Employee> subordinates;

    Employee(String name, String jobTitle, String department,int clearenceLevel, int salary, String sorting) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.department = department;
        this.salary = salary;
        this.clearenceLevel = clearenceLevel;

        switch (sorting) {
            case "without_sorting":
                // without sorting
                this.subordinates = new HashSet<>();
                break;
            case "natural_sorting":
                // sorting with natural order
                this.subordinates = new TreeSet<>();
                break;
            case "alternative_sorting":
                this.subordinates = new TreeSet<>(new AlternatywnyComparator());
                break;
            default:
                this.subordinates = new HashSet<>();
        }


    }

    public void print(int depth) {
        String odp = "";
        for (int i =0; i<depth; i++) {
            odp += "-";
        }
        odp += this.toString() + "\n";
        System.out.print(odp);
        if (!this.subordinates.isEmpty()) {
            for (Iterator<Employee> it = this.subordinates.iterator(); it.hasNext(); ) {
                Employee apprentice = it.next();
                apprentice.print(depth+1);
            }
        }
    }

    public void addsubordinates(Employee newEmployee) {
        subordinates.add(newEmployee);
    }

    public Map<String, Integer> getStatistics(String sorting){
        Map<String, Integer> statystyki;
        if(sorting == "without_sorting") {
            statystyki = new HashMap<>();
        }
        else {
            statystyki = new TreeMap<>();
        }
        int sum = 0;

        for (Employee subordinate : subordinates) {
            if (subordinate.subordinates.isEmpty()) {
                sum++;
                statystyki.put(subordinate.name, 0);
            }
            else {

                Map<String, Integer> hissubordinates = subordinate.getStatistics(sorting);

                sum ++;
                sum += hissubordinates.get(subordinate.name);
                statystyki.putAll(hissubordinates);
            }

        }

        statystyki.put(this.name, sum);
        return  statystyki;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", department='" + department + '\'' +
                ", clearenceLevel='" + clearenceLevel + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (clearenceLevel != employee.clearenceLevel) return false;
        if (salary != employee.salary) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (jobTitle != null ? !jobTitle.equals(employee.jobTitle) : employee.jobTitle != null) return false;
        if (department != null ? !department.equals(employee.department) : employee.department != null) return false;
        return subordinates != null ? subordinates.equals(employee.subordinates) : employee.subordinates == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + clearenceLevel;
        result = 31 * result + salary;
        result = 31 * result + (subordinates != null ? subordinates.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Employee employee) {
        int result = this.name.compareTo(employee.name);
        if (result != 0) {
            return result;
        }
        result = this.jobTitle.compareTo(employee.jobTitle);
        if (result != 0) {
            return result;
        }
        result = this.department.compareTo(employee.department);
        if (result != 0) {
            return result;
        }
        result = this.clearenceLevel - employee.clearenceLevel;
        if (result != 0) {
            return result;
        }
        result = this.salary - employee.salary;

        return result;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getClearenceLevel() {
        return  clearenceLevel;
    }

}

