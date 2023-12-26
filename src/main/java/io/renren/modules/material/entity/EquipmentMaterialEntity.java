package io.renren.modules.material.entity;

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
 * @date 2023-12-25 16:35:36
 */
@Data
@TableName("equipment_material")
public class EquipmentMaterialEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer equipmentId;
	/**
	 * 设备编号
	 */
	private String deviceNumber;
	/**
	 * 设备种类
	 */
	private String deviceCategory;
	/**
	 * 设备名称
	 */
	private String deviceName;
	/**
	 * 细分名称
	 */
	private String subcategoryName;
	/**
	 * 规格型号
	 */
	private String modelSpecification;
	/**
	 * 入库人员
	 */
	private String warehousePerson;
	/**
	 * 入库数量
	 */
	private Integer warehouseQuantity;
	/**
	 * 入库后库存
	 */
	private Integer currentStock;
	/**
	 * 到货日期
	 */
	private Date arrivalDate;
	/**
	 * 物资来源
	 */
	private String materialSource;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 供应商
	 */
	private String supplier;
	/**
	 * 材料编码
	 */
	private String materialCode;
	/**
	 * 物料描述
	 */
	private String materialDescription;
	/**
	 * 含税金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 签单收货人
	 */
	private String signee;
	/**
	 * 所属设备类别
	 */
	private String deviceType;
	/**
	 * 库房
	 */
	private String storageRoom;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 操作账号
	 */
	private String operatorAccount;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 更新时间
	 */
	private Date updatedAt;
	/**
	 * 货位
	 */
	private String goodsAddr;
	/**
	 * 照片
	 */
	private String goodsPhoto;
}
