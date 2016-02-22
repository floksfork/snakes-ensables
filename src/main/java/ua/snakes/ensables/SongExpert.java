package ua.snakes.ensables;

import ua.snakes.ensables.model.Ensemble;
import ua.snakes.ensables.model.Snake;

import java.util.*;

public class SongExpert {

    public List<String> hearAndTellLongSong(Ensemble ensemble, int mprs) {
        List<String> song = new LinkedList<String>();
        if (ensemble == null) return song;

        int longSongSize = calcSongSize(ensemble.getSingingSnakes().size(), mprs);
        Set<Snake> singingSnakes = ensemble.getSingingSnakes().keySet();

        for (Snake snake : singingSnakes) {
            song.add(snake.getName());

            // repeats per snake
            Map<Snake, Integer> rps = new HashMap<Snake, Integer>(singingSnakes.size());
            if (!rps.containsKey(snake)){
                rps.put(snake, 0);
            }
            Integer snakeRepeats = rps.get(snake);
            snakeRepeats ++;

            if (song.size() == longSongSize) {
                return song;
            }
        }

        return song;
    }

    private int calcSongSize(int snakes, int mprs)  {
        if (mprs == 0){
            return 1;
        }

        return snakes * mprs;
    }

    private boolean isAllSnakesHaveMaxRepeats(Set<Snake> snakes, Map<Snake, Integer> rps, int mrps){

        for (Snake snake : snakes){
            if (!rps.containsKey(snake)){
                return false;
            }

            if (rps.get(snake) != mrps){
                return false;
            }
        }

        return true;
    }

}
