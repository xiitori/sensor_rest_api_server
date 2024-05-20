package ru.xiitori.springproject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {
    @NotNull(message = "Value can not be empty")
    @Min(value = -100, message = "Value can not be less than -100")
    @Max(value = 100, message = "Value can not be greater than 100")
    private Double value;

    @NotNull(message = "Raining can not be empty")
    private Boolean raining;

    @NotNull(message = "Sensor can not be empty")
    private SensorDTO sensor;

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }
}
