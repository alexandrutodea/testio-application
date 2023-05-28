package me.alextodea.testioapplication.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExerciseDto {
    @Size(min = 10) @NotEmpty
    private String description;
    @Size(min = 25) @NotEmpty
    private String mainClass;
    @Size(min = 25) @NotEmpty
    private String testClass;
    @Size(min = 10) @NotEmpty
    private String prefilledCode;
}
