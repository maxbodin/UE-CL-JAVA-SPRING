package org.imt.tournamentmaster.service.reporting;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.imt.tournamentmaster.model.resultat.Resultat;

public interface ReportingService {

    String report(Resultat resultat) throws JsonProcessingException;
}
