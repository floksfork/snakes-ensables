package ua.snakes.ensembles.model;


import java.util.*;

/**
 * Serves to build an ensemble.
 */
public class Ensemble {
    private Map<Snake, List<Snake>> singingSnakes;

    public Ensemble() {
        singingSnakes = new HashMap<Snake, List<Snake>>();
    }

    public Ensemble(Map<Integer, Snake> snakes) {
        if (snakes == null) {
            singingSnakes = new HashMap<Snake, List<Snake>>();
        } else {
            buildEnsemble(snakes);
        }
    }

    /**
     * @return map: Map<Snake, List<Snake>> , where @key is the snake who will start singing first
     * and @value - rest related snakes, who may sign immediately after first snake.
     */
    public Map<Snake, List<Snake>> getSingingSnakes() {
        return singingSnakes;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "singingSnakes=" + singingSnakes +
                '}';
    }

    private void buildEnsemble(Map<Integer, Snake> snakes) {
        singingSnakes = new HashMap<Snake, List<Snake>>(snakes.size());

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
        List snakes = singingSnakes.get(first);

        if (snakes == null){
            snakes = new LinkedList();
            singingSnakes.put(first, snakes);
        }

        snakes.add(next);
    }
}
