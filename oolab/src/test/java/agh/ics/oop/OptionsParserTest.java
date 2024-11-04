package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class OptionsParserTest {
    /*
    The only method here is tested with Given-When-Then pattern. The test includes valid, invalid and mixed arguments.
    */

    // testing MoveDirection[] parse(String[] args) method
    @Test
    void changingStringArgumentsToDirections() {
        // given
        String[] validArguments = {"f", "b", "l", "r"};
        String[] invalidArguments = {"a", "x", "v", "m", "y"};
        String[] mixedArguments = {"a", "f", "f", "x", "y", "l", "z", "r", "p"};

        MoveDirection forward = MoveDirection.FORWARD;
        MoveDirection backward = MoveDirection.BACKWARD;
        MoveDirection left = MoveDirection.LEFT;
        MoveDirection right = MoveDirection.RIGHT;

        // when
        List<MoveDirection> properResultValid = List.of(forward, backward, left, right);
        List<MoveDirection> properResultInvalid = List.of();
        List<MoveDirection> properResultMixed = List.of(forward, forward, left, right);

        List<MoveDirection> resultValid = OptionsParser.parse(validArguments);
        List<MoveDirection> resultInvalid = OptionsParser.parse(invalidArguments);
        List<MoveDirection> resultMixed = OptionsParser.parse(mixedArguments);

        // then
        assertEquals(resultValid, properResultValid);
        assertEquals(resultInvalid, properResultInvalid);
        assertEquals(resultMixed, properResultMixed);
    }
}