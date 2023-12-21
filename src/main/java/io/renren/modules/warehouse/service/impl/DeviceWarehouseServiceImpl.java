package io.renren.modules.warehouse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.warehouse.dao.DeviceWarehouseDao;
import io.renren.modules.warehouse.entity.DeviceWarehouseEntity;
import io.renren.modules.warehouse.service.DeviceWarehouseService;


@Service("deviceWarehouseService")
public class DeviceWarehouseServiceImpl extends ServiceImpl<DeviceWarehouseDao, DeviceWarehouseEntity> implements DeviceWarehouseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DeviceWarehouseEntity> page = this.page(
                new Query<DeviceWarehouseEntity>().getPage(params),
                new QueryWrapper<DeviceWarehouseEntity>()
        );

        return new PageUtils(page);
    }

}