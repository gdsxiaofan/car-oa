<style scoped>
  .layout {
    background: #f5f7f9;
    position: relative;
    overflow: hidden;
    height: 100%;
  }

  .layout-breadcrumb {
    padding: 10px 15px 0;
  }

  .layout-content {
    min-height: 200px;
    margin: 15px;
    overflow: auto;
    background: #fff;
    border-radius: 4px;
    height: 80%;
  }

  .layout-content-main {
    padding: 10px;
  }

  .layout-copy {
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
  }

  .layout-menu-left {
    background: #464c5b;
  }

  .layout-header {
    height: 60px;
    background: #fff;

  }

  .layout-logo-left {
    width: 90%;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    margin: 15px auto;
    line-height: 30px;
    color: white;
    text-align: center;
    vertical-align: middle;
    overflow: hidden;
  }

  .layout-ceiling-main a {
    color: #9ba7b5;
  }

  .layout-hide-text .layout-text {
    display: none;
  }

  .ivu-col {
    transition: width .2s ease-in-out;
  }

  .ivu-row-flex {
    height: 100%;
  }

  .userinfo {
    display: inline-block;
    float: right;
  }

  .userinfo .ivu-dropdown {
    margin-top: 50px;
  }

  .ivu-dropdown {
    margin-right: 25px;
    margin-top: 22px;
  }

  .ivu-menu-submenu-title {
    padding: 14px;
  }

  .head-img {
    width: 100%;
    height: 60px;
    line-height: 60px;
    float: right;
    margin-top: -50px;
  }

  .head-img img {
    border-radius: 20px;
    margin: 10px 0px 10px 10px;
    width: 40px;
    height: 40px;
    float: right;
  }


</style>
<template>
  <div class="layout" :class="{'layout-hide-text': spanLeft < 5}">
    <Row type="flex">
      <i-col :span="spanLeft" class="layout-menu-left">
        <Menu :mode="modeType" theme="dark" width="auto" :active-name="this.$route.path" :open-names="openNames"
              @on-select="menuSelect" accordion>
          <div class="layout-logo-left">
            <!--<Icon type="paper-airplane" :size="logoSize" v-show="logoIsDisplay"></Icon>-->
            <h3>工单管理系统</h3>
          </div>
          <template v-for="(item,index) in menu">
            <Submenu :name="item.name" v-if="item.level==2">
              <template slot="title">
                <Icon :type="item.icon" :size="iconSize"></Icon>
                <span class="layout-text">{{item.name}}</span>
              </template>
              <template v-for="(child,childIndex) in item.children" v-if="!child.hidden">
                <Menu-item :name="child.path">
                  <Icon :type="child.icon" :size="iconSize"></Icon>
                  <span class="layout-text">{{child.name}}</span>
                </Menu-item>
              </template>
            </Submenu>
            <!--<template v-if="item.leaf&&item.children.length>0">-->
            <!--<Menu-item :name="item.children[0].path">-->
            <!--<Icon :type="item.iconCls" :size="iconSize"></Icon>-->
            <!--<span class="layout-text">{{item.children[0].name}}</span>-->
            <!--</Menu-item>-->
            <!--</template>-->
          </template>
        </Menu>
      </i-col>
      <i-col :span="spanRight">
        <div class="layout-header">
          <i-button type="text" @click="toggleClick">
            <Icon type="navicon" size="32"></Icon>
          </i-button>
          <div class="userinfo">
            <Dropdown placement="bottom-end">
                        <span class="head-img">
                            {{curUserName}}|
                            管理员|<span style="cursor: pointer" @click="modifyPassWord">修改密码</span>|<span
                          style="cursor: pointer" @click="logout">退出</span>
                        </span>
            </Dropdown>
          </div>
        </div>
        <div class="layout-breadcrumb">
          <Breadcrumb>
            <Breadcrumb-item href="#">应用中心</Breadcrumb-item>
            <Breadcrumb-item>{{$route.name}}</Breadcrumb-item>
          </Breadcrumb>
        </div>
        <div class="layout-content">
          <div class="layout-content-main">
            <router-view></router-view>
          </div>
        </div>
      </i-col>
    </Row>

    <Modal v-model="modal1" title="修改密码" @on-ok.prevent="comfirmModifyPS" @on-cancel="cancel">
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
        <Form-item label="原密码" prop="oldPassword">
          <Input v-model="formValidate.oldPassword" placeholder="请输入原始密码"></Input>
        </Form-item>
        <Form-item label="新密码" prop="newPassword">
          <Input v-model="formValidate.newPassword" placeholder="请输入新密码"></Input>
        </Form-item>
        <Form-item label="确认新密码" prop="resetPassword">
          <Input v-model="formValidate.resetPassword" placeholder="请再次输入新密码"></Input>
        </Form-item>
      </Form>
    </Modal>
  </div>
  <!-- 修改密码 模态框 -->

  <!-- 修改密码 模态框 -->
