<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="4" >
        时间区间：
        </Col>
        <Col :span="7">
        <DatePicker type="daterange" :value="[queryCondition.begin,queryCondition.end]" @on-change='queryCondition.begin=arguments[0][0];queryCondition.end=arguments[0][1]'
                    placeholder="选择日期和时间（不含秒）"
                    style="width: 300px"
                    :clearable="false"></DatePicker>
        </Col>
        <Col :span="2":offset="4">
        工单名：
        </Col>
        <Col :span="3" >
        <Input type="text" v-model="queryCondition.tpmBillName" placeholder="请输入..."></Input>
        </Col>
        <Col :span="3" offset='18' class="ModalRow">
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
          <strong class="label">标准检查操作步骤</strong>
          </Col>
          <Col span="14">
          {{detail.serviceDescribe}}
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">参考图片</strong>
          </Col>
          <Col span="14">
          <div class="demo-upload-list" v-for="(item,index) in detail.demoAttachements">
            <img @click="handleView(item.id)" :src="env+'/v1/image/sosOutImg/'+item.id">
          </div>
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">巡检描述</strong>
          </Col>
          <Col span="14">
          {{detail.finishComment}}
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">报修图片</strong>
          </Col>
          <Col span="14">
          <div class="demo-upload-list" v-for="(item,index) in lookList">
            <img @click="handleView(item)"  :src="env+'/v1/image/sosOutImg/'+item">
          </div>
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">维修描述</strong>
          </Col>
          <Col span="14">
          {{detail.repairedComment}}
          </Col>
        </Row>
        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">处理图片</strong>
          </Col>
          <Col span="14">
          <div class="demo-upload-list" v-for="(item,index) in fixImgList">
            <img @click="handleView(item)"  :src="env+'/v1/image/sosOutImg/'+item">
          </div>
          </Col>
        </Row>

        <Row class="ModalRow">
          <Col span="10">
          <strong class="label">审核结果</strong>
          </Col>
          <Col span="14">
          {{detail.rejectReason}}
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
        <!--<Button type="primary" @click="userModal.isShow=false;doModal.isShow=true">开始处理</Button>-->
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
    getAuditlist,
    getOrderInfo,
    checkUser,
    doOrder
  } from '../../api/order'
  import {formatData} from '../../lib/utils/common'
  export default {
    data() {
      return {
        env:process.env.NODE_ENV === 'production' ? '' : "car",
        img: '',
        fixImgList:[],
        lookList: [],
        visible: false,
        imgList: [],
        desc: "",
        orderType: 5,
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
          tpmBillName: '',
          total: 0,
          begin:formatData.call(new Date(), "yyyy-MM-dd"),
          end:formatData.call(new Date(), "yyyy-MM-dd")
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
            title: '巡检人',
            key: "repairedUserName"
          },
          {
            title: '状态',
            key: 'tpmStatusName'
          },
          {
            title: '是否异常',
            render: (h, params) => {
              return h('div', params.row.repairedUserId===0||params.row.tpmStatus===3?"异常":"无异常")
            }
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
                      params.row.repairAttachements.forEach(e=>{
                        fixImgList.push(e.id)
                      })
                      this.fixImgList=fixImgList
                      let lookList=[]
                      params.row.pendAttachements.forEach(e=>{
                        lookList.push(e.id)
                      })
                      this.lookList=lookList
                    }
                  }
                },
                '详情'
              )]
//              if (params.row.tpmStatus===2||params.row.tpmStatus===4 )
//                button.push(h('Button', {
//                    props: {
//                      type: 'info'
//                    },
//                    style: {
//                      marginRight: '5px'
//                    },
//                    on: {
//                      click: () => {
//                        this.orderId = params.row.id
//                        let fixImgList=[]
//                        params.row.repairAttachements.forEach(e=>{
//                          fixImgList.push(e.id)
//                        })
//                        this.fixImgList=fixImgList
//                        let lookList=[]
//                        params.row.pendAttachements.forEach(e=>{
//                          lookList.push(e.id)
//                        })
//                        this.lookList=lookList
//                        this.doModal.isShow = true
//                      }
//                    }
//                  },
//                  '审核'
//                ))
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
      doOrder() {
        this.doModal.isLoading = true
        let orderParam = new FormData();
        orderParam.append("dealType", this.orderType)
        orderParam.append("id", this.orderId)
        orderParam.append("desc", this.desc)
        doOrder(orderParam).then(res => {
          this.doModal.isLoading = false
          this.doModal.isShow = false
          this.$Message.success(res.data.message)
          this.getlist()
        }).catch(e => {
          this.doModal.isLoading = false
        })
      },
      handleView(id) {
        this.img = this.env+'/v1/image/sosOutImg/'+id;
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
