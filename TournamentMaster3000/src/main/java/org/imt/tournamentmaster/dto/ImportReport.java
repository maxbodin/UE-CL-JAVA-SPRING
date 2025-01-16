package org.imt.tournamentmaster.dto;

import org.imt.tournamentmaster.model.match.Match;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportReport {

    private final List<Match> successfulMatches = new ArrayList<>();
    private final List<FailedMatch> failedMatches = new ArrayList<>();
    private final Date date;

    public ImportReport() {
         date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void addSuccessfulMatch(Match match) {
        successfulMatches.add(match);
    }

    public void addFailedMatch(Match match, String reason) {
        failedMatches.add(new FailedMatch(match, reason));
    }

    public boolean hasFailures() {
        return !failedMatches.isEmpty();
    }

    public List<Match> getSuccessfulMatches() {
        return successfulMatches;
    }

    public List<FailedMatch> getFailedMatches() {
        return failedMatches;
    }

    public String toString() {
        if (successfulMatches.isEmpty() && failedMatches.isEmpty()) {
            return "No matches were imported.";
        }
        if (successfulMatches.isEmpty()) {
            return "All matches failed to import.";
        }

        StringBuilder report = new StringBuilder("Import successfully completed.\n");
        report.append(successfulMatches.size()).append(" were imported and ");
        report.append(failedMatches.size()).append(" failed to import.\n");
        report.append("--------------------\n");
        report.append("Report date:").append(date).append("\n");
        report.append("--------------------\n");

        for (Match match : successfulMatches) {
            report.append("The winner team of the match ").append(match.getId())
                    .append("is ").append(match.determineWinner().getNom())
                    .append("\n");
        }
        report.append("--------------------\n");
        for (FailedMatch failedMatch : failedMatches) {
            report.append("The match ").append(failedMatch.match().getId())
                    .append("failed due to ").append(failedMatch.reason())
                    .append("\n");
        }

        return report.toString();
    }

    public record FailedMatch(Match match, String reason) {
    }
}