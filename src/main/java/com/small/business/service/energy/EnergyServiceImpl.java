package com.small.business.service.energy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.small.business.dao.energy.EnergyDao;
import com.small.business.model.user.Energy;

@Service("EnergyService")
public class EnergyServiceImpl implements EnergyService {

    @Autowired
    EnergyDao energyDao;
    public List<Energy> getAllEnergy() {
    	return energyDao.getAllEnergy();
    	
    }

    public Energy getEnergyByRoomId(Long roomId) {
    	return energyDao.getEnergyByRoomId(roomId);
    }

    public long addEnergy(Energy energy) {
    	return energyDao.addEnergy(energy);
    }

    public boolean deleteEnergyById(Long id) {
    	return energyDao.deleteEnergyById(id);
    }

    public boolean deleteAll() {
    	return false;
    }

    public boolean updateEnergy(Energy energy) {
    	return energyDao.updateEnergy(energy);
    }

}
