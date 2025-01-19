package org.imt.tournamentmaster.controller.joueur;

import org.imt.tournamentmaster.controller.equipe.JoueurController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class JoueurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JoueurController joueurController;

    @Test
    public void testGetJoueurById() throws Exception {
        mockMvc.perform(get("/api/joueur/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("John"))
                .andExpect(jsonPath("$.prenom").value("Doe"))
                .andExpect(jsonPath("$.numero").value(10));
    }

    @Test
    public void testGetJoueurById_NotFound() throws Exception {
        mockMvc.perform(get("/api/joueur/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllJoueurs() throws Exception {
        mockMvc.perform(get("/api/joueur"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNumber()) // Assert array response
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].nom").isNotEmpty());
    }

    @Test
    public void testAddJoueur() throws Exception {
        String newJoueur = """
                {
                    "nom": "John",
                    "prenom": "Doe",
                    "numero": 10
                }
                """;

        mockMvc.perform(post("/api/joueur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newJoueur))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nom").value("John"))
                .andExpect(jsonPath("$.prenom").value("Doe"))
                .andExpect(jsonPath("$.numero").value(10));
    }

    @Test
    public void testUpdateJoueur() throws Exception {
        String updatedJoueur = """
                {
                    "nom": "Jane",
                    "prenom": "Doe",
                    "numero": 15
                }
                """;

        mockMvc.perform(put("/api/joueur/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJoueur))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Jane"))
                .andExpect(jsonPath("$.prenom").value("Doe"))
                .andExpect(jsonPath("$.numero").value(15));
    }

    @Test
    public void testUpdateJoueur_NotFound() throws Exception {
        String updatedJoueur = """
                {
                    "nom": "Jane",
                    "prenom": "Doe",
                    "numero": 15
                }
                """;

        mockMvc.perform(put("/api/joueur/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJoueur))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteJoueur() throws Exception {
        mockMvc.perform(delete("/api/joueur/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteJoueur_NotFound() throws Exception {
        mockMvc.perform(delete("/api/joueur/999"))
                .andExpect(status().isNotFound());
    }
}
