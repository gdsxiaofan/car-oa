<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="2" style="margin-top:0.2%">
        角色：
        </Col>
        <Col :span="3">
        <Select v-model="queryCondition.roleId" filterable not-found-text>
          <Option :value="0" :key="0">全部</Option>
          <Option v-for="item in RoleList" :value="item.id" :key="item.id">{{ item.roleName }}</Option>
        </Select>
        </Col>
        <Col :span="2" :offset="1" style="margin-top:0.2%">
        工号：
        </Col>
        <Col :span="2">
        <Input type="text" v-model="queryCondition.employeeNo" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" :offset="1" style="margin-top:0.2%">
        姓名：
        </Col>
        <Col :span="2">
        <Input type="text" v-model="queryCondition.userName" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" offset="1">
        <Button type="primary" shape="circle" icon="ios-search" @click="getlist">查询</Button>
        </Col>
      </Row>
    </Card>
    <Table border :columns="columns" @on-selection-change="getSelection" :data="list"></Table>
    <div style="margin: 10px;overflow: hidden">
      <div style="float: right;">
        <Page :total="queryCondition.total" :current.sync="queryCondition.pageNum" @on-change="getlist" show-total
              show-elevator></Page>
      </div>
    </div>

  </div>
</template>

<script>
  import {getUserList} from '../../api/role/showUser'
  import {getRoleList} from '../../api/role/role'

  export default {
    data() {
      return {
        userInfo: {id: '', roleId: ''},
        selection: '',
        queryCondition: {
          pageSize: 10,
          pageNum: 1,
          roleId: null,
          userName: '',
          employeeNo: '',
          total: 0
        },
        columns: [
          {
            title: '编号',
            key: 'id'
          },
          {
            title: '工号',
            key: 'employeeNo'
          },
          {
            title: '姓名',
            key: 'employeeName'
          },
          {
            title: '手机号码',
            key: 'employeeMobile'
          },
          {
            title: '角色',
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
                    this.userInfo.id = params.row.id
                    this.$Modal.confirm({
                      title: '修改角色',
                      content: '<Select v-model="userInfo.roleId" style="width:200px">' +
                      '      <Option v-for="item in RoleList" :value="item.id" :key="item.id">{{ item.roleName }}</Option>' +
                      '    </Select>'
                    });
                    this.$nextTick(()=>{
                      this.userInfo.roleId=params.row.roleId
                    })
                  }
                }
              }, '更换角色')
            ])
          }
        ],
        list: [],
        RoleModal: {
          title: '',
          isShow: false,
          isLoading: true
        },
        RoleList: []
      }
    },
    methods: {
      getSelection(selection) {
        this.selection = selection
      },
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getUserList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      },
    },
    created() {
//获取rolelist
      getRoleList({pageSize: 0, pageNum: 1}).then(res => {
        this.RoleList = res.data.data.list
        this.$nextTick(() => {
          this.queryCondition.roleId = this.$route.query.id ? Number(this.$route.query.id) : 0
        })
        this.getlist()
      })
    }
  }
</script>

<style>

</style>
