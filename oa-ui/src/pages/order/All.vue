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
        <Col :span="4">
        工单类别：
        </Col>
        <Col :span="7">
        <Select v-model="queryCondition.orderType">
          <Option value="all">全部</Option>
          <Option value="look">巡检</Option>
          <Option value="fix">维修</Option>
        </Select>
        </Col>
        <Col :span="4" :offset="2">
        处理人：
        </Col>
        <Col :span="7">
        <Input type="text" v-model="queryCondition.doPerson" placeholder="请输入..."/>
        </Col>
        <Col :span="3" offset="18" style="margin-top:2%">
        <Button type="primary" shape="circle" icon="ios-search"
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
        <Col  span="16">
      <Row>
        <Col  span="10">
        <strong class="label">label="工单号："</strong>
        </Col>
        <Col span="14">
        <p class="label">label="工单号："</p>
        </Col>
        <Col  span="10">
        <strong class="label">label="工单号："</strong>
        </Col>
        <Col span="14">
        <p class="label">label="工单号："</p>
        </Col>
        <Col  span="10">
        <strong class="label">label="工单号："</strong>
        </Col>
        <Col span="14">
        <p class="label">label="工单号："</p>
        </Col>
        <Col  span="10">
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
        <Col  span="10">
        <strong class="label">label="工单号："</strong>
        </Col>
        <Col span="14">
        label="描述："
        </Col>
      </Row>
        </Col >
        <Col  span="8">
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
        userModal: {
          isShow: false,
          isLoading: false,
          title: ''
        },
        queryCondition: {
          pageSize: 10,
          pageNum: 1,
          orderStatus:'all',
          orderType:'all',
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
            key:"serviceName"

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
                      getOrderInfo(params.row.id).then(res=>{
                        this.service.id = params.row.id
                        this.service.serviceName = params.row.serviceName
                        this.userModal.isShow = true
                      })
                    }
                  }
                },
                '详情'
              )]
            )
          }
        ],
        list: [{id: 1, serviceName: 'aaa'}],
        RoleList: []
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
  .label{
    font-size: 14px;
  }
  .time{
    font-size: 14px;
    font-weight: bold;
  }
  .content{
    padding-left: 5px;
  }
</style>
