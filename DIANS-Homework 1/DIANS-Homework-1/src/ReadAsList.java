import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReadAsList {

    public static List<String> read(File in) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(in)));
        List<String> data = new ArrayList<>();
        String line;
        String text = "";

        Stack<String> parenthesesStack = new Stack<>();

        while (true) {

            if ((line = br.readLine()) != null) {
                text = text.concat(line + "\n");

                if (line.contains("{")) {
                    parenthesesStack.push("{");

                } else if (line.contains("},")) {
                    parenthesesStack.pop();

                    if (parenthesesStack.isEmpty()) {
                        data.add(text);
                        text = "";
                    }
                }
            } else break;
        }
        return data;
    }
}
