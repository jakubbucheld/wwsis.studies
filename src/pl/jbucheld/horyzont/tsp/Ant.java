package pl.jbucheld.horyzont.tsp;

import java.util.*;

public class Ant
{
    private List<Coordinates> visited;

    public Ant()
    {
        this.visited = new ArrayList<>();
    }

    public List<Coordinates> getVisited() {
        return visited;
    }

    public void setVisited(List<Coordinates> visited) {
        this.visited = visited;
    }

    public void visitCity(Coordinates city)
    {
//        trail.add(city);
        visited.add(city);
    }

    public void clearVisitedList()
    {
        this.visited = new ArrayList<>();
    }


//    protected double trailLength(double graph[][])
//    {
//        double length = graph[trail[trailSize - 1]][trail[0]];
//        for (int i = 0; i < trailSize - 1; i++)
//            length += graph[trail[i]][trail[i + 1]];
//        return length;
//    }
//
//    protected void clear()
//    {
//        for (int i = 0; i < trailSize; i++)
//            visited[i] = false;
//    }
}