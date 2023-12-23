import java.io.*;
import java.sql.*;
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

    private static String truncateValue(String value, int maxLength) {
        if (value.length() > maxLength)
            return value.substring(0, maxLength - 3).concat("...");
        else return value;
    }

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/MacedonianHeritageDB";
        String user = "DIANS_User";
        String password = "DIANS-Hw3";

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

        StringBuilder sb = new StringBuilder();
        TableCreatorFilter tcf = new TableCreatorFilter();

        data.forEach(i ->{
            pipe.empty();
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
            if(i.contains("name")){
                pipe.runFilter(i);
                sb.append(tcf.execute(pipe.getData()));
            }
        });

        String info = sb.toString();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "heritage_table", null);

            if (tables.next()) {
                String deleteTableSql = "DROP TABLE heritage_table";
                try (PreparedStatement deleteTableStatement = connection.prepareStatement(deleteTableSql)) {
                    deleteTableStatement.executeUpdate();
                }
            }

            String createTableSql = "CREATE TABLE IF NOT EXISTS heritage_table ("
                    + "name VARCHAR(255), "
                    + "name_En VARCHAR(255), "
                    + "ID VARCHAR(255), "
                    + "Description VARCHAR(500), "
                    + "Type VARCHAR(255), "
                    + "workingHours VARCHAR(255), "
                    + "City VARCHAR(255), "
                    + "Phone VARCHAR(255), "
                    + "Website VARCHAR(255), "
                    + "Coordinates VARCHAR(255))";

            try (PreparedStatement createTableStatement = connection.prepareStatement(createTableSql)) {
                createTableStatement.executeUpdate();
            }

            String[] rows = info.split("\n");

            for (String row : rows) {
                String[] columns = row.split(";   ");

                String name = truncateValue(columns[0], 250);
                String name_En = truncateValue(columns[1], 250);
                String ID = truncateValue(columns[2], 250);
                String Description = truncateValue(columns[3], 500);
                String Type = truncateValue(columns[4], 250);
                String workingHours = truncateValue(columns[5], 250);
                String City = truncateValue(columns[6], 250);
                String Phone = truncateValue(columns[7], 250);
                String Website = truncateValue(columns[8], 250);
                String Coordinates = truncateValue(columns[9], 250);

                // Prepare and execute INSERT statement
                String insertSql = "INSERT INTO heritage_table (name, name_En, ID, Description, Type, workingHours, City, Phone, Website, Coordinates) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, name_En);
                    preparedStatement.setString(3, ID);
                    preparedStatement.setString(4, Description);
                    preparedStatement.setString(5, Type);
                    preparedStatement.setString(6, workingHours);
                    preparedStatement.setString(7, City);
                    preparedStatement.setString(8, Phone);
                    preparedStatement.setString(9, Website);
                    preparedStatement.setString(10, Coordinates);

                    preparedStatement.executeUpdate();
                }
            }

            System.out.println("Data inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        System.out.println(sb.toString());


    }
}