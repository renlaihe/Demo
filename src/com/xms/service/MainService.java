package com.xms.service;

import java.util.List;

import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;

public interface MainService {
	
//	查询全部课程方向
	List<CourseDirection> findAllCourseDirection();
	
//	查询实战推荐课程（5个）
	List<Course> findDemoCourse();
	
//	查询新手入门课程（10个）
	List<Course> findNewCourse();
	
	List<Course> findCourse(int contentId,int size);
	
//	根据课程方向ID查询对应课程内容
	List<CourseContent> findCourseContentByCourseDirectionId(int courseDirectionId);
	
//	根据课程方向ID和课程内容ID查询对应课程
	List<Course> findCourseByCourseDirectionIdAndCourseContentId(int courseDirectionId,int courseContentId);
	
//	根据课程ID查询课程信息
	Course findCourseByCourseId(int courseId);
	
//	根据用户ID查找购物车
	Car findCarByUserId(int userId);
	
//	创建购物车
	void saveCar(Car car);
	
//	根据购物车ID查询此购物车中全部商品ID
	List<Integer> findCourseIdByCarId(int carId);
	
//	生成商品条目
	void saveItem(Item item);
	
//	将商品条目添加到购物车
	void saveCarItem(int carId,int itemId);
	
//	根据用户ID查询对应购物车中商品条目信息
	List<Item> findItemByUserId(int userId);
	
//	根据商品条目ID删除Car_Item对应关系
	void deleteCarItem(int itemId);
	
//	根据商品条目ID删除商品条目信息
	void deleteItem(int itemId);
	
//	根据商品条目ID删除Car_Item对应关系
	void batchDeleteCarItem(Integer [] itemIds);
	
//	根据商品条目ID删除商品条目信息
	void batchDeleteItem(Integer [] itemIds);
	
//	根据商品条目ID查询商品条目信息
	Item findItemByItemId(int itemId);
	
//	生成订单
	void saveOrder(Order order);
	
//	将订单与商品条目关联
	void saveOrderItem(int orderId,int itemId);
	
}
