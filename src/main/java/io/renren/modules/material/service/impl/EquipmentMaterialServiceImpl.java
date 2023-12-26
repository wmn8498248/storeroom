package io.renren.modules.material.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.material.dao.EquipmentMaterialDao;
import io.renren.modules.material.entity.EquipmentMaterialEntity;
import io.renren.modules.material.service.EquipmentMaterialService;


@Service("equipmentMaterialService")
public class EquipmentMaterialServiceImpl extends ServiceImpl<EquipmentMaterialDao, EquipmentMaterialEntity> implements EquipmentMaterialService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String storageRoom  = (String)params.get("storageRoom");
        String deviceCategory  = (String)params.get("deviceCategory");
        String deviceName  = (String)params.get("deviceName");
        String goodsAddr  = (String)params.get("goodsAddr");
        IPage<EquipmentMaterialEntity> page = this.page(
                new Query<EquipmentMaterialEntity>().getPage(params),
                new QueryWrapper<EquipmentMaterialEntity>().like(StringUtils.isNotBlank(storageRoom),"storage_room", storageRoom)
                        .like(StringUtils.isNotBlank(deviceCategory),"device_category", deviceCategory)
                        .like(StringUtils.isNotBlank(deviceName),"device_name", deviceName)
                        .like(StringUtils.isNotBlank(goodsAddr),"goods_addr", goodsAddr)
        );

        return new PageUtils(page);
    }

}