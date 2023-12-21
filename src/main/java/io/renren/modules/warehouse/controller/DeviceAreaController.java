package io.renren.modules.warehouse.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.warehouse.entity.DeviceAreaEntity;
import io.renren.modules.warehouse.service.DeviceAreaService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-21 14:47:46
 */
@RestController
@RequestMapping("complaint/devicearea")
public class DeviceAreaController {
    @Autowired
    private DeviceAreaService deviceAreaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("complaint:devicearea:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deviceAreaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("complaint:devicearea:info")
    public R info(@PathVariable("id") Long id){
		DeviceAreaEntity deviceArea = deviceAreaService.getById(id);

        return R.ok().put("deviceArea", deviceArea);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("complaint:devicearea:save")
    public R save(@RequestBody DeviceAreaEntity deviceArea){
		deviceAreaService.save(deviceArea);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("complaint:devicearea:update")
    public R update(@RequestBody DeviceAreaEntity deviceArea){
		deviceAreaService.updateById(deviceArea);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("complaint:devicearea:delete")
    public R delete(@RequestBody Long[] ids){
		deviceAreaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
