package com.small.business.dao.energy;

import java.util.List;

import com.small.business.model.user.Energy;

public interface EnergyDao {

    public List<Energy> getAllEnergy();

    public Energy getEnergyByRoomId(Long roomId);

    public long addEnergy(Energy energy);

    public boolean deleteEnergyById(Long id);

    public boolean deleteAll();

    public boolean updateEnergy(Energy energy);


}