</template>

<script>
  import _ from "lodash"

  export default {
    data() {
      return {
        openNames: [this.$route.matched[0].name],
        curUserName: sessionStorage.getItem('user').replace(/\"/g, ""),
        modeType: "vertical",
        spanLeft: 5,
        spanRight: 19,
        logoIsDisplay: false,
        loading: true,
        modal1: false,
        formValidate: {
          oldPassword: '',
          newPassword: '',
          resetPassword: ''
        },
        ruleValidate: {
          oldPassword: [
            {required: true, message: '密码不能为空', trigger: 'blur'}
          ],
          newPassword: [
            {required: true, message: '密码不能为空', trigger: 'blur'}
          ],
          resetPassword: [
            {required: true, message: '密码不能为空', trigger: 'blur'}
          ],
        }
      }
    },
    computed: {
      iconSize() {
        return this.spanLeft === 5 ? 14 : 24;
      },
      logoSize() {
        if (this.spanLeft !== 5) {
          this.logoIsDisplay = true;
          return 30;
        } else {
          this.logoIsDisplay = false;
          return 0;
        }
      },
      menu() {
        let menu = _.cloneDeep(this.$router.options.routes[0])
        let a = [menu.children[1]]
        let b = [{
          path: '/print', name: '权限管理', icon: 'ios-home', level: 2,
          children: [
            {path: 'page0', name: '角色管理', icon: 'ios-home'},
            {path: 'page0', name: '新增角色', icon: 'ios-home'},
            {path: 'page0', name: '用户角色管理', icon: 'ios-home'}]
        }, {
          path: '/print', name: '工单管理', icon: 'ios-home', level: 2,
          children: [
            {path: 'page0', name: '我的工单', icon: 'ios-home'},
            {path: 'page0', name: '发布工单', icon: 'ios-home'},
            {path: 'page0', name: '派发工单', icon: 'ios-home'},
            {path: 'page0', name: '历史工单', icon: 'ios-home'},
            {path: 'page0', name: '所有工单', icon: 'ios-home'}]
        },{
          path: '/print', name: '员工管理', icon: 'ios-home', level: 2,
          children: [
            {path: 'page0', name: '员工信息管理', icon: 'ios-home'},
            {path: 'page0', name: '新增员工', icon: 'ios-home'}]
        },{
          path: '/print', name: '设备管理', icon: 'ios-home', level: 2,
          children: [
            {path: 'page0', name: '设备基础信息管理', icon: 'ios-home'},
            {path: 'page0', name: '新增设备信息', icon: 'ios-home'}]
        },
        ]
        return b
      }
    },
    methods: {
      toggleClick() {
        if (this.spanLeft === 5) {
          this.spanLeft = 1;
          this.spanRight = 23;
        } else {
          this.spanLeft = 5;
          this.spanRight = 19;
        }
      },
      modifyPassWord() {
        this.modal1 = true;
      },
      logout() {
        this.$router.push('/login');
      },
      comfirmModifyPS() {
        return false;
        this.$refs.formValidate.validate((valid) => {
          if (valid) {
            this.modal1 = false;
            //         this.loading = false;
            this.$Message.success('提交成功!');
          } else {
            this.$Message.error('表单验证失败!');
            return false;
          }
        })
        // this.$Message.info('点击了确定');
      },
      cancel() {
        this.modal1 = false;
        this.$Message.info('点击了取消');
      },
      menuSelect(name) {
        debugger
        this.$router.push({path: name});
      }

    },
    mounted() {
    }
  }
</script>
