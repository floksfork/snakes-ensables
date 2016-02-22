package ua.snakes.ensables

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import ua.snakes.ensables.model.Ensemble
import ua.snakes.ensables.model.Snake

class SongExpertTest extends Specification {

    @Shared
    def songExpert = new SongExpert()

    @Unroll
    def "no song is told when no ensemble"() {
        expect:
        new SongExpert().hearAndTellLongSong(null, mrps) == []

        where:
        mrps << [0, 1, 100, 200, 1000]
    }

    @Unroll("#N-name song for 0-MPRS")
    def "N-name song for 0-MPRS"() {

        def singingSnks = [:]
        snakes.each {singingSnks << [(it) : it]}

        def ensemble = Mock(Ensemble)
        ensemble.singingSnakes >> singingSnks

        when:
        def song = songExpert.hearAndTellLongSong(ensemble, 0)

        then:
        song == longSong

        where:

        N << [1, 2]
        snakes << [
                [new Snake("Gorgias", 0, 0)],
                [new Snake("Protagoras", 0, 0), new Snake("Gorgias", 1, 1)]
                [new Snake("Rod", 0, 1), new Snake("Jane", 1, 0)]
        ]

        longSong << [
                ["Gorgias"],
                ["Protagoras"]
                ["Rod", "Jane"]
        ]
    }
}
