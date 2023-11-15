package com.example.basic;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.model.Course;
import com.example.basic.model.Major;
import com.example.basic.repository.CourseRepository;
import com.example.basic.repository.MajorRepository;

@SpringBootTest
class MajorCourseTests {
	@Autowired MajorRepository majorRepository;
	@Autowired CourseRepository courseRepository;

	@Test
	void contextLoads() {
		Major major = new Major();
		major.setName("IT");
		major.setMaxPrsn(10);
		major.setEbtbDate(new Date());
		majorRepository.save(major);

		Course course = new Course();
		course.setName("웹프로그래밍");
		course.setMajor(major);

		courseRepository.save(course);
	}

}
