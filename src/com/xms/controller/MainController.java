package com.xms.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;
import com.xms.entity.User;
import com.xms.service.MainService;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private MainService mainService;

	@RequestMapping("/toIndex")
	public String toIndex(Model model) {
		
//		��ѯȫ���γ̷���
		List<CourseDirection> courseDirections
			= mainService.findAllCourseDirection();
		model.addAttribute("courseDirections",courseDirections);
//		model.addAttribute("size",courseDirections.size());
		
//		��ѯʵս�Ƽ��γ̣�5����
		List<Course> demoCourses = mainService.findDemoCourse();
//		List<Course> demoCourses = mainService.findCourse(2,5);
		model.addAttribute("demoCourses",demoCourses);
		
//		��ѯ�������ſγ̣�10����
		List<Course> newCourses = mainService.findNewCourse();
//		List<Course> newCourses = mainService.findCourse(1,10);
		model.addAttribute("newCourses",newCourses);
		
		return "jsp/index";
		
	}
	
//	���ݿγ̷���ID�Ϳγ�����ID��ѯ�γ���Ϣ
	@RequestMapping("/toCourse")
	public String toCourse(
		@ModelAttribute("courseDirectionId") int courseDirectionId,
			@ModelAttribute("courseContentId") int courseContentId,Model model) {
		
//		model.addAttribute("courseDirectionId",courseDirectionId);
		
//		��ѯȫ���γ̷���
		List<CourseDirection> courseDirections 
			= mainService.findAllCourseDirection();
		model.addAttribute("courseDirections",courseDirections);
		
//		���ݿγ̷���ID��ѯ��Ӧ�Ŀγ�����
		List<CourseContent> courseContents =
			mainService.findCourseContentByCourseDirectionId(courseDirectionId);
		model.addAttribute("courseContents",courseContents);
		
//		���ݿγ̷���ID�Ϳγ�����ID��ѯ��Ӧ�γ�
		List<Course> courses = mainService
			.findCourseByCourseDirectionIdAndCourseContentId(
				courseDirectionId,courseContentId);
		model.addAttribute("courses",courses);
		
		return "jsp/course";
		
	}
	
//	��Ƶ����
	@RequestMapping("/toVideo")
	public String toVideo(int courseId,Model model) {
		
//		���ݿγ�ID��ѯ�γ���Ϣ
		Course course = mainService.findCourseByCourseId(courseId);
		model.addAttribute("course",course);
		
		return "jsp/video";
	}
	
//	����
	@RequestMapping("/buy")
	@ResponseBody
	public boolean buy(int courseId,HttpServletRequest request) {
		
//		System.out.println(123);
		
		User user = (User) request.getSession().getAttribute("user");
		
//		�����û�ID��ѯ�乺�ﳵ��������ﳵ���ڣ�ֱ���ã���������ڣ��򴴽�
		Car car = mainService.findCarByUserId(user.getId());
		
		if (car == null) {
			car = new Car();
			car.setU_id(user.getId());
			mainService.saveCar(car);
		}
		
//		���ݵ�ǰ���ﳵID��ѯ���ﳵȫ����ƷID
		List<Integer> ids = mainService.findCourseIdByCarId(car.getId());
		
//		�����û�ID��ѯ���û���������ƷID
//		List<Integer> ids2 = mainService.findCourseIdByUserId(user.getId());
		
//		�жϹ��ﳵ���Ƿ��������Ʒ
		if (ids.contains(courseId)) {
//			�ѹ��������Ʒ
			return false;
		} else {
//			û�й��������Ʒ
			Course course = mainService.findCourseByCourseId(courseId);
			
//			������Ʒ��Ŀ
			Item item = new Item();
			item.setC_id(course.getId());
			item.setC_name(course.getName());
			item.setC_picture_url(course.getPicture_url());
			item.setC_price(course.getPrice());
			item.setAdd_time(new Timestamp(System.currentTimeMillis()));
			item.setRemove_time(null);
			
			mainService.saveItem(item);
			
//			����Ʒ��Ŀ��ӵ����ﳵ
			mainService.saveCarItem(car.getId(),item.getId());
			
//			����ɹ�
			return true;
		}
		
	}
	
