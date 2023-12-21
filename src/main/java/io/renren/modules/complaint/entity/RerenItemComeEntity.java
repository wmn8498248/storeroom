package io.renren.modules.complaint.entity;

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
 * @date 2023-12-19 19:04:37
 */
@Data
@TableName("reren_item_come")
public class RerenItemComeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 编号
	 */
	private String itemNo;
	/**
	 * 物料名称
	 */
	private String itemName;
	/**
	 * 规格型号
	 */
	private String specification;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 当前货位
	 */
	private String itemPlace;
	/**
	 * 生产厂家
	 */
	private String itemManufacturer;
	/**
	 * 当前库存
	 */
	private Long itemStock;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 当前仓库名称
	 */
	private String placeName;
	/**
	 * 当前货架
	 */
	private String goodsId;
	/**
	 * 状态（1:入库，2:出库）
	 */
	private String goodsStatus;
	/**
	 * 入库数量
	 */
	private Long quantityCome;
	/**
	 * 出库数量
	 */
	private Long quantityOut;
	/**
	 * 剩余库存
	 */
	private Long quantityCount;
	/**
	 * 入库时间
	 */
	private Date comeTime;
	/**
	 * 出库时间
	 */
	private Date outTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 操作人员
	 */
	private String operatorName;

}
