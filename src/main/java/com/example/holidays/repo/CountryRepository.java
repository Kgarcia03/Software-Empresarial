package com.example.holidays.repo;
import com.example.holidays.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CountryRepository extends JpaRepository<Country,Long>{}