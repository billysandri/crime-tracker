package seng202.group6.Services;

import org.apache.commons.lang3.ObjectUtils;
import seng202.group6.Models.Crime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * The filter class is used to filter an arraylist of crimes by specific characteristics.
 * You first create a filter object, then call the setter methods to set the parameters of the filter.
 * Finally, applyFilter can be called with the arraylist to be filtered.
 */

public class Filter {

    private LocalDate start;
    private LocalDate end;
    private Set<String> types;
    private Set<String> locations;
    private Boolean arrest;
    private Boolean domestic;
    private Set<Integer> beats = new HashSet<>();
    private Set<Integer> wards = new HashSet<>();


    /**
     * Builds an SQL query from the different filter settings
     * @return The query as a String
     */
    private String queryBuilder() {
        String tableName = "Crimes";
        String statement = "SELECT * FROM " + tableName + " ";
        statement += "WHERE ";

        if (start != null) {
            statement += "occurrence_date > '" + LocalDateTime.of(start, LocalTime.MIDNIGHT) + "' ";
            statement += "AND ";
        }

        if (end != null) {
            statement += "occurrence_date <= '" + LocalDateTime.of(end, LocalTime.MIDNIGHT) + "' ";
            statement += "AND ";
        }

        if (!types.isEmpty()) {
            statement += "primary_description IN ('" + String.join("', '", types) + "') ";
            statement += "AND ";
        }


        if (!locations.isEmpty()) {
            statement += "location IN ('" + String.join("', '", locations) + "') ";
            statement += "AND ";
        }

        if (arrest != null) {
            statement += "arrest = " + arrest + " ";
            statement += "AND ";
        }

        if (domestic != null) {
            statement += "domestic = " + domestic + " ";
            statement += "AND ";
        }


        if (!beats.isEmpty()) {
            statement += "beat IN (" + String.join(", ", beats.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toSet()))
                    + ") ";
            statement += "AND ";
        }

        if (!wards.isEmpty()) {
            statement += "ward IN (" + String.join(", ", wards.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toSet()))
                    + ") ";

        } else {
            statement = statement.substring(0, statement.length() - 4); //Get rid of trailing AND
        }

        statement += ";";


        return statement;

    }


    /**
     * Filters the given set using the properties of the filter
     * @param crimes The arrayList to be filtered
     * @return The filtered arrayList
     */
    public ArrayList<Crime> applyFilter(ArrayList<Crime> crimes) {
        System.out.println(queryBuilder());
        ArrayList<Crime> output = new ArrayList<>();
        for (Crime crime : crimes) {

            if (
                    (start == null || crime.getDate().isAfter(ChronoLocalDateTime.from
                            (LocalDateTime.of(start, LocalTime.MIDNIGHT)))) &&
                    (end == null || crime.getDate().isBefore(ChronoLocalDateTime.from
                            (LocalDateTime.of(end, LocalTime.MIDNIGHT)))) &&
                    (types.isEmpty() || types.contains(crime.getPrimaryDescription())) &&
                    (locations.isEmpty() || locations.contains(crime.getLocationDescription())) &&
                    (arrest == null || crime.isArrest() == arrest) &&
                    (domestic == null || crime.isDomestic() == domestic) &&
                    (beats.isEmpty() || beats.contains(crime.getBeat())) &&
                    (wards.isEmpty() || wards.contains(crime.getWard()))
            ) {
                output.add(crime);
            }

        }
        return output;
    }

    /**
     * Sets the filter to filter from a specific start date
     * @param start The start date
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    /**
     * Sets the filter to filter until a specific end date
     * @param end The end date
     */
    public void setEnd(LocalDate end) {
        this.end = end.plusDays(1);
    }

    /**
     * Sets the filter to filter for crimes with their primary description (type) in types
     * @param types A set of Strings of primary descriptions.
     */
    public void setTypes(Set<String> types) {
        this.types = types;
    }

    /**
     * Sets the filter to filter for crimes with their location description in locations
     * @param locations A set of Strings of location descriptions
     */
    public void setLocations(Set<String> locations) {
        this.locations = locations;
    }

    /**
     * Sets the filter to filter for crimes with the given value of arrest
     * @param arrest boolean value for if there was an arrest
     */
    public void setArrest(boolean arrest) {
        this.arrest = arrest;
    }

    /**
     * Sets the filter to filter for crimes with the given value of domestic
     * @param domestic boolean value for if the crime was domestic
     */
    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    /**
     * Sets the filter to filter for crimes with the given beats
     * @param beatString A comma separated list of beats
     */
    public void setBeats(String beatString) {
        if (!beatString.isEmpty()) {
            for (String beat : beatString.split(",\\s*")) {
                beats.add(Integer.parseInt(beat));
            }
        }
    }

    /**
     * Sets the filter to filter for crimes with the given wards
     * @param wardString A comma separated list of wards
     */
    public void setWards(String wardString) {
        if (!wardString.isEmpty()) {
            for (String ward : wardString.split(",")) {
                wards.add(Integer.parseInt(ward));
            }
        }

    }


}





