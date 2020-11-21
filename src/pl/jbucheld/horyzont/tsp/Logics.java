package pl.jbucheld.horyzont.tsp;

import java.text.DecimalFormat;
import java.util.*;

public class Logics
{
    final MathematicalFunctions MF = new MathematicalFunctions();
    DecimalFormat decimalFormat = new DecimalFormat("##.##");

    public List<Coordinates> inputCities(Integer numberOfCities)
    {
        List<Coordinates> inputCities = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numberOfCities; i++)
        {
            System.out.println("Wprowadź współrzędną X: ");
            if(!scanner.hasNextInt())
            {
                System.out.println("Podaj poparwną wartość INT");
                scanner.next();
            }
            int tHorizontal = scanner.nextInt();

            System.out.println("Wprowadź współrzędną Y: ");
            if(!scanner.hasNextInt())
            {
                System.out.println("Podaj poparwną wartość INT");
                scanner.next();
            }
            int tVertical = scanner.nextInt();
            inputCities.add(new Coordinates(tHorizontal, tVertical));
        }
        return inputCities;
    }

    public List<Coordinates> generateCities(Integer numberOfCities)
    {
        List<Coordinates> generatedCities = new ArrayList<>();
        Random coordinatesGenerator = new Random();
        for (int i = 0; i < numberOfCities; i++)
        {
//            System.out.println("!!! Rozpoczecie generowania miasta "  + i);
            int tHorizontal = coordinatesGenerator.nextInt(10+10)-10;
            int tVertical = coordinatesGenerator.nextInt(10+10)-10;

//            System.out.println("   1.tymczasowe : " + tHorizontal + " " + tVertical);
            if(ifCityExists(tHorizontal, tVertical, List.copyOf(generatedCities))) i=i-1;
            else generatedCities.add(new Coordinates(tHorizontal, tVertical));
        }
        return generatedCities;
    }

    boolean ifCityExists(Integer horizontal,
                         Integer vertical,
                         List<Coordinates> cities)
    {
        boolean check=false;
        if(cities.isEmpty()) return false;

        Coordinates verify = new Coordinates(horizontal, vertical);
//        System.out.println("   2. Sprawdzam czy miasto " + verify.toString() + " istnieje");
        for (Coordinates c:cities)
        {
//            System.out.println("   - miasto z kolekcji : " + c.toString());
//            System.out.println("   - nowe " + verify.toString());

            if (c.getVertical().equals(verify.getVertical()) && c.getHorizontal().equals(verify.getHorizontal()))
            {
//                System.out.println("   -! miasto wystąpiło !");
                check = true;
                break;
            }
            else
            {
//                System.out.println("   - nie ma takiego miasta, można dodać");
                check = false;
            }
        }
        verify.setHorizontal(null);
        return check;
    }

    public Double[][] calculateDistances(List<Coordinates> cities)
    {
        Double[][] routes = new Double[cities.size()][cities.size()];
        for (int i = 0; i < routes.length; i++)
        {
            for (int j = 0; j < routes[i].length; j++)
            {
                if(i==j)
                {
                    routes[i][j]=0.00;
                }
                else
                {
                    routes[i][j] = MF.calculateEuclidianDistance(cities.get(i), cities.get(j));
                }
            }
        }
        return routes;
    }

    public Double[][] generatePheromoneArray(List<Coordinates> cities)
    {
        Double[][] pheromoneArray = new Double[cities.size()][cities.size()];
        for (Double[] doubles : pheromoneArray) {
            Arrays.fill(doubles, 0.0);
        }
        return pheromoneArray;
    }

    public void printArray(Double[][] routes)
    {
        for (int i = 0; i < routes.length; i++)
        {
            for (int j = 0; j < routes[i].length; j++)
            {
                System.out.print(decimalFormat.format(routes[i][j]) + "\t");
            }
            System.out.println();
        }
    }

    public List<Ant> createAnts(Integer numberOfAnts)
    {
        List<Ant> ants = new ArrayList<>();
        for(int i=0;i<numberOfAnts;i++) ants.add(new Ant());

        return ants;
    }

    public void setupAnts(List<Ant> ants,
                          List<Coordinates> cities)
    {
        Random locationGenerator = new Random();
        for (Ant ant : ants)
        {
            ant.visitCity(cities.get(locationGenerator.nextInt(cities.size())));
        }
    }

    public void nonRandomSetupAnts(List<Ant> ants,
                                   List<Coordinates> cities)
    {
        int additionalIterator=0;
        for (Ant ant : ants)
        {
            if(additionalIterator>cities.size()-1) additionalIterator=0;
            ant.visitCity(cities.get(additionalIterator));
            additionalIterator++;
        }
    }
    public Double calculateTrailLength(List<Coordinates> antRoute,
                                       Double[][] distancesArray)
    {
        Double totalDistance = 0.0;
        for (Coordinates c : antRoute)
        {
            if (c==(antRoute.get(antRoute.size()-1)))
            {
                break;
            }
            else
            {
                totalDistance = totalDistance + MF.calculateEuclidianDistance(c, antRoute.get(antRoute.indexOf(c)));

            }
        }
        return totalDistance;
    }

}
