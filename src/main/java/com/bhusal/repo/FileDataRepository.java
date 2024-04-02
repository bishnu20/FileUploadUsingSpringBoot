package com.bhusal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhusal.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Integer> {

}
