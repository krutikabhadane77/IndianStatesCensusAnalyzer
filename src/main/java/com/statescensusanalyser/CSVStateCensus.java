package com.statescensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {

    @CsvBindByName (column = "State")
    private String state;

    @CsvBindByName (column = "Population")
    private long pop;

    @CsvBindByName (column = "AreaInSqKm")
    private int area;

    @CsvBindByName (column = "DensityPerSqKm")
    private int density;

    @Override
    public String toString() {
        return "IndiaStateCensus [ State = " + state + " , Population = " + pop +
                " , AreaInSqKm = " + area + " , density = " + density + " ]";
    }
}
