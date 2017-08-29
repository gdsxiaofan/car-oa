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
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="Role={};RoleModal.isShow=true;RoleModal.title='新建角色'">新建角色
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
    <Modal v-model="RoleModal.isShow"
           :title="RoleModal.title"
    >
      <Form :model="Role" :label-width="80" ref="role">
        <Form-item label="角色名：">
          <Input v-model="Role.roleName" placeholder="请输入"></Input>
        </Form-item>
        <Form-item label="权限菜单">
          <Checkbox-group v-model="checkMenu">
          <Collapse>
            <Panel :name="item.name" v-for="item in menuMainGroup" :key="item.id">
              {{item.name}}
              <p slot="content">
                <Checkbox :label="ckbox.id" :key="ckbox.id" v-for="ckbox in allMenu" v-if="ckbox.parentId===item.id">
                  <Icon :type="ckbox.icon"></Icon>
                  <span>{{ckbox.name}}</span>
                </Checkbox>
              </p>
            </Panel>
          </Collapse>
          </Checkbox-group>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="RoleModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doRole" :loading="RoleModal.isLoading">提交</Button>
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
                    this.RoleModal.title = '修改角色'
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
                        delRole(params.row.id).then(res => {

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
        Role: {
          roleName: ''
        },
        allMenu: [{"id": 106, "name": "权限管理", "href": "", "icon": "", "orderTop": 1, "parentId": 0},
          {"id": 107, "name": "工单管理", "href": "", "icon": "", "orderTop": 1, "parentId": 0},
          {"id": 108, "name": "员工管理", "href": "", "icon": "", "orderTop": 1, "parentId": 0},
          {"id": 109, "name": "设备管理", "href": "", "icon": "", "orderTop": 1, "parentId": 0},
          {"id": 110, "name": "角色管理", "href": "/role", "icon": "", "orderTop": 2, "parentId": 106},
          {"id": 112, "name": "用户角色管理", "href": "/userRole", "icon": "", "orderTop": 2, "parentId": 106},
          {"id": 113, "name": "我的工单", "href": "/myorderTop", "icon": "", "orderTop": 2, "parentId": 107},
          {"id": 114, "name": "发布工单", "href": "/publishorderTop", "icon": "", "orderTop": 2, "parentId": 107},
          {"id": 115, "name": "派发工单", "href": "/dispatchorderTop", "icon": "", "orderTop": 2, "parentId": 107},
          {"id": 116, "name": "历史工单", "href": "/historyorderTop", "icon": "", "orderTop": 2, "parentId": 107},
          {"id": 117, "name": "所有工单", "href": "/allorderTop", "icon": "", "orderTop": 2, "parentId": 107},
          {"id": 118, "name": "员工信息管理", "href": "/employeeInfo", "icon": "", "orderTop": 2, "parentId": 108},
          {"id": 119, "name": "新增员工", "href": "/employeeAdd", "icon": "", "orderTop": 2, "parentId": 108},
          {"id": 120, "name": "设备基础信息管理", "href": "/deviceInfo", "icon": "", "orderTop": 2, "parentId": 109},
          {"id": 121, "name": "新增设备信息", "href": "/deviceAdd", "icon": "", "orderTop": 2, "parentId": 109}
          ],
        checkMenu:[]
      }
    },
    computed:{
      menuMainGroup() {
        return this.allMenu.filter(item => {
          return item.parentId === 0 && item.orderTop===1
        })
      },
      menuMain() {
        return this.allMenu.filter(item => {
          return item.parentId === 0 && item.orderTop===2
        })
      },
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
      doRole(){
        let checkMenu=this.allMenu.filter(x=>{
          //得到所有被选择的菜单
              return   this.checkMenu.filter(y=>{
                  return  x.id===y
                }).length>0
        }).map(x=>{
          return x.parentId
        })
        this.checkMenu=this.checkMenu.concat(checkMenu)
        console.log([...new Set(this.checkMenu)])


//        this.RoleModal.isShow=false
      }
    },
    created() {
      this.getlist()
    }
  }
</script>

<style>

</style>
