package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.DeviceBiz;
import com.xiaofan.car.dao.repository.CheckInfoMapper;
import com.xiaofan.car.dao.repository.DeviceInfoMapper;
import com.xiaofan.car.persistence.model.DeviceInfo;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.vo.CheckInfoVo;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;
import com.xiaofan.car.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/15
 * @since 1.0.0
 */
@Service
@Slf4j
public class DeviceBizImpl implements DeviceBiz {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    @Autowired
    private DeviceService deviceService;

    /**
     * 查询设备列表
     * @param deviceParam
     * @return
     */
    @Override
    public PageInfo<DeviceInfoVo> getDeviceList(DeviceParam deviceParam) {
        // 1.设置分页参数
        PageHelper.startPage(deviceParam.getPageNum(),deviceParam.getPageSize());
        // 2.查询对应的设备列表数据
        List<DeviceInfoVo> deviceInfoVoList = deviceInfoMapper.selectDeviceList(deviceParam.getDeviceName());

        if(CollectionUtils.isEmpty(deviceInfoVoList)){
            return null;
        }
        // 3.处理返回数据
        PageInfo<DeviceInfoVo> returnVo = new PageInfo<>(deviceInfoVoList);
        return returnVo;
    }

    /**
     * 查询设备详情
     * @param deviceId
     * @return
     */
    @Override
    public DeviceInfoVo getDeviceDetail(Integer deviceId) {
        // 1.校验参数
        Assert.notNull(deviceId,"请选中对应的设备");
        // 2.查询对应的设备信息
        DeviceInfoVo deviceInfoVo = deviceInfoMapper.selectDeviceById(deviceId);
        if(deviceInfoVo==null){
            return null;
        }
        // 3.查询设备关联的检查项信息
        List<CheckInfoVo> checkInfoVos = checkInfoMapper.getCheckInfoByDeviceId(deviceId);

        deviceInfoVo.setCheckInfoVoList(checkInfoVos);

        return deviceInfoVo;
    }

    /**
     * 新增设备信息
     * @param deviceInfoParam
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public DeviceInfoVo addDevice(DeviceInfoParam deviceInfoParam) {
        deviceService.saveDevice(deviceInfoParam);
        return null;
    }

    /**
     * 删除设备信息
     * @param deviceId
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void deleteDevice(Integer deviceId) {
        deviceService.deleteDevice(deviceId);
        //删除对应的服务项
        checkInfoMapper.deleteForDeleteDevice(deviceId);
    }

    /**
     * 更新设备信息
     * @param deviceInfoParam
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void updateDevice(DeviceInfoParam deviceInfoParam) {
        deviceService.updateDevice(deviceInfoParam);
    }

    /**
     * 批量新增设备信息
     * @param multipartFile
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean addDevice(MultipartFile multipartFile) {
        try{
            String fileName = multipartFile.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf("."));
            InputStream is = multipartFile.getInputStream();
            Workbook wb =null;
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                throw new RuntimeException("上传文件格式为：xls或者xlsx");
            }
            Sheet sheet = wb.getSheetAt(0);
            //获取总行数
            int rowNum = sheet.getLastRowNum();
            Date dateNow = Calendar.getInstance().getTime();
            List<DeviceInfo> deviceInfos = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for(int i=2;i<rowNum;i++){
                Row row = sheet.getRow(i);
                DeviceInfo deviceInfo = new DeviceInfo();
                if(StringUtils.isBlank(row.getCell(0).getStringCellValue())){
                    continue;
                }
                deviceInfo.setDeviceName(row.getCell(0).getStringCellValue());
                deviceInfo.setArea(row.getCell(1).getStringCellValue());
                deviceInfo.setLocation(row.getCell(2).getStringCellValue());
                deviceInfo.setFromDate(sdf.parse(row.getCell(3).getStringCellValue()));
                deviceInfo.setToDate(sdf.parse(row.getCell(4).getStringCellValue()));
                deviceInfo.setCreateTime(dateNow);
                deviceInfos.add(deviceInfo);
            }
            if(CollectionUtils.isNotEmpty(deviceInfos)){
                for(DeviceInfo deviceInfo:deviceInfos){
                    deviceInfoMapper.saveDevice(deviceInfo);
                }
            }
        }catch(Exception e){
            log.error("批量上传设备信息失败：",e);
            return false;
        }
        return true;
    }
}
