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
		
//		查询全部课程方向
		List<CourseDirection> courseDirections
			= mainService.findAllCourseDirection();
		model.addAttribute("courseDirections",courseDirections);
//		model.addAttribute("size",courseDirections.size());
		
//		查询实战推荐课程（5个）
		List<Course> demoCourses = mainService.findDemoCourse();
//		List<Course> demoCourses = mainService.findCourse(2,5);
		model.addAttribute("demoCourses",demoCourses);
		
//		查询新手入门课程（10个）
		List<Course> newCourses = mainService.findNewCourse();
//		List<Course> newCourses = mainService.findCourse(1,10);
		model.addAttribute("newCourses",newCourses);
		
		return "jsp/index";
		
	}
	
//	根据课程方向ID和课程内容ID查询课程信息
	@RequestMapping("/toCourse")
	public String toCourse(
		@ModelAttribute("courseDirectionId") int courseDirectionId,
			@ModelAttribute("courseContentId") int courseContentId,Model model) {
		
//		model.addAttribute("courseDirectionId",courseDirectionId);
		
//		查询全部课程方向
		List<CourseDirection> courseDirections 
			= mainService.findAllCourseDirection();
		model.addAttribute("courseDirections",courseDirections);
		
//		根据课程方向ID查询对应的课程内容
		List<CourseContent> courseContents =
			mainService.findCourseContentByCourseDirectionId(courseDirectionId);
		model.addAttribute("courseContents",courseContents);
		
//		根据课程方向ID和课程内容ID查询对应课程
		List<Course> courses = mainService
			.findCourseByCourseDirectionIdAndCourseContentId(
				courseDirectionId,courseContentId);
		model.addAttribute("courses",courses);
		
		return "jsp/course";
		
	}
	
//	视频播放
	@RequestMapping("/toVideo")
	public String toVideo(int courseId,Model model) {
		
//		根据课程ID查询课程信息
		Course course = mainService.findCourseByCourseId(courseId);
		model.addAttribute("course",course);
		
		return "jsp/video";
	}
	
//	购买
	@RequestMapping("/buy")
	@ResponseBody
	public boolean buy(int courseId,HttpServletRequest request) {
		
//		System.out.println(123);
		
		User user = (User) request.getSession().getAttribute("user");
		
//		根据用户ID查询其购物车，如果购物车存在，直接用，如果不存在，则创建
		Car car = mainService.findCarByUserId(user.getId());
		
		if (car == null) {
			car = new Car();
			car.setU_id(user.getId());
			mainService.saveCar(car);
		}
		
//		根据当前购物车ID查询购物车全部商品ID
		List<Integer> ids = mainService.findCourseIdByCarId(car.getId());
		
//		根据用户ID查询此用户订单中商品ID
//		List<Integer> ids2 = mainService.findCourseIdByUserId(user.getId());
		
//		判断购物车中是否包含此商品
		if (ids.contains(courseId)) {
//			已购买过此商品
			return false;
		} else {
//			没有购买过此商品
			Course course = mainService.findCourseByCourseId(courseId);
			
//			生成商品条目
			Item item = new Item();
			item.setC_id(course.getId());
			item.setC_name(course.getName());
			item.setC_picture_url(course.getPicture_url());
			item.setC_price(course.getPrice());
			item.setAdd_time(new Timestamp(System.currentTimeMillis()));
			item.setRemove_time(null);
			
			mainService.saveItem(item);
			
//			将商品条目添加到购物车
			mainService.saveCarItem(car.getId(),item.getId());
			
//			购买成功
			return true;
		}
		
	}
	
//	查看购物车
	@RequestMapping("/toCar")
	public String toCar(HttpServletRequest request,Model model) {
		
		User user = (User) request.getSession().getAttribute("user");
		
//		根据用户ID查询对应购物车中商品条目信息
		List<Item> items = mainService.findItemByUserId(user.getId());
		model.addAttribute("items",items);
		
		return "jsp/car";
	}
	
//	删除商品条目
	@RequestMapping("/deleteItem")
	public String delete(@RequestParam("item_id") int id) {
		
//		从xc_car_item表中删除购物车与此商品条目的对应关系
		mainService.deleteCarItem(id);
		
//		从xc_item表中删除对应的商品条目信息
		mainService.deleteItem(id);
		
//		重定向到购物车页面
		return "redirect:/main/toCar";
		
	}
	
//	批量删除
	@RequestMapping("/batchDelete")
	public String batchDelete(Integer [] itemIds) {
		
//		for(int i=0;i<itemIds.length;i++) {
//			delete(itemIds[i]);
//		}
		
//		从xc_car_item表中批量删除购物车与商品条目的对应关系
		mainService.batchDeleteCarItem(itemIds);
		
//		从xc_item表中批量删除对应的商品条目信息
		mainService.batchDeleteItem(itemIds);
		
//		重定向到购物车页面
		return "redirect:/main/toCar";
		
	}
	
//	跳转至订单页面
	@RequestMapping("/toOrder")
	public String toOrder(Integer [] itemIds,Model model) {
		
//		计算购买商品的总价
		double totalPrice = 0.0;
		
//		根据商品条目ID查询商品条目信息
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
	
//	提交订单
	@RequestMapping("/order")
	public String order(Integer [] itemIds,
		HttpServletRequest request,Model model) {
		
		List<Item> items = new ArrayList<>();
		
		double totalPrice = 0.0;
		
//		根据商品条目ID将购买的商品条目从购物车中删除
		for (int i=0;i<itemIds.length;i++) {
			
			Item item = mainService.findItemByItemId(itemIds[i]);
			
//			计算订单中商品总价
			totalPrice += item.getC_price();
			
			mainService.deleteCarItem(item.getId());
			
			items.add(item);
			
		}
		
		model.addAttribute("totalPrice",totalPrice);
		
//		生成订单
		Order order = new Order();
		
		User user = (User) request.getSession().getAttribute("user");
		
		order.setU_id(user.getId());
		order.setOrder_time(new Timestamp(System.currentTimeMillis()));
		order.setTotal_price(totalPrice);
		order.setPay_type("Y");
		
//		保存订单
		mainService.saveOrder(order);
		
//		将订单与商品条目关联
		for(Item item : items) {
			mainService.saveOrderItem(order.getId(),item.getId());
		}
		
		return "jsp/success";
		
	}
	
}
