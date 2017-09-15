<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="2" :offset="2" style="margin-top:0.2%">
        设备名称：
        </Col>
        <Col :span="6">
        <Input type="text" v-model="queryCondition.roleName" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" offset="2">
        <Button type="primary" shape="circle" icon="ios-search" @click="getlist">查询</Button>
        </Col>
        <Col :span="2" offset="4">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="Device={id:'',roleName: ''};DeviceModal.isShow=true;DeviceModal.title='新建设备';collapse=[]">
          新建设备
        </Button>
        </Col>
      </Row>
    </Card>
    <Table border :columns="columns" :data="list"></Table>
    <div style="margin: 10px;overflow: hidden">
      <div style="float: right;">
        <Page :total="queryCondition.total" :current.sync="queryCondition.pageNum" @on-change="getlist" show-total
              show-elevator></Page>
      </div>
    </div>
    <Modal v-model="DeviceModal.isShow"
           :title="DeviceModal.title"
    >
      <Form :model="Device" :rules="rule" :label-width="80" ref="device">
        <Form-item prop="roleName" label="设备名：">
          <Input v-model="Device.roleName" placeholder="请输入"></Input>
        </Form-item>
        <Form-item label="巡检天数">
          <Input v-model="Device.roleName" placeholder="请输入"></Input>
        </Form-item>
        <Form-item label="属性一">
          <Input v-model="Device.roleName" placeholder="请输入"></Input>
        </Form-item>
        <Form-item label="属性二">
          <Input v-model="Device.roleName" placeholder="请输入"></Input>
        </Form-item>
        <Form-item label="属性三">
          <Input v-model="Device.roleName" placeholder="请输入"></Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="DeviceModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doDevice" :loading="DeviceModal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    getDeviceList,
    addDevcie,
    updateDevcie
  } from '../../api/device/device'

  export default {
    data() {
      return {
        list: [
          {id: 1, roleName: "备用"}
        ],
        queryCondition: {
          pageSize: 10,
          pageNum: 1,
          roleName: '',
          total: 0
        },
        columns: [
          {
            title: '编号',
            key: 'id'
          },
          {
            title: '巡检天数',
            key: 'roleName'
          },
          {
            title: '属性一',
            key: 'roleName'
          },
          {
            title: '属性二',
            key: 'roleName'
          },
          {
            title: '属性三',
            key: 'roleName'
          },
          {
            title: '操作',
            width: 300,
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

                    this.Device.id = params.row.id
                    this.Device.roleName = params.row.roleName
//                    getDeviceById(params.row.id).then(res=>{
//                      this.Device.checkMenu = res.data.data.checkMenu
                    this.DeviceModal.isShow = true
                    this.DeviceModal.title = '修改设备'
//                      this.collapse=[]
//                    })

                  }
                }
              }, '修改'),
              h('Button', {
                props: {
                  type: 'success'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    let device=params.row
                    this.$router.push({path: 'service',query:{deviceId:device.id,serviceName:device.roleName}})
                  }
                }
              }, '服务列表'),
            ])
          }
        ],
        rule:{
          roleName: [
            {required: true, message: '不可为空', trigger: 'blur'}
          ],
        },
        DeviceModal: {
          title: '',
          isShow: false,
          isLoading: false
        },
        Device: {
          id: '',
          roleName: ''
        },
      }
    },
    methods: {
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getDeviceList(this.queryCondition).then(res => {
//          this.queryCondition.pageNum = res.data.data.pageNum
//          this.list = res.data.data.list
//          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      },
      doDevice() {
        this.$refs['device'].validate((valid) => {
          if (valid) {
            this.DeviceModal.isLoading = true
            if (this.DeviceModal.title === '新建设备') {
              addDevcie(this.Device).then(res => {
                this.$Message.success(res.data.message)
                this.DeviceModal.isLoading = false
                this.DeviceModal.isShow = false
                this.getlist()
              })
            } else if (this.DeviceModal.title === '修改设备') {
              updateDevcie(this.Device).then(res => {
                this.$Message.success(res.data.message)
                this.DeviceModal.isLoading = false
                this.DeviceModal.isShow = false
                this.getlist()
              })
            }
          }
        })
      }
    }
  }
</script>

<style>

</style>
