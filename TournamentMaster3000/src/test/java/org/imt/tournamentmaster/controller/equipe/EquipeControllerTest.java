package org.imt.tournamentmaster.controller.equipe;

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
public class EquipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EquipeController equipeController;

    @Test
    public void testGetEquipeById() throws Exception {
        mockMvc.perform(get("/api/equipe/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Equipe A"))
                .andExpect(jsonPath("$.joueurs").isArray());
    }

    @Test
    public void testGetEquipeByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/equipe/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllEquipes() throws Exception {
        mockMvc.perform(get("/api/equipe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").isNumber());
    }

    @Test
    public void testCreateEquipe() throws Exception {
        String newEquipeJson = """
                    {
                        "nom": "Equipe B",
                        "joueurs": [
                            {"id": 1, "nom": "Doe", "prenom": "John", "numero": 1},
                            {"id": 2, "nom": "Doe", "prenom": "Jane 2", "numero": 2}
                        ]
                    }
                """;

        mockMvc.perform(post("/api/equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newEquipeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nom").value("Equipe B"))
                .andExpect(jsonPath("$.joueurs").isArray())
                .andExpect(jsonPath("$.joueurs.length()").value(2));
    }

    @Test
    public void testUpdateEquipe() throws Exception {
        String updatedEquipeJson = """
                    {
                        "nom": "Equipe A Updated",
                        "joueurs": [
                            {"id": 3, "nom": "Joueur 3", "prenom": "Prenom 3", "numero": 30}
                        ]
                    }
                """;

        mockMvc.perform(put("/api/equipe/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEquipeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Equipe A Updated"))
                .andExpect(jsonPath("$.joueurs").isArray())
                .andExpect(jsonPath("$.joueurs.length()").value(1));
    }

    @Test
    public void testUpdateEquipeNotFound() throws Exception {
        String updatedEquipeJson = """
                    {
                        "nom": "Nonexistent Equipe",
                        "joueurs": []
                    }
                """;

        mockMvc.perform(put("/api/equipe/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEquipeJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteEquipe() throws Exception {
        mockMvc.perform(delete("/api/equipe/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteEquipeNotFound() throws Exception {
        mockMvc.perform(delete("/api/equipe/999"))
                .andExpect(status().isNotFound());
    }
}