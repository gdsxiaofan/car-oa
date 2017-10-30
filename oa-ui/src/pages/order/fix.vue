<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="4">
        工单名：
        </Col>
        <Col :span="7">
        <Input type="text" v-model="queryCondition.tpmBillName" placeholder="请输入..."></Input>
        </Col>

        <Col :span="3" offset="7" >
        <Button type="primary" shape="circle" icon="ios-search"
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
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">工单号</strong>
          </Col>
          <Col span="14">
          <p class="label">{{detail.id}}</p>
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">工单名称</strong>
          </Col>
          <Col span="14">
          <p class="label">{{detail.tpmName}}</p>
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">设备名称</strong>
          </Col>
          <Col span="14">
          <p class="label">{{detail.deviceName}}</p>
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">描述</strong>
          </Col>
          <Col span="14">
          {{detail.serviceDescribe}}
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">报修图片</strong>
          </Col>
          <Col span="14">
          <div class="demo-upload-list" v-for="(item,index) in fixImgList">
            <img @click="handleView(item)"  :src="env+'/v1/image/sosOutImg/'+item">
          </div>
          </Col>
        </Row>
        </Col >
        <!--<Col span="8">-->
        <!--<Timeline>-->
          <!--<TimelineItem>-->
            <!--<p class="time">事件：1976年</p>-->
            <!--<p class="content">Apple I 问世</p>-->
          <!--</TimelineItem>-->
          <!--<TimelineItem>-->
            <!--<p class="time">事件：1976年</p>-->
            <!--<p class="content">Apple I 问世</p>-->
          <!--</TimelineItem>-->
          <!--<TimelineItem>-->
            <!--<p class="time">事件：1976年</p>-->
            <!--<p class="content">Apple I 问世</p>-->
          <!--</TimelineItem>-->
        <!--</Timeline>-->
        <!--</Col>-->
      </Row>
      <div slot="footer">
        <Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>
        <!--<Button type="primary" @click="userModal.isShow=false;checkModal.isShow=true">开始处理</Button>-->
      </div>
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
        <Button type="ghost" @click="checkModal.isShow=false" style="margin-left: 8px">取消</Button>
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
        <p class="label">{{detail.id}}</p>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">工单名称</strong>
        </Col>
        <Col span="14">
        <p class="label">{{detail.tpmName}}</p>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">设备名称</strong>
        </Col>
        <Col span="14">
        <p class="label">{{detail.deviceName}}</p>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">描述</strong>
        </Col>
        <Col span="14">
        {{detail.serviceDescribe}}
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">报修图片</strong>
        </Col>
        <Col span="14">
        <div class="demo-upload-list" v-for="(item,index) in fixImgList">
          <img @click="handleView(item)"  :src="env+'/v1/image/sosOutImg/'+item">
        </div>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">处理图片</strong>
        </Col>
        <Col span="14">
        <div class="demo-upload-list" v-for="(item,index) in uploadList">
          <img :src="env+'/v1/image/sosOutImg/'+item">
          <div class="demo-upload-list-cover">
            <Icon type="ios-eye-outline" @click.native="handleView(item)"></Icon>
            <Icon type="ios-trash-outline" @click.native="handleRemove(index)"></Icon>
          </div>
        </div>
        <upload
          ref="uploads"
          :action="env+'/v1/image/upPic'"
          :show-upload-list="false"
          :before-upload="handleBeforeUpload"
          :on-success="handleSuccess"
          multiple
          type="drag"
          style="display: inline-block;width:58px;">
          <div style="width: 58px;height:58px;line-height: 58px;">
            <Icon type="camera" size="20"></Icon>
          </div>
        </upload>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">处理描述</strong>
        </Col>
        <Col span="14">
        <Input v-model="desc" type="textarea" placeholder="请输入..." autosize/>
        </Col>
      </Row>
      <div slot="footer">
        <Button type="ghost" @click="doModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doOrder" :loading="doModal.isLoading">提交</Button>
      </div>
    </Modal>
    <Modal title="查看图片" v-model="visible">
      <img :src=" img" v-if="visible" style="width: 100%">
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
  import upload from '../upload.vue'

  export default {
    data() {
      return {
        env:process.env.NODE_ENV === 'production' ? '' : "car",
        img: '',
        fixImgList:[],
        visible: false,
        uploadList: [],
        imgList: [],
        desc: "",
        orderType: 4,
        orderId: 0,
        detail: {
          id: '',
          tpmName: '',
          deviceName: ''
        },
        userModal: {
          isShow: false,
          isLoading: false,
        },
        doUserId: '',
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
          tpmStatus: 3,
          pageSize: 10,
          pageNum: 1,
          tpmBillName: '',
          total: 0
        },
        columns: [
          {
            title: '工单号',
            key: 'id'
          },
          {
            title: '工单名称',
            key: 'tpmName'
          },
          {
            title: '设备名称',
            key: "deviceName"
          },
          {
            title: '巡检时间',
            key: "checkTime"
          },
          {
            title: '操作',
            width: 240,
            render: (h, params) => {
              let button = [h('Button', {
                  props: {
                    type: 'primary'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.userModal.isShow = true
                      this.detail = params.row
                      let fixImgList=[]
                      params.row.pendAttachements.forEach(e=>{
                        fixImgList.push(e.id)
                      })
                      this.fixImgList=fixImgList
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
                        let fixImgList=[]
                        params.row.pendAttachements.forEach(e=>{
                          fixImgList.push(e.id)
                        })
                        this.fixImgList=fixImgList
                        this.employee = {
                          employeeNo: "",
                          employeePassword: ''
                        }
                        this.detail = params.row
                        this.uploadList = []
                        this.desc = ''
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
        list: [],
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
                this.doUserId = res.data.data
                this.checkModal.isShow = false
                this.checkModal.isLoading = false
                this.doModal.isShow = true
              } else {
                this.checkModal.isLoading = false
                this.$Message.error(res.data.message)
              }
            }).catch(e => {
              this.checkModal.isLoading = false
            })
          }
        })
      },
      doOrder() {
        this.doModal.isLoading = true
        let orderParam = new FormData();
        orderParam.append("userId",this.doUserId)
        orderParam.append("id",this.orderId)
        orderParam.append("dealType",this.orderType)
        orderParam.append("desc",this.desc)
        let attachmentIds=''
        this.uploadList.forEach((i,n)=>{
          if(this.uploadList.length===n+1){
            attachmentIds+=i
          }else {
            attachmentIds+=i+','
          }
        })
        orderParam.append("attachmentIds",attachmentIds)
//        if (this.uploadList) {
//          this.uploadList.forEach(x => {
//            orderParam.append("file", x)
//          })
//        } else {
//          orderParam.append("file", null)
//        }
        doOrder(orderParam).then(res => {
          this.doModal.isLoading = false
          this.doModal.isShow = false
          this.$Message.success(res.data.message)
          this.getlist()
        }).catch(e => {
          this.doModal.isLoading = false
//          this.$Message.error(e)
        })
      },
      handleView(id) {
        this.img = this.env+'/v1/image/sosOutImg/'+id;
        this.visible = true;
      },
      handleRemove(index) {
        this.uploadList.splice(index, 1);
//        this.imgList.splice(index, 1);
      },
      handleSuccess (res, file) {
        this.uploadList.push(res.message)
        // 因为上传过程为实例，这里模拟添加 url
//        file.url = 'https://o5wwk8baw.qnssl.com/7eb99afb9d5f317c912f08b5212fd69a/avatar';
//        file.name = '7eb99afb9d5f317c912f08b5212fd69a';
      },
      handleBeforeUpload(file) {

        const format = ['jpg', 'jpeg', 'png'];
        const maxsize = "2048";
        const check = this.uploadList.length < 5;
        if (!check) {
          this.$Notice.warning({
            title: '最多只能上传 5 张图片。'
          });
//        } else if (this.checkImg(file, format, maxsize)) {
//          let reader = new FileReader();
//          reader.onload = (e) => {
//            this.imgList.push(e.target.result)
//          };
//          reader.readAsDataURL(file);
//          this.uploadList.push(file)
        }
        return check
      },
      checkImg(file, format, maxSize) {
        // check format
        if (format.length) {
          const _file_format = file.name.split('.').pop().toLocaleLowerCase();
          const checked = format.some(item => item.toLocaleLowerCase() === _file_format);
          if (!checked) {
            this.$Notice.warning({
              title: '文件格式不正确',
              desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
            return false;
          }
        }
        // check maxSize
        if (maxSize) {
          if (file.size > this.maxSize * 1024) {
            this.$Notice.warning({
              title: '超出文件大小限制',
              desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
            return false;
          }
        }
        return true
      }
    },
    created() {
      this.getlist()
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
    display: block;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, .6);
  }

  .demo-upload-list-cover i {
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    margin: 0 2px;
  }
</style>
