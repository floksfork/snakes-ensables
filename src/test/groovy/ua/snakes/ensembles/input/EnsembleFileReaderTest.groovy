package ua.snakes.ensembles.input

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import ua.snakes.ensembles.model.Snake

class EnsembleFileReaderTest extends Specification {

    @Shared
    def reader = new EnsembleFileReader();

    def EMPTY = "src\\test\\groovy\\ua\\snakes\\ensembles\\input\\samples\\empty.csv"
    def SAMPLE1 = "src\\test\\groovy\\ua\\snakes\\ensembles\\input\\samples\\sample1.csv"
    def SAMPLE2 = "src\\test\\groovy\\ua\\snakes\\ensembles\\input\\samples\\sample2.csv"

    @Unroll
    def "return empty map if no file provided"() {
        expect:
        reader.read(file) == [:]

        where:
        file << [null, ""]
    }

    def "return empty map if file cannot be found"() {
        expect:
        reader.read("../bla/bla/bla") == [:]
    }

    def "return empty map if file is empty"() {
        expect:
        reader.read(EMPTY) == [:]
    }

    def "return empty map if file has wrong format"(){
        expect:
        reader.read("wrong.format") == [:]
    }

    def "return full map if file has data"() {
        given:
        def data1 = [
                0: new Snake("Rod", 1, 1, 261.626),
                1: new Snake("Jane", 0, 0, 440),
                2: new Snake("Freddy", 1, 2, 311.127)
        ]

        def data2 = [
                0: new Snake("Jane", 2, 2),
                1: new Snake("Freddy", 0, 1),
                2: new Snake("Rod", 0, 0)
        ]
        expect:

        reader.read(SAMPLE1) == data1
        reader.read(SAMPLE2) == data2
    }
}
