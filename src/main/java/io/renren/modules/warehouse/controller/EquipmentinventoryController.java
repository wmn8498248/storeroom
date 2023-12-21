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

import io.renren.modules.warehouse.entity.EquipmentinventoryEntity;
import io.renren.modules.warehouse.service.EquipmentinventoryService;
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
@RequestMapping("complaint/equipmentinventory")
public class EquipmentinventoryController {
    @Autowired
    private EquipmentinventoryService equipmentinventoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("complaint:equipmentinventory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = equipmentinventoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("complaint:equipmentinventory:info")
    public R info(@PathVariable("id") Integer id){
		EquipmentinventoryEntity equipmentinventory = equipmentinventoryService.getById(id);

        return R.ok().put("equipmentinventory", equipmentinventory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("complaint:equipmentinventory:save")
    public R save(@RequestBody EquipmentinventoryEntity equipmentinventory){
		equipmentinventoryService.save(equipmentinventory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("complaint:equipmentinventory:update")
    public R update(@RequestBody EquipmentinventoryEntity equipmentinventory){
		equipmentinventoryService.updateById(equipmentinventory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("complaint:equipmentinventory:delete")
    public R delete(@RequestBody Integer[] ids){
		equipmentinventoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
