package ua.snakes.ensables;

import ua.snakes.ensables.model.Ensemble;
import ua.snakes.ensables.model.Snake;

import java.util.*;

/**
 * The expert of snakes' songs. Knows what songs are long and what ones are short.
 */
public class SongExpert {

    /**
     * Simulates snakes singing by listening songs performed in the snakes' ensemble
     * and returns the longest song that can be listened.
     *
     * @param ensemble - current ensemble of snakes
     * @param mrps - maximum repeats per snake allowed.
     *
     * @return song: List<String> - the longest song that can be listened.
     */
    public List<String> hearAndTellLongSong(Ensemble ensemble, int mrps) {

        // impossible to hear a song as there is no ensemble.
        if (ensemble == null) return new LinkedList<String>();

        return hearLongSong(ensemble.getSingingSnakes(), mrps);
    }

    private List<String> hearLongSong(Map<Snake, List<Snake>> singingSnakes, int mrps) {

        // as each snake can sing at most mrps + 1 times
        // it means that the longest song would consist of maximum "singings" of a snake
        // so it should be N * (mrps + 1) for N-snakes in ensemble
        final int expectedSongLength = singingSnakes.entrySet().size() * (mrps + 1);

        List<String> longSong = new ArrayList<String>();
        for (Snake snake : singingSnakes.keySet()) {
            Map<Snake, Integer> rps = new HashMap<Snake, Integer>();
            List<String> song = songBegunBy(snake, rps, singingSnakes, mrps);

            // instant long song detection
            if (song.size() == expectedSongLength) {
                return song;
            }

            //worst case for long song detection in case there are some snakes in ensemble
            // that have head-end and tail-end wrapped around itself.
            if (longSong.size() < song.size()) {
                longSong = song;
            }
        }

        return longSong;
    }

    private List<String> songBegunBy(Snake snake, Map<Snake, Integer> rps,
                                     Map<Snake, List<Snake>> singingSnakes, int mrps) {
        List<String> song = new LinkedList<String>();

        if (!rps.containsKey(snake)) {
            rps.put(snake, 0);
        }

        int repeats = rps.get(snake);
        if (repeats == (mrps + 1)) {
            return song;
        }

        // sing further
        song.add(snake.getName());
        repeats++;
        rps.put(snake, repeats);

        List<Snake> nextSingers = singingSnakes.get(snake);
        for (Snake nextSinger : nextSingers) {
            song.addAll(songBegunBy(nextSinger, rps, singingSnakes, mrps));
        }

        return song;
    }
}