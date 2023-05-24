package me.alextodea.testioapplication.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InstructorRequestDto {
    @Size(min = 10)
    private String instructorRequestText;
}
