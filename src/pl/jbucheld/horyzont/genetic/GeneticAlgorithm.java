package pl.jbucheld.horyzont.genetic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GeneticAlgorithm
{
    private static final Logics logics = new Logics();
    private AlgorithmConfigData algorithmConfigData;
    private Random random = new Random();


    public void getNextGeneration(List<Map<Integer,Integer>> history)
    {
        Map<Integer, Integer> bestGeneration = new HashMap<>();
        Map<Integer, Integer> offspring = createOffspring(history.get(history.size()-1));
        performBinaryChromosomeCrossing(offspring);
        performBinaryChromosomeMutation(offspring);
        history.add(offspring);
    }


    public Map<Integer, Integer> createOffspring(Map<Integer, Integer> ancestor)
    {
        System.out.println("---> getting previous generation as base for operation");
        Map <Integer, Integer> offspring = new HashMap<>();
        for (int i = 1; i <= ancestor.size(); i++)
        {
            int offspringKey = logics.roulettePickKeyOnGivenGeneration(ancestor, algorithmConfigData);
            offspring.put(i, ancestor.get(offspringKey));
//            System.out.println("wprowadzono :: " + offspring.get(i));
        }
        for (Map.Entry<Integer,Integer> entry: offspring.entrySet()) System.out.println(entry);
        return offspring;
    }


    private void performBinaryChromosomeCrossing(Map<Integer, Integer> offspring)
    {
        for (int i = 1; i <= offspring.size(); i+=2) {
            double check = random.nextDouble();
            System.out.println("---> CHECK for crossing :: " + check);
            if (check<algorithmConfigData.getCrossingFactor() && i != (offspring.size()))
            {
//                System.out.println("Crossing chromosome " + i + " with chromosome " + (i + 1));

                int locus = random.nextInt((algorithmConfigData.getTargetBinaryWordLength() - 1) - 1) + 1;
//                System.out.println("locus: " + locus);

                Integer first = logics.performSingleCrossing(offspring.get(i), offspring.get(i + 1), algorithmConfigData.getTargetBinaryWordLength(), locus);
                Integer second = logics.performSingleCrossing(offspring.get(i + 1), offspring.get(i), algorithmConfigData.getTargetBinaryWordLength(), locus);
                offspring.replace(i, first);
                offspring.replace(i + 1, second);
            }
        }
    }

    private void performBinaryChromosomeMutation(Map<Integer, Integer> offspring)
    {
        for (Map.Entry <Integer, Integer> entry : offspring.entrySet()) {
            if(random.nextDouble()<algorithmConfigData.getMutationFactor())
            {
                System.out.println("Starting mutation...");
                int locus = random.nextInt(algorithmConfigData.getTargetBinaryWordLength()-1)+1;

                entry.setValue(logics.performSingleMutation(entry.getValue(), locus, algorithmConfigData.getTargetBinaryWordLength()));

            }
        }
    }


    public GeneticAlgorithm(AlgorithmConfigData algorithmConfigData) {
        this.algorithmConfigData = algorithmConfigData;
    }
}
