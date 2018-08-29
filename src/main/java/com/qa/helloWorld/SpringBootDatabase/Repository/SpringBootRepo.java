package com.qa.helloWorld.SpringBootDatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.helloWorld.SpringBootDatabase.Model.SpringBootData;


@Repository
public interface SpringBootRepo extends JpaRepository<SpringBootData, Long> {

	
}
