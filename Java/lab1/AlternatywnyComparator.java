package com.mycompany.app;

import java.util.Comparator;

public class AlternatywnyComparator implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {
        int result = o1.getClearenceLevel() - o2.getClearenceLevel();
        if (result != 0) {
            return result;
        }
        result = o1.getName().compareTo(o2.getName());
        if (result != 0) {
            return result;
        }
        result = o1.getJobTitle().compareTo(o2.getJobTitle());
        if (result != 0) {
            return result;
        }
        result = o1.getDepartment().compareTo(o2.getDepartment());
        if (result != 0) {
            return result;
        }

        result = o1.getSalary() - o2.getSalary();

        return result;
    }
}
