package io.renren.modules.materialRemove.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.material.entity.EquipmentMaterialEntity;
import io.renren.modules.material.service.EquipmentMaterialService;
import io.renren.modules.warehouse.entity.MaterialsupplyquantityEntity;
import io.renren.modules.warehouse.service.MaterialsupplyquantityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.materialRemove.entity.EquipmentMaterialRemoveEntity;
import io.renren.modules.materialRemove.service.EquipmentMaterialRemoveService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-27 13:41:46
 */
@RestController
@RequestMapping("materialRemove/equipmentmaterialremove")
public class EquipmentMaterialRemoveController {
    @Autowired
    private EquipmentMaterialRemoveService equipmentMaterialRemoveService;

    @Autowired
    private EquipmentMaterialService equipmentMaterialService;

    @Autowired
    private MaterialsupplyquantityService materialsupplyquantityService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("materialRemove:equipmentmaterialremove:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = equipmentMaterialRemoveService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{equipmentId}")
    @RequiresPermissions("materialRemove:equipmentmaterialremove:info")
    public R info(@PathVariable("equipmentId") Integer equipmentId){
		EquipmentMaterialRemoveEntity equipmentMaterialRemove = equipmentMaterialRemoveService.getById(equipmentId);

        return R.ok().put("equipmentMaterialRemove", equipmentMaterialRemove);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("materialRemove:equipmentmaterialremove:save")
    public R save(@RequestBody EquipmentMaterialRemoveEntity equipmentMaterialRemove){

        Integer equipmentId = equipmentMaterialRemove.getEquipmentId(); // 获取ID
        EquipmentMaterialEntity equipmentMaterial = equipmentMaterialService.getById(equipmentId);  //仓库实例类
        Integer warehouseQuantity = equipmentMaterial.getWarehouseQuantity(); //获取存放数量


        Integer removeNum = equipmentMaterialRemove.getRemoveNum(); //获取领取数量
        if(warehouseQuantity >= removeNum) {
            equipmentMaterial.setWarehouseQuantity(warehouseQuantity - removeNum);
        } else {
            return R.error("数量不足无法领取！");
        }

        equipmentMaterialService.updateById(equipmentMaterial);// 更新仓库实例类

        Integer deviceNumber = Integer.parseInt(equipmentMaterialRemove.getDeviceNumber());
        MaterialsupplyquantityEntity materialsupplyquantity = materialsupplyquantityService.getById(deviceNumber);// 获取区域实例类
        Integer currentquantityofspareparts =  materialsupplyquantity.getCurrentquantityofspareparts();// 现有备品库数量
        if(currentquantityofspareparts >=  removeNum) {
            materialsupplyquantity.setCurrentquantityofspareparts(currentquantityofspareparts - removeNum);
        } else {
            materialsupplyquantity.setCurrentquantityofspareparts(0);
//            return R.error("数量不足无法领取！");
        }
        equipmentMaterialRemove.setRoomId(equipmentMaterialRemove.getEquipmentId()); //插入仓库ID
		equipmentMaterialRemoveService.save(equipmentMaterialRemove);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("materialRemove:equipmentmaterialremove:update")
    public R update(@RequestBody EquipmentMaterialRemoveEntity equipmentMaterialRemove){
		equipmentMaterialRemoveService.updateById(equipmentMaterialRemove);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("materialRemove:equipmentmaterialremove:delete")
    public R delete(@RequestBody Integer[] equipmentIds){
		equipmentMaterialRemoveService.removeByIds(Arrays.asList(equipmentIds));

        return R.ok();
    }

}
