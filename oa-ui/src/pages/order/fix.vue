<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="4">
        工单号：
        </Col>
        <Col :span="7">
        <Input type="text" v-model="queryCondition.orderNo" placeholder="请输入..."></Input>
        </Col>
        <Col :span="4" :offset="2">
        工单状态：
        </Col>
        <Col :span="7">
        <Select v-model="queryCondition.orderStatus">
          <Option value="all">全部</Option>
          <Option value="doing">未完成</Option>
          <Option value="done">已完成</Option>
        </Select>
        </Col>
      </Row>
      <Row style="margin-top:2%">
        <Col :span="3" offset="18" style="margin-top:2%">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="getlist">
          查询
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
      <Row>
        <Col span="16">
        <Row>
          <Col span="10">
          <strong class="label">label="工单号："</strong>
          </Col>
          <Col span="14">
          <p class="label">label="工单号："</p>
          </Col>
          <Col span="10">
          <strong class="label">label="工单号："</strong>
          </Col>
          <Col span="14">
          <p class="label">label="工单号："</p>
          </Col>
          <Col span="10">
          <strong class="label">label="工单号："</strong>
          </Col>
          <Col span="14">
          <p class="label">label="工单号："</p>
          </Col>
          <Col span="10">
          <strong class="label">label="工单号："</strong>
          </Col>
          <Col span="14">
          <p class="label">label="工单号："</p>
          </Col>
          <Col span="10">
          <strong class="label">label="工单号："</strong>
          </Col>
          <Col span="14">
          <p class="label">label="工单号："</p>
          </Col>
          <Col span="10">
          <strong class="label">label="工单号："</strong>
          </Col>
          <Col span="14">
          label="描述："
          </Col>
        </Row>
        </Col >
        <Col span="8">
        <Timeline>
          <TimelineItem>
            <p class="time">事件：1976年</p>
            <p class="content">Apple I 问世</p>
          </TimelineItem>
          <TimelineItem>
            <p class="time">事件：1976年</p>
            <p class="content">Apple I 问世</p>
          </TimelineItem>
          <TimelineItem>
            <p class="time">事件：1976年</p>
            <p class="content">Apple I 问世</p>
          </TimelineItem>
        </Timeline>
        </Col>
      </Row>
      <!--<div slot="footer">-->
      <!--<Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>-->
      <!--<Button type="primary" @click="doService" :loading="userModal.isLoading">提交</Button>-->
      <!--</div>-->
    </Modal>
    <Modal v-model="doModal.isShow"
           title="验证身份"
    >
      <Form ref="do" :model="employee" :rules="userRule" :label-width="80">
        <FormItem prop="employeeNo" label="工号">
          <Input type="text" v-model="employee.employeeNo" placeholder="Username">
          </Input>
        </FormItem>
        <FormItem prop="employeePassword" label="密码">
          <Input type="password" v-model="employee.employeePassword" placeholder="Password">
          </Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="doModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doOrder" :loading="doModal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    getOrderList,
    getOrderInfo,
    doOrder
  } from '../../api/order'

  export default {
    data() {
      return {
        service: {
          id: '',
          serviceName: '',

        },
        userModal: {
          isShow: false,
          isLoading: false,
        },
        employee: {
          orderId: '',
          employeeNo: "",
          employeePassword: ''
        },
        userRule: {
          employeeNo: [
            {required: true, message: '请填写工号', trigger: 'blur'}
          ],
          employeePassword: [
            {required: true, message: '请填写密码', trigger: 'blur'},
          ]
        },
        doModal: {
          isShow: false,
          isLoading: false,
        },
        queryCondition: {
          orderType: "fix",
          orderStatus: "all",
          pageSize: 10,
          pageNum: 1,
          orderNo: '',
          total: 0
        },
        columns: [
          {
            title: '工单号',
            key: 'id'
          },
          {
            title: '维修服务',
            key: 'serviceName'
          },
          {
            title: '设备名称',
            key: "serviceName"
          },
          {
            title: '提交时间',
            key: 'serviceName'
          },
          {
            title: '状态',
            key: 'serviceName'
          },
          {
            title: '操作',
            width: 240,
            render: (h, params) => {
              let button = [h('Button', {
                  props: {
                    type: 'info'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
//                      this.$refs['user'].resetFields()
                      getOrderInfo(params.row.id).then(res => {
                        this.employee.orderId = params.row.id
                        this.userModal.isShow = true

                      })

                    }
                  }
                },
                '详情'
              )]
              if (true)
                button.push(h('Button', {
                    props: {
                      type: 'info'
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                        this.employee = {
                          orderId: '',
                          employeeNo: "",
                          employeePassword: ''
                        }
                        this.doModal.isShow = true
                      }
                    }
                  },
                  '开始处理'
                ))
              return h('div', button)
            }
          }
        ],
        list: [{id: 1, serviceName: 'aaa'}],
        RoleList: []
      };
    },
    methods: {
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getOrderList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      },
      doOrder() {

        this.$refs['do'].validate((valid) => {
          if (valid) {
            this.doModal.isLoading = true
            let order = {
              id: this.employee.orderId,
              orderStatus: 1,
              employee: this.employee
            }
            doOrder(order).then(res => {
              if (res.data.code === 1) {
                this.doModal.isShow = false
                this.doModal.isLoading = false
                this.$Message.success(res.data.message)
              } else {
                this.doModal.isLoading = false
                this.$Message.error(res.data.message)
              }
            }).catch(e => {
              this.doModal.isLoading = false
//          this.$Message.error(e);
            })
          }
        })
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
