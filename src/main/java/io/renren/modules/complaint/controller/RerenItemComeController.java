package io.renren.modules.complaint.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.complaint.entity.RerenItemComeEntity;
import io.renren.modules.complaint.service.RerenItemComeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-19 19:04:37
 */
@RestController
@RequestMapping("complaint/rerenitemcome")
public class RerenItemComeController {
    @Autowired
    private RerenItemComeService rerenItemComeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("complaint:rerenitemcome:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rerenItemComeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("complaint:rerenitemcome:info")
    public R info(@PathVariable("id") Long id){
		RerenItemComeEntity rerenItemCome = rerenItemComeService.getById(id);

        return R.ok().put("rerenItemCome", rerenItemCome);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("complaint:rerenitemcome:save")
    public R save(@RequestBody RerenItemComeEntity rerenItemCome){
		rerenItemComeService.save(rerenItemCome);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("complaint:rerenitemcome:update")
    public R update(@RequestBody RerenItemComeEntity rerenItemCome){
		rerenItemComeService.updateById(rerenItemCome);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("complaint:rerenitemcome:delete")
    public R delete(@RequestBody Long[] ids){
		rerenItemComeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
