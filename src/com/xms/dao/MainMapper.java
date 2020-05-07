package com.xms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xms.annotation.MyAnnotation;
import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;

@MyAnnotation
public interface MainMapper {
	
//	查询全部课程方向
	List<CourseDirection> findAllCourseDirection(); 
	
//	查询全部实战推荐课程
	List<Course> findAllDemoCourse();
	
//	查询全部新手入门课程
	List<Course> findAllNewCourse();
	
	List<Course> findCourseByContentId(int contentId);
	
//	根据课程方向ID查询对应课程内容
	List<CourseContent> findCourseContentByCourseDirectionId(@Param("courseDirectionId") int courseDirectionId);
 	
//	根据课程方向ID和课程内容ID查询课程
	List<Course> findCourseByCourseDirectionIdAndCourseContentId(Map<String,Object> map);
	
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
	void saveCarItem(Map<String,Object> map);
	
//	根据用户ID查询对应购物车中商品条目信息
	List<Item> findItemByUserId(int userId);
	
//	根据商品条目ID删除Car_Item对应关系
	void deleteCarItem(int itemId);
	
//	根据商品条目ID删除商品条目信息
	void deleteItem(int itemId);
	
//	根据商品条目ID删除Car_Item对应关系
	void batchDeleteCarItem(@Param("itemIds") Integer [] itemIds);
	
//	根据商品条目ID删除商品条目信息
	void batchDeleteItem(@Param("itemIds") Integer [] itemIds);
	
//	根据商品条目ID查询商品条目信息
	Item findItemByItemId(int itemId);
	
//	生成订单
	void saveOrder(Order order);
	
//	将订单与商品条目关联
	void saveOrderItem(Map<String,Object> map);
	
}
