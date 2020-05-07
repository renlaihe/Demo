package com.xms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xms.dao.MainMapper;
import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;
import com.xms.service.MainService;

@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	private MainMapper mainMapper;	
	
//	注入批处理操作sqlSession
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<CourseDirection> findAllCourseDirection() {
		
		List<CourseDirection> courseDirections 
			= mainMapper.findAllCourseDirection();
		
		return courseDirections;
	}
	
	@Override
	public List<Course> findDemoCourse() {
		
		List<Course> demoCourses = mainMapper.findAllDemoCourse();
		
		List<Course> listCourses = new ArrayList<>();
		
		Random random = new Random();
		
		if (demoCourses.size() <= 5) {
			return demoCourses;
		} else {
			for(int i=0;i<5;i++) {
				int index = random.nextInt(demoCourses.size());
				
				Course course = demoCourses.remove(index);
				
				listCourses.add(course);
			}	
			return listCourses;
		}
	}
	
	@Override
	public List<Course> findNewCourse() {
		
		List<Course> newCourses = mainMapper.findAllNewCourse();
		
		List<Course> listcCourses = new ArrayList<>();
		
		Random random = new Random();
		
		if (newCourses.size() <= 10) {
			return newCourses;
		}else {
			for(int i=0;i<10;i++) {
				listcCourses.add(newCourses.remove(
					random.nextInt(newCourses.size())));
			}
			return listcCourses;
		}
	}
	
	@Override
	public List<Course> findCourse(int contentId, int size) {
		
		List<Course> courses = mainMapper.findCourseByContentId(contentId);
		
		if (courses.size() <= size) {
			return courses;
		} else {
			List<Course> listCourses = new ArrayList<>();
			
			Random random = new Random();
			
			for(int i=0;i<size;i++) {
				listCourses.add(courses.remove(random.nextInt(courses.size())));
			}
			return listCourses;
		}
	}
	
	@Override
	public List<CourseContent> findCourseContentByCourseDirectionId(int courseDirectionId) {
		return mainMapper.findCourseContentByCourseDirectionId(courseDirectionId);
	}
	
	@Override
	public List<Course> findCourseByCourseDirectionIdAndCourseContentId(int courseDirectionId, int courseContentId) {

		Map<String,Object> map = new HashMap<>();
		map.put("courseDirectionId",courseDirectionId);
		map.put("courseContentId",courseContentId);
		
		return mainMapper.findCourseByCourseDirectionIdAndCourseContentId(map);
	}
	
	@Override
	public Course findCourseByCourseId(int courseId) {
		return mainMapper.findCourseByCourseId(courseId);
	}
	
	@Override
	public Car findCarByUserId(int userId) {
		return mainMapper.findCarByUserId(userId);
	}
	
	@Override
	public void saveCar(Car car) {
		mainMapper.saveCar(car);
	}
	
	@Override
	public List<Integer> findCourseIdByCarId(int carId) {
		return mainMapper.findCourseIdByCarId(carId);
	}
	
	@Override
	public void saveItem(Item item) {
		mainMapper.saveItem(item);
	}
	
	@Override
	public void saveCarItem(int carId, int itemId) {
		
		Map<String,Object> map = new HashMap<>();
		map.put("c_id",carId);
		map.put("i_id",itemId);
		
		mainMapper.saveCarItem(map);
		
	}
	
	@Override
	public List<Item> findItemByUserId(int userId) {
		return mainMapper.findItemByUserId(userId);
	}
	
	@Override
	public void deleteCarItem(int itemId) {
		mainMapper.deleteCarItem(itemId);
	}
	
	@Override
	public void deleteItem(int itemId) {
		mainMapper.deleteItem(itemId);
	}
	
	@Override
	public void batchDeleteCarItem(Integer [] itemIds) { 
		mainMapper.batchDeleteCarItem(itemIds);
	}
	
	@Override
	public void batchDeleteItem(Integer [] itemIds) {
		mainMapper.batchDeleteItem(itemIds);
	}
	
	@Override
	public Item findItemByItemId(int itemId) {
		return mainMapper.findItemByItemId(itemId);
	}
	
	@Override
	public void saveOrder(Order order) {
		mainMapper.saveOrder(order);
	}
	
	@Override
	public void saveOrderItem(int orderId, int itemId) {
		
		Map<String,Object> map = new HashMap<>();
		map.put("orderId",orderId);
		map.put("itemId",itemId);
		
		mainMapper.saveOrderItem(map);
		
	}
}
