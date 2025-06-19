package com.example.library.controller;

import com.example.library.dto.ReaderLetterStatsDto;
import com.example.library.service.ReaderStatsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    @Autowired
    private ReaderStatsService readerStatsService;

    @GetMapping("/stats/by-letter")
    public ReaderLetterStatsDto getReadersByLetterStats(
            @RequestParam String letter,
            @RequestParam(required = false, defaultValue = "2000") int minYear) {
        return readerStatsService.getStatsByLetter(letter, minYear);
    }
}
