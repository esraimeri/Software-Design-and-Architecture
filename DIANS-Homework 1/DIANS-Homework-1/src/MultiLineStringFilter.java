import java.util.ArrayList;
import java.util.List;

public class MultiLineStringFilter implements Filter {
    @Override
    public Object execute(Object input) {

        String[] lines = input.toString().split("\n");
        List<String> filteredLines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        boolean multiLineFlag = false;
        

        for (int i = 0; i < lines.length; i++) {
            long count = lines[i].chars().filter(ch -> ch == '\"').count();

            if (count % 2 != 0) {
                if (!multiLineFlag) {
                    multiLineFlag = true;
                    currentLine.append(lines[i]);
                } else {
                    currentLine.append("\n").append(lines[i]);
                }
            } else {
                if (multiLineFlag) {
                    multiLineFlag = false;
                    filteredLines.add(currentLine.toString());
                    currentLine.setLength(0); // clear the StringBuilder
                } else {
                    filteredLines.add(lines[i]);
                    filteredLines.add("\n");
                }
            }
        }

        return filteredLines;
    }

    @Override
    public String getData() {
        return "";
    }

    @Override
    public void clearData() {

    }
}
