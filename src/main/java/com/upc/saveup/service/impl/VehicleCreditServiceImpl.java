package com.upc.saveup.service.impl;

import com.upc.saveup.dto.VehicleCreditDto;
import com.upc.saveup.exception.ResourceNotFoundException;
import com.upc.saveup.model.User;
import com.upc.saveup.model.VehicleCredit;
import com.upc.saveup.repository.UserRepository;
import com.upc.saveup.repository.VehicleCreditRepository;
import com.upc.saveup.service.VehicleCreditService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VehicleCreditServiceImpl implements VehicleCreditService {
    private VehicleCreditRepository vehicleCreditRepository;
    private UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public VehicleCreditServiceImpl(VehicleCreditRepository vehicleCreditRepository,
                                    UserRepository userRepository,
                                    ModelMapper modelMapper, JdbcTemplate jdbcTemplate) {
        this.vehicleCreditRepository = vehicleCreditRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public VehicleCreditDto createVehicleCredit(VehicleCreditDto vehicleCreditDto) {
        VehicleCredit vehicleCredit=DtoToEntity(vehicleCreditDto);

        User user = userRepository.findById(vehicleCreditDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con id: "
                        + vehicleCreditDto.getUserId()));
        vehicleCredit.setUser(user);
        return EntityToDto(vehicleCreditRepository.save(vehicleCredit));

    }

    @Override
    public List<VehicleCredit> getVehiclesCreditsByUser(int userId) {
        return vehicleCreditRepository.findByUserId(userId);
    }

    @Override
    public void deleteVehiclesCreditsForUser(int userId) {
        vehicleCreditRepository.deleteByUserId(userId);
    }


    private VehicleCreditDto EntityToDto(VehicleCredit vehicleCredit)
    { return modelMapper.map(vehicleCredit, VehicleCreditDto.class); }

    private VehicleCredit DtoToEntity(VehicleCreditDto vehicleCreditDto) {
        return modelMapper.map(vehicleCreditDto, VehicleCredit.class);
    }
}
