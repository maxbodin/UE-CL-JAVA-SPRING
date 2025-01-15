package org.imt.tournamentmaster.service.reporting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.springframework.stereotype.Component;

@Component
public class JsonReportingService implements ReportingService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String report(Resultat resultat) throws JsonProcessingException {
        return objectMapper.writeValueAsString(resultat);
    }
}
