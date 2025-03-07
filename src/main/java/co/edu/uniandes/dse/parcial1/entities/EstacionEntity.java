package co.edu.uniandes.dse.parcial1.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class EstacionEntity extends BaseEntity {
    private String name;
    private String direccion;
    private Integer capacidad;

    @ManyToMany(mappedBy = "estaciones")
    private List<RutaEntity> rutas;

}
