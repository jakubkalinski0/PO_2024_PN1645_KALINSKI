package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for parsing an array of arguments into a list of movement directions.
 */
public class OptionsParser {

    /**
     * Parses an array of strings into a list of MoveDirection values.
     *
     * @param       args an array of strings representing movement directions
     * @return      a list of MoveDirection corresponding to the given arguments
     * @throws      IllegalArgumentException if an argument does not correspond to any movement direction
     */
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> directions = new ArrayList<>();

        for (String arg : args) {
            directions.add(mapStringToDirection(arg));
        }

        return directions;
    }

    /**
     * Maps a single string to its corresponding MoveDirection value.
     *
     * @param       arg a string representing a movement direction
     * @return      the corresponding MoveDirection value
     * @throws      IllegalArgumentException if the argument is invalid
     */
    private static MoveDirection mapStringToDirection(String arg) {
        return switch (arg) {
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "l" -> MoveDirection.LEFT;
            case "r" -> MoveDirection.RIGHT;
            default -> throw new IllegalArgumentException(arg + " is not a legal move specification.");
        };
    }
}