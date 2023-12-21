package io.renren.modules.complaint.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.complaint.entity.RerenItemComeEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-19 19:04:37
 */
public interface RerenItemComeService extends IService<RerenItemComeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

