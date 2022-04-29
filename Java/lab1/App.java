package com.mycompany.app;

import java.io.File;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String sorting = args[0];

        Employee Tomasz = new Employee("Tomasz", "Profesor", "Sytemy wbudowane", 6, 1234, sorting );
        Tomasz.addsubordinates(new Employee("Jan", "Doktor", "Sytemy wbudowane", 2, 20, sorting));

        Employee Piotr = new Employee("Piotr", "Magister", "Bazy danych", 6, 312, sorting);
        Employee Gabriel = new Employee("Piotr", "Magister", "Bazy danych", 6, 312, sorting);
        Gabriel.addsubordinates(new Employee("Aleksandra", "Mechanik", "Sytemy wbudowane", 1, 490,sorting));

        Piotr.addsubordinates(Gabriel);
        Piotr.addsubordinates(new Employee("Mikołaj", "Magister", "Bazy danych", 3, 500, sorting));
        Tomasz.addsubordinates(Piotr);

        Tomasz.addsubordinates(new Employee("Marek", "Profesor", "Sytemy wbudowane", 2, 500, sorting));
        Tomasz.addsubordinates(new Employee("Filip", "Inżynier", "Bazy danych", 3, 20, sorting));
        Employee Krzyszof = new Employee("Krzysztof", "Magister", "Bazy danych", 1, 12, sorting);
        Krzyszof.addsubordinates(new Employee("Zmuda", "Inżynier", "Księgowość", 4, 1233, sorting));
        Tomasz.addsubordinates(Krzyszof);
        Tomasz.addsubordinates(new Employee("Dominik", "Magister", "Księgowość", 2, 12333, sorting));
        Tomasz.addsubordinates(new Employee("Marcin", "Inżynier", "Sytemy wbudowane", 6, 756, sorting));

        Tomasz.print(1);

        Map<String, Integer> stats = Tomasz.getStatistics(sorting);
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        //System.out.println(directory.listFiles());
    }
}
