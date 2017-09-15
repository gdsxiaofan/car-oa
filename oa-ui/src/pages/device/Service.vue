<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="2" style="margin-top:0.2%">
        服务名称：
        </Col>
        <Col :span="3">
        <Input type="text" v-model="queryCondition.name" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" offset="1">
        <Button type="primary" shape="circle" icon="ios-search" @click="getlist">查询</Button>
        </Col>
        <Col :span="2" offset="1">
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
      <Form ref="user" :model="userInfo" :rules="userRules" :label-width="80">
        <Form-item label="工号：" prop="employeeNo">
          <Input type="text" v-model="userInfo.employeeNo" placeholder="工号">
          <Icon type="ios-person-outline" slot="prepend"></Icon>
          </Input>
        </Form-item>
        <FormItem label="密码：" :prop="userModal.title==='修改用户'?'':'employeePassword'">
          <Input type="password" v-model="userInfo.employeePassword" :placeholder="userModal.title==='修改用户'?'不填则不修改':'密码'">
          <Icon type="ios-locked-outline" slot="prepend"></Icon>
          </Input>
        </FormItem>
        <Form-item label="姓名：" prop="employeeName">
          <Input type="text" v-model="userInfo.employeeName" placeholder=""/>
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
    addService,
    getServiceList,
    updateService
  } from '../../api/device/service'

  export default {
    data() {
      return {
        service: {
          id: '',
          serviceName: '',

        },
        userRules: {
          serviceName: [
            {required: true, message: '请填写工号', trigger: 'blur'}
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
          deviceId: null,
          serviceName:"",
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
            key: 'serviceName'
          },
          {
            title: '属性二',
            key: 'serviceName'
          },
          {
            title: '属性三',
            key: 'serviceName'
          },
          {
            title: '属性四',
            key: 'serviceName'
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
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getServiceList(this.queryCondition).then(res => {
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
          this.queryCondition.deviceId = this.$route.query.deviceId ? Number(this.$route.query.deviceId) : 0
          this.queryCondition.serviceName = this.$route.query.serviceName
    }
  }
</script>

<style>

</style>
