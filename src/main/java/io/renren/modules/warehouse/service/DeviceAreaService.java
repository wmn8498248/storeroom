package io.renren.modules.warehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.warehouse.entity.DeviceAreaEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-21 14:47:46
 */
public interface DeviceAreaService extends IService<DeviceAreaEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

