import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CityFilter implements Filter {

    private String data;

    @Override
    public Object execute(Object input) {
        String in = input.toString();
        List<String> parts = List.of(in.split("\n"));


        int deleteIndex = IntStream.range(0, parts.size())
                .filter(i -> parts.get(i).contains("\"addr:city\""))
                .findFirst()
                .orElse(-1);


        if (deleteIndex != -1) {
            String c = parts.get(deleteIndex).split(": ")[1];
            this.data = "City: " + PipeAndFilter.removeWhiteSpacesAndQuotes(c);
            //System.out.println(data);
            List<String> modifiedParts = IntStream.range(0, parts.size())
                    .filter(i -> i != deleteIndex)
                    .mapToObj(parts::get).collect(Collectors.toList());

            return modifiedParts.stream().collect(Collectors.joining("\n"));
        }


        return parts.stream().collect(Collectors.joining("\n"));

    }

    @Override
    public String getData() {
        return data;
    }
}
