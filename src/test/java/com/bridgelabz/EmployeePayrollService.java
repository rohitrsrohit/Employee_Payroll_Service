package com.bridgelabz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    public void printData(IOService fileIo) {
        try {
            Files.lines(new File("payroll-file.txt").toPath()).forEach(System.out::println);
        } catch (IOException e) { }
    }

    public long countEntries(IOService fileIo) {
        long entries = 0;
        try {
            entries = Files.lines(new File("payroll-file.txt").toPath()).count();
        } catch (IOException e) { }
        return entries;
    }

    public enum IOService {CONSOLE_IO, FILE_IO,DB_IO, REST_IO}
    private List<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService() {}
    public EmployeePayrollService(List<EmployeePayrollData>
                                          employeePayrollList) {}

    public static void main(String [] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);
    }



    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee Name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Employee Payroll Roster to console\n" + employeePayrollList);
        else if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().writeData(employeePayrollList);
        }
    }
}