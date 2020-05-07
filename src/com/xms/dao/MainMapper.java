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
	
//	��ѯȫ���γ̷���
	List<CourseDirection> findAllCourseDirection(); 
	
//	��ѯȫ��ʵս�Ƽ��γ�
	List<Course> findAllDemoCourse();
	
//	��ѯȫ���������ſγ�
	List<Course> findAllNewCourse();
	
	List<Course> findCourseByContentId(int contentId);
	
//	���ݿγ̷���ID��ѯ��Ӧ�γ�����
	List<CourseContent> findCourseContentByCourseDirectionId(@Param("courseDirectionId") int courseDirectionId);
 	
//	���ݿγ̷���ID�Ϳγ�����ID��ѯ�γ�
	List<Course> findCourseByCourseDirectionIdAndCourseContentId(Map<String,Object> map);
	
//	���ݿγ�ID��ѯ�γ���Ϣ
	Course findCourseByCourseId(int courseId);
	
//	�����û�ID���ҹ��ﳵ
	Car findCarByUserId(int userId);
	
//	�������ﳵ
	void saveCar(Car car);
	
//	���ݹ��ﳵID��ѯ�˹��ﳵ��ȫ����ƷID
	List<Integer> findCourseIdByCarId(int carId);
	
//	������Ʒ��Ŀ
	void saveItem(Item item);
	
//	����Ʒ��Ŀ��ӵ����ﳵ
	void saveCarItem(Map<String,Object> map);
	
//	�����û�ID��ѯ��Ӧ���ﳵ����Ʒ��Ŀ��Ϣ
	List<Item> findItemByUserId(int userId);
	
//	������Ʒ��ĿIDɾ��Car_Item��Ӧ��ϵ
	void deleteCarItem(int itemId);
	
//	������Ʒ��ĿIDɾ����Ʒ��Ŀ��Ϣ
	void deleteItem(int itemId);
	
//	������Ʒ��ĿIDɾ��Car_Item��Ӧ��ϵ
	void batchDeleteCarItem(@Param("itemIds") Integer [] itemIds);
	
//	������Ʒ��ĿIDɾ����Ʒ��Ŀ��Ϣ
	void batchDeleteItem(@Param("itemIds") Integer [] itemIds);
	
//	������Ʒ��ĿID��ѯ��Ʒ��Ŀ��Ϣ
	Item findItemByItemId(int itemId);
	
//	���ɶ���
	void saveOrder(Order order);
	
//	����������Ʒ��Ŀ����
	void saveOrderItem(Map<String,Object> map);
	
}
