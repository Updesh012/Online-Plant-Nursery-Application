package com.masai;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo2 extends JpaRepository<Seed, Integer>{

}