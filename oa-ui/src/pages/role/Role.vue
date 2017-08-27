<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="2" :offset="2" style="margin-top:0.2%">
        名称：
        </Col>
        <Col :span="6">
        <Input type="text" v-model="queryCondition.roleName" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" offset="2">
        <Button type="primary" shape="circle" icon="ios-search" @click="getlist">查询</Button>
        </Col>
        <Col :span="2" offset="4">
        <Button type="success" shape="circle" icon="ios-personadd" @click="Role={};RoleModal.isShow=true;RoleModal.title='新建角色'">新建角色</Button>
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
    <Modal v-model="RoleModal.isShow"
           :title="RoleModal.title"
    >
      <p>--------------</p>
      <div slot="footer">
        <Button type="ghost" @click="RoleModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="RoleModal.isShow=false" :loading="RoleModal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    getRoleList,
    delRole
  } from '../../api/role/role'

  export default {
    data() {
      return {
        selection: '',
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
            title: '名称',
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
                    //todo 得到角色
                    this.RoleModal.isShow = true
                    this.RoleModal.title='修改角色'
                  }
                }
              }, '修改'),
              h('Button', {
                props: {
                  type: 'primary'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.$router.push({path: 'userRole?id=' + params.row.id})
                  }
                }
              }, '查看用户'),
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
                      content: '<p>' + params.row.roleName + '</p>',
                      loading: true,
                      onOk: () => {
                        delRole(params.row.id).then(res=>{

                          this.$Message.info(res.data.message);
                          this.$Modal.remove()
                          this.getlist()
                        })

                      }
                    });
                  }
                }
              }, '删除')
            ])
          }
        ],
        list: [],
        RoleModal: {
          title: '',
          isShow: false,
          isLoading: false
        },
        Role: {}
      }
    },
    methods: {
      getSelection(selection) {
        this.selection = selection
      },
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getRoleList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      },
    },
    created() {
      this.getlist()
    }
  }
</script>

<style>

</style>
