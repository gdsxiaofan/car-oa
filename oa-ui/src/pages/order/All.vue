<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="4" >
        工单号：
        </Col>
        <Col :span="7">
        <Input type="text" v-model="queryCondition.name" placeholder="请输入..."></Input>
        </Col>
        <Col :span="4" :offset="2" >
        工单状态：
        </Col>
        <Col :span="7">
        <Select>
          <i-option value="null">全部</i-option>
        </Select>
        </Col>
      </Row>
      <Row style="margin-top:2%">
        <Col :span="4" >
        工单类别：
        </Col>
        <Col :span="7">
        <Select>
          <i-option value="null">全部</i-option>
        </Select>
        </Col>
        <Col :span="4" :offset="2" >
        处理人：
        </Col>
        <Col :span="7">
        <Input type="text" v-model="queryCondition.name" placeholder="请输入..."></Input>
        </Col>
        <Col :span="3" offset="18" style="margin-top:2%">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="add">
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
    <Modal v-model="userModal.isShow"
           :title="userModal.title"
    >
      <Form ref="user" :model="service" :rules="serviceRules" :label-width="80">
        <Form-item label="服务名称：" prop="serviceName">
          <Input type="text" v-model="service.serviceName" placeholder="服务名称"/>
        </Form-item>
        <Form-item label="属性一：" prop="serviceName">
          <Input type="text" v-model="service.serviceName" placeholder="属性一"/>
        </Form-item>
        <Form-item label="属性二：" prop="serviceName">
          <Input type="text" v-model="service.serviceName" placeholder="属性二"/>
        </Form-item>
        <Form-item label="属性三：" prop="serviceName">
          <Input type="text" v-model="service.serviceName" placeholder="属性三"/>
        </Form-item>
        <Form-item label="属性四：" prop="serviceName">
          <Input type="text" v-model="service.serviceName" placeholder="属性四"/>
        </Form-item>
        <Form-item label="描述：" prop="serviceName">
          <Input type="text" v-model="service.serviceName" placeholder="描述"/>
        </Form-item>

      </Form>
      <div slot="footer">
        <Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doService" :loading="userModal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    addService,
    getServiceList,
    updateService,
    delService
  } from '../../api/device/service'
  import IOption from "../../../node_modules/iview/src/components/select/option.vue";

  export default {
    components: {IOption},
    data() {
      return {
        service: {
          id: '',
          serviceName: '',

        },
        serviceRules: {
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
          deviceId: 0,
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
            render: (h, params) => {
              return h('div', this.$route.query.serviceName)
            }
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
            width: 240,
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
                      this.$refs['user'].resetFields()
                      this.service.id = params.row.id
                      this.service.serviceName = params.row.serviceName
                      this.userModal.isShow = true
                      this.userModal.title = '修改服务'
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
                      content: '<p>' + params.row.serviceName + '</p>',
                      loading: true,
                      onOk: () => {
                        delService(params.row.id).then(res => {
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
        list: [{id: 1, serviceName: 'aaa'}],
        RoleList: []
      }
    },
    methods: {
      add() {
        this.$refs['user'].resetFields()
        this.userModal.title = '新增服务'
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
      doService() {
        this.$refs['user'].validate((valid) => {
          if (valid) {
            this.userModal.isLoading = true
            if (this.userModal.title === '修改服务') {
              updateService(this.userInfo).then(res => {
                this.userModal.isLoading = false
                this.userModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            } else if (this.userModal.title === '新增用户') {
              addService(this.userInfo).then(res => {
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
    }
  }
</script>

<style>

</style>
