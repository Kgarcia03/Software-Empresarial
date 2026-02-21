package com.example.holidays.domain;
import jakarta.persistence.*;
import lombok.*;
@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(indexes = @Index(columnList = "country_id"))
public class Holiday {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional=false)
  private Country country;
  @ManyToOne(optional=false)
  private HolidayType type;
  @Column(nullable=false)
  private String name;
  private Integer month;
  private Integer day;
  private Integer easterOffset;
}