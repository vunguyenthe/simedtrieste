package com.small.business.service.energy;

import java.util.List;

import com.small.business.model.user.Energy;

public interface EnergyService {

    public List<Energy> getAllEnergy();

    public Energy getEnergyByRoomId(Long roomId);

    public long addEnergy(Energy Energy);

    public boolean deleteEnergyById(Long id);

    public boolean deleteAll();

    public boolean updateEnergy(Energy user);

}
