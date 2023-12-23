import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoordinatesFilter implements Filter {

    private String data;

    public Object execute(Object input) {
        String in = input.toString();
        List<String> parts = new ArrayList<>(List.of(in.split("\n")));
        int deleteIndex = 0;
        String c = "";


        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).contains("\"coordinates\"")) {
                String tmo = parts.get(i + 1);
                this.data = String.format("Geolocation: %s %s\n", parts.get(i + 2).trim(), tmo.substring(0, tmo.length() - 1).trim());
                c = data;
                deleteIndex = i;
                break;
            }
        }

        //System.out.println(c);


        parts.remove(deleteIndex);
        if (deleteIndex + 1 < parts.size()) {
            parts.remove(deleteIndex + 1);
        }
        if (deleteIndex + 2 < parts.size()) {
            parts.remove(deleteIndex + 2);
        }

        return parts.stream().collect(Collectors.joining("\n"));
    }

    @Override
    public String getData() {
        return data;
    }
}
