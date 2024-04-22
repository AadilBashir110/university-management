package com.adil.universitymanagement.repository;

import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
