package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    /*
    The only method here is tested with Given-When-Then pattern. The test includes valid, invalid and mixed arguments.
    */

    // testing MoveDirection[] parse(String[] args) method
    @Test
    void testParseMethod() {
        // given
        String[] validArguments = {"f", "b", "l", "r"};
        String[] invalidArguments = {"a", "x", "v", "m", "y"};
        String[] mixedArguments = {"a", "f", "f", "x", "y", "l", "z", "r", "p"};

        MoveDirection forward = MoveDirection.FORWARD;
        MoveDirection backward = MoveDirection.BACKWARD;
        MoveDirection left = MoveDirection.LEFT;
        MoveDirection right = MoveDirection.RIGHT;

        // when
        MoveDirection[] properResultValid = {forward, backward, left, right};
        MoveDirection[] properResultInvalid = {};
        MoveDirection[] properResultMixed = {forward, forward, left, right};

        MoveDirection[] resultValid = OptionsParser.parse(validArguments);
        MoveDirection[] resultInvalid = OptionsParser.parse(invalidArguments);
        MoveDirection[] resultMixed = OptionsParser.parse(mixedArguments);

        // then
        assertArrayEquals(resultValid, properResultValid);
        assertArrayEquals(resultInvalid, properResultInvalid);
        assertArrayEquals(resultMixed, properResultMixed);
    }
}