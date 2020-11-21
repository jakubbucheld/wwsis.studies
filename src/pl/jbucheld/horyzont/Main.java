package pl.jbucheld.horyzont;

import pl.jbucheld.horyzont.tsp.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    static Logics logics = new Logics();
    static MathematicalFunctions MF = new MathematicalFunctions();
    static DecimalFormat decimalFormat = new DecimalFormat("##.##");
    static AntColonyOptimization antColonyOptimization = new AntColonyOptimization();

    public static void main(String[] args)
    {
//        antColonyOptimization.getStartupInfo();
        antColonyOptimization.solve();
        antColonyOptimization.getNaiveResult();


    }




}
