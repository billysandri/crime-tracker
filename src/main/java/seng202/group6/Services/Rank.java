package seng202.group6.Services;

import seng202.group6.Models.Crime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

//Todo complete rank class to involve dangerous areas
//Todo make sure crime frequency is ranked correctly
//Todo test

public class Rank {
    /**
     * Ranks data by passed in String parameter crime type
     * @param crimes The input to count types
     * @param type   A string of primary type that will be counted
     * @return The number of certain crime type
     */
    public static int frequencyOfCrime(ArrayList<Crime> crimes, String type) {
        int freqCount = 0;
        for (Crime crime : crimes) {
            if (crime.getPrimaryDescription().equals(type)) {
                freqCount++;
            }
        }
        return freqCount;
    }

    /**
     * Sorts data by frequency of crime type
     * @param crimes The input to be ranked
     * @return Array list of type String in decreasing order of crime type
     */
    public static ArrayList<String> rankedTypeList(ArrayList<Crime> crimes) {
        ArrayList<String> initialList = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        for (Crime crime : crimes) {
            initialList.add(crime.getPrimaryDescription());
        }
        initialList.sort(Comparator.comparing(i -> Collections.frequency(initialList, i)).reversed());

        for (String element : initialList) {
            if (!output.contains(element)) {
                output.add(element);
            }
        }
        return output;
    }

    /**
     * Ranks data by passed in String parameter area
     * @param crimes The input to count types
     * @param area A string of the block area that will be counted
     * @return The number of certain crime in an area
     */
    public static int frequencyOfArea(ArrayList<Crime> crimes, String area) {
        int freqCount = 0;
        for (Crime crime : crimes) {
            if (crime.getBlock().substring(0,4).equals(area.substring(0,4))) {
                freqCount++;
            }
        }
        return freqCount;
    }

    /**
     * Sorts data by frequency of crime area
     * @param crimes The input to be ranked
     * @return Array list of type String in decreasing order of crime area
     */
    public static ArrayList<String> rankedAreaList(ArrayList<Crime> crimes) {
        ArrayList<String> initialList = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        for (Crime crime : crimes) {
            initialList.add(crime.getBlock().substring(0,4));
        }
        initialList.sort(Comparator.comparing(i -> Collections.frequency(initialList, i)).reversed());

        for (String element : initialList) {
            if (!output.contains(element)) {
                output.add(element);
            }
        }
        return output;
    }
}
