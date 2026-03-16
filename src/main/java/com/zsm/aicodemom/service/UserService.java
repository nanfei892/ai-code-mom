package com.zsm.aicodemom.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.zsm.aicodemom.model.dto.user.UserQueryRequest;
import com.zsm.aicodemom.model.entity.User;
import com.zsm.aicodemom.model.vo.LoginUserVO;
import com.zsm.aicodemom.model.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 用户 服务层。
 *
 * @author <a href="https://github.com/nanfei892">男妃</a>
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @param request 请求对象
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     * @param request request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     * @param request 请求
     * @return 结果
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 密码加密
     * @param userPassword 带加密密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String userPassword);

    /**
     * 获取脱敏后的单个用户信息
     * @param user 用户
     * @return 脱敏后的用户信息
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏后的用户列表
     * @param userList 用户列表
     * @return 脱敏后的用户列表
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 将查询请求转为QueryWrapper对象
     * @param userQueryRequest 查询请求对象
     * @return QueryWrapper对象
     */
    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);
}
