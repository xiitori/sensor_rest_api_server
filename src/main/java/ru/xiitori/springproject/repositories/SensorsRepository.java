package ru.xiitori.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xiitori.springproject.models.Sensor;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    public Sensor findByName(String name);
}
