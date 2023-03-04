package com.example.cicdjenkinscode.repository;

import com.example.cicdjenkinscode.entity.HelloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloEntityJpaRepository extends JpaRepository<HelloEntity, Long> {

    HelloEntity findByHelloValue(String helloValue);
}
