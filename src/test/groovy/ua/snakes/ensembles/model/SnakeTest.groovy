package ua.snakes.ensembles.model

import spock.lang.Specification

class SnakeTest extends Specification {
    def "equals and hashCode are OK for 4-param constructor" (){
        when:
        def snake1 = new Snake("Harry", 3, 2, 155.46);
        def snake2 = new Snake("Harry", 3, 2, 155.46);

        then:
        snake1 == snake2
        snake1.hashCode() == snake2.hashCode()
    }

    def "equals and hashCode are OK for 3-param constructor" (){
        when:
        def snake1 = new Snake("Harry", 3, 2);
        def snake2 = new Snake("Harry", 3, 2);

        then:
        snake1 == snake2
        snake1.hashCode() == snake2.hashCode()
    }
}
