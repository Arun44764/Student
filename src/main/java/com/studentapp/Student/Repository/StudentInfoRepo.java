package com.studentapp.Student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentapp.Student.model.StudentInfo;

public interface StudentInfoRepo extends JpaRepository<StudentInfo,Integer> {

}
