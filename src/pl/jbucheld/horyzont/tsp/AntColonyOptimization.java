package pl.jbucheld.horyzont.tsp;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.IntStream;

public class AntColonyOptimization
{
    static Logics logics = new Logics();
    static MathematicalFunctions MF = new MathematicalFunctions();
    static DecimalFormat decimalFormat = new DecimalFormat("##.##");

    private double alpha = 1;
    private double beta = 5;
    private int numberOfCities = 5;
    private int numberOfAnts = 10;
    private int currentIndex;
    private double Q = 500;
    private double evaporation = 0.5;
    private int maxIterations = 100;
    List<Coordinates> bestTour;

    private List<Ant> listOfAnts = new ArrayList<>();
//    private List<Coordinates> cities = logics.generateCities(numberOfCities);
    private List<Coordinates> cities = logics.inputCities(numberOfCities);
    private Double[][] routes = logics.calculateDistances(cities);
    private Double[][] pheromones = logics.generatePheromoneArray(cities);
    private List<Ant> antsList = logics.createAnts(numberOfAnts);


    public List<Coordinates> solve()
    {
        setupAnts();
        pheromones = logics.generatePheromoneArray(cities);
        IntStream.range(0, maxIterations)
                .forEach(i -> {
                    moveAnts();
                    evaporatePheromones();
                    bestTour = List.copyOf(updateBestResult());
                });
        System.out.println("Najkrótsza trasa wiedzie przez ::");
        for (Coordinates c:bestTour)
        {
            System.out.println(c.toString());
        }

        System.out.println("Najkrótsza trasa wynosi :: " + calculateFinalDistance(bestTour));
        return bestTour;
    }

    private double calculateTotalDistance(Ant ant)
    {
        double total = 0.0;
        for (Coordinates c : ant.getVisited())
        {
            if (!(ant.getVisited().indexOf(c) == 0))
            {
                total += MF.calculateEuclidianDistance(c, ant.getVisited().get(ant.getVisited().indexOf(c)-1));
            }
        }
        total += MF.calculateEuclidianDistance(ant.getVisited().get(0), ant.getVisited().get(ant.getVisited().size()-1));
        return total;
    }

    private List<Coordinates> updateBestResult()
    {
        Map<Ant, Double> totalDistances = new HashMap<>();
        for (Ant a: antsList)
        {
            totalDistances.put(a, calculateTotalDistance(a));
        }
        return List.copyOf(totalDistances.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey().getVisited());
    }

    public void getStartupInfo()
    {
        for (Coordinates c : cities) System.out.println(c.toString());
        logics.printArray(routes);
        logics.printArray(pheromones);
    }

    public void setupAnts()
    {
        logics.setupAnts(antsList, cities);
//        logics.nonRandomSetupAnts(antsList, cities);
        for (Ant a:antsList) System.out.println("Mrówka " +
                antsList.indexOf(a) +
                " zaczyna w mieście " +
                a.currentPosition());
        currentIndex = 0;
    }

    public Coordinates selectNextCity(Ant ant)
    {
        Map<Coordinates, Double> probabilitiesOfPicking = new HashMap<>();

//        System.out.println("Mrówka " + antsList.indexOf(ant) +  " odwiedziła :: ");
//        for (Coordinates print:ant.getVisited()) System.out.println(print.toString());

        for (Coordinates c:cities)
        {
            if(ant.ifVisited(c)) probabilitiesOfPicking.put(c, 0.0);

            else if (!ant.ifVisited(c))
            {
                double probability = Math.pow(pheromones[cities.indexOf(ant.currentPosition())][cities.indexOf(c)], alpha)
                                    + Math.pow(MF.reciprocal(MF.calculateEuclidianDistance(c, ant.currentPosition())), beta);
                probabilitiesOfPicking.put(c, probability);
            }
        }
        return probabilitiesOfPicking.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
    }

    public void moveAnts()
    {
        IntStream.range(currentIndex, numberOfCities-1)
                .forEach(i ->
                {
                    for (Ant ant:antsList)
                    {
                        Coordinates nextCity = selectNextCity(ant);
                        ant.visitCity(nextCity);
                        updatePheromoneOnTrail(ant, nextCity);
                    }
                    currentIndex++;
                });
    }


    public void updatePheromoneOnTrail(Ant ant,
                                Coordinates targetCity)
    {
        pheromones[cities.indexOf(ant.currentPosition())][cities.indexOf(targetCity)]
                += Q / MF.calculateEuclidianDistance(ant.currentPosition(), targetCity);
    }

    public void evaporatePheromones()
    {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] *= evaporation;
            }
        }
    }

    public double calculateFinalDistance(List<Coordinates> route)
    {
        double total = 0.0;
        for (Coordinates c : route)
        {
            if (!(route.indexOf(c) == 0))
            {
                total += MF.calculateEuclidianDistance(c, route.get(route.indexOf(c)-1));
            }
        }
        total += MF.calculateEuclidianDistance(route.get(0), route.get(route.size()-1));
        return total;
    }

    public void getNaiveResult()
    {
        System.out.println("Rozwiązanie 'naiwne' problemu :: ");
        System.out.println("Naiwna trasa wiedzie przez wszyskie punkty po kolei :");
        for (Coordinates c: cities) System.out.println("- " + c);
        System.out.println("Długość naiwnej trasy wynosi :" + calculateFinalDistance(cities));
    }
}
