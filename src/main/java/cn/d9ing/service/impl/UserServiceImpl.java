package cn.d9ing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d9ing.dao.UserMapper;
import cn.d9ing.service.IUserService;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017Âπ?5Êú?12Êó? ‰∏ãÂçà5:26:44
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userDao;

}
