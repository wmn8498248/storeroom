package io.renren.modules.materialRemove.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.materialRemove.dao.EquipmentMaterialRemoveDao;
import io.renren.modules.materialRemove.entity.EquipmentMaterialRemoveEntity;
import io.renren.modules.materialRemove.service.EquipmentMaterialRemoveService;


@Service("equipmentMaterialRemoveService")
public class EquipmentMaterialRemoveServiceImpl extends ServiceImpl<EquipmentMaterialRemoveDao, EquipmentMaterialRemoveEntity> implements EquipmentMaterialRemoveService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String roomId = (String)params.get("roomId");
        String storageRoom  = (String)params.get("storageRoom");
        String deviceCategory  = (String)params.get("deviceCategory");
        String deviceName  = (String)params.get("deviceName");
        String goodsAddr  = (String)params.get("goodsAddr");

        IPage<EquipmentMaterialRemoveEntity> page = this.page(
                new Query<EquipmentMaterialRemoveEntity>().getPage(params),
                new QueryWrapper<EquipmentMaterialRemoveEntity>().like(StringUtils.isNotBlank(storageRoom),"storage_room", storageRoom)
                        .like(StringUtils.isNotBlank(deviceCategory),"device_category", deviceCategory)
                        .like(StringUtils.isNotBlank(deviceName),"device_name", deviceName)
                        .like(StringUtils.isNotBlank(goodsAddr),"goods_addr", goodsAddr)
                        .eq(StringUtils.isNotBlank(roomId),"room_id",roomId)
        );

        return new PageUtils(page);
    }

}