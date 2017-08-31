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
                @click="Role={id:'',roleName: '',checkMenu: []};RoleModal.isShow=true;RoleModal.title='新建角色';collapse=[]">
          新建角色
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
          <Checkbox-group v-model="Role.checkMenu">
            <Collapse v-model="collapse">
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
    getAllMenu,
    getRoleList,
    getRoleById,
    delRole,
    addRole,
    updateRole,
  } from '../../api/role/role'

  export default {
    data() {
      return {
        collapse:[],
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
                    this.Role.id=params.row.id
                    this.Role.roleName=params.row.roleName
                    //todo 得到角色
                    getRoleById(params.row.id).then(res=>{
                      this.Role.checkMenu = res.data.data.checkMenu
                      this.RoleModal.isShow = true
                      this.RoleModal.title = '修改角色'
                      this.collapse=[]
                    })

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
                          this.$Message.success(res.data.message);
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
          id: '',
          roleName: '',
          checkMenu: []
        },
        allMenu: [
        ],

      }
    },
    computed: {
      menuMainGroup() {
        return this.allMenu.filter(item => {
          return item.parentId === 0 && item.orderTop === 1
        })
      },
      menuMain() {
        return this.allMenu.filter(item => {
          return item.parentId === 0 && item.orderTop === 2
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
      doRole() {
        this.RoleModal.isLoading=true
        let indexOf = function (ary, val) {
          for (let i = 0; i < ary.length; i++) {
            if (ary[i] === val) return i;
          }
          return -1;
        }
        let checkMenu = this.allMenu.filter(x => {
          //得到所有被选择的菜单
          return this.Role.checkMenu.filter(y => {
            return x.id === y
          }).length > 0
        }).map(x => {
          return x.parentId
        })

        this.Role.checkMenu = Array.from(new Set(this.Role.checkMenu.concat(checkMenu)))
        console.log(this.Role.checkMenu)
        //删除id为0的menu
        let index = indexOf(this.Role.checkMenu, 0)
        this.Role.checkMenu.splice(index, index === -1 ? 0 : 1)
        if (this.RoleModal.title === '新建角色') {
          addRole(this.Role).then(res => {
            this.$Message.success(res.data.message)
            this.RoleModal.isLoading=false
            this.RoleModal.isShow=false
            this.getlist()
          })
        } else if (this.RoleModal.title === '修改角色') {
          updateRole(this.Role).then(res => {
            this.$Message.success(res.data.message)
            this.RoleModal.isLoading=false
            this.RoleModal.isShow=false
            this.getlist()
          })
        }
//        this.RoleModal.isShow=false
      }
    },
    created() {
      this.getlist()
      getAllMenu().then(res=>{
        this.allMenu=res.data.data
      })
    }
  }
</script>

<style>

</style>
