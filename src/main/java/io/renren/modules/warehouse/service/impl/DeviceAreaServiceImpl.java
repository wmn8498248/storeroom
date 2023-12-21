package io.renren.modules.warehouse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.warehouse.dao.DeviceAreaDao;
import io.renren.modules.warehouse.entity.DeviceAreaEntity;
import io.renren.modules.warehouse.service.DeviceAreaService;


@Service("deviceAreaService")
public class DeviceAreaServiceImpl extends ServiceImpl<DeviceAreaDao, DeviceAreaEntity> implements DeviceAreaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DeviceAreaEntity> page = this.page(
                new Query<DeviceAreaEntity>().getPage(params),
                new QueryWrapper<DeviceAreaEntity>()
        );

        return new PageUtils(page);
    }

}