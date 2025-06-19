package com.example.library.repository;

import com.example.library.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

    @Query("SELECT YEAR(r.registrationDate) as year, COUNT(r) as count " +
            "FROM Reader r " +
            "WHERE r.lastName LIKE :letter% " +
            "AND YEAR(r.registrationDate) >= :minYear " +
            "GROUP BY YEAR(r.registrationDate)")
    List<Object[]> countReadersByLetterGroupByYear(@Param("letter") String letter,
                                                   @Param("minYear") int minYear);

    @Query("SELECT DISTINCT r.lastName " +
            "FROM Reader r " +
            "WHERE r.lastName LIKE :letter% " +
            "AND YEAR(r.registrationDate) = :year " +
            "ORDER BY r.lastName LIMIT 3")
    List<String> findSampleSurnames(@Param("letter") String letter,
                                    @Param("year") int year);
}