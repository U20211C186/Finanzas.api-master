package com.upc.saveup.service;

import com.upc.saveup.dto.VehicleCreditDto;
import com.upc.saveup.model.VehicleCredit;

import java.util.List;
import java.util.Map;

public interface VehicleCreditService {
    public abstract VehicleCreditDto createVehicleCredit(VehicleCreditDto vehicleCreditDto);
    public abstract List<VehicleCredit> getVehiclesCreditsByUser(int userId);
    public abstract void deleteVehiclesCreditsForUser(int userId);


}
