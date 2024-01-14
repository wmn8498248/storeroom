package io.renren.modules.materialRemove.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.materialRemove.entity.EquipmentMaterialRemoveEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-27 13:41:46
 */
public interface EquipmentMaterialRemoveService extends IService<EquipmentMaterialRemoveEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

