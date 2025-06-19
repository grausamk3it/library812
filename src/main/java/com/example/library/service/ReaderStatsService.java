package com.example.library.service;

import com.example.library.dto.ReaderLetterStatsDto;
import com.example.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderStatsService {
    private final ReaderRepository readerRepository;

    public ReaderLetterStatsDto getStatsByLetter(String letter, int minYear) {
        List<Object[]> yearStats = readerRepository.countReadersByLetterGroupByYear(letter, minYear);

        List<ReaderLetterStatsDto.YearStats> stats = yearStats.stream()
                .map(row -> {
                    int year = (int) row[0];
                    int count = ((Number) row[1]).intValue();
                    List<String> samples = readerRepository.findSampleSurnames(letter, year);
                    return new ReaderLetterStatsDto.YearStats(year, count, samples); // Теперь конструктор существует
                })
                .collect(Collectors.toList());

        int total = stats.stream().mapToInt(ReaderLetterStatsDto.YearStats::getCount).sum();

        return new ReaderLetterStatsDto(letter, total, stats);
    }
}