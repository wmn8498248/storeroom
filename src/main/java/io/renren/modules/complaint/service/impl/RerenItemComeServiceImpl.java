package io.renren.modules.complaint.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.complaint.dao.RerenItemComeDao;
import io.renren.modules.complaint.entity.RerenItemComeEntity;
import io.renren.modules.complaint.service.RerenItemComeService;


@Service("rerenItemComeService")
public class RerenItemComeServiceImpl extends ServiceImpl<RerenItemComeDao, RerenItemComeEntity> implements RerenItemComeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RerenItemComeEntity> page = this.page(
                new Query<RerenItemComeEntity>().getPage(params),
                new QueryWrapper<RerenItemComeEntity>()
        );

        return new PageUtils(page);
    }

}