package ua.snakes.ensembles.model;

/**
 * Serves to represent a snake from Standard Snake Ensemble Serialisation File.
 */
public class Snake {

    private String name;
    private int tail;
    private int head;
    private double pitch;

    public Snake(String name, int tail, int head) {
        this.name = name;
        this.tail = tail;
        this.head = head;
    }

    public Snake(String name, int tail, int head, double pitch) {
        this.name = name;
        this.tail = tail;
        this.head = head;
        this.pitch = pitch;
    }

    public String getName() {
        return name;
    }

    public int getTail() {
        return tail;
    }

    public int getHead() {
        return head;
    }

    public double getPitch() {
        return pitch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snake snake = (Snake) o;

        if (head != snake.head) return false;
        if (Double.compare(snake.pitch, pitch) != 0) return false;
        if (tail != snake.tail) return false;
        if (name != null ? !name.equals(snake.name) : snake.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + tail;
        result = 31 * result + head;
        temp = Double.doubleToLongBits(pitch);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", tail=" + tail +
                ", head=" + head +
                ", pitch=" + pitch +
                '}';
    }
}
