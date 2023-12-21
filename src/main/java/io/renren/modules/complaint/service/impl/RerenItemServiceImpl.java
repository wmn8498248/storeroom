package io.renren.modules.complaint.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.complaint.dao.RerenItemDao;
import io.renren.modules.complaint.entity.RerenItemEntity;
import io.renren.modules.complaint.service.RerenItemService;


@Service("rerenItemService")
public class RerenItemServiceImpl extends ServiceImpl<RerenItemDao, RerenItemEntity> implements RerenItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String placeName  = (String)params.get("placeName");
        String itemName  = (String)params.get("itemName");
        String specification  = (String)params.get("specification");
        String itemManufacturer  = (String)params.get("itemManufacturer");
        IPage<RerenItemEntity> page = this.page(
                new Query<RerenItemEntity>().getPage(params),
                new QueryWrapper<RerenItemEntity>().like(StringUtils.isNotBlank(placeName),"place_name", placeName)
                        .like(StringUtils.isNotBlank(itemName),"item_name", itemName)
                        .like(StringUtils.isNotBlank(specification),"specification", specification)
                        .like(StringUtils.isNotBlank(itemManufacturer),"item_manufacturer", itemManufacturer)
        );

        return new PageUtils(page);
    }

}