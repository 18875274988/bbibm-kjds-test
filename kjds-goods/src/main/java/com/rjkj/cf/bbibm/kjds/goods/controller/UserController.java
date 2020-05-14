package com.rjkj.cf.bbibm.kjds.goods.controller;

import com.rjkj.cf.bbibm.kjds.goods.entity.User;
import com.rjkj.cf.bbibm.kjds.goods.mapper.UserMapper;
import com.rjkj.cf.common.core.util.R;
import com.rjkj.cf.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user" )
@Api(value = "user", tags = "mybatis-plus测试")
public class UserController {
    private final UserMapper userMapper;
    @ApiOperation(value = "mybatis-plus测试", notes = "mybatis-plus测试")
    @SysLog("mybatis-plus测试" )
    @GetMapping("testmybatis")
    public R removeById() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
        return null;
    }
}
