package org.imt.tournamentmaster.controller.resultat;

import jakarta.validation.constraints.Min;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.service.resultat.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resultat")
@Validated
public class ResultatController {

    private final ResultatService resultatService;

    @Autowired
    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") long id) {
        Optional<Resultat> resultat = resultatService.getById(id);

        return resultat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Resultat>> getAll() {
        return ResponseEntity.ok(resultatService.getAll());
    }
}
