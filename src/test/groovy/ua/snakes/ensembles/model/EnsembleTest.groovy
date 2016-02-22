package ua.snakes.ensembles.model

import spock.lang.Specification
import spock.lang.Unroll

class EnsembleTest extends Specification {

    def "default constructor is OK"() {
        when:
        def ensemble = new Ensemble()

        then:
        ensemble.singingSnakes != null
        ensemble.singingSnakes.isEmpty()
    }

    def "constructor based on snakes is OK on null input"() {
        when:
        def ensemble = new Ensemble(null)

        then:
        notThrown(NullPointerException.class)
        ensemble.singingSnakes != null
        ensemble.singingSnakes.isEmpty()
    }

    def "building 1-snake ensemble"() {
        def snake = new Snake("Gorgias", 0, 0)

        when:
        def ensemble = new Ensemble([0: snake])

        then:
        ensemble.singingSnakes.size() == 1
        ensemble.singingSnakes[snake] == [snake, snake]
    }

    def "building 2-snakes ensemble"() {

        when:
        def ensemble = new Ensemble([0: g, 1: p])

        then:
        ensemble.singingSnakes.size() == 2
        ensemble.singingSnakes[g] == gSnakes
        ensemble.singingSnakes[p] == pSnakes

        where:
        g << [new Snake("Gorgias", 0, 0),   new Snake("Gorgias", 0, 1)]
        p << [new Snake("Protagoras", 1, 1), new Snake("Protagoras", 1, 0)]

        gSnakes << [
                [new Snake("Gorgias", 0, 0), new Snake("Gorgias", 0, 0)],
                [new Snake("Gorgias", 0, 1), new Snake("Protagoras", 1, 0)]
        ]
        pSnakes << [
                [new Snake("Protagoras", 1, 1), new Snake("Protagoras", 1, 1)],
                [new Snake("Protagoras", 1, 0),  new Snake("Gorgias", 0, 1)]
        ]
    }

    @Unroll
    def "building ensemble in different ways"() {
        given:
        def snakes = [(jNo): jane, (rNo): rod, (fNo): freddy]

        when:
        def ensemble = new Ensemble(snakes)

        then:
        ensemble.singingSnakes.size() == snakes.size()
        ensemble.singingSnakes[jane] == [rod, rod, freddy]
        ensemble.singingSnakes[freddy] == [freddy]
        ensemble.singingSnakes[rod] == [jane, jane]

        where:

        jNo | rNo | fNo
        1   | 0   | 2
        0   | 2   | 1

        rod << [new Snake("Rod", 1, 1, 261.626), new Snake("Rod", 0, 0)]
        jane << [new Snake("Jane", 0, 0, 440), new Snake("Jane", 2, 2)]
        freddy << [new Snake("Freddy", 1, 2, 311.127), new Snake("Freddy", 0, 1)]
    }
}
