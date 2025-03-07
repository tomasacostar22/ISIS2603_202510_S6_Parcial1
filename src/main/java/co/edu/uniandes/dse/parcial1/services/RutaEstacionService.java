package co.edu.uniandes.dse.parcial1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.EstacionEntity;
import co.edu.uniandes.dse.parcial1.entities.RutaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.repositories.EstacionRepository;
import co.edu.uniandes.dse.parcial1.repositories.RutaRepository;


@Service
public class RutaEstacionService {

    @Autowired
    EstacionRepository estacionRepository;

    @Autowired
    RutaRepository rutaRepository;

    @Transactional
    public RutaEntity removeEstacionRuta(Long idEstacion, Long idRuta) throws EntityNotFoundException{

        Optional<EstacionEntity> estacion = estacionRepository.findById(idEstacion);
        Optional<RutaEntity> ruta = rutaRepository.findById(idRuta);

        if (estacion.isEmpty()){

            throw new EntityNotFoundException("No se encontro una estacion con el id proporcionado");

        }

        if (ruta.isEmpty()){

            throw new EntityNotFoundException("No se encontro una ruta con el id proporcionado");
        }


        if (!ruta.get().getTipo().equals("nocturna")){

            estacion.get().getRutas().remove(ruta.get());
            ruta.get().getEstaciones().remove(estacion.get());
            return ruta.get();
        }


        List<RutaEntity> estacionesDeRuta = estacion.get().getRutas();
        int rutasNocturnas = 0;

        for (RutaEntity rutaEntity : estacionesDeRuta) {
            if (rutaEntity.getTipo().equals("nocturna")){
                rutasNocturnas++;
            }
        }

        if (rutasNocturnas == 1){
            throw new EntityNotFoundException("No se puede eliminar la estacion de la ruta porque es la unica ruta nocturna que tiene la estacion");
        }

        estacion.get().getRutas().remove(ruta.get());
        ruta.get().getEstaciones().remove(estacion.get());
        return ruta.get();

    }
        
        
    

    public RutaEntity addEstacionRuta(Long idEstacion, Long idRuta) throws EntityNotFoundException{

        Optional<EstacionEntity> estacion = estacionRepository.findById(idEstacion);
        Optional<RutaEntity> ruta = rutaRepository.findById(idRuta);

        if (estacion.isEmpty()){

            throw new EntityNotFoundException("No se encontro una estacion con el id proporcionado");

        }

        if (ruta.isEmpty()){

            throw new EntityNotFoundException("No se encontro una ruta con el id proporcionado");
        }

        estacion.get().getRutas().add(ruta.get());
        ruta.get().getEstaciones().add(estacion.get());
        return ruta.get();


    }


}

