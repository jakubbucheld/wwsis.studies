package pl.jbucheld.horyzont.genetic;

import java.util.*;

public class Logics
{
    private Random random = new Random();

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


    public Map<Integer, Integer> calculateFunctionValuesMapBasedOnPopulationMap(Map<Integer, Integer> startingPopulation,
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


    public Integer roulettePickKeyOnGivenGeneration(Map<Integer, Integer> generationZero, AlgorithmConfigData configData)
    {

        Map<Integer, Integer> functionValuesMap = calculateFunctionValuesMapBasedOnPopulationMap(generationZero, configData);
        Integer total = totalPopulationFunctionValue(functionValuesMap);
        total = Math.abs(total);

        int pick = random.nextInt(total);
        int temporaryTotal=0;
        Map.Entry<Integer, Integer> finalEntry = functionValuesMap.entrySet().iterator().next();
        for (Map.Entry<Integer, Integer> entry : functionValuesMap.entrySet())
        {
            temporaryTotal += Math.abs(entry.getValue());
            if (pick <= temporaryTotal)
            {
                finalEntry = entry;
                break;
            }

        }
//        System.out.println("FINAL ENTRY <> " + finalEntry);
        return finalEntry.getKey();
    }


    public Integer performSingleMutation(Integer integer, int locus, int targetBinaryWordLength)
    {
        String binaryWord = convertIntegerToBinaryString(integer, targetBinaryWordLength);
//        System.out.println("---> before mutation : " + binaryWord + ", locus : " + locus);

        StringBuilder binaryWordAfterMutation = new StringBuilder(binaryWord);
        if (binaryWord.charAt(locus)=='0') binaryWordAfterMutation.setCharAt(locus, '1');
        else binaryWordAfterMutation.setCharAt(locus, '0');
//        System.out.println("---> after mutation " + binaryWordAfterMutation);
        return Integer.parseInt(binaryWordAfterMutation.toString(), 2);
    }

    public Integer performSingleCrossing(Integer first, Integer second, int targetBinaryWordLength, int locus)
    {
        String firstAsBinaryWord = convertIntegerToBinaryString(first, targetBinaryWordLength);
        String secondAsBinaryWord = convertIntegerToBinaryString(second, targetBinaryWordLength);

//        System.out.println("1. - " + firstAsBinaryWord);
//        System.out.println("2. - " + secondAsBinaryWord);

        StringBuilder finalString = new StringBuilder();
        finalString.append(firstAsBinaryWord, 0, locus).append(secondAsBinaryWord.substring(locus));

//        System.out.println("---> crossed (binary): " + finalString.toString());
//        System.out.println("---> crossed (decimal): " + Integer.parseInt(finalString.toString(),2));

        return Integer.parseInt(finalString.toString(),2);
    }


}
