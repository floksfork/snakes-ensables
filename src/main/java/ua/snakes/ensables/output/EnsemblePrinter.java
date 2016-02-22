package ua.snakes.ensables.output;

import java.util.List;

public class EnsemblePrinter {
    public void writeToConsole(int sings, int mrps, int snakes, List<String> names) {

        if (names == null) return;

        System.out.println(
                "3.0.11: Oleksandr Siianko:"
                        + sings + " sings in a longest" + mrps + "-MRPS song"
                        + " for this" + snakes + "-snake ensemble:"
        );

        for (int i = 0; i < names.size(); i++) {
            if (i > 0) System.out.print(", ");

            System.out.print(names.get(i));
        }
        System.out.print(".");
    }
}
