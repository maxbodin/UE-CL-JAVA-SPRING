package org.imt.tournamentmaster.service.reporting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.imt.tournamentmaster.TestUtils;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class JsonReportingServiceTest {

    private final static String EXPECTED_REPORT = "{\"match\":{\"equipeA\":{\"nom\":\"Equipe 1\",\"joueurs\":[{\"nom\":\"Doe\",\"prenom\":\"John\",\"numero\":1},{\"nom\":\"Doe\",\"prenom\":\"Jane\",\"numero\":2},{\"nom\":\"Doe\",\"prenom\":\"Jack\",\"numero\":3}]},\"equipeB\":{\"nom\":\"Equipe 2\",\"joueurs\":[{\"nom\":\"Doe\",\"prenom\":\"Jill\",\"numero\":4},{\"nom\":\"Doe\",\"prenom\":\"Jim\",\"numero\":5},{\"nom\":\"Doe\",\"prenom\":\"Jenny\",\"numero\":6}]},\"rounds\":[{\"equipeA\":{\"nom\":\"Equipe 1\",\"joueurs\":[{\"nom\":\"Doe\",\"prenom\":\"John\",\"numero\":1},{\"nom\":\"Doe\",\"prenom\":\"Jane\",\"numero\":2},{\"nom\":\"Doe\",\"prenom\":\"Jack\",\"numero\":3}]},\"equipeB\":{\"nom\":\"Equipe 2\",\"joueurs\":[{\"nom\":\"Doe\",\"prenom\":\"Jill\",\"numero\":4},{\"nom\":\"Doe\",\"prenom\":\"Jim\",\"numero\":5},{\"nom\":\"Doe\",\"prenom\":\"Jenny\",\"numero\":6}]},\"scoreA\":1,\"scoreB\":2,\"roundNumber\":1},{\"equipeA\":{\"nom\":\"Equipe 1\",\"joueurs\":[{\"nom\":\"Doe\",\"prenom\":\"John\",\"numero\":1},{\"nom\":\"Doe\",\"prenom\":\"Jane\",\"numero\":2},{\"nom\":\"Doe\",\"prenom\":\"Jack\",\"numero\":3}]},\"equipeB\":{\"nom\":\"Equipe 2\",\"joueurs\":[{\"nom\":\"Doe\",\"prenom\":\"Jill\",\"numero\":4},{\"nom\":\"Doe\",\"prenom\":\"Jim\",\"numero\":5},{\"nom\":\"Doe\",\"prenom\":\"Jenny\",\"numero\":6}]},\"scoreA\":3,\"scoreB\":4,\"roundNumber\":2},{\"equipeA\":{\"nom\":\"Equipe 1\",\"joueurs\":[{\"nom\":\"Doe\",\"prenom\":\"John\",\"numero\":1},{\"nom\":\"Doe\",\"prenom\":\"Jane\",\"numero\":2},{\"nom\":\"Doe\",\"prenom\":\"Jack\",\"numero\":3}]},\"equipeB\":{\"nom\":\"Equipe 2\",\"joueurs\":[{\"nom\":\"Doe\",\"prenom\":\"Jill\",\"numero\":4},{\"nom\":\"Doe\",\"prenom\":\"Jim\",\"numero\":5},{\"nom\":\"Doe\",\"prenom\":\"Jenny\",\"numero\":6}]},\"scoreA\":5,\"scoreB\":6,\"roundNumber\":3}],\"status\":\"TERMINE\"}}";

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(JsonReportingServiceTest.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ReportingService reportingService;

    @Test
    public void testGenerateReport() {
        // create a resultat
        Resultat resultat = TestUtils.generateResultat();

        // report
        try {
            String report = reportingService.report(resultat);
            logger.info(report);

            Resultat expected = objectMapper.readValue(EXPECTED_REPORT.getBytes(StandardCharsets.UTF_8), Resultat.class);
            Resultat actual = objectMapper.readValue(report.getBytes(StandardCharsets.UTF_8), Resultat.class);

            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            fail("Error while reporting", e);
        }
    }
}
