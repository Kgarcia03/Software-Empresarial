package com.example.holidays.repo;
import com.example.holidays.domain.CalendarDay;
import com.example.holidays.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
public interface CalendarDayRepository extends JpaRepository<CalendarDay,Long>{
  List<CalendarDay> findByCountryAndDateBetween(Country c, LocalDate start, LocalDate end);
  boolean existsByCountryAndDate(Country c, LocalDate date);
}