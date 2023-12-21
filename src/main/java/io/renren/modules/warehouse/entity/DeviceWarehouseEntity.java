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
@TableName("device_warehouse")
public class DeviceWarehouseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 库房名称
	 */
	private String warehouseName;
	/**
	 * 区域ID
	 */
	private Long warehouseId;

}
