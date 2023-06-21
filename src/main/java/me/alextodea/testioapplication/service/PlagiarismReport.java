package me.alextodea.testioapplication.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlagiarismReport {
    private Long submissionId;
    private double plagiarismPercentage;

    public PlagiarismReport(Long submissionId, double plagiarismPercentage) {
        this.submissionId = submissionId;
        this.plagiarismPercentage = plagiarismPercentage;
    }
}


