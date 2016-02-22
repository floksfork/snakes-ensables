package ua.snakes.ensembles.output;

import java.util.List;

/**
 * Serves to print ensemble simulation result.
 */
public class EnsemblePrinter {

    /**
     * Prints to console ensemble simulation result.
     *
     * @param mrps - maximum repeats per snake allowed.
     * @param snakesNumber - number of snakes in the ensemble.
     * @param names - list representation of the song performed by the snakes.
     *              The list should contain snakes' names, who have performed the longest song.
     */
    public void writeToConsole(int mrps, int snakesNumber, List<String> names) {

        if (names == null || names.isEmpty()) return;

        System.out.println(
                "3.0.11: Oleksandr Siianko: "
                        + names.size() + " sings in a longest " + mrps + "-MRPS song"
                        + " for this " + snakesNumber + "-snake ensemble:"
        );

        for (int i = 0; i < names.size(); i++) {
            if (i > 0) System.out.print(", ");

            System.out.print(names.get(i));
        }
        System.out.print(".");
    }
}
