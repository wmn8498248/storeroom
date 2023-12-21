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

import io.renren.modules.warehouse.entity.MaterialsupplyquantityEntity;
import io.renren.modules.warehouse.service.MaterialsupplyquantityService;
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
@RequestMapping("complaint/materialsupplyquantity")
public class MaterialsupplyquantityController {
    @Autowired
    private MaterialsupplyquantityService materialsupplyquantityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("complaint:materialsupplyquantity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = materialsupplyquantityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("complaint:materialsupplyquantity:info")
    public R info(@PathVariable("id") Integer id){
		MaterialsupplyquantityEntity materialsupplyquantity = materialsupplyquantityService.getById(id);

        return R.ok().put("materialsupplyquantity", materialsupplyquantity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("complaint:materialsupplyquantity:save")
    public R save(@RequestBody MaterialsupplyquantityEntity materialsupplyquantity){
		materialsupplyquantityService.save(materialsupplyquantity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("complaint:materialsupplyquantity:update")
    public R update(@RequestBody MaterialsupplyquantityEntity materialsupplyquantity){
		materialsupplyquantityService.updateById(materialsupplyquantity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("complaint:materialsupplyquantity:delete")
    public R delete(@RequestBody Integer[] ids){
		materialsupplyquantityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
