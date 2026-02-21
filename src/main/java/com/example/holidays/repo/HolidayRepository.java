package com.example.holidays.repo;
import com.example.holidays.domain.Holiday;
import com.example.holidays.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HolidayRepository extends JpaRepository<Holiday,Long>{
  List<Holiday> findByCountry(Country country);
}