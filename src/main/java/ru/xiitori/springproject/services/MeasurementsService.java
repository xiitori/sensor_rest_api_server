package ru.xiitori.springproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xiitori.springproject.dto.MeasurementDTO;
import ru.xiitori.springproject.models.Measurement;
import ru.xiitori.springproject.models.Sensor;
import ru.xiitori.springproject.repositories.MeasurementsRepository;
import ru.xiitori.springproject.util.SensorNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        Sensor sensor = sensorsService.findSensorByName(measurement.getSensor().getName());
        if (sensor == null) {
            throw new SensorNotFoundException();
        }
        measurement.setSensor(sensor);

        measurementsRepository.save(measurement);
    }

    public int getRainyDaysCount() {
        return measurementsRepository.findMeasurementsByRainingTrue().size();
    }
}
