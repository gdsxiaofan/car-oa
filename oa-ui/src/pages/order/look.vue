<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="4">
        工单号：
        </Col>
        <Col :span="7">
        <Input type="text" v-model="queryCondition.orderNo" placeholder="请输入..."></Input>
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
        <Col :span="3" offset="18" style="margin-top:2%">
        <Button type="success" shape="circle" icon="ios-personadd"
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
        <Col span="16">
        <Row>
          <Col span="10">
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
          <Col span="10">
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
          <Col span="10">
          <strong class="label">label="工单号："</strong>
          </Col>
          <Col span="14">
          <p class="label">label="工单号："</p>
          </Col>
          <Col span="10">
          <strong class="label">label="工单号："</strong>
          </Col>
          <Col span="14">
          label="描述："
          </Col>
        </Row>
        </Col >
        <Col span="8">
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
      <!--<div slot="footer">-->
      <!--<Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>-->
      <!--<Button type="primary" @click="doService" :loading="userModal.isLoading">提交</Button>-->
      <!--</div>-->
    </Modal>
    <Modal v-model="checkModal.isShow"
           title="验证身份"
    >
      <Form ref="checkUser" :model="employee" :rules="userRule" :label-width="80">
        <FormItem prop="employeeNo" label="工号">
          <Input type="text" v-model="employee.employeeNo" placeholder="Username">
          </Input>
        </FormItem>
        <FormItem prop="employeePassword" label="密码">
          <Input type="password" v-model="employee.employeePassword" placeholder="Password">
          </Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="doModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="checkUser" :loading="doModal.isLoading">提交</Button>
      </div>
    </Modal>
    <Modal v-model="doModal.isShow" width="800"
           title="工单详情"
    >
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">工单号</strong>
        </Col>
        <Col span="14">
        <p class="label">sadad</p>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">工单名称</strong>
        </Col>
        <Col span="14">
        <p class="label">label="工单号："</p>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">设备名称</strong>
        </Col>
        <Col span="14">
        <p class="label">asdad</p>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">排班</strong>
        </Col>
        <Col span="14">
        <p class="label">label="工单号："</p>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">描述</strong>
        </Col>
        <Col span="14">
        <Input v-model="value6" type="textarea" disabled placeholder="请输入..." autosize/>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">处理方式</strong>
        </Col>
        <Col span="14">
        <Select v-model="value5" placeholder="请输入..." autosize>
          <Option value="verify" selected>巡检完成</Option>
          <Option value="fix">报修</Option>
        </Select>
        </Col>
      </Row>

      <Row class="ModalRow" v-show="value5==='verify'">
        <Col span="10">
        <strong class="label">巡检批注</strong>
        </Col>
        <Col span="14">
        <Input v-model="value6" type="textarea" placeholder="请输入..." autosize/>
        </Col>
      </Row>
      <div v-show="value5!=='verify'">
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">报修图片</strong>
          </Col>
          <Col span="14">
          <div class="demo-upload-list" v-for="item in uploadList">
            <template v-if="item.status === 'finished'">
              <img :src=" up.url+'/v1/image/sosOutImg' + item.url">
              <div class="demo-upload-list-cover">
                <Icon type="ios-eye-outline" @click.native="handleView(item.url)"></Icon>
                <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
              </div>
            </template>
            <template v-else>
              <Progress v-if="item.showProgress" :percent="item.percentage" hide-info></Progress>
            </template>
          </div>
          <Upload
            ref="uploads"
            :action="up.url+'/v1/image/upPic'"
            :headers="up.header"
            :show-upload-list="false"
            :default-file-list="defaultList"
            :on-success="handleSuccess"
            :format="['jpg','jpeg','png']"
            :max-size="2048"
            :on-format-error="handleFormatError"
            :on-exceeded-size="handleMaxSize"
            :before-upload="handleBeforeUpload"
            :on-error="handleError"
            multiple
            type="drag"

            style="display: inline-block;width:58px;">
            <div style="width: 58px;height:58px;line-height: 58px;">
              <Icon type="camera" size="20"></Icon>
            </div>
          </Upload>
          <Modal title="查看图片" v-model="visible">
            <img :src="up.url+'/v1/image/sosOutImg' + imgUrl" v-if="visible" style="width: 100%">
          </Modal>
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">报修描述</strong>
          </Col>
          <Col span="14">
          <Input v-model="value6" type="textarea" placeholder="请输入..." autosize/>
          </Col>
        </Row>
      </div>
      <!--<div slot="footer">-->
      <!--<Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>-->
      <!--<Button type="primary" @click="doService" :loading="userModal.isLoading">提交</Button>-->
      <!--</div>-->
    </Modal>
  </div>
</template>

