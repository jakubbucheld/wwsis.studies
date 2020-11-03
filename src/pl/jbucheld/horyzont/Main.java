package pl.jbucheld.horyzont;

import pl.jbucheld.horyzont.tsp.Ant;
import pl.jbucheld.horyzont.tsp.Coordinates;
import pl.jbucheld.horyzont.tsp.Logics;
import pl.jbucheld.horyzont.tsp.MathematicalFunctions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    static Logics logics = new Logics();
    static MathematicalFunctions MF = new MathematicalFunctions();
    static DecimalFormat decimalFormat = new DecimalFormat("##.##");

    public static void main(String[] args)
    {
        List<Ant> listOfAnts = new ArrayList<>();
        List<Coordinates> cities = logics.generateCities(6);
        for (Coordinates c : cities)
        {
            System.out.println(c.toString());
        }

        Double[][] routes = logics.calculateDistances(cities);
        logics.printDistancesArray(routes);

        List<Ant> antsList = logics.createAnts(10);
        logics.setupAnts(antsList, cities);


    }

}
