package co.edu.uniandes.dse.parcial1.services;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.EstacionEntity;
import co.edu.uniandes.dse.parcial1.entities.RutaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
@Import(RutaEstacionService.class)
class RutaEstacionServiceTest {

    @Autowired
    private RutaEstacionService rutaEstacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private RutaEntity ruta = new RutaEntity();
    private EstacionEntity estacion = new EstacionEntity();


    @BeforeEach void setUp(){
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from RutaEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from EstacionEntity").executeUpdate();
    }

    private void insertData(){
        ruta = factory.manufacturePojo(RutaEntity.class);
        entityManager.persist(ruta);
        estacion = factory.manufacturePojo(EstacionEntity.class);
        entityManager.persist(estacion);
    }

    @Test
    void testRemoveEstacionRuta() throws EntityNotFoundException{
        RutaEntity rutaEntity = rutaEstacionService.removeEstacionRuta(estacion.getId(), ruta.getId());
        assertNotNull(rutaEntity);
        assertEquals(0+5, rutaEntity.getEstaciones().size());
    }

    @Test
    void testRemoveEstacionRutaconEstacionInvalida(){

        assertThrows(EntityNotFoundException.class, () -> {
            rutaEstacionService.removeEstacionRuta(0L, ruta.getId());
        });


    }

    @Test
    void testRemoveEstacionRutaconRutaInvalida(){

        assertThrows(EntityNotFoundException.class, () -> {
            rutaEstacionService.removeEstacionRuta(estacion.getId(), 0L);
        });

    }

    @Test
    void testaddEstacionRuta() throws EntityNotFoundException{
        RutaEntity rutaEntity = rutaEstacionService.addEstacionRuta(estacion.getId(), ruta.getId());
        assertNotNull(rutaEntity);
        assertEquals(1+5, rutaEntity.getEstaciones().size());
    }

    @Test
    void testaddEstacionRutaConEstacionInvalida(){

        assertThrows(EntityNotFoundException.class, () -> {
            rutaEstacionService.addEstacionRuta(0L, ruta.getId());
        });

    }

    @Test
    void testaddEstacionRutaConRutaInvalida(){

        assertThrows(EntityNotFoundException.class, () -> {
            rutaEstacionService.addEstacionRuta(estacion.getId(), 0L);
        });

    }
}