<script>
  import {
    getOrderList,
    getOrderInfo,
    checkUser,
    doOrder
  } from '../../api/order'

  export default {
    data() {
      return {
        up: {
          url: process.env.NODE_ENV === 'production' ? '' : 'car' ,
          header: {'Authorization': sessionStorage.getItem('Authorization')}
        },
        defaultList: [
        ],
        imgUrl: '',
        visible: false,
        uploadList: [],
        value6: "",
        value5: "fix",
        userModal: {
          isShow: false,
          isLoading: false,
        },
        orderId: '',
        employee: {

          employeeNo: "",
          employeePassword: ''
        },
        userRule: {
          employeeNo: [
            {required: true, message: '请填写工号', trigger: 'blur'}
          ],
          employeePassword: [
            {required: true, message: '请填写密码', trigger: 'blur'},
          ]
        },
        checkModal: {
          isShow: false,
          isLoading: false,
        },
        doModal: {
          isShow: false,
          isLoading: false,
        },
        queryCondition: {
          orderType: "look",
          orderStatus: "all",
          pageSize: 10,
          pageNum: 1,
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
            title: '设备名称',
            key: "serviceName"
          },
          {
            title: '负责人',
            key: 'serviceName'
          },
          {
            title: '状态',
            key: 'serviceName'
          },
          {
            title: '是否异常',
            key: 'serviceName'
          },
          {
            title: '操作',
            width: 240,
            render: (h, params) => {
              let button = [h('Button', {
                  props: {
                    type: 'info'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
//                      this.$refs['user'].resetFields()
                      getOrderInfo(params.row.id).then(res => {
                        this.employee.orderId = params.row.id
                        this.userModal.isShow = true

                      })

                    }
                  }
                },
                '详情'
              )]
              if (true)
                button.push(h('Button', {
                    props: {
                      type: 'info'
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                        this.orderId = params.row.id
                        this.employee = {
                          employeeNo: "",
                          employeePassword: ''
                        }
                        this.checkModal.isShow = true
                      }
                    }
                  },
                  '开始处理'
                ))
              return h('div', button)
            }
          }
        ],
        list: [{id: 1, serviceName: 'aaa'}],
        RoleList: []
      };
    },
    methods: {
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getOrderList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      },
      checkUser() {

        this.$refs['checkUser'].validate((valid) => {
          if (valid) {
            this.checkModal.isLoading = true
            checkUser(this.employee).then(res => {
              if (res.data.code === 1) {
                this.checkModal.isShow = false
                this.checkModal.isLoading = false
                this.doModal.isShow = true
              } else {
                this.checkModal.isLoading = false
                this.$Message.error(res.data.message)
              }
            }).catch(e => {
              this.checkModal.isLoading = false
//          this.$Message.error(e);
            })
          }
        })
      },
      handleView(url) {
        this.imgUrl = url;
        this.visible = true;
      },
      handleRemove(file) {
        // 从 upload 实例删除数据
        const fileList = this.$refs.upload.fileList;
        this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
      },
      handleSuccess(res, file) {
        // 因为上传过程为实例，这里模拟添加 url
        console.log(res)
        file.url = res.message
        file.name = res.data
      },
      handleError(e) {
        if (e.status === 401) {
          this.$router.replace({path: '/login', query: {redirect: this.$router.currentRoute.fullPath}})
        }
      },
      handleFormatError(file) {
        this.$Notice.warning({
          title: '文件格式不正确',
          desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
        });
      },
      handleMaxSize(file) {
        this.$Notice.warning({
          title: '超出文件大小限制',
          desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
        });
      },
      handleBeforeUpload() {
        const check = this.uploadList.length < 5;
        if (!check) {
          this.$Notice.warning({
            title: '最多只能上传 5 张图片。'
          });
        }
        return check;
      }
    },
    mounted() {
      this.uploadList = this.$refs.uploads.fileList;
    }
  }
</script>

<style scoped>
  .ModalRow {
    margin-top: 1%;
  }

  .label {
    font-size: 14px;
  }

  .time {
    font-size: 14px;
    font-weight: bold;
  }

  .content {
    padding-left: 5px;
  }

  .demo-upload-list {
    display: inline-block;
    width: 60px;
    height: 60px;
    text-align: center;
    line-height: 60px;
    border: 1px solid transparent;
    border-radius: 4px;
    overflow: hidden;
    background: #fff;
    position: relative;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
    margin-right: 4px;
  }

  .demo-upload-list img {
    width: 100%;
    height: 100%;
  }

  .demo-upload-list-cover {
    display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, .6);
  }

  .demo-upload-list:hover .demo-upload-list-cover {
    display: block;
  }

  .demo-upload-list-cover i {
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    margin: 0 2px;
  }
</style>
