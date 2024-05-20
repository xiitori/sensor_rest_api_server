package ru.xiitori.springproject.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.xiitori.springproject.dto.MeasurementDTO;
import ru.xiitori.springproject.models.Measurement;
import ru.xiitori.springproject.services.MeasurementsService;
import ru.xiitori.springproject.util.ErrorResponse;
import ru.xiitori.springproject.util.ErrorUtils;
import ru.xiitori.springproject.util.SensorNotFoundException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;

    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public List<MeasurementDTO> getMeasurements() {
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO).toList();
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementsService.getRainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<ErrorResponse> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ErrorUtils.getResponse(bindingResult);
        }

        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementsService.addMeasurement(measurement);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(SensorNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(
                Collections.singletonList("This sensor is not registered")),
                HttpStatus.BAD_REQUEST);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
