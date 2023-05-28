package me.alextodea.testioapplication.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SubmissionDto {
    @Size(min = 25)
    private String submission;
}
