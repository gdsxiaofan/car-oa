<template>
  <div>
    <Card>
      <p slot="title">设备信息</p>
      <Row>
        <Col :span="9" :offset="2" style="margin-top:0.2%">
        设备名：{{device.deviceName}}
        </Col>
        <!--<Col :span="9" :offset="2" style="margin-top:0.2%">-->
        <!--巡检天数:{{device.routingDays}}-->
        <!--</Col>-->
        <Col :span="9" :offset="2" style="margin-top:0.2%">
        区域:{{device.area}}
        </Col>
        <Col :span="9" :offset="2" style="margin-top:2%">
        位置:{{device.location}}
        </Col>
        <Col :span="3" :offset="7" style="margin-top:2%">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="add">
          新建服务
        </Button>
        </Col>
      </Row>
    </Card>
    <Table border :columns="columns" :data="list"></Table>
    <Row>
      <Col :offset="11">
      <Button type="primary" v-on:click.native="$router.go(-1)">
        上一页
      </Button>
      </Col>
    </Row>
    <!--<div style="margin: 10px;overflow: hidden">-->
    <!--<div style="float: right;">-->
    <!--<Page :total="queryCondition.total" :current.sync="queryCondition.pageNum" @on-change="getlist" show-total-->
    <!--show-elevator></Page>-->
    <!--</div>-->
    <!--</div>-->
    <Modal v-model="userModal.isShow"
           :title="userModal.title"
    >
      <Form ref="user" :model="service" :rules="serviceRules" :label-width="80">
        <Form-item label="服务名称：" prop="checkPoint">
          <Input type="text" v-model="service.serviceName" :disabled="userModal.disabled" placeholder="服务名称"/>
        </Form-item>
        <Form-item label="巡检周期：">
          <InputNumber :max="99999" v-model="service.checkCycle" :disabled="userModal.disabled"></InputNumber>天
        </Form-item>
        <Form-item label="检查时间">
          <TimePicker format="HH:mm" :value="service.checkTime" @on-change='service.checkTime=arguments[0]'
                      style="width: 300px" :disabled="userModal.disabled" :key="service.checkTime"
                      :clearable="false"></TimePicker>
        </Form-item>
        <Form-item label="开始时间">
          <DatePicker type="date" :value="service.fromDate" @on-change='service.fromDate=arguments[0]'
                      placeholder="选择日期和时间（不含秒）"   :key="service.fromDate"
                      style="width: 300px" :disabled="userModal.disabled"
                      :clearable="false"></DatePicker>
        </Form-item>
        <Form-item label="参考照片">
          <Row class="ModalRow">
            <div class="demo-upload-list" v-for="(item,index) in uploadList">
              <img @click="handleView(item)" :src="env+'/v1/image/sosOutImg/'+item" v-if="userModal.disabled">
              <img :src="env+'/v1/image/sosOutImg/'+item" v-else>
              <div class="demo-upload-list-cover" v-if="!userModal.disabled">
                <Icon type="ios-eye-outline" @click.native="handleView(item)"></Icon>
                <Icon type="ios-trash-outline" @click.native="handleRemove(index)"></Icon>
              </div>
            </div>
            <upload v-if="!userModal.disabled"
                    ref="uploads"
                    :action="env+'/v1/image/upPic'"
                    :show-upload-list="false"
                    :before-upload="handleBeforeUpload"
                    :on-success="handleSuccess"
                    multiple
                    type="drag"
                    style="display: inline-block;width:58px;">
              <div style="width: 58px;height:58px;line-height: 58px;">
                <Icon type="camera" size="20"></Icon>
              </div>
            </upload>
          </Row>
        </Form-item>
        <Form-item label="标准检查操作步骤">
          <Input type="textarea" autosize v-model="service.serviceDescribe" :disabled="userModal.disabled"
                 placeholder=""/>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doService" :loading="userModal.isLoading" v-if="!userModal.disabled">提交</Button>
        <Button type="primary" @click="userModal.isShow=false" v-else>确认</Button>
      </div>
    </Modal>
    <Modal title="查看图片" v-model="visible">
      <img :src="img" v-if="visible" style="width: 100%">
    </Modal>
  </div>
</template>

