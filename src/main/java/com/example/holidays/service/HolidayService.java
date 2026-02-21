package com.example.holidays.service;
import com.example.holidays.domain.*;
import com.example.holidays.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class HolidayService {
  private final CountryRepository countryRepo;
  private final HolidayRepository holidayRepo;
  private final CalendarDayRepository calendarRepo;
  public record ValidationResult(boolean holiday, String name, LocalDate date, String message) {}
  public List<LocalDate> listHolidays(Long countryId, int year) {
    Country country = countryRepo.findById(countryId)
        .orElseThrow(() -> new IllegalArgumentException("País no encontrado: " + countryId));
    List<Holiday> defs = holidayRepo.findByCountry(country);
    LocalDate easter = EasterCalculator.easterSunday(year);
    List<LocalDate> result = new ArrayList<>();
    for (Holiday h : defs) {
      LocalDate date;
      switch (h.getType().getMode()) {
        case 1 -> date = LocalDate.of(year, h.getMonth(), h.getDay());
        case 2 -> date = EasterCalculator.nextMonday(LocalDate.of(year, h.getMonth(), h.getDay()));
        case 3 -> date = easter.plusDays(h.getEasterOffset());
        case 4 -> date = EasterCalculator.nextMonday(easter.plusDays(h.getEasterOffset()));
        default -> throw new IllegalStateException("Tipo no soportado: " + h.getType().getMode());
      }
      result.add(date);
    }
    return result.stream().distinct().sorted().collect(Collectors.toList());
  }
  public ValidationResult validate(Long countryId, LocalDate date) {
    int year = date.getYear();
    List<LocalDate> holidays = listHolidays(countryId, year);
    boolean isHoliday = holidays.contains(date);
    if (isHoliday) {
      return new ValidationResult(true, "Festivo", date, "Es festivo");
    }
    return new ValidationResult(false, null, date, "No es festivo");
  }
  @Transactional
  public boolean generateYearCalendar(Long countryId, int year) {
    Country country = countryRepo.findById(countryId)
        .orElseThrow(() -> new IllegalArgumentException("País no encontrado: " + countryId));
    List<LocalDate> holidays = listHolidays(countryId, year);
    LocalDate start = LocalDate.of(year, 1, 1);
    LocalDate end = LocalDate.of(year, 12, 31);
    for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
      if (calendarRepo.existsByCountryAndDate(country, d)) continue;
      boolean isHoliday = holidays.contains(d);
      CalendarDay.Kind kind = isHoliday
          ? CalendarDay.Kind.HOLIDAY
          : (d.getDayOfWeek().getValue() >= 6 ? CalendarDay.Kind.WEEKEND : CalendarDay.Kind.WORKDAY);
      calendarRepo.save(new CalendarDay(null, country, d, kind));
    }
    return true;
  }
  public List<CalendarDay> getYearCalendar(Long countryId, int year) {
    Country country = countryRepo.findById(countryId)
        .orElseThrow(() -> new IllegalArgumentException("País no encontrado: " + countryId));
    LocalDate start = LocalDate.of(year,1,1);
    LocalDate end = LocalDate.of(year,12,31);
    return calendarRepo.findByCountryAndDateBetween(country, start, end);
  }
}