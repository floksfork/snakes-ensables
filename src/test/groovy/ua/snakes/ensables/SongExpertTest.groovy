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

    @Unroll("1-snake ensemble. #rps-MRPS")
    def "1-snake ensemble. N-MRPS"() {
        given:
        def g = new Snake("Gorgias", 0, 0)
        def ensemble = Mock(Ensemble)
        ensemble.singingSnakes >> [(g): [g, g]]

        when:
        def song = songExpert.hearAndTellLongSong(ensemble, rps)

        then:

        song == result

        where:
        rps | result
        0   | ["Gorgias"]
        1   | ["Gorgias", "Gorgias"]
        2   | ["Gorgias", "Gorgias", "Gorgias"]
        3   | ["Gorgias", "Gorgias", "Gorgias", "Gorgias"]
        5   | ["Gorgias", "Gorgias", "Gorgias", "Gorgias", "Gorgias", "Gorgias"]
    }

    @Unroll("unrelated 2-snakes ensemble. #mrps-MRPS")
    def "unrelated 2-snakes ensemble. N-MRPS"() {
        given:
        def g = new Snake("Gorgias", 0, 0)
        def p = new Snake("Protagoras", 1, 1)

        def ensemble = Mock(Ensemble)
        ensemble.singingSnakes >> [(g): [g, g], (p): [p, p]]

        when:
        def song = songExpert.hearAndTellLongSong(ensemble, mrps)

        then:
        song == result1 || song == result2

        where:
        mrps | result1                                      | result2
        0    | ["Gorgias"]                                  | ["Protagoras"]
        1    | ["Gorgias", "Gorgias"]                       | ["Protagoras", "Protagoras"]
        2    | ["Gorgias", "Gorgias", "Gorgias"]            | ["Protagoras", "Protagoras", "Protagoras"]
        3    | ["Gorgias", "Gorgias", "Gorgias", "Gorgias"] | ["Protagoras", "Protagoras", "Protagoras", "Protagoras"]
    }

    def "related 2-snakes ensemble. 0-MRPS"() {
        given:
        def r = Mock(Snake)
        def j = Mock(Snake)
        r.name >> "Rod"
        j.name >> "Jane"

        def ensemble = Mock(Ensemble)
        ensemble.singingSnakes >> [(r): [j, r], (j): [r, j]]

        when:
        def song = songExpert.hearAndTellLongSong(ensemble, 0)

        then:
        song == ["Rod", "Jane"] || song == ["Jane", "Rod"]
    }

    def "related 2-snakes ensemble. 1-MRPS"() {
        given:
        def r = Mock(Snake)
        def j = Mock(Snake)
        r.name >> "Rod"
        j.name >> "Jane"

        def ensemble = Mock(Ensemble)
        ensemble.singingSnakes >> [(r): [j, r], (j): [r, j]]

        def result1 = ["Rod", "Jane", "Jane", "Rod"]
        def result2 = ["Jane", "Rod", "Rod", "Jane"]
        def result3 = ["Jane", "Jane", "Rod", "Rod"]
        def result4 = ["Rod", "Rod", "Jane", "Jane"]
        def result5 = ["Jane", "Rod", "Jane", "Rod"]
        def result6 = ["Rod", "Jane", "Rod", "Jane"]

        when:
        def song = songExpert.hearAndTellLongSong(ensemble, 1)

        then:
        song == result1 || song == result2 || song == result3 || song == result4 || song == result5 || song == result6
    }


    @Unroll("related 3-snakes ensemble. #mrps-MRPS")
    def "related 3-snakes ensemble. N-MRPS"() {
        given:
        def r = Mock(Snake)
        def j = Mock(Snake)
        def f = Mock(Snake)
        r.name >> "Rod"
        j.name >> "Jane"
        f.name >> "Freddy"

        def ensemble = Mock(Ensemble)
        ensemble.singingSnakes >> [(r): [j, j], (j): [r, r, f], (f): [f]]

        when:
        def song = songExpert.hearAndTellLongSong(ensemble, mrps)

        then:
        song == result

        where:
        mrps | result
        0    | ["Rod", "Jane", "Freddy"]
        1    | ["Rod", "Jane", "Rod", "Jane", "Freddy", "Freddy"]
    }

    @Unroll("related 2-snakes and unrelated snake ensemble. #mrps-MRPS")
    def "related 2-snakes and unrelated snake ensemble. N-MRPS"() {
        given:
        def r = Mock(Snake)
        def j = Mock(Snake)
        def f = Mock(Snake)
        r.name >> "Rod"
        j.name >> "Jane"
        f.name >> "Freddy"

        def ensemble = Mock(Ensemble)
        ensemble.singingSnakes >> [(r): [j, j], (j): [r, r], (f): [f, f]]

        when:
        def song = songExpert.hearAndTellLongSong(ensemble, mrps)

        then:
        song == result1 || song == result2

        where:
        mrps | result1                        | result2
        0    | ["Jane", "Rod"]                | ["Rod", "Jane"]
        1    | ["Jane", "Rod", "Jane", "Rod"] | ["Rod", "Jane", "Rod", "Jane"]
    }

    @Unroll("unrelated 3-snakes ensemble. #mrps-MRPS")
    def "unrelated 3-snakes ensemble. N-MRPS"() {
        def r = Mock(Snake)
        def j = Mock(Snake)
        def f = Mock(Snake)
        r.name >> "Rod"
        j.name >> "Jane"
        f.name >> "Freddy"

        def ensemble = Mock(Ensemble)
        ensemble.singingSnakes >> [(r): [r, r], (j): [j, j], (f): [f, f]]

        when:
        def song = songExpert.hearAndTellLongSong(ensemble, mrps)

        then:
        println(song)
        song == result1 || song == result2 || song == result3

        where:
        mrps | result1          | result2        | result3
        0    | ["Jane"]         | ["Rod"]        | ["Freddy"]
        1    | ["Jane", "Jane"] | ["Rod", "Rod"] | ["Freddy", "Freddy"]
    }
}