//	�鿴���ﳵ
	@RequestMapping("/toCar")
	public String toCar(HttpServletRequest request,Model model) {
		
		User user = (User) request.getSession().getAttribute("user");
		
//		�����û�ID��ѯ��Ӧ���ﳵ����Ʒ��Ŀ��Ϣ
		List<Item> items = mainService.findItemByUserId(user.getId());
		model.addAttribute("items",items);
		
		return "jsp/car";
	}
	
//	ɾ����Ʒ��Ŀ
	@RequestMapping("/deleteItem")
	public String delete(@RequestParam("item_id") int id) {
		
//		��xc_car_item����ɾ�����ﳵ�����Ʒ��Ŀ�Ķ�Ӧ��ϵ
		mainService.deleteCarItem(id);
		
//		��xc_item����ɾ����Ӧ����Ʒ��Ŀ��Ϣ
		mainService.deleteItem(id);
		
//		�ض��򵽹��ﳵҳ��
		return "redirect:/main/toCar";
		
	}
	
//	����ɾ��
	@RequestMapping("/batchDelete")
	public String batchDelete(Integer [] itemIds) {
		
//		for(int i=0;i<itemIds.length;i++) {
//			delete(itemIds[i]);
//		}
		
//		��xc_car_item��������ɾ�����ﳵ����Ʒ��Ŀ�Ķ�Ӧ��ϵ
		mainService.batchDeleteCarItem(itemIds);
		
//		��xc_item��������ɾ����Ӧ����Ʒ��Ŀ��Ϣ
		mainService.batchDeleteItem(itemIds);
		
//		�ض��򵽹��ﳵҳ��
		return "redirect:/main/toCar";
		
	}
	
//	��ת������ҳ��
	@RequestMapping("/toOrder")
	public String toOrder(Integer [] itemIds,Model model) {
		
//		���㹺����Ʒ���ܼ�
		double totalPrice = 0.0;
		
//		������Ʒ��ĿID��ѯ��Ʒ��Ŀ��Ϣ
		List<Item> items = new ArrayList<>();
		for(Integer itemId : itemIds) {
			Item item = mainService.findItemByItemId(itemId);
			
			totalPrice += item.getC_price();
			
			items.add(item);
		}
		
		model.addAttribute("items",items);
		model.addAttribute("totalPrice",totalPrice);
		
		return "jsp/order";
	}
	
//	�ύ����
	@RequestMapping("/order")
	public String order(Integer [] itemIds,
		HttpServletRequest request,Model model) {
		
		List<Item> items = new ArrayList<>();
		
		double totalPrice = 0.0;
		
//		������Ʒ��ĿID���������Ʒ��Ŀ�ӹ��ﳵ��ɾ��
		for (int i=0;i<itemIds.length;i++) {
			
			Item item = mainService.findItemByItemId(itemIds[i]);
			
//			���㶩������Ʒ�ܼ�
			totalPrice += item.getC_price();
			
			mainService.deleteCarItem(item.getId());
			
			items.add(item);
			
		}
		
		model.addAttribute("totalPrice",totalPrice);
		
//		���ɶ���
		Order order = new Order();
		
		User user = (User) request.getSession().getAttribute("user");
		
		order.setU_id(user.getId());
		order.setOrder_time(new Timestamp(System.currentTimeMillis()));
		order.setTotal_price(totalPrice);
		order.setPay_type("Y");
		
//		���涩��
		mainService.saveOrder(order);
		
//		����������Ʒ��Ŀ����
		for(Item item : items) {
			mainService.saveOrderItem(order.getId(),item.getId());
		}
		
		return "jsp/success";
		
	}
	
}
