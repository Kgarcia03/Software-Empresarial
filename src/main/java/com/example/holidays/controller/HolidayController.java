package com.example.holidays.controller;
import com.example.holidays.domain.CalendarDay;
import com.example.holidays.service.HolidayService;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HolidayController {
  private final HolidayService service;
  @GetMapping("/holidays/validate")
  public HolidayService.ValidationResult validate(
      @RequestParam @NotNull Long countryId,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return service.validate(countryId, date);
  }
  @GetMapping("/holidays/{countryId}/{year}")
  public List<String> list(@PathVariable Long countryId, @PathVariable @Min(1900) @Max(2100) int year) {
    return service.listHolidays(countryId, year).stream().map(LocalDate::toString).toList();
  }
  @PostMapping("/calendar/generate")
  public Map<String, Object> generate(@RequestParam Long countryId, @RequestParam int year) {
    boolean ok = service.generateYearCalendar(countryId, year);
    return Map.of("success", ok);
  }
  @GetMapping("/calendar/{countryId}/{year}")
  public List<CalendarDay> calendar(@PathVariable Long countryId, @PathVariable int year) {
    return service.getYearCalendar(countryId, year);
  }
}