<template>
  <div>
    <Card>
      <p slot="title">设备信息</p>
      <Row>
        <Col :span="2" :offset="2" style="margin-top:0.2%">
        设备名称：
        </Col>
        <Col :span="6">
        设备名字
        </Col>
        <Col :span="2" offset="2">
        设备名字
        </Col>
        <Col :span="2" offset="4">
        设备名字
        </Col>
      </Row>
    </Card>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="4" style="margin-top:0.2%">
        服务名称：
        </Col>
        <Col :span="4">
        <Input type="text" v-model="queryCondition.name" placeholder="请输入..."></Input>
        </Col>
        <Col :span="3" offset="1">
        <Button type="primary" shape="circle" icon="ios-search" @click="getlist">查询</Button>
        </Col>
        <Col :span="3" offset="1">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="add">
          新建服务
        </Button>
        </Col>
      </Row>
    </Card>
    <Table border :columns="columns"  :data="list"></Table>
    <div style="margin: 10px;overflow: hidden">
      <div style="float: right;">
        <Page :total="queryCondition.total" :current.sync="queryCondition.pageNum" @on-change="getlist" show-total
              show-elevator></Page>
      </div>
    </div>
    <Modal v-model="userModal.isShow"
           :title="userModal.title"
    >
      <Form ref="user" :model="service" :rules="serviceRules" :label-width="80">
        <Form-item label="服务名称：" prop="serviceName">
          <Input type="text" v-model="service.serviceName" :disabled="userModal.disabled" placeholder="服务名称" />
        </Form-item>
        <Form-item label="属性一：" >
          <Input type="text" v-model="service.property1" :disabled="userModal.disabled" placeholder="属性一" />
        </Form-item>
        <Form-item label="属性二：">
          <Input type="text" v-model="service.property2" :disabled="userModal.disabled" placeholder="属性二" />
        </Form-item>
        <Form-item label="属性三：" >
          <Input type="text" v-model="service.property3" :disabled="userModal.disabled" placeholder="属性三" />
        </Form-item>
        <Form-item label="属性四：" >
          <Input type="text" v-model="service.property4" :disabled="userModal.disabled" placeholder="属性四" />
        </Form-item>
        <Form-item label="描述："   >
          <Input type="textarea" autosize :disabled="userModal.disabled" v-model="service.serviceDescrible" placeholder="描述" />
        </Form-item>

      </Form>
      <div slot="footer">
        <Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doService" :loading="userModal.isLoading" v-if="!userModal.disabled">提交</Button>
        <Button type="primary" @click="userModal.isShow=false"  v-else>确认</Button>
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

  export default {
    data() {
      return {
        service: {
          id: '',
          serviceName: '',
          property1:'',
          property2:'',
          property3:'',
          property4:'',
          serviceDescrible:'',
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
        queryCondition: {
          pageSize: 10,
          pageNum: 1,
          deviceId: 0,
          name: '',
          total: 0
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
            title: '属性一',
            key: 'property1'
          },
          {
            title: '属性二',
            key: 'property2'
          },
          {
            title: '属性三',
            key: 'property3'
          },
          {
            title: '属性四',
            key: 'property4'
          },
          {
            title: '操作',
            width:240,
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
                      this.showServiceInfo('服务详情',params)
                    }
                  }
                },
                '详情'
              ),
              h('Button', {
                  props: {
                    type: 'info'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.showServiceInfo('修改服务',params)
                    }
                  }
                },
                '修改'
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
                      content: '<p>' + params.row.serviceName + '</p>',
                      loading: true,
                      onOk: () => {
                        delService(params.row.id).then(res => {
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
        list: [{id:1,serviceName:'aaa'}],
        RoleList: []
      }
    },
    methods: {
      add() {
        this.service={
          id: '',
          serviceName: '',
          property1:'',
          property2:'',
          property3:'',
          property4:'',
          serviceDescrible:'',
        }
        this.userModal.title = '新增服务'
        this.userModal.isShow = true
        this.userModal.disabled = false
      },
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getServiceList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      },
      showServiceInfo(type,params){
        this.$refs['user'].resetFields()
        this.service.id = params.row.id
        this.service.serviceName = params.row.serviceName
        this.service.property1 = params.row.property1
        this.service.property2 = params.row.property2
        this.service.property3 = params.row.property3
        this.service.property4 = params.row.property4
        this.service.serviceDescrible = params.row.serviceDescrible
        this.userModal.isShow = true
        this.userModal.title = type
        this.userModal.disabled = type==='服务详情'
      },
      doService() {
        this.$refs['user'].validate((valid) => {
          if (valid) {
            console.log(valid)
            this.userModal.isLoading = true
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
          this.queryCondition.deviceId = this.$route.query.deviceId ? Number(this.$route.query.deviceId) : 0
    }
  }
</script>

<style>

</style>
