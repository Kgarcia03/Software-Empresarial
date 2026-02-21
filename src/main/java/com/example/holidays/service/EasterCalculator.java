package com.example.holidays.service;
import java.time.LocalDate;
public class EasterCalculator {
  public static LocalDate easterSunday(int year) {
    int a = year % 19;
    int b = year % 4;
    int c = year % 7;
    int d = (19 * a + 24) % 30;
    int dias = d + (2*b + 4*c + 6*d + 5) % 7;
    LocalDate palmSunday = LocalDate.of(year, 3, 15).plusDays(dias);
    return palmSunday.plusDays(7);
  }
  public static LocalDate nextMonday(LocalDate date) {
    int dow = date.getDayOfWeek().getValue();
    return dow == 1 ? date : date.plusDays((8 - dow) % 7);
  }
}