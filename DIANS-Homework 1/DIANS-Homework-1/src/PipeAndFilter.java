import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PipeAndFilter {

    public static String removeWhiteSpacesAndQuotes(String inputStr){
        return inputStr.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c != ' ' && c != '"')
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {


        Pipe<String> pipe = new Pipe<>();
        String filePath = "DIANS-Homework 1/DIANS-Homework-1/src/data.txt";
        List<String> data = new ArrayList<>();


        try {
            data = ReadAsList.read(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        MultiLineStringFilter joinSplitLines = new MultiLineStringFilter();
        GeneralCleanerFilter generalCleanerFilter = new GeneralCleanerFilter();
        NameFilter nameFilter = new NameFilter();
        NameEnFilter nameEnFilter = new NameEnFilter();
        IdFilter idFilter = new IdFilter();
        DescriptionFilter descriptionFilter = new DescriptionFilter();
        TypeFilter typeFilter = new TypeFilter();
        OpeningHoursFilter openingHoursFilter = new OpeningHoursFilter();
        CityFilter cityFilter = new CityFilter();
        PhoneFilter phoneFilter = new PhoneFilter();
        WebsiteFilter websiteFilter = new WebsiteFilter();
        CoordinatesFilter coordinatesFilter = new CoordinatesFilter();


        pipe.addFilter(joinSplitLines);
        pipe.addFilter(generalCleanerFilter);
        pipe.addFilter(nameFilter);
        pipe.addFilter(nameEnFilter);
        pipe.addFilter(idFilter);
        pipe.addFilter(descriptionFilter);
        pipe.addFilter(typeFilter);
        pipe.addFilter(openingHoursFilter);
        pipe.addFilter(cityFilter);
        pipe.addFilter(phoneFilter);
        pipe.addFilter(websiteFilter);
        pipe.addFilter(coordinatesFilter);


        data.forEach(i ->{
            if(i.contains("name")){
                pipe.runFilter(i);
            }
        });
    }
}