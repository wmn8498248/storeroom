package io.renren.modules.material.controller;

import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.renren.modules.warehouse.entity.MaterialsupplyquantityEntity;
import io.renren.modules.warehouse.service.MaterialsupplyquantityService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.material.entity.EquipmentMaterialEntity;
import io.renren.modules.material.service.EquipmentMaterialService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-25 16:35:36
 */
@RestController
@RequestMapping("material/equipmentmaterial")
public class EquipmentMaterialController {
    @Autowired
    private EquipmentMaterialService equipmentMaterialService;

    @Autowired
    private MaterialsupplyquantityService materialsupplyquantityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("material:equipmentmaterial:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = equipmentMaterialService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{equipmentId}")
    @RequiresPermissions("material:equipmentmaterial:info")
    public R info(@PathVariable("equipmentId") Integer equipmentId){
		EquipmentMaterialEntity equipmentMaterial = equipmentMaterialService.getById(equipmentId);


        return R.ok().put("equipmentMaterial", equipmentMaterial);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("material:equipmentmaterial:save")
    public R save(@RequestBody EquipmentMaterialEntity equipmentMaterial){
        String deviceNumber = equipmentMaterial.getDeviceNumber();
        MaterialsupplyquantityEntity materialsupplyquantity = materialsupplyquantityService.getById(deviceNumber);

//        入库后的库存
        Integer meterialCount =  materialsupplyquantity.getCurrentquantityofspareparts(); // 库存数量
        Integer meterialAdd = equipmentMaterial.getWarehouseQuantity();
//        Integer currentStock = equipmentMaterial.getCurrentStock();
        Integer currentStock = meterialCount + meterialAdd;
        materialsupplyquantity.setCurrentquantityofspareparts(currentStock);

//        缺额数量
        Integer quotaforsparepartscalculation = materialsupplyquantity.getQuotaforsparepartscalculation().intValue(); //定额数量

        if (quotaforsparepartscalculation >= currentStock) {
            Integer scarcityQuantity = quotaforsparepartscalculation - currentStock; // 差额
            materialsupplyquantity.setDeficiencyquantity(scarcityQuantity); // 稀缺数量
            materialsupplyquantity.setPlannedpurchasequantity(scarcityQuantity); // 计划采购数量
            // 将 Integer 转换为 BigDecimal，然后相乘
            BigDecimal scarcityAmount = materialsupplyquantity.getMaterialunitprice().multiply(new BigDecimal(scarcityQuantity));
            materialsupplyquantity.setDeficiencyamount(scarcityAmount); // 稀缺金额
        } else {
            materialsupplyquantity.setDeficiencyquantity(0);
            materialsupplyquantity.setPlannedpurchasequantity(0);
            materialsupplyquantity.setDeficiencyamount(new BigDecimal(0));
        }

        String warehouse = materialsupplyquantity.getWarehouse();// 获取库房名称 a

        String storageRoom = equipmentMaterial.getStorageRoom(); // 插入的库房成 a b

        if (warehouse == null || warehouse.equals("")) {
            warehouse = storageRoom;
            materialsupplyquantity.setWarehouse(JSON.toJSONString(warehouse));
        } else {
            if(warehouse.contains(storageRoom)){

            } else {
                warehouse = storageRoom + "," + warehouse;
            }
        }
        materialsupplyquantity.setWarehouse(warehouse);

//        if(warehouse.isEmpty()){
//
//        } else {
//            JSONObject jsonObject = JSON.parseObject(warehouse);
//            for (String key : jsonObject.keySet()) {
//                // 3. 遍历属性，并执行相应的操作
//                Object value = jsonObject.get(key);
//                // 在这里写下你需要执行的操作代码
//                System.out.println(key + ": " + value);
//            }
//
//            String jsonObjectStr = JSON.toJSONString(jsonObject);
//        }

//        List<String> list = new ArrayList<String>() {{
//            add(warehouse);
//            add(storageRoom);
//        }};

//        materialsupplyquantity.setWarehouse(method_2(list));

         //稀缺数量
//        Integer quotaforsparepartscalculation =

//        入库数量



        materialsupplyquantityService.updateById(materialsupplyquantity);

		equipmentMaterialService.save(equipmentMaterial);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("material:equipmentmaterial:update")
    public R update(@RequestBody EquipmentMaterialEntity equipmentMaterial){
		equipmentMaterialService.updateById(equipmentMaterial);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("material:equipmentmaterial:delete")
    public R delete(@RequestBody Integer[] equipmentIds){
		equipmentMaterialService.removeByIds(Arrays.asList(equipmentIds));

        return R.ok();
    }
    public String method_2(List<String> list) {
        HashSet<String> set = new HashSet<>(list);
        System.out.println("去重集合:" + set);
        return set.toString();
    }
}
