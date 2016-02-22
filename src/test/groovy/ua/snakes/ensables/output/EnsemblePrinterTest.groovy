package ua.snakes.ensables.output

import spock.lang.Shared
import spock.lang.Specification


class EnsemblePrinterTest extends Specification {
    @Shared
    EnsemblePrinter p = new EnsemblePrinter()

    def "no NullPointerException if list of names is empty"() {
        when:
        p.writeToConsole(10, 10, null)

        then:
        notThrown(NullPointerException)
    }
}
