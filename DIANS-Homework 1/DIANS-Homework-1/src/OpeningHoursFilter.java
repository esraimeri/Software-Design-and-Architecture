import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OpeningHoursFilter implements Filter {
    @Override
    public Object execute(Object input) {
        String in = input.toString();
        List<String> parts = List.of(in.split("\n"));


        int deleteIndex = IntStream.range(0, parts.size())
                .filter(i -> parts.get(i).contains("\"opening_hours\""))
                .findFirst()
                .orElse(-1);


        if (deleteIndex != -1) {
            String c = parts.get(deleteIndex).split(": ")[1];
            System.out.println("Working hours: " + PipeAndFilter.removeWhiteSpacesAndQuotes(c));
            List<String> modifiedParts = IntStream.range(0, parts.size())
                    .filter(i -> i != deleteIndex)
                    .mapToObj(parts::get).toList();

            return modifiedParts.stream().collect(Collectors.joining("\n"));
        }


        return parts.stream().collect(Collectors.joining("\n"));

    }

}
