package ua.snakes.ensables.model;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ensemble {
    Map<Snake, Set<Snake>> singingSnakes;

    public Ensemble() {
        singingSnakes = new HashMap<Snake, Set<Snake>>();
    }

    public Ensemble(Map<Integer, Snake> snakes) {
        if (snakes == null) {
            singingSnakes = new HashMap<Snake, Set<Snake>>();
        } else {
            buildEnsemble(snakes);
        }
    }

    public Map<Snake, Set<Snake>> getSingingSnakes() {
        return singingSnakes;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "singingSnakes=" + singingSnakes +
                '}';
    }

    private void buildEnsemble(Map<Integer, Snake> snakes) {
        singingSnakes = new HashMap<Snake, Set<Snake>>(snakes.size());

        for(Snake snake: snakes.values()){
            Snake beforeMe = snakes.get(snake.getTail());
            Snake afterMe = snakes.get(snake.getHead());

            addSingingSnakeBeforeMe(snake, beforeMe);
            addSingingSnakeAfterMe(snake, afterMe);
        }
    }

    private void addSingingSnakeBeforeMe(Snake me, Snake beforeMe){
        addSingingSnake(beforeMe, me);
    }

    private void addSingingSnakeAfterMe(Snake me, Snake afterMe){
        addSingingSnake(me, afterMe);
    }

    private void addSingingSnake(Snake first, Snake next){
        Set snakes = singingSnakes.get(first);

        if (snakes == null){
            snakes = new HashSet();
            singingSnakes.put(first, snakes);
        }

        snakes.add(next);
    }
}