<script>
  import {
    addService,
    getServiceList,
    updateService,
    delService
  } from '../../api/device/service'
  import {
    devcieInfo
  } from '../../api/device/device'
  import {
    formatData
  } from '../../lib/utils/common'

  export default {
    data() {
      return {
        env: process.env.NODE_ENV === 'production' ? '' : "car",
        uploadList: [],
        visible: false,
        device: {
          deviceName: '',
          routingDays: 1,
          area: '',
          location: ''
        },
        service: {
          id: '',
          checkPoint: '',
          checkComment: '',
          setValue: '',
          shiftsNo: '',
          checkTime: '',
          serviceDescribe: '',
          fromDate: '',
          lastCheckTime: '',
        },
        serviceRules: {
          serviceName: [
            {required: true, message: '请填写服务名', trigger: 'blur'}
          ],

        },
        userModal: {
          isShow: false,
          isLoading: false,
          title: ''
        },

        columns: [
          {
            title: '序号',
            key: 'id'
          },
          {
            title: '服务名称',
            key: 'serviceName'
          },
          {
            title: '设备名称',
            key: 'deviceName'
          },
          {
            title: '巡检周期（天）',
            key: 'checkCycle'
          },
          {
            title: '巡检日期',
            key: 'firstCheckTime'
          },
          {
            title: '检查时间',
            key: 'checkTime'
          },
          {
            title: '操作',
            width: 240,
            render: (h, params) => h('div', [
              h('Button', {
                  props: {
                    type: 'info'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.showServiceInfo('修改服务', params)
                    }
                  }
                },
                '修改'
              ),
              h('Button', {
                  props: {
                    type: 'primary'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.showServiceInfo('服务详情', params)
                    }
                  }
                },
                '详情'
              ),
              h('Button', {
                props: {
                  type: 'error'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.$Modal.confirm({
                      title: '是否删除',
                      content: '<p>' + params.row.checkPoint + '</p>',
                      loading: true,
                      onOk: () => {
                        delService(params.row).then(res => {
                          this.$Message.success(res.data.message);
                          this.$Modal.remove()
                          this.getlist()
                        })

                      }
                    });
                  }
                }
              }, '删除')]
            )
          }
        ],
        list: [],
        RoleList: []
      }
    },
    methods: {
      add() {
        this.service = {
          id: '',
          checkPoint: '',
          checkComment: '',
          setValue: '',
          checkCycle: 30,
          shiftsNo: 1,
          checkTime: '08:00',
          serviceDescribe: '',
          fromDate: formatData.call(new Date(), 'yyyy-MM-dd'),
          lastCheckTime: new Date(),
        }
        this.uploadList = []
        this.userModal.title = '新增服务'
        this.userModal.isShow = true
        this.userModal.disabled = false
      },
      getlist() {
        getServiceList(this.$route.query.deviceId).then(res => {
          this.list = res.data.data
        }).catch(err => {

        });
      },
      showServiceInfo(type, params) {
        this.$refs['user'].resetFields()
        this.service.id = params.row.id
        this.service.serviceName = params.row.serviceName
        this.service.checkCycle = params.row.checkCycle
        this.service.serviceDescribe = params.row.serviceDescribe
        this.service.fromDate = params.row.firstCheckTime
        this.service.lastCheckTime = params.row.lastCheckTime
        this.service.checkTime = params.row.checkTime
        this.uploadList = params.row.attachements.map(e => e.id)
        this.userModal.isShow = true
        this.userModal.title = type
        this.userModal.disabled = type === '服务详情'
      },
      doService() {
        this.$refs['user'].validate((valid) => {
          if (valid) {
            this.userModal.isLoading = true
            this.service.deviceId = this.$route.query.deviceId
            this.service.deviceName = this.device.deviceName
            let attachmentIds = ''
            this.uploadList.forEach((i, n) => {
              if (this.uploadList.length === n + 1) {
                attachmentIds += i
              } else {
                attachmentIds += i + ','
              }
            })
            this.service.attachmentIds = attachmentIds
            if (this.userModal.title === '修改服务') {
              updateService(this.service).then(res => {
                this.userModal.isLoading = false
                this.userModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              }).catch(e => {
                this.userModal.isLoading = false
              })
            } else if (this.userModal.title === '新增服务') {
              addService(this.service).then(res => {
                this.userModal.isLoading = false
                this.userModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              }).catch(e => {
                this.userModal.isLoading = false
              })
            }
          }
        })
      },
      handleView(id) {
        this.img = this.env + '/v1/image/sosOutImg/' + id;
        this.visible = true;
      },
      handleRemove(index) {
        this.uploadList.splice(index, 1);
      },
      handleSuccess(res, file) {
        this.uploadList.push(res.message)
      },
      handleBeforeUpload(file) {

        const format = ['jpg', 'jpeg', 'png'];
        const maxsize = "2048";
        const check = this.uploadList.length < 5;
        if (!check) {
          this.$Notice.warning({
            title: '最多只能上传 5 张图片。'
          });
        }
        return check
      },
      checkImg(file, format, maxSize) {
        // check format
        if (format.length) {
          const _file_format = file.name.split('.').pop().toLocaleLowerCase();
          const checked = format.some(item => item.toLocaleLowerCase() === _file_format);
          if (!checked) {
            this.$Notice.warning({
              title: '文件格式不正确',
              desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
            return false;
          }
        }
        // check maxSize
        if (maxSize) {
          if (file.size > this.maxSize * 1024) {
            this.$Notice.warning({
              title: '超出文件大小限制',
              desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
            return false;
          }
        }
        return true
      }
    },
    created() {
      devcieInfo(this.$route.query.deviceId).then(res => {
        this.device.area = res.data.data.area
        this.device.deviceName = res.data.data.deviceName
        this.device.location = res.data.data.location
        this.device.routingDays = res.data.data.routingDays
      })
      getServiceList(this.$route.query.deviceId).then(res => {
        this.list = res.data.data
      })
    }
  }
</script>

<style scoped="">
  .ModalRow {
    margin-top: 1%;
  }

  .demo-upload-list {
    display: inline-block;
    width: 60px;
    height: 60px;
    text-align: center;
    line-height: 60px;
    border: 1px solid transparent;
    border-radius: 4px;
    overflow: hidden;
    background: #fff;
    position: relative;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
    margin-right: 4px;
  }

  .demo-upload-list img {
    width: 100%;
    height: 100%;
  }

  .demo-upload-list-cover {
    display: block;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, .6);
  }

  .demo-upload-list-cover i {
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    margin: 0 2px;
  }
</style>
