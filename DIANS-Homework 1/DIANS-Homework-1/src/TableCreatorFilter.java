import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableCreatorFilter implements Filter{

    public Object execute(Object input) {

        String lines = (String) input;
        String [] locInfo = lines.split("\n");
        StringBuilder sb = new StringBuilder();

        int i = 2;
        if(lines.contains("Name") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        i++;
        sb.append(";    ");

        if(lines.contains("Name-EN") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        i++;
        sb.append(";    ");

        if(lines.contains("ID") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        i++;
        sb.append(";    ");

        if(lines.contains("Description") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        i++;
        sb.append(";    ");

        if(lines.contains("Type") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        i++;
        sb.append(";    ");

        if(lines.contains("Working hours") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        i++;
        sb.append(";    ");

        if(lines.contains("City") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        i++;
        sb.append(";    ");

        if(lines.contains("Phone") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        i++;
        sb.append(";    ");

        if(lines.contains("Website") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        i++;
        sb.append(";    ");

        if(lines.contains("Geolocation") && !locInfo[i].equals("") && !locInfo[i].equals("null")){
            sb.append(locInfo[i].split(": ")[1]);
        }
        sb.append(";    \n");

        return sb.toString();
    }

    @Override
    public String getData() {
        return null;
    }

    @Override
    public void clearData() {

    }

}