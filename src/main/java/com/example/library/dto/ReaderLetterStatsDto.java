package com.example.library.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderLetterStatsDto {
    private String letter;
    private int totalCount;
    private List<YearStats> yearsStats;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class YearStats {
        private int year;
        private int count;
        private List<String> sampleSurnames;
    }
}