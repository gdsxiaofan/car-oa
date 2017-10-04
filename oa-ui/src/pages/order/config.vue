<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="4">
        工单号：
        </Col>
        <Col :span="7">
        <Input type="text" v-model="queryCondition.orderNo" placeholder="请输入..."/>
        </Col>
        <Col :span="4" :offset="2">
        设备名称：
        </Col>
        <Col :span="7">
        <Input type="text" v-model="queryCondition.doPerson" placeholder="请输入..."/>
        </Col>
        <Col :span="3" offset="15" style="margin-top:2%">
        <Button type="primary" shape="circle" icon="ios-search"
                @click="getlist">
          查询
        </Button>
        </Col>
        <Col :span="3" offset="1" style="margin-top:2%">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="Role={id:'',roleName: '',checkMenu: []};RoleModal.isShow=true;RoleModal.title='新建角色';collapse=[]">
          新建角色
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
    <Modal v-model="userModal.isShow" width="800"
           title="工单详情"
    >
      <Form ref="user" :model="orderInfo" :rules="orderRule" :label-width="80">
        <Form-item label="工单号：" prop="serviceName">
          <Input type="text" v-model="orderInfo.serviceName" placeholder="服务名称"/>
        </Form-item>
        <Form-item label="工单名称：" prop="serviceName">
          <Input type="text" v-model="orderInfo.serviceName" placeholder="服务名称"/>
        </Form-item>
        <Form-item label="维修设备：" prop="serviceName">
          <Select v-model="orderInfo.service" multiple>
            <Option value="0">asdsad</Option>
            <Option value="1">asdsad</Option>
            <Option value="2">asdsad</Option>
            <Option value="3">asdsad</Option>
          </Select>
        </Form-item>
        <Form-item label="服务名称：" prop="serviceName">
          <Input type="text" v-model="orderInfo.serviceName" placeholder="服务名称"/>
        </Form-item>

      </Form>
    </Modal>
  </div>
</template>

<script>
  import {
    getOrderList,
    getOrderInfo
  } from '../../api/order'

  export default {
    data() {
      return {
        orderInfo: {
          service:[]
        },
        orderRule: {
          serviceName: {required: true, message: '请填写工号', trigger: 'blur'}
        },
        userModal: {
          isShow: false,
          isLoading: false,
          title: ''
        },
        queryCondition: {
          pageSize: 10,
          pageNum: 1,
          orderStatus: 'all',
          orderType: 'all',
          doPerson: '',
          orderNo: '',
          total: 0
        },
        columns: [
          {
            title: '工单号',
            key: 'id'
          },
          {
            title: '工单名称',
            key: 'serviceName'
          },
          {
            title: '维修设备',
            key: "serviceName"

          },
          {
            title: '负责人',
            key: 'serviceName'
          },
          {
            title: '工单类型',
            key: 'serviceName'
          },
          {
            title: '状态',
            key: 'serviceName'
          },
          {
            title: '操作',
            width: 240,
            render: (h, params) => h('div', [
              h('Button', {
                  props: {
                    type: 'primary'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      getOrderInfo(params.row.id).then(res => {
                        this.orderInfo.id = params.row.id
                        this.orderInfo.serviceName = params.row.serviceName
                        this.userModal.isShow = true
                      })
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
                      getOrderInfo(params.row.id).then(res => {
                        this.orderInfo.id = params.row.id
                        this.orderInfo.serviceName = params.row.serviceName
                        this.userModal.isShow = true
                      })
                    }
                  }
                },
                '编辑'
              )]
            )
          }
        ],
        list: [{id: 1, serviceName: 'aaa'}],
      }
    },
    methods: {
      add() {
//        this.$refs['user'].resetFields()
        this.userModal.title = '新增服务'
        this.userModal.isShow = true

      },
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getOrderList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      }
    },
    created() {
      this.queryCondition.deviceId = this.$route.query.deviceId ? Number(this.$route.query.deviceId) : 0
    }
  }
</script>

<style scoped>
  .label {
    font-size: 14px;
  }

  .time {
    font-size: 14px;
    font-weight: bold;
  }

  .content {
    padding-left: 5px;
  }
</style>
