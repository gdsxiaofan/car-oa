<template>
  <div>
    <Card>
      <p slot="title">设备信息</p>
      <Row>
        <Col :span="9" :offset="2" style="margin-top:0.2%">
        设备名：{{device.deviceName}}
        </Col>
        <Col :span="9" :offset="2" style="margin-top:0.2%">
        巡检天数:{{device.routingDays}}
        </Col>
        <Col :span="9" :offset="2" style="margin-top:2%">
        区域:{{device.area}}
        </Col>
        <Col :span="9" :offset="2" style="margin-top:2%">
        位置:{{device.location}}
        </Col>
        <Col :span="3" :offset="18" style="margin-top:2%">
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
        <Form-item label="检查点：" prop="checkPoint">
          <Input type="text" v-model="service.checkPoint" :disabled="userModal.disabled" placeholder="服务名称"/>
        </Form-item>
        <Form-item label="检查/维护内容：">
          <Input type="textarea" autosize v-model="service.checkComment" :disabled="userModal.disabled"
                 placeholder="属性一"/>
        </Form-item>
        <Form-item label="设定值：">
          <Input type="text" v-model="service.setValue" :disabled="userModal.disabled" placeholder="属性二"/>
        </Form-item>
        <Form-item label="班次：">
          <Select type="text" v-model="service.shiftsNo" :disabled="userModal.disabled" placeholder="属性三">
            <Option :value="1">早班</Option>
            <Option :value="2">中班</Option>
          </Select>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doService" :loading="userModal.isLoading" v-if="!userModal.disabled">提交</Button>
        <Button type="primary" @click="userModal.isShow=false" v-else>确认</Button>
      </div>
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

  export default {
    data() {
      return {
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
            title: '检查点',
            key: 'checkPoint'
          },
          {
            title: '检查/维护内容',
            key: 'checkComment'
          },
          {
            title: '设定值',
            key: 'setValue'
          },
          {
            title: '班次',
            key: 'shiftsNoName'
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
        list: [{id: 1, serviceName: 'aaa'}],
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
          shiftsNo: '',
        }
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
        this.service.checkPoint = params.row.checkPoint
        this.service.checkComment = params.row.checkComment
        this.service.setValue = params.row.setValue
        this.service.shiftsNo = params.row.shiftsNo
        this.userModal.isShow = true
        this.userModal.title = type
        this.userModal.disabled = type === '服务详情'
      },
      doService() {
        this.$refs['user'].validate((valid) => {
          if (valid) {
            this.userModal.isLoading = true
            this.service.deviceId = this.$route.query.deviceId
            if (this.userModal.title === '修改服务') {
              updateService(this.service).then(res => {
                this.userModal.isLoading = false
                this.userModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            } else if (this.userModal.title === '新增服务') {
              addService(this.service).then(res => {
                this.userModal.isLoading = false
                this.userModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            }
          }
        })
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

<style>

</style>
