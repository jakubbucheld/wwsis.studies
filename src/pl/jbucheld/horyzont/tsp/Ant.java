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
        visited.add(city);
    }

    public boolean ifVisited(Coordinates city)
    {
        return visited.contains(city);
    }

    public Coordinates currentPosition()
    {
        return visited.get(visited.size()-1);
    }

    public void clearVisitedList()
    {
        this.visited = new ArrayList<>();
    }
}