package pl.jbucheld.horyzont.tsp;

public class MathematicalFunctions
{
    public Double calculateEuclidianDistance(Coordinates coordinate1,
                                             Coordinates coordinate2)

    {
        Double distance = Math.sqrt(
                Math.pow(coordinate1.getHorizontal() - coordinate2.getHorizontal(), 2)
                        + Math.pow(coordinate1.getVertical() - coordinate2.getVertical(), 2)
        );
//        System.out.println("Dystans pomiędzy miastami wynosi :: " + distance);
        return distance;
    }

    public Double reciprocal(Double number)
    {
        return 1/number;
    }

}
