package ua.snakes.ensembles;


import ua.snakes.ensembles.input.EnsembleFileReader;
import ua.snakes.ensembles.model.Ensemble;
import ua.snakes.ensembles.model.Snake;
import ua.snakes.ensembles.output.EnsemblePrinter;

import java.util.List;
import java.util.Map;

/**
 * The entry point to the ensemble simulation app.
 */
public class LongSnakeSong {
    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("Please specify ensemble file and MPRS number.");
        } else if (args[0] == null || args[0].isEmpty()) {
            System.out.println("Please specify ensemble file.");
        } else if (args[1] == null || args[1].isEmpty()) {
            System.out.println("Please specify MPRS number.");
        } else {
            String file = args[0];

            int mrps = parseMrps(args[1]);
            if (mrps < 0) {
                System.out.println("Please specify correct MPRS number.");
                return;
            }

            EnsembleFileReader fr = new EnsembleFileReader();
            Map<Integer, Snake> snakes = fr.read(file);
            Ensemble ensemble = new Ensemble(snakes);
            SongExpert songExpert = new SongExpert();
            List<String> song = songExpert.hearAndTellLongSong(ensemble, mrps);

            EnsemblePrinter printer = new EnsemblePrinter();
            printer.writeToConsole(mrps, snakes.size(), song);
        }
    }

    private static int parseMrps(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            System.out.println("Wrong MPRS number: " + value + ". The number must be int value");
        }

        return -1;
    }
}
