package io.renren.modules.warehouse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.warehouse.dao.MaterialsupplyquantityDao;
import io.renren.modules.warehouse.entity.MaterialsupplyquantityEntity;
import io.renren.modules.warehouse.service.MaterialsupplyquantityService;


@Service("materialsupplyquantityService")
public class MaterialsupplyquantityServiceImpl extends ServiceImpl<MaterialsupplyquantityDao, MaterialsupplyquantityEntity> implements MaterialsupplyquantityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialsupplyquantityEntity> page = this.page(
                new Query<MaterialsupplyquantityEntity>().getPage(params),
                new QueryWrapper<MaterialsupplyquantityEntity>()
        );

        return new PageUtils(page);
    }

}