package ua.snakes.ensables.model

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

    @Unroll
    def "building ensemble in different ways"() {
        given:
        def snakes = [0: snake0, 1: snake1, 2: snake2]

        when:
        def ensemble = new Ensemble(snakes)

        then:
        ensemble.singingSnakes.size() == snakes.size()
        ensemble.singingSnakes[jane] == [rod, freddy] as Set
        ensemble.singingSnakes[freddy] == [freddy] as Set
        ensemble.singingSnakes[rod] == [jane] as Set

        where:
        rod << [new Snake("Rod", 1, 1, 261.626), new Snake("Rod", 0, 0)]
        jane << [new Snake("Jane", 0, 0, 440), new Snake("Jane", 2, 2)]
        freddy << [new Snake("Freddy", 1, 2, 311.127), new Snake("Freddy", 0, 1)]


        snake0 << [new Snake("Rod", 1, 1, 261.626), new Snake("Jane", 2, 2)]
        snake1 << [new Snake("Jane", 0, 0, 440), new Snake("Freddy", 0, 1)]
        snake2 << [new Snake("Freddy", 1, 2, 311.127), new Snake("Rod", 0, 0)]
    }
}
