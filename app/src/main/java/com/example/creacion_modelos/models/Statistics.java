package com.example.creacion_modelos.models;

import java.util.ArrayList;
import java.util.Date;

public class Statistics {

    public String                   materialName;
    public ArrayList<ValueStats>    gains;
    public ArrayList<ValueStats>    weights;
    public double                   totalGain;
    public double                   totalWeight;

    public Statistics() {
        this.materialName   = "";
        this.gains          = new ArrayList<ValueStats>();
        this.weights        = new ArrayList<ValueStats>();
        this.totalGain      = 0;
        this.totalWeight    = 0;
    }

    public Statistics(String materialName, ArrayList<ValueStats> gains, ArrayList<ValueStats> weights) {
        this.materialName   = materialName;
        this.gains          = gains;
        this.weights        = weights;

        calculateTotalGain();
        calculateTotalWeight();
    }

    public Statistics(String materialName, ArrayList<ValueStats> gains, ArrayList<ValueStats> weights, double totalGain, double totalWeight) {
        this.materialName   = materialName;
        this.gains          = gains;
        this.weights        = weights;
        this.totalGain      = totalGain;
        this.totalWeight    = totalWeight;

        calculateTotalGain();
        calculateTotalWeight();
    }

    public void calculateTotalGain(){
        for (ValueStats value : gains) {
            this.totalGain += value.value;
        }
    }

    public void calculateTotalWeight(){
        for (ValueStats value : weights) {
            this.totalWeight += value.value;
        }
    }
}
