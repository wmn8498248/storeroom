package io.renren.modules.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-12-21 14:47:46
 */
@Data
@TableName("materialsupplyquantity")
public class MaterialsupplyquantityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId
	private Integer id;
	/**
	 * 设备种类
	 */
	private String equipmentcategory;
	/**
	 * 设备名称
	 */
	private String equipmentname;
	/**
	 * 定额测算标准
	 */
	private String quotacalculationstandard;
	/**
	 * 细分名称
	 */
	private String subdivisionname;
	/**
	 * 规格型号
	 */
	private String specificationsmodel;
	/**
	 * 设备厂家
	 */
	private String equipmentmanufacturer;
	/**
	 * 测算备品备件定额
	 */
	private BigDecimal quotaforsparepartscalculation;
	/**
	 * 现有备品备件数量
	 */
	private Integer currentquantityofspareparts;
	/**
	 * 缺额数量
	 */
	private Integer deficiencyquantity;
	/**
	 * 物资指导价
	 */
	private BigDecimal materialunitprice;
	/**
	 * 计划采购数量
	 */
	private Integer plannedpurchasequantity;
	/**
	 * 缺额金额
	 */
	private BigDecimal deficiencyamount;
	/**
	 * 紧急程度
	 */
	private String urgencylevel;
	/**
	 * 采购批次
	 */
	private String purchasebatch;
	/**
	 * 到货进度
	 */
	private String arrivalprogress;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 库房
	 */
	private String warehouse;
	/**
	 * 更新时间
	 */
	private Date updatedate;
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 操作人员
	 */
	private String operatorname;

}
