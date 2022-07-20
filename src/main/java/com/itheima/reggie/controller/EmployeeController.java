package com.itheima.reggie.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){

        //1、将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
       password = DigestUtils.md5DigestAsHex(password.getBytes());
        String username = employee.getUsername();
        log.info("password = " + password);
        log.info( "username = "+ username);

        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);


        //3、如果没有查询到则返回登录失败结果
        if(emp == null){
            return R.error("没有查询到,登录失败");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("密码比对不一致,登录失败");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if(emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        //6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }
    /**
     * 获取当前的用户
     * @param request
     * @return
     */
    @GetMapping("/currentUser")
    public R<Employee> login(HttpServletRequest request) {
        //获取员工id
        Long emplid = (Long) request.getSession().getAttribute("employee");

        //2、根据session id查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getId,emplid);
        Employee emp = employeeService.getOne(queryWrapper);

        return R.success(emp);
    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 添加员工
     * @param
     * @return
     */
    @PostMapping("")
    public R<String> addemployee(HttpServletRequest request,@RequestBody Employee employee){

       String password = DigestUtils.md5DigestAsHex("12345".getBytes());employee.setPassword(password);

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empid = (Long) request.getSession().getAttribute("employee");

        employee.setCreateUser(empid);
        employee.setUpdateUser(empid);

        log.info("新增员工，{}",employee);

        employeeService.save(employee);

        return R.success("OK");
    }

    /**
     * 分页查询
     * current=1&pageSize=20&username=ROOT&name=%E5%88%98%E9%9D%92%E5%B1%B1&phone=19961849159
     **/
    @GetMapping("/page")
    public String getemployees(int current, int pageSize, String name, String username, String phone){
       log.info("current = {},pageSize = {},name = {},username = {},phone = {} ",current,pageSize,name,username,phone);
       //构造分页构造器
        Page pageinfo = new Page(current,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
       queryWrapper.like(name!=null,Employee::getName,name)
               .like(username!=null,Employee::getUsername,username)
               .like(phone!=null,Employee::getPhone,phone);
        //排序条件
        queryWrapper.orderByDesc(Employee::getCreateTime);


        //执行查询
        employeeService.page(pageinfo,queryWrapper);
        System.out.println(pageinfo);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("total", pageinfo.getTotal());
        result.put("data",pageinfo.getRecords() );
        return result.toJSONString();
    }



//    @GetMapping("/TEST")
//     public String writeByBody() {
//        //获取员工id
//        // 将获取的json数据封装一层，然后在给返回
//        JSONObject result = new JSONObject();
//        result.put("msg", "ok");
//        result.put("method", "@ResponseBody");
//        result.put("data", );
//
//        return result.toJSONString();
//    }








}
