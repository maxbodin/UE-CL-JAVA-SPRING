package org.imt.tournamentmaster.controller.equipe;

import jakarta.validation.ConstraintViolationException;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class EquipeControllerTest {

    @Autowired
    private EquipeController equipeController;

    @Test
    public void testGetEquipeById() {
        Equipe equipe1 = equipeController.getById(1L).getBody();

        // assert
        Assertions.assertNotNull(equipe1);
        Assertions.assertEquals(1L, equipe1.getId());
        Assertions.assertEquals("Equipe 1", equipe1.getNom());
        Assertions.assertNotNull(equipe1.getJoueurs());

        Equipe equipe2 = equipeController.getById(2L).getBody();

        // assert
        Assertions.assertNotNull(equipe2);
        Assertions.assertEquals(2L, equipe2.getId());
        Assertions.assertEquals("Equipe 2", equipe2.getNom());
        Assertions.assertNotNull(equipe2.getJoueurs());
    }

    @Test
    public void testGetNonExistingEquipeById_shouldBeNull() {
        HttpStatusCode status = equipeController.getById(42L).getStatusCode();

        // assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND, status);
    }

    @Test
    public void testGetAll() {
        ResponseEntity<List<Equipe>> response = equipeController.getAll();

        // assert
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
    }

    @Test
    public void testCreateEquipe() {
        Equipe equipe = new Equipe();
        equipe.setNom("Equipe 3");

        // assert
        Assertions.assertThrows(ConstraintViolationException.class, () -> equipeController.createEquipe(new Equipe()));
    }
}