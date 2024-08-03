package com.example.creacion_modelos.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ValueStats {

    public Double           value;
    public LocalDateTime    date;

    public ValueStats() {
        this.value  = 0.0;
        this.date   = LocalDateTime.now();
    }

    public ValueStats(Double value, String date) {
        this.value  = value;
        this.date   = LocalDateTime.parse(date);
    }
}
