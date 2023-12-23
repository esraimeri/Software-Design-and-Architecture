import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralCleanerFilter implements Filter {

    @Override
    public Object execute(Object input) {
        String[] lines = input.toString().split("\n");
        char[] targets = {'{', '}', '[', ']'};
        List<String> newLines = new ArrayList<>();

        for (String line : lines) {
            // Skip lines that contain only commas
            if (line.trim().equals(",")) {
                continue;
            }

            List<Character> newParts = filterOutTargets(line.toCharArray(), targets);
            newLines.add(removeExtraSpaces(listToString(newParts)) + "\n");

        }


        return removeCommas(String.join("", newLines));
    }

    @Override
    public String getData() {
        return "";
    }

    private List<Character> filterOutTargets(char[] parts, char[] targets) {

        return new String(parts)
                .chars()
                .mapToObj(c -> (char) c)
                .filter(part -> !hasTarget(part, targets))
                .collect(ArrayList::new, List::add, List::addAll);
    }

    private boolean hasTarget(char part, char[] targets) {

        for (char target : targets) {
            if (part == target) {
                return true;
            }
        }

        return false;
    }

    private String listToString(List<Character> charList) {

        return charList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private String removeExtraSpaces(String input) {
        return input.replaceAll("\\s+", " ");
    }

    private String removeCommas(String input) {

        return input.replace(",", "");

    }

}
