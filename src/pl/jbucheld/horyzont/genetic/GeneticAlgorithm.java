package pl.jbucheld.horyzont.genetic;

import java.util.Map;

public class GeneticAlgorithm
{
    private static final Logics logics = new Logics();
    private AlgorithmConfigData algorithmConfigData;

    public Map<Integer, Integer> getNextGeneration(Map<Integer, Integer> generationZero)
    {
        Map<Integer, Integer> offspring = createOffspring(generationZero, algorithmConfigData);
        performBinaryChromosomeCrossing(generationZero, offspring);

    }

    private void performBinaryChromosomeCrossing(Map<Integer, Integer> generationZero, Map<Integer, Integer> offspring)
    {

    }

    private Map<Integer, Integer> createOffspring(Map<Integer, Integer> generationZero, AlgorithmConfigData algorithmConfigData)
    {

    }


    public GeneticAlgorithm(AlgorithmConfigData algorithmConfigData) {
        this.algorithmConfigData = algorithmConfigData;
    }
}
