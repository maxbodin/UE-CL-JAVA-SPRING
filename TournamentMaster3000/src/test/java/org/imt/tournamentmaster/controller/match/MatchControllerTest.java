package org.imt.tournamentmaster.controller.match;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSearchByEquipeAId() throws Exception {
        mockMvc.perform(get("/api/match/search")
                        .param("equipeAId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].equipeA.id").value(1))
                .andExpect(jsonPath("$[0].equipeB.id").value(2))
                .andExpect(jsonPath("$[0].status").value("EN_COURS"));
    }

    @Test
    public void testSearchByEquipeBId() throws Exception {
        mockMvc.perform(get("/api/match/search")
                        .param("equipeBId", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].equipeA.id").value(1))
                .andExpect(jsonPath("$[0].equipeB.id").value(2))
                .andExpect(jsonPath("$[0].status").value("EN_COURS"));
    }

    @Test
    public void testSearchByStatus() throws Exception {
        mockMvc.perform(get("/api/match/search")
                        .param("status", "EN_COURS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("EN_COURS"));
    }

    @Test
    public void testSearchByEquipeAIdAndStatus() throws Exception {
        mockMvc.perform(get("/api/match/search")
                        .param("equipeAId", "1")
                        .param("status", "EN_COURS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].equipeA.id").value(1))
                .andExpect(jsonPath("$[0].status").value("EN_COURS"));
    }

    @Test
    public void testSearchByEquipeBIdAndStatus() throws Exception {
        // Searching for matches where EquipeB has ID = 2 and status = "1"
        mockMvc.perform(get("/api/match/search")
                        .param("equipeBId", "2")
                        .param("status", "TERMINE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].equipeB.id").value(2))
                .andExpect(jsonPath("$[0].status").value("TERMINE"));
    }

    @Test
    public void testSearchNoResults() throws Exception {
        mockMvc.perform(get("/api/match/search")
                        .param("equipeAId", "999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testSearchWithMultipleParamsNoResults() throws Exception {
        mockMvc.perform(get("/api/match/search")
                        .param("equipeAId", "999")
                        .param("equipeBId", "888")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testSearchByEquipeAAndEquipeBId() throws Exception {
        mockMvc.perform(get("/api/match/search")
                        .param("equipeAId", "1")
                        .param("equipeBId", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].equipeA.id").value(1))
                .andExpect(jsonPath("$[0].equipeB.id").value(2));
    }
}
