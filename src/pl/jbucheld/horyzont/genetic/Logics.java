package pl.jbucheld.horyzont.genetic;

import java.util.*;

public class Logics
{

    public AlgorithmConfigData collectStartingData()
    {
        Scanner scanConfigData = new Scanner(System.in);
        AlgorithmConfigData configData = new AlgorithmConfigData();

        System.out.println("Wprowadz czynniki funkcji");
        System.out.println("A :: ");
        configData.setFactorA(scanConfigData.nextInt());

        System.out.println("B :: ");
        configData.setFactorB(scanConfigData.nextInt());

        System.out.println("C :: ");
        configData.setFactorC(scanConfigData.nextInt());

        System.out.println("D :: ");
        configData.setFactorD(scanConfigData.nextInt());

        System.out.println("Wprowadź wartość ułamkową współczynnika krzyżowania - wskazane ~0,8 :: ");
        configData.setCrossingFactor(scanConfigData.nextDouble());

        System.out.println("Wprowadź wartość ułamkową współczynnika mutacji - wskazane ~0,2 :: ");
        configData.setMutationFactor(scanConfigData.nextDouble());

        System.out.println("Wprowadź limit epok, po którym należy przerwać pracę algorytmu :: ");
        configData.setGenerationLimit(scanConfigData.nextInt());

        System.out.println("Wprowadź limit wystąpień maksymalnego wyniku :: ");
        configData.setRepeatingLimit(scanConfigData.nextInt());

        return configData;
    }

    public Integer calculateFunctionOnGivenX(AlgorithmConfigData configData,
                                             Integer X)
    {
        return ((configData.getFactorA()*X) * (configData.getFactorA()*X) * (configData.getFactorA()*X))
        + ((configData.getFactorB()*X) * (configData.getFactorB()*X))
        + configData.getFactorC()*X
        + configData.getFactorD();
//
//        return Math.pow((configData.getFactorA()*X), 3)
//        + Math.pow((configData.getFactorB()*X), 2)
//        + configData.getFactorC()*X
//        + configData.getFactorD();
    }

    public Map<Integer, Integer> randomizeMapOfIntegerPopulation(Integer range, Integer amountOfRecords)
    {
        Random random = new Random();
        Map<Integer, Integer> finalIntegerMap = new HashMap<>();
        for (int i = 0; i < amountOfRecords; i++) finalIntegerMap.put(i+1, random.nextInt(range));
        return finalIntegerMap;
    }

    public String convertIntegerToBinaryString(Integer integer, Integer targetBinaryWordLength)
    {
        String temporary = Integer.toBinaryString(integer);
        StringBuilder stringBuilder = new StringBuilder();
        if (temporary.length()<targetBinaryWordLength)
        {
            stringBuilder.append("0".repeat(targetBinaryWordLength - temporary.length()));
            stringBuilder.append(temporary);
            return stringBuilder.toString();
        }
        else return temporary;
    }

    public Map<Integer, Integer> calculateFunctionValuesBasedOnPopulationMap(Map<Integer, Integer> startingPopulation,
                                                                             AlgorithmConfigData configData)
    {
        Map<Integer, Integer> finalFunctionMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : startingPopulation.entrySet())
        {
            finalFunctionMap.put(entry.getKey(), calculateFunctionOnGivenX(configData, entry.getValue()));
        }
        return finalFunctionMap;
    }
    public Integer totalPopulationFunctionValue(Map<Integer, Integer> functionValuesMap)
    {
        Integer total = 0;
        for (Map.Entry<Integer, Integer> entry : functionValuesMap.entrySet()) total += entry.getValue();
        return total;
    }

    public Map.Entry<Integer, Integer> randomPickFromFunctionValuesMap(Map<Integer, Integer> functionValuesMap)
    {
        Random random = new Random();
        Integer total = totalPopulationFunctionValue(functionValuesMap);

        int pick = random.nextInt(total);
            System.out.println("PICK :: " + pick);

        int temporaryTotal=0;
        Map.Entry<Integer, Integer> finalEntry = functionValuesMap.entrySet().iterator().next();

            System.out.println("FINAL ENTRY <> " + finalEntry);

        for (Map.Entry<Integer, Integer> entry : functionValuesMap.entrySet())
        {
            temporaryTotal += entry.getValue();
            if (pick <= temporaryTotal)
            {
                finalEntry = entry;
                break;
            }
        }
        return finalEntry;
    }

    public Map<Integer, Integer> determineOffspringForGivenGeneration(Map<Integer, Integer> generationZero)
    {

    }

}
