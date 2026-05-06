package com.example.thymeleaf_examples.repository;

import com.example.thymeleaf_examples.domain.DemoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoItemRepository  extends JpaRepository<DemoItem, Long> {

}
