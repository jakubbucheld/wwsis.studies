package pl.jbucheld.horyzont.genetic;

import java.util.*;

public class Main
{
    private static final Logics logics = new Logics();

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int targetBinaryWordLength = 5;
        int amountOfRecords = 6;
        final AlgorithmConfigData currentConfigData
                = new AlgorithmConfigData(2,0,-2, 3,0.8,0.2,3,3,5, 6);
        GeneticAlgorithm algorithm = new GeneticAlgorithm(currentConfigData);

        Map<Integer, Integer> startingPopulation = logics.randomizeMapOfIntegerPopulation(32, amountOfRecords);
//        Map<Integer, Integer> functionValuesMap = logics.calculateFunctionValuesMapBasedOnPopulationMap(startingPopulation, currentConfigData);

        System.out.println("-> STARTING POPULATION: ");
        for (Map.Entry<Integer, Integer> entry : startingPopulation.entrySet()) System.out.println(entry);
//        for (int i = 1; i <= startingPopulation.size(); i++)
//        {
//            System.out.println("Entry :: " + "index (" + i + ") "  + startingPopulation.get(i)
//                    + ", binary :: " + logics.convertIntegerToBinaryString(startingPopulation.get(i), targetBinaryWordLength)
//                    + ", calculated function value :: " + logics.calculateFunctionOnGivenX(currentConfigData, startingPopulation.get(i)));
////                    + ", mapped function value ;; " + functionValuesMap.get(i));
//        }

//        System.out.println("Randomized entry: >>> " + logics.roulettePickKeyOnGivenGeneration(startingPopulation, currentConfigData));
//        for (Map.Entry<Integer, Integer> entry : startingPopulation.entrySet()) System.out.println(entry);
        System.out.println("TPFV :: " + logics.totalPopulationFunctionValue(logics.calculateFunctionValuesMapBasedOnPopulationMap(startingPopulation, currentConfigData)));

        Map<Integer, Integer> offspring = algorithm.getNextGeneration(startingPopulation);
        System.out.println("TPFV :: " + logics.totalPopulationFunctionValue(logics.calculateFunctionValuesMapBasedOnPopulationMap(offspring, currentConfigData)));

        Map<Integer, Integer> offspring2 = algorithm.getNextGeneration(offspring);
        System.out.println("TPFV :: " + logics.totalPopulationFunctionValue(logics.calculateFunctionValuesMapBasedOnPopulationMap(offspring2, currentConfigData)));


    }
}
