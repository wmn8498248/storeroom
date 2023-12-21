package io.renren.modules.complaint.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.complaint.entity.RerenItemOutEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-19 19:04:37
 */
public interface RerenItemOutService extends IService<RerenItemOutEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

