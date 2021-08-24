package seng202.group6.Controllers;

import seng202.group6.Models.Crime;

import java.util.ArrayList;
import java.util.Set;

public class Filter {
    /**
     * Filters by primary description of crime
     * @param crimes The input to be filtered
     * @param types A Set of strings representing the values to allow
     * @return The filtered list
     */
    public static ArrayList<Crime> filterByCrimeType(ArrayList<Crime> crimes, Set<String> types) {
        ArrayList<Crime> output = new ArrayList<>();
        for (Crime crime : crimes) {
            if (types.contains(crime.getPrimaryDescription())) {
                output.add(crime);
            }
        }
        return output;
    }

    /**
     * Filters by location description
     * @param crimes The input to be filtered
     * @param locations A Set of strings representing the values to allow
     * @return The filtered list
     */
    public static ArrayList<Crime> filterByLocation(ArrayList<Crime> crimes, Set<String> locations) {
        ArrayList<Crime> output = new ArrayList<>();
        for (Crime crime : crimes) {
            if (locations.contains(crime.getLocationDescription())) {
                output.add(crime);
            }
        }
        return output;
    }

    /**
     * Filters by beat
     * @param crimes The input to be filtered
     * @param beats A Set of Integers representing the values to allow
     * @return The filtered list
     */
    public static ArrayList<Crime> filterByBeat(ArrayList<Crime> crimes, Set<Integer> beats) {
        ArrayList<Crime> output = new ArrayList<>();
        for (Crime crime : crimes) {
            if (beats.contains(crime.getBeat())) {
                output.add(crime);
            }
        }
        return output;
    }

    /**
     * Filters by ward
     * @param crimes The input to be filtered
     * @param wards A Set of Integers representing the values to allow
     * @return The filtered list
     */
    public static ArrayList<Crime> filterByWard(ArrayList<Crime> crimes, Set<Integer> wards) {
        ArrayList<Crime> output = new ArrayList<>();
        for (Crime crime : crimes) {
            if (wards.contains(crime.getWard())) {
                output.add(crime);
            }
        }
        return output;
    }
    /**
     * Filters by if the crime involved an arrest
     * @param crimes The input to be filtered
     * @param arrested A boolean denoting if an arrest was involved
     * @return The filtered list
     */
    public static ArrayList<Crime> filterByArrest(ArrayList<Crime> crimes, boolean arrested) {
        ArrayList<Crime> output = new ArrayList<>();
        for (Crime crime : crimes) {
            if (crime.isArrest() == arrested) {
                output.add(crime);
            }
        }
        return output;
    }

    /**
     * Filters by if the crime involved an arrest
     * @param crimes The input to be filtered
     * @param domestic A boolean denoting if the crime was domestic
     * @return The filtered list
     */
    public static ArrayList<Crime> filterByDomestic(ArrayList<Crime> crimes, boolean domestic) {
        ArrayList<Crime> output = new ArrayList<>();
        for (Crime crime : crimes) {
            if (crime.isDomestic() == domestic) {
                output.add(crime);
            }
        }
        return output;
    }
}
