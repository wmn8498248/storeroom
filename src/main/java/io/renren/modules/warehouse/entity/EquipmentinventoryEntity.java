package io.renren.modules.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("equipmentinventory")
public class EquipmentinventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId
	private Integer id;
	/**
	 * 设备名称
	 */
	private String equipmentname;
	/**
	 * 规格型号
	 */
	private String specificationsmodel;
	/**
	 * 设备厂家
	 */
	private String equipmentmanufacturer;
	/**
	 * 现有备品备件数量
	 */
	private Integer currentquantityofspareparts;
	/**
	 * 货位
	 */
	private String storagelocation;
	/**
	 * 所属设备分类
	 */
	private String equipmentcategory;
	/**
	 * 物料现场描述
	 */
	private String materialdescription;
	/**
	 * 照片路径
	 */
	private String photopath;
	/**
	 * 出入库操作
	 */
	private String inoutoperation;
	/**
	 * 更新日期
	 */
	private Date updatedate;

}
