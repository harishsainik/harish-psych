package com.psych.harishpsych.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentWriterRepository extends JpaRepository<ContentWriter, Long> {
}
