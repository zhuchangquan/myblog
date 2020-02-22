//package com.mind;
//
//import java.util.List;
//
//import com.mind.service.UserService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.mind.mapper.UserMapper;
//import com.mind.MyBlogApplication;
//import com.mind.entity.User;
///**
// * SpringBoot测试类
// * @RunWith:启动类
// * SpringJunit4ClassRunner 让junit与spring环境进行整合
// *
// * @SpringBootTest(classes={MyBlogApplication.class}) 1.当前为springBoot的测试类
// * @SpringBootTest(classes={MyBlogApplication.class}) 2.加载springBoot的启动类,启动springBoot
// * @author TOSHIBA
// *
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes= {MyBlogApplication.class})
//public class MyBlogApplicationTests {
//
//	@Autowired
//    private UserMapper userMapper;
//	@Autowired
//	private UserService userService;
//
//    /**
//     * 测试mybatis plus的集成
//     */
//    @Test
//    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(1, userList.size());
//        userList.forEach(System.out::println);
//    }
//
//    @Test
//    public void testLogin() {
//        System.out.println(("----- selectAll method test ------"));
//        userService.login("4d546@qq.com","1222");
//
//    }
//
//}
//
