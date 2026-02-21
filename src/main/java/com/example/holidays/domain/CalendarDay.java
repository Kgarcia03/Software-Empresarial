package com.example.holidays.domain;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(indexes = {@Index(columnList = "country_id, date", unique = true)})
public class CalendarDay {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional=false)
  private Country country;
  @Column(nullable=false)
  private LocalDate date;
  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private Kind kind;
  public enum Kind { WORKDAY, WEEKEND, HOLIDAY }
}