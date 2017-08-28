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
      <Form :model="Role" :label-width="80">
        <Form-item label="角色名：">
          <Input v-model="Role.roleName" placeholder="请输入"></Input>
        </Form-item>
        <Collapse v-model="value1">
          <Panel name="1">
            史蒂夫·乔布斯
            <p slot="content">史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。</p>
          </Panel>
          <Panel name="2">
            斯蒂夫·盖瑞·沃兹尼亚克
            <p slot="content">
              斯蒂夫·盖瑞·沃兹尼亚克（Stephen Gary Wozniak），美国电脑工程师，曾与史蒂夫·乔布斯合伙创立苹果电脑（今之苹果公司）。斯蒂夫·盖瑞·沃兹尼亚克曾就读于美国科罗拉多大学，后转学入美国著名高等学府加州大学伯克利分校（UC Berkeley）并获得电机工程及计算机（EECS）本科学位（1987年）。</p>
          </Panel>
          <Panel name="3">
            乔纳森·伊夫
            <p slot="content">
              乔纳森·伊夫是一位工业设计师，现任Apple公司设计师兼资深副总裁，英国爵士。他曾参与设计了iPod，iMac，iPhone，iPad等众多苹果产品。除了乔布斯，他是对苹果那些著名的产品最有影响力的人。</p>
          </Panel>
        </Collapse>
      </Form>
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
          roleName:''
        }
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
