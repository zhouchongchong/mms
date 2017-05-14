package cn.d9ing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d9ing.dao.TreasureMapper;
import cn.d9ing.service.ITreasureService;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017Âπ?5Êú?12Êó? ‰∏ãÂçà5:30:56
 */
@Service
public class TreasureServiceImpl implements ITreasureService{
	@Autowired
	private TreasureMapper treasureDao;
}
