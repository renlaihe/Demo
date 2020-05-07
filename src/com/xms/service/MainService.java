package com.xms.service;

import java.util.List;

import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;

public interface MainService {
	
//	��ѯȫ���γ̷���
	List<CourseDirection> findAllCourseDirection();
	
//	��ѯʵս�Ƽ��γ̣�5����
	List<Course> findDemoCourse();
	
//	��ѯ�������ſγ̣�10����
	List<Course> findNewCourse();
	
	List<Course> findCourse(int contentId,int size);
	
//	���ݿγ̷���ID��ѯ��Ӧ�γ�����
	List<CourseContent> findCourseContentByCourseDirectionId(int courseDirectionId);
	
//	���ݿγ̷���ID�Ϳγ�����ID��ѯ��Ӧ�γ�
	List<Course> findCourseByCourseDirectionIdAndCourseContentId(int courseDirectionId,int courseContentId);
	
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
	void saveCarItem(int carId,int itemId);
	
//	�����û�ID��ѯ��Ӧ���ﳵ����Ʒ��Ŀ��Ϣ
	List<Item> findItemByUserId(int userId);
	
//	������Ʒ��ĿIDɾ��Car_Item��Ӧ��ϵ
	void deleteCarItem(int itemId);
	
//	������Ʒ��ĿIDɾ����Ʒ��Ŀ��Ϣ
	void deleteItem(int itemId);
	
//	������Ʒ��ĿIDɾ��Car_Item��Ӧ��ϵ
	void batchDeleteCarItem(Integer [] itemIds);
	
//	������Ʒ��ĿIDɾ����Ʒ��Ŀ��Ϣ
	void batchDeleteItem(Integer [] itemIds);
	
//	������Ʒ��ĿID��ѯ��Ʒ��Ŀ��Ϣ
	Item findItemByItemId(int itemId);
	
//	���ɶ���
	void saveOrder(Order order);
	
//	����������Ʒ��Ŀ����
	void saveOrderItem(int orderId,int itemId);
	
}
