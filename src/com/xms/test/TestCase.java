package com.xms.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xms.entity.Course;
import com.xms.entity.CourseDirection;
import com.xms.service.MainService;

public class TestCase {
	
//	��ѯȫ���γ̷���
	@Test
	public void testOne() {
		
		ClassPathXmlApplicationContext context =
			new ClassPathXmlApplicationContext("applicationContext.xml")	;
		
		MainService mainService = context
			.getBean("mainServiceImpl",MainService.class);
		
		List<CourseDirection> courseDirections 
			= mainService.findAllCourseDirection();
		
		for (CourseDirection courseDirection : courseDirections) {
			System.out.println(courseDirection.getId());
			System.out.println(courseDirection.getName());
			System.out.println(courseDirection.getPicture_url());
			System.out.println("======================");
		}
		
		context.close();
		
	}
	
//	��ѯʵս�Ƽ��γ�
	@Test
	public void testTwo() {
		
		ClassPathXmlApplicationContext context =
			new ClassPathXmlApplicationContext("applicationContext.xml")	;
		
		MainService mainService = context
			.getBean("mainServiceImpl",MainService.class);
		
		List<Course> courses = mainService.findDemoCourse();
		
		for (Course course : courses) {
			System.out.println(course.getId());
			System.out.println(course.getName());
			System.out.println(course.getPrice());
			System.out.println("=============");
		}
		
		context.close();
		
	}
	
//	��ѯ�������ſγ�
	@Test
	public void testThree() {
		
		ClassPathXmlApplicationContext context =
			new ClassPathXmlApplicationContext("applicationContext.xml")	;
		
		MainService mainService = context
			.getBean("mainServiceImpl",MainService.class);
		
		List<Course> courses = mainService.findNewCourse();
		
		for (Course course : courses) {
			System.out.println(course.getId());
			System.out.println(course.getName());
			System.out.println(course.getPrice());
			System.out.println("=============");
		}
		
		context.close();
		
	}
	
}
