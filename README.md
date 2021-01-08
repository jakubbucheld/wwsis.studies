# WWSIS - Artificial Intelligence

## TSP - ASO
This section is my approach to Travelling Salesman Problem, trying to  build OOP Ant Colony Optimization Algorithm.

## Simple Genetic Algorithm
This section is my approach to basics of genetic algorithms. It still has some malfunctions, but its *WIP*. The purpose of this particular algorithm is to find maximal value of given function, on given factors, etc. 

## Dev note ::
Nov 21. 2020
Finished ACO-TSP.
At current stage you can select (by // other option) between generating and inputting data.
LINES 84/85 - select if you want to randomize ant starting position, or if you want to loop them, filling all the cities almost equally.
LINES 24/25 - select if you want to input citeies coordinates or if you want the programme to generate it for you (in bounds of -10 to 10 each axis by default).
Alpha & Beta parameters are set (probably) the best possible way. Q factor (amount of pheromone) is set to 500, but feel free to experiment with different values.
Probability choice depends on reciprocal of route between two particular cities - I think it's one of the most reliable way of doing this.

I'll write down the readme when I'll get some sleep...
