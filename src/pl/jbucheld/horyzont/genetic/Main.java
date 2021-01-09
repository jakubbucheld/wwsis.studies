package pl.jbucheld.horyzont.genetic;

import java.util.*;

public class Main
{
    private static final Logics logics = new Logics();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> bestGeneration = new HashMap<>();
        int bestGenerationCounter = 0;
        int algorithmIterator = 0;

        final AlgorithmConfigData currentConfigData
                = new AlgorithmConfigData(2, 0, -2, 3, 0.8, 0.2, 10, 10, 5, 6);
        GeneticAlgorithm algorithm = new GeneticAlgorithm(currentConfigData);
        List<Map<Integer, Integer>> generationHistory = new ArrayList<>();

        Map<Integer, Integer> startingPopulation = logics.randomizeMapOfIntegerPopulation(32, currentConfigData.getAmountOfRecords());
        generationHistory.add(startingPopulation);
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

        do
        {
            System.out.println("-> Getting new generation.. " + generationHistory.size());
            algorithm.getNextGeneration(generationHistory);
            int currentGenerationValue = logics.totalPopulationFunctionValue(logics.calculateFunctionValuesMapBasedOnPopulationMap(generationHistory.get(generationHistory.size()-1), currentConfigData));
            int bestGenerationValue = logics.totalPopulationFunctionValue(logics.calculateFunctionValuesMapBasedOnPopulationMap(bestGeneration, currentConfigData));

            if(currentGenerationValue > bestGenerationValue)
            {
                bestGeneration.clear();
                bestGeneration = generationHistory.get(generationHistory.size()-1);
                bestGenerationCounter=0;
            }
            else if (bestGenerationValue == currentGenerationValue)
            {
                bestGenerationCounter++;
            }
        algorithmIterator++;

        } while (algorithmIterator<currentConfigData.getRepeatingLimit() && bestGenerationCounter<=currentConfigData.getRepeatingLimit());

        System.out.println("<> NAJLEPSZA GENERACJA :: ");
        System.out.println(bestGeneration);
        System.out.println("<> Wartość zbiorcza najlepszej populacji :: " + logics.totalPopulationFunctionValue(logics.calculateFunctionValuesMapBasedOnPopulationMap(bestGeneration, currentConfigData)));
//        for (int i = 1; i <= bestGeneration.size(); i++)
//        {
//            System.out.println("Entry :: " + "index (" + i + ") "  + bestGeneration.get(i)
//                    + ", binary :: " + logics.convertIntegerToBinaryString(bestGeneration.get(i), currentConfigData.targetBinaryWordLength)
//                    + ", calculated function value :: " + logics.calculateFunctionOnGivenX(currentConfigData, bestGeneration.get(i)));
//        }
    }
}
