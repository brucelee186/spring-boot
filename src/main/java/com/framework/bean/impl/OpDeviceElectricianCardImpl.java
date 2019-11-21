package com.framework.bean.impl;

import com.framework.bean.OpDeviceElectricianCard;

/*
 **********************************************
 * 项目名称: AnSteel
 * 模块名称: 基础资料 -> 设备电工牌
 * 作者:    Auto
 * 日期:    2019/8/19
 **********************************************
 */
public class OpDeviceElectricianCardImpl extends OpDeviceElectricianCard {
	private static final long serialVersionUID = 1L;
	
	private String location;
	private String name;
	private String description;
	private String deviceName;//设备名称
	
	private String nameOpDevice;
	
	private String locationOpDeviceCabinet;
	
	private String nameElectrician;
	
	private String nameStatusElectrician;
	
	public String getNameStatusElectrician() {
		return nameStatusElectrician;
	}

	public void setNameStatusElectrician(String nameStatusElectrician) {
		this.nameStatusElectrician = nameStatusElectrician;
	}

	public String getNameElectrician() {
		return nameElectrician;
	}

	public void setNameElectrician(String nameElectrician) {
		this.nameElectrician = nameElectrician;
	}

	public String getLocationOpDeviceCabinet() {
		return locationOpDeviceCabinet;
	}

	public void setLocationOpDeviceCabinet(String locationOpDeviceCabinet) {
		this.locationOpDeviceCabinet = locationOpDeviceCabinet;
	}

	public String getNameOpDevice() {
		return nameOpDevice;
	}

	public void setNameOpDevice(String nameOpDevice) {
		this.nameOpDevice = nameOpDevice;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	
}