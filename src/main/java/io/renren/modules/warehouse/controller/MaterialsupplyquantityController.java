package io.renren.modules.warehouse.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.warehouse.entity.MaterialsupplyquantityEntity;
import io.renren.modules.warehouse.service.MaterialsupplyquantityService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;

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
     * 导入
     */
    @RequestMapping("/toimport")
    @RequiresPermissions("complaint:materialsupplyquantity:save")
    public R toimport(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return R.ok("请选择一个文件");
        }
        try {
            // 保存文件到服务器
            File serverFile = new File("/" + file.getOriginalFilename());
            file.transferTo(serverFile);
            return R.ok("图片上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return R.ok("图片上传失败");
        }
    }

    /**
     * 导入
     */
    @RequestMapping("/import")
    @RequiresPermissions("complaint:materialsupplyquantity:save")
    public R save(@RequestParam("file") MultipartFile file, @RequestParam String equipmentArea){
//        materialsupplyquantityService.save(materialsupplyquantity);
        boolean isExcel2003 = true;
        if (file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is;
        StringBuffer sb = new StringBuffer();

        try {
            is = file.getInputStream();
            Workbook wb;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            Sheet sheet = wb.getSheetAt(0);
            for (int r = 1; r <= (sheet != null ? sheet.getLastRowNum() : 0); r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                String equipmentCategory = getCellValue(row.getCell(1)); // 设备种类
                String equipmentName = getCellValue(row.getCell(2)); // 设备名称
                String quotaCalculationStandard = getCellValue(row.getCell(3)); // 定额测算标准
                String subdivisionName = getCellValue(row.getCell(4)); // 细分名称
                String specificationsModel = getCellValue(row.getCell(5)); // 规格型号
                String equipmentManufacturer = getCellValue(row.getCell(6)); // 设备厂家
                String quotaForSparePartsCalculation = getCellValue(row.getCell(7)); // 测算备品备件定额
                String currentQuantityOfSpareParts = getCellValue(row.getCell(8)); // 现有备品备件数量
                String deficiencyQuantity = getCellValue(row.getCell(9)); // 缺额数量
                String materialUnitPrice = getCellValue(row.getCell(10)); // 物资指导价
                String plannedPurchaseQuantity = getCellValue(row.getCell(11)); // 计划采购数量
                String deficiencyAmount = getCellValue(row.getCell(12)); // 缺额金额

                MaterialsupplyquantityEntity materialsupplyquantity =  new MaterialsupplyquantityEntity();
                materialsupplyquantity.setEquipmentcategory(getIsEmpty(equipmentCategory, "/"));
                materialsupplyquantity.setEquipmentname(getIsEmpty(equipmentName, "/"));
                materialsupplyquantity.setQuotacalculationstandard(getIsEmpty(quotaCalculationStandard, "/"));
                materialsupplyquantity.setSubdivisionname(getIsEmpty(subdivisionName, "/"));
                materialsupplyquantity.setSpecificationsmodel(getIsEmpty(specificationsModel, "/"));
                materialsupplyquantity.setEquipmentmanufacturer(getIsEmpty(equipmentManufacturer, "/"));
                materialsupplyquantity.setQuotaforsparepartscalculation(new BigDecimal(getIsEmpty(quotaForSparePartsCalculation, "0")));
                materialsupplyquantity.setCurrentquantityofspareparts(Integer.parseInt(getIsEmpty(currentQuantityOfSpareParts, "0")));
                materialsupplyquantity.setDeficiencyquantity(Integer.parseInt(getIsEmpty(deficiencyQuantity, "0")));
                materialsupplyquantity.setMaterialunitprice(new BigDecimal(getIsEmpty(materialUnitPrice, "0")));
                materialsupplyquantity.setPlannedpurchasequantity(Integer.parseInt(getIsEmpty(plannedPurchaseQuantity, "0")));
                materialsupplyquantity.setDeficiencyamount(new BigDecimal(getIsEmpty(deficiencyAmount, "0")));
                materialsupplyquantity.setEquipmentarea(getIsEmpty(equipmentArea, "/"));
                materialsupplyquantityService.save(materialsupplyquantity);
            }

            if (sb.length() > 0) {
                return R.ok("导入成功！之前已存在传感器包括：" + sb);
            }
            return R.ok("导入成功！");

        } catch (IOException e) {
            e.printStackTrace();
            return R.error("导入文件异常！");
        }

//        return R.ok();
    }

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
    public String getIsEmpty(String value, String defaultValue){
        if (value == null || value.isEmpty()) {
            value = defaultValue;
        }
        return value;
    };
    public String getCellValue(Cell cell) {
        String cellValue = "";
        // 以下是判断数据的类型
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                // 数字
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            case STRING:
                // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case FORMULA:
                // 公式
                cellValue = cell.getCellFormula() + "";
                break;
            case BLANK:
                // 空值
                cellValue = "";
                break;
            case ERROR:
                // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


}
