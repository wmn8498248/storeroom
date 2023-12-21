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

import io.renren.modules.complaint.entity.RerenItemOutEntity;
import io.renren.modules.complaint.service.RerenItemOutService;
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
@RequestMapping("complaint/rerenitemout")
public class RerenItemOutController {
    @Autowired
    private RerenItemOutService rerenItemOutService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("complaint:rerenitemout:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rerenItemOutService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("complaint:rerenitemout:info")
    public R info(@PathVariable("id") Long id){
		RerenItemOutEntity rerenItemOut = rerenItemOutService.getById(id);

        return R.ok().put("rerenItemOut", rerenItemOut);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("complaint:rerenitemout:save")
    public R save(@RequestBody RerenItemOutEntity rerenItemOut){
		rerenItemOutService.save(rerenItemOut);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("complaint:rerenitemout:update")
    public R update(@RequestBody RerenItemOutEntity rerenItemOut){
		rerenItemOutService.updateById(rerenItemOut);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("complaint:rerenitemout:delete")
    public R delete(@RequestBody Long[] ids){
		rerenItemOutService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
