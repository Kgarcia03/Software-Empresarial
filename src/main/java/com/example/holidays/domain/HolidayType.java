package com.example.holidays.domain;
import jakarta.persistence.*;
import lombok.*;
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class HolidayType {
  @Id
  private Integer id;
  @Column(nullable=false)
  private String name;
  @Column(nullable=false)
  private Integer mode;
}