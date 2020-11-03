package pl.jbucheld.horyzont.tsp;

public class Coordinates
{
    private Integer horizontal;  // -x
    private Integer vertical;    // -y

    public Coordinates(Integer horizontal,
                       Integer vertical)
    {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Integer getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Integer horizontal) {
        this.horizontal = horizontal;
    }

    public Integer getVertical() {
        return vertical;
    }

    public void setVertical(Integer vertical) {
        this.vertical = vertical;
    }

    @Override
    public String toString() {
        return "Coordinates{" + horizontal +
                ", " + vertical + '}';
    }
}
