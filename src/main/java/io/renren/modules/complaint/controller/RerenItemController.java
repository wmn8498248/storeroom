package io.renren.modules.complaint.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.complaint.service.RerenItemComeService;
import io.renren.modules.complaint.service.RerenItemOutService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.complaint.entity.RerenItemEntity;
import io.renren.modules.complaint.entity.RerenItemComeEntity;
import io.renren.modules.complaint.entity.RerenItemOutEntity;

import io.renren.modules.complaint.service.RerenItemService;
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
@RequestMapping("complaint/rerenitem")
public class RerenItemController {
    @Autowired
    private RerenItemService rerenItemService;

    @Autowired
    private RerenItemOutService rerenItemOutService;

    @Autowired
    private RerenItemComeService rerenItemComeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("complaint:rerenitem:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rerenItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("complaint:rerenitem:info")
    public R info(@PathVariable("id") Long id){
		RerenItemEntity rerenItem = rerenItemService.getById(id);

        return R.ok().put("rerenItem", rerenItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("complaint:rerenitem:save")
    public R save(@RequestBody RerenItemEntity rerenItem){
        RerenItemComeEntity comeEntity = new RerenItemComeEntity();
        comeEntity.setPlaceName(rerenItem.getPlaceName());
        comeEntity.setItemName(rerenItem.getItemName());
        comeEntity.setSpecification(rerenItem.getSpecification());
        comeEntity.setUnit(rerenItem.getUnit());
        comeEntity.setItemPlace(rerenItem.getItemPlace());
        comeEntity.setItemManufacturer(rerenItem.getItemManufacturer());
        comeEntity.setItemStock(rerenItem.getItemStock());
        comeEntity.setRemark(rerenItem.getRemark());
        comeEntity.setGoodsId(rerenItem.getGoodsId());
        comeEntity.setComeTime(rerenItem.getComeTime());
        comeEntity.setOperatorName(rerenItem.getOperatorName());

        comeEntity.setQuantityCome(rerenItem.getItemStock());
        comeEntity.setUpdateTime(rerenItem.getUpdateTime());

        rerenItemComeService.save(comeEntity);
        rerenItemService.save(rerenItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("complaint:rerenitem:update")
    public R update(@RequestBody RerenItemEntity rerenItem){

        RerenItemOutEntity  outEntity = new RerenItemOutEntity(); //出库记录
        RerenItemComeEntity  comeEntity = new RerenItemComeEntity(); //出库记录

        if(rerenItem.getQuantityCome() == -1){
            outEntity.setPlaceName(rerenItem.getPlaceName());
            outEntity.setItemName(rerenItem.getItemName());
            outEntity.setSpecification(rerenItem.getSpecification());
            outEntity.setUnit(rerenItem.getUnit());
            outEntity.setItemPlace(rerenItem.getItemPlace());
            outEntity.setItemManufacturer(rerenItem.getItemManufacturer());
            outEntity.setRemark(rerenItem.getRemark());
            outEntity.setGoodsId(rerenItem.getGoodsId());
            outEntity.setComeTime(rerenItem.getComeTime());
            outEntity.setOperatorName(rerenItem.getItemNo());
            outEntity.setOperatorName(rerenItem.getOperatorName());
            outEntity.setQuantityCount(rerenItem.getQuantityCount());
            outEntity.setQuantityCome(rerenItem.getQuantityCome());
            outEntity.setQuantityOut(rerenItem.getQuantityOut());
            outEntity.setUpdateTime(rerenItem.getUpdateTime());
            outEntity.setItemStock(rerenItem.getItemStock() - rerenItem.getQuantityOut());
            rerenItem.setItemStock(rerenItem.getItemStock() - rerenItem.getQuantityOut());
            rerenItemOutService.save(outEntity);

        }
        if(rerenItem.getQuantityOut() == -1){
            comeEntity.setPlaceName(rerenItem.getPlaceName());
            comeEntity.setItemName(rerenItem.getItemName());
            comeEntity.setSpecification(rerenItem.getSpecification());
            comeEntity.setUnit(rerenItem.getUnit());
            comeEntity.setItemPlace(rerenItem.getItemPlace());
            comeEntity.setItemManufacturer(rerenItem.getItemManufacturer());
            comeEntity.setRemark(rerenItem.getRemark());
            comeEntity.setGoodsId(rerenItem.getGoodsId());
            comeEntity.setComeTime(rerenItem.getComeTime());
            comeEntity.setOperatorName(rerenItem.getOperatorName());
            comeEntity.setQuantityCount(rerenItem.getQuantityCount());
            comeEntity.setQuantityCome(rerenItem.getQuantityCome());
            comeEntity.setQuantityOut(rerenItem.getQuantityOut());
            comeEntity.setUpdateTime(rerenItem.getUpdateTime());
            comeEntity.setItemStock(rerenItem.getItemStock() + rerenItem.getQuantityCome());
            rerenItem.setItemStock(rerenItem.getItemStock() + rerenItem.getQuantityCome());
            rerenItemComeService.save(comeEntity);

        }

		rerenItemService.updateById(rerenItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("complaint:rerenitem:delete")
    public R delete(@RequestBody Long[] ids){
		rerenItemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
