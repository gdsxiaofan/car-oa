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
        <Col :span="2" offset="1">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="add">
          新建用户
        </Button>
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
    <Modal v-model="userModal.isShow"
           :title="userModal.title"
    >
      <Form ref="user" :model="userInfo" :rules="userRules" :label-width="80">
        <Form-item label="工号：" prop="employeeNo">
          <Input type="text" v-model="userInfo.employeeNo" placeholder="工号">
          <Icon type="ios-person-outline" slot="prepend"></Icon>
          </Input>
        </Form-item>
        <FormItem label="密码：" :prop="userModal.title==='修改用户'?'':'employeePassword'">
          <Input type="password" v-model="userInfo.employeePassword"
                 :placeholder="userModal.title==='修改用户'?'不填则不修改':'密码'">
          <Icon type="ios-locked-outline" slot="prepend"></Icon>
          </Input>
        </FormItem>
        <Form-item label="姓名：" prop="employeeName">
          <Input type="text" v-model="userInfo.employeeName" placeholder=""/>
        </Form-item>
        <Form-item label="排班：" >
          <Select v-model="userInfo.shiftsNo" style="width:200px">
            <Option :value="0">无排班</Option>
            <Option :value="1">早班</Option>
            <Option :value="2">晚班</Option>
          </Select>
        </Form-item>
        <Form-item label="手机号码：" prop="employeeMobile">
          <Input type="text" v-model="userInfo.employeeMobile" placeholder=""/>
        </Form-item>
        <Form-item label="角色" prop="roleId">
          <Select v-model="userInfo.roleId" style="width:200px">
            <Option v-for="item in RoleList" :value="item.id" :key="item.id">{{ item.roleName }}</Option>
          </Select>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doUser" :loading="userModal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    getUserList,
    updateUser,
    delUser,
    addUser,
    isActiveUser
  } from '../../api/role/showUser'
  import {getRoleList} from '../../api/role/role'

  export default {
    data() {
      return {
        userInfo: {
          id: '',
          roleId: '',
          roleName: '',
          employeeMobile: '',
          employeeNo: '',
          shiftsNo:0,
          employeeName: '',
          employeePassword: ''
        },
        userRules: {
          employeeNo: [
            {required: true, message: '请填写工号', trigger: 'blur'}
          ],
          employeeName: [
            {required: true, message: '请填写姓名', trigger: 'blur'}
          ],
          employeeMobile: [
            {required: true, message: '请填写手机号', trigger: 'blur'}
          ],
          employeePassword: [
            {required: true, message: '请填写密码', trigger: 'blur'}
          ],
          roleId: [
            {type: 'number', required: true, message: '请选择角色', trigger: 'change'},
          ]
        },
        userModal: {
          isShow: false,
          isLoading: false,
          title: ''
        },
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
            title: '排班',
            key: 'shiftsNoName'
          },
          {
            title: '操作',
            width: 300,
            render: (h, params) => h('div', [
              h('Button', {
                props: {
                  type: 'ghost'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.$Modal.confirm({
                      title: params.row.isActive ? '是否停用' : '是否启用',
                      content: '<p>' + params.row.employeeName + '</p>',
                      loading: true,
                      onOk: () => {
                        isActiveUser(params.row.id, !params.row.isActive).then(res => {
                          this.$Message.success(res.data.message);
                          this.$Modal.remove()
                          this.getlist()
                        })

                      }
                    });
                  }
                }
              }, params.row.isActive ? '停用' : '启用'),
              h('Button', {
                  props: {
                    type: 'info'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.$refs['user'].resetFields()
                      this.userInfo.id = params.row.id
                      this.userInfo.roleName = params.row.roleName
                      this.userInfo.employeeName = params.row.employeeName
                      this.userInfo.employeeNo = params.row.employeeNo
                      this.userInfo.employeeMobile = params.row.employeeMobile
                      this.userInfo.shiftsNo = params.row.shiftsNo
                      this.userInfo.roleId = params.row.roleId
                      this.userModal.isShow = true
                      this.userModal.title = '修改用户'
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
                      content: '<p>' + params.row.employeeName + '</p>',
                      loading: true,
                      onOk: () => {
                        delUser(params.row.id).then(res => {
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
        this.$refs['user'].resetFields()
        this.userModal.title = '新增用户'
        this.userModal.isShow = true

      },
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
      doUser() {
        this.$refs['user'].validate((valid) => {
          if (valid) {
            this.userModal.isLoading = true
            if (this.userModal.title === '修改用户') {
              updateUser(this.userInfo).then(res => {
                this.userModal.isLoading = false
                this.userModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            } else if (this.userModal.title === '新增用户') {
              addUser(this.userInfo).then(res => {
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
