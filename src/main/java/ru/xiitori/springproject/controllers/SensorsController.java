package ru.xiitori.springproject.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xiitori.springproject.dto.SensorDTO;
import ru.xiitori.springproject.models.Sensor;
import ru.xiitori.springproject.services.SensorsService;
import ru.xiitori.springproject.util.ErrorResponse;
import ru.xiitori.springproject.util.ErrorUtils;
import ru.xiitori.springproject.util.SensorValidator;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;

    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<ErrorResponse> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                        BindingResult bindingResult) {
        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);

        if (bindingResult.hasErrors()) {
            return ErrorUtils.getResponse(bindingResult);
        }

        sensorsService.addSensor(convertToSensor(sensorDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
