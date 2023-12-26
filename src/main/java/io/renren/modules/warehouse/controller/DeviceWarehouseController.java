package io.renren.modules.warehouse.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.warehouse.entity.DeviceWarehouseEntity;
import io.renren.modules.warehouse.service.DeviceWarehouseService;
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
@RequestMapping("complaint/devicewarehouse")
public class DeviceWarehouseController {
    @Autowired
    private DeviceWarehouseService deviceWarehouseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("complaint:devicewarehouse:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deviceWarehouseService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/key")
    @RequiresPermissions("complaint:devicewarehouse:list")
    public R key(@RequestParam Map<String, Object> params){
        PageUtils page = deviceWarehouseService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("complaint:devicewarehouse:info")
    public R info(@PathVariable("id") Long id){
		DeviceWarehouseEntity deviceWarehouse = deviceWarehouseService.getById(id);

        return R.ok().put("deviceWarehouse", deviceWarehouse);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("complaint:devicewarehouse:save")
    public R save(@RequestBody DeviceWarehouseEntity deviceWarehouse){
		deviceWarehouseService.save(deviceWarehouse);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("complaint:devicewarehouse:update")
    public R update(@RequestBody DeviceWarehouseEntity deviceWarehouse){
		deviceWarehouseService.updateById(deviceWarehouse);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("complaint:devicewarehouse:delete")
    public R delete(@RequestBody Long[] ids){
		deviceWarehouseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
