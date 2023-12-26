package io.renren.modules.warehouse.service.impl;

import io.renren.modules.complaint.entity.RerenItemEntity;
import org.apache.commons.lang.StringUtils;
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
        String equipmentArea  = (String)params.get("equipmentarea");
        String equipmentName  = (String)params.get("equipmentname");
        String subdivisionName  = (String)params.get("subdivisionname");
        String scarcenum  = (String)params.get("scarcenum");
        String procurenum  = (String)params.get("procurenum");
        String devicenumber  = (String)params.get("devicenumber");
//        Boolean scarce = ((Boolean)params.get("scarceStatus")).booleanValue();

        IPage<MaterialsupplyquantityEntity> page = this.page(
                new Query<MaterialsupplyquantityEntity>().getPage(params),
                new QueryWrapper<MaterialsupplyquantityEntity>().like(StringUtils.isNotBlank(equipmentArea),"EquipmentArea", equipmentArea)
                        .like(StringUtils.isNotBlank(equipmentName),"EquipmentName", equipmentName)
                        .like(StringUtils.isNotBlank(subdivisionName),"SubdivisionName", subdivisionName)
                        .like(StringUtils.isNotBlank(devicenumber),"ID", devicenumber)
                        .ge("deficiencyquantity", scarcenum)
                        .ge("plannedpurchasequantity", procurenum)
        );


        return new PageUtils(page);
    }

}