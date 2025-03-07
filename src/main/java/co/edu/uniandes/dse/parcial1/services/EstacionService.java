package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.EstacionEntity;
import co.edu.uniandes.dse.parcial1.repositories.EstacionRepository;

@Service
public class EstacionService {

    @Autowired
    private EstacionRepository estacionRepository;

    @Transactional
    public void createEstacion(EstacionEntity estacion) {

        estacionRepository.save(estacion);
    }

    
    
}
