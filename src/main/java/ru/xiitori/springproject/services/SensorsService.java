package ru.xiitori.springproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xiitori.springproject.models.Sensor;
import ru.xiitori.springproject.repositories.SensorsRepository;
import ru.xiitori.springproject.util.SensorNotFoundException;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional
    public void addSensor(Sensor sensor) {
        sensorsRepository.save(sensor);
    }

    public Sensor findSensorById(int id) {
        return sensorsRepository.findById(id).orElseThrow(SensorNotFoundException::new);
    }

    public Sensor findSensorByName(String name) {
        return sensorsRepository.findByName(name);
    }
}
