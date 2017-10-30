<template>
  <div>
    <Card>
      <p slot="title">操作</p>
      <Row>
        <Col :span="3" :offset="18" style="margin-top:0.2%">
        <Button type="primary" shape="circle" icon="ios-personadd" @click="plan={
          id: '',
          arrangeName: '',
          day: ['06:00:00', '11:00:00'],
          night: ['13:00:00', '17:00:00']
        };Modal.isShow=true;Modal.title='新增排班';">新增
        </Button>
        </Col>
      </Row>
    </Card>
    <Table border :columns="columns" :data="list">
    </Table>
    <Modal v-model="Modal.isShow"
           :title="Modal.title"
    >
      <Form :model="plan" :label-width="80" :rules="rule" label-position="right" ref="plan">
        <Form-item prop="arrangeName" label="名字：">
          <Input v-model="plan.arrangeName"/>
        </Form-item>
        <Form-item label="早班：">
          <TimePicker :value="plan.day" type="timerange" placement="bottom-end" placeholder="选择时间"
                      @on-change="plan.day=arguments[0]" :clearable="false"
                      style="width: 168px"></TimePicker>
        </Form-item>
        <Form-item label="晚班:">
          <TimePicker :value="plan.night" type="timerange" placement="bottom-end" placeholder="选择时间"
                      @on-change="plan.night=arguments[0]" :clearable="false"
                      style="width: 168px"></TimePicker>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="Modal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doPlan" :loading="Modal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    getPlan,
    updatePlan,
    addPlan,
    deletePlan
  } from '../../api/role/role'
  import _ from 'lodash'

  export default {
    data() {
      return {
        columns: [
          {
            title: '名称',
            key: 'arrangeName'
          },
          {
            title: '早班',
            render: (h, params) => {
              return params.row.morningStart + '-' + params.row.morningEnd
            }
          },
          {
            title: '晚班',
            render: (h, params) => {
              return params.row.noonStart + '-' + params.row.noonEnd
            }
          },
          {
            title: '状态',
            key: 'isEffectiveName'
          },
          {
            title: '操作',
            width: 300,
            render: (h, params) => {
              let button = [
                h('Button', {
                  props: {
                    type: 'info'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.Modal.isShow = true
                      this.Modal.title = '修改排班'
                      this.plan.id = params.row.id
                      this.plan.arrangeName = params.row.arrangeName
                      this.plan.day = [params.row.morningStart, params.row.morningEnd]
                      this.plan.night = [params.row.noonStart, params.row.noonEnd]
                    }
                  }
                }, '修改')
              ]
              if (params.row.isEffective === 2) {
                button.push(h('Button', {
                  props: {
                    type: params.row.isEffective === 2 ? 'primary' : 'warning'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.$Modal.confirm({
                        title: '是否启用',
                        content: '<p>将启用' + params.row.arrangeName + '，同时将其他停用</p>',
                        loading: true,
                        onOk: () => {
                          let plan = {
                            id: params.row.id,
                            isEffective: 1
                          }
                          updatePlan(plan).then(res => {
                            this.$Message.success(res.data.message);
                            this.$Modal.remove()
                            this.getList()
                          })

                        }
                      });
                    }
                  }
                }, '启用'))
                button.push(h('Button', {
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
                        content: '<p>' + params.row.arrangeName + '</p>',
                        loading: true,
                        onOk: () => {
                          deletePlan(params.row.id).then(res => {
                            this.$Message.success(res.data.message);
                            this.$Modal.remove()
                            this.getList()
                          })
                        }
                      });
                    }
                  }
                }, '删除'))
              }
              return h('div', button)
            }
          }
        ],
        list: [],
        plan: {
          id: '',
          arrangeName: '',
          day: ['06:00:00', '11:00:00'],
          night: ['13:41:00', '17:00:00']
        },
        rule: {
          arrangeName: [
            {required: true, message: '不可为空', trigger: 'blur'}
          ],
        },
        Modal: {
          title: '',
          isShow: false,
          isLoading: false,
          disabled: false
        },
      }
    },
    methods: {
      test(e) {
        console.log(e)
      },
      doPlan() {
        this.$refs['plan'].validate((valid) => {
          if (valid) {
            this.Modal.isLoading = true
            let plan = _.cloneDeep(this.plan)
            plan.morningStart = plan.day[0]
            plan.morningEnd = plan.day[1]
            plan.noonStart = plan.night[0]
            plan.noonEnd = plan.night[1]
            if (this.Modal.title === '新增排班') {
              addPlan(plan).then(res => {
                this.$Message.success(res.data.message)
                this.Modal.isLoading = false
                this.Modal.isShow = false
                this.getList()
              })
            } else if (this.Modal.title === '修改排班') {
              updatePlan(plan).then(res => {
                this.$Message.success(res.data.message)
                this.Modal.isLoading = false
                this.Modal.isShow = false
                this.getList()
              })
            }
          }
        })
      },
      getList() {
        getPlan().then(res => {
          this.list = res.data.data
        })
      }
    },
    created() {
      this.getList()
    }
  }
</script>

<style>

</style>
