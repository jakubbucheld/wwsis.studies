package pl.jbucheld.horyzont.genetic;

public class AlgorithmConfigData
{
    //alorithm uses a :: y = ax^3 + bx^2 + cx + d function. next four fields are used to represent them as function determining numbers
    private Integer factorA;
    private Integer factorB;
    private Integer factorC;
    private Integer factorD;

    // these two fields are used to represent mutation chance factor and crossing chance factor
    private Double crossingFactor;
    private Double mutationFactor;

    // these two fields are used to determine if algorithm should be stopped or not
    // (due to no changes through *eraLimit* eras OR due to *repeatingLimit* repeats of maximum possible score
    private int generationLimit;
    private int repeatingLimit;

    // these two fields determine population size and binary word length
    int targetBinaryWordLength;
    int amountOfRecords;

    public AlgorithmConfigData() {}

    public AlgorithmConfigData(Integer factorA, Integer factorB, Integer factorC, Integer factorD, Double crossingFactor, Double mutationFactor, int eraLimit, int repeatingLimit, int targetBinaryWordLength, int amountOfRecords) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.factorC = factorC;
        this.factorD = factorD;
        this.crossingFactor = crossingFactor;
        this.mutationFactor = mutationFactor;
        this.generationLimit = eraLimit;
        this.repeatingLimit = repeatingLimit;
        this.targetBinaryWordLength = targetBinaryWordLength;
        this.amountOfRecords = amountOfRecords;
    }

    @Override
    public String toString() {
        return "AlgorithmConfigData{" +
                "factorA=" + factorA +
                ", factorB=" + factorB +
                ", factorC=" + factorC +
                ", factorD=" + factorD +
                ", crossingFactor=" + crossingFactor +
                ", mutationFactor=" + mutationFactor +
                ", eraLimit=" + generationLimit +
                ", repeatingLimit=" + repeatingLimit +
                ", targetBinaryWordLength=" + targetBinaryWordLength +
                ", amountOfRecords=" + amountOfRecords +
                '}';
    }

    public Integer getFactorA() {
        return factorA;
    }

    public void setFactorA(Integer factorA) {
        this.factorA = factorA;
    }

    public Integer getFactorB() {
        return factorB;
    }

    public void setFactorB(Integer factorB) {
        this.factorB = factorB;
    }

    public Integer getFactorC() {
        return factorC;
    }

    public void setFactorC(Integer factorC) {
        this.factorC = factorC;
    }

    public Integer getFactorD() {
        return factorD;
    }

    public void setFactorD(Integer factorD) {
        this.factorD = factorD;
    }

    public Double getCrossingFactor() {
        return crossingFactor;
    }

    public void setCrossingFactor(Double crossingFactor) {
        this.crossingFactor = crossingFactor;
    }

    public Double getMutationFactor() {
        return mutationFactor;
    }

    public void setMutationFactor(Double mutationFactor) {
        this.mutationFactor = mutationFactor;
    }

    public int getGenerationLimit() {
        return generationLimit;
    }

    public void setGenerationLimit(int generationLimit) {
        this.generationLimit = generationLimit;
    }

    public int getRepeatingLimit() {
        return repeatingLimit;
    }

    public void setRepeatingLimit(int repeatingLimit) {
        this.repeatingLimit = repeatingLimit;
    }

    public int getTargetBinaryWordLength() {
        return targetBinaryWordLength;
    }

    public void setTargetBinaryWordLength(int targetBinaryWordLength) {
        this.targetBinaryWordLength = targetBinaryWordLength;
    }

    public int getAmountOfRecords() {
        return amountOfRecords;
    }

    public void setAmountOfRecords(int amountOfRecords) {
        this.amountOfRecords = amountOfRecords;
    }

}
