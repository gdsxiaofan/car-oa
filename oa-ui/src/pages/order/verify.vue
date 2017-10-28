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
        <Col :span="3" offset="7" >
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
      <div slot="footer">
        <Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="userModal.isShow=false;doModal.isShow=true">开始处理</Button>
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
        <Input v-model="desc" type="textarea" disabled placeholder="请输入..." autosize/>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">报修图片</strong>
        </Col>
        <Col span="14">
        <div class="demo-upload-list" v-for="(item,index) in fixImgList">
          <img @click="handleView(base+item)"  :src="base+item">
        </div>
        </Col>
      </Row>
      <Row class="ModalRow">
        <Col span="10">
        <strong class="label">处理图片</strong>
        </Col>
        <Col span="14">
        <div class="demo-upload-list" v-for="(item,index) in fixImgList">
          <img @click="handleView(base+item)"  :src="base+item">
        </div>
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
        base:(process.env.NODE_ENV === 'production' ? '' : "car") +"v1/image/sosOutImg/",
        img: '',
        fixImgList:['\\4643e8dd-e76d-4805-ac90-3a95a2fe98ed\\229\\14\\4643e8dd-e76d-4805-ac90-3a95a2fe98ed20171003230233023509.jpg'],
        visible: false,
        uploadList: [],
        imgList: [],
        desc: "",
        orderType: "verified",
        userModal: {
          isShow: false,
          isLoading: false,
        },
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
        doModal: {
          isShow: false,
          isLoading: false,
        },
        queryCondition: {
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
            key: 'tpmStatusName'
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
                      getOrderInfo(params.row.id).then(res => {
                        //todo
                        this.userModal.isShow = true
                      })
                    }
                  }
                },
                '详情'
              )]
              if (params.row.tpmStatus===2&&params.row.tpmStatus===4 )
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
                        this.doModal.isShow = true
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
      doOrder() {
        this.doModal.isLoading = true
        let orderParam = new FormData();
        orderParam.append("orderType", this.orderType)
        orderParam.append("desc", this.desc)
        if (this.uploadList) {
          this.uploadList.forEach(x => {
            orderParam.append("file", x)
          })
        } else {
          orderParam.append("file", null)
        }
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
      handleView(img) {
        this.img = img;
        this.visible = true;
      },
      handleRemove(index) {
        this.uploadList.splice(index, 1);
        this.imgList.splice(index, 1);
      },
      handleBeforeUpload(file) {

        const format = ['jpg', 'jpeg', 'png'];
        const maxsize = "2048";
        const check = this.uploadList.length < 5;
        if (!check) {
          this.$Notice.warning({
            title: '最多只能上传 5 张图片。'
          });
        } else if (this.checkImg(file, format, maxsize)) {
          let reader = new FileReader();
          reader.onload = (e) => {
            this.imgList.push(e.target.result)
          };
          reader.readAsDataURL(file);
          this.uploadList.push(file)
        }
        return false
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
