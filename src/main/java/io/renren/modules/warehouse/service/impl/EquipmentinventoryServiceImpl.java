package io.renren.modules.warehouse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.warehouse.dao.EquipmentinventoryDao;
import io.renren.modules.warehouse.entity.EquipmentinventoryEntity;
import io.renren.modules.warehouse.service.EquipmentinventoryService;


@Service("equipmentinventoryService")
public class EquipmentinventoryServiceImpl extends ServiceImpl<EquipmentinventoryDao, EquipmentinventoryEntity> implements EquipmentinventoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EquipmentinventoryEntity> page = this.page(
                new Query<EquipmentinventoryEntity>().getPage(params),
                new QueryWrapper<EquipmentinventoryEntity>()
        );

        return new PageUtils(page);
    }

}