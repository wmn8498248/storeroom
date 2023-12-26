package io.renren.modules.material.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.material.entity.EquipmentMaterialEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-25 16:35:36
 */
public interface EquipmentMaterialService extends IService<EquipmentMaterialEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

