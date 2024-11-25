package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class OptionsParserTest {
    // Test for valid arguments
    @Test
    void parse_shouldReturnCorrectDirectionsForValidArguments() {
        // given
        String[] validArguments = {"f", "b", "l", "r"};
        List<MoveDirection> expectedDirections = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT
        );

        // when
        List<MoveDirection> result = OptionsParser.parse(validArguments);

        // then
        assertEquals(expectedDirections, result);
    }

    // Test for exception when invalid argument causes a failure (if applicable)
    @Test
    void parse_shouldThrowExceptionForInvalidDirection() {
        // given
        String[] invalidArguments = {"x"};

        // then
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(invalidArguments));
    }
}