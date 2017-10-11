<template>
  <div class="layout" :class="{'layout-hide-text': spanLeft < 5}">
    <Row type="flex">
      <i-col :span="spanLeft" class="layout-menu-left">
        <Menu ref="menu" :mode="modeType" theme="dark" width="auto" :active-name="activeNames" :open-names="openNames"
              @on-select="menuSelect" accordion>
          <div class="layout-logo-left">
            <!--<Icon type="paper-airplane" :size="logoSize" v-show="logoIsDisplay"></Icon>-->
            <h3>总装车间TPM管理系统</h3>
          </div>
          <template v-for="(item,index) in menuMain">
            <Submenu :name="item.id" v-if="item.orderTop==1">
              <template slot="title">
                <Icon :type="item.icon" :size="iconSize"></Icon>
                <span class="layout-text">{{item.name}}</span>
              </template>
              <template v-for="(child,childIndex) in menu" v-if="child.parentId==item.id&&child.deleteStatus===1">
                <Menu-item :name="child.id">
                  <Icon :type="child.icon" :size="iconSize"></Icon>
                  <span class="layout-text">{{child.name}}</span>
                </Menu-item>
              </template>
            </Submenu>
            <template v-if="item.orderTop!=1">
              <Menu-item :name="item.id">
                <Icon :type="item.icon" :size="iconSize"></Icon>
                <span class="layout-text">{{item.name}}</span>
              </Menu-item>
            </template>
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
                            {{userName}}|{{userRole}}|<span style="cursor: pointer" @click="modifyPassWord">修改密码</span>|<span
                          style="cursor: pointer" @click="logout">退出</span>
                        </span>
            </Dropdown>
          </div>
        </div>
        <div class="layout-breadcrumb">
          <Breadcrumb>
            <Breadcrumb-item v-for="item in Breadcrumb" :href="item.href" :key="item.href">
              {{item.name}}
            </Breadcrumb-item>
          </Breadcrumb>
        </div>
        <div class="layout-content">
          <div class="layout-content-main">
            <router-view></router-view>
          </div>
        </div>
      </i-col>
    </Row>

    <Modal v-model="modal1" title="修改密码" @on-cancel="cancel('formValidate')">
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
        <Form-item label="原密码" prop="oldPassword">
          <Input type="password" v-model="formValidate.oldPassword" placeholder="请输入原始密码"></Input>
        </Form-item>
        <Form-item label="新密码" prop="newPassword">
          <Input type="password" v-model="formValidate.newPassword" placeholder="请输入新密码"></Input>
        </Form-item>
        <Form-item label="确认新密码" prop="resetPassword">
          <Input type="password" v-model="formValidate.resetPassword" placeholder="请再次输入新密码"></Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="cancel('formValidate')" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="comfirmModifyPS" :loading="loading1">提交</Button>
      </div>
    </Modal>
  </div>
  <!-- 修改密码 模态框 -->

  <!-- 修改密码 模态框 -->
</template>

<script>
  import _ from "lodash"
  import {
    updatePwd,
    getUserInfo
  } from "../api/api"

  export default {
    data() {
      const validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.formValidate.resetPassword !== '') {
            // 对第二个密码框单独验证
            this.$refs.formValidate.validateField('resetPassword');
          }
          callback();
        }
      }
      const validatePassCheck = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.formValidate.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      }
      return {
        modeType: "vertical",
        spanLeft: 5,
        spanRight: 19,
        logoIsDisplay: false,
        loading: true,
        modal1: false,
        loading1: false,
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
            {required: true, validator: validatePass, trigger: 'blur'}
          ],
          resetPassword: [
            {required: true, validator: validatePassCheck, trigger: 'blur'}
          ],
        },
        menu: [],
        userName: '',
        userRole: ''
      }
    },
    computed: {
      iconSize() {
        return this.spanLeft === 5 ? 24 : 14;
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
      menuMain() {
        return this.menu.filter(item => {
          return item.parentId === 0
        })
      },
      openNames() {
        return this.$route.path === '/' ? [] : this.menu.filter(item => {
          return item.href === this.$route.path
        }).map(item => {
          return item.parentId
        })
      },
      activeNames() {
        return (this.$route.path === '/'||this.menuMain.length===0)? 0 : this.menu.filter(item => {
          return item.href === this.$route.path
        })[0].id
      },
      Breadcrumb() {
        let bread = []
        if (this.$route.path === '/') {
          return bread
        }
        let menucache = this.menu.filter(item => {
          return item.id === this.activeNames
        })[0]
        if(menucache)bread.push(menucache)
        while (menucache&&menucache.parentId !== 0) {
          menucache = this.menu.filter(item => {
            return item.id === menucache.parentId
          })[0]
          bread.push(menucache)
        }
        return bread.reverse()
      }
    },
    methods: {
      toggleClick() {
        if (this.spanLeft === 5) {
          this.spanLeft = 2;
          this.spanRight = 22;
        } else {
          this.spanLeft = 5;
          this.spanRight = 19;
        }
      },
      modifyPassWord() {
        this.modal1 = true;
      },
      logout() {
        sessionStorage.removeItem('user')
        this.$router.push('/login');
      },
      comfirmModifyPS() {
        this.$refs.formValidate.validate((valid) => {
          if (valid) {
            this.loading1 = true;
            updatePwd(this.formValidate.oldPassword, this.formValidate.newPassword).then(res => {
              this.loading1 = false;
              if(res.data.code ===1){//修改成功
                this.modal1 = false;
                this.$Message.success(res.data.message);
                this.$refs.formValidate.resetFields();
              }else {//原密码不正确
                this.$Message.error(res.data.message);
              }

            }).catch(err => {
              this.loading1 = false;
              this.$Message.error('表单验证失败!' + err);
            })
          } else {
            this.$Message.error('表单验证失败!');
            return false;
          }
        })
        // this.$Message.info('点击了确定');
      },
      cancel(name) {
        this.modal1 = false;
        this.$refs[name].resetFields();
        this.$Message.info('点击了取消');
      },
      menuSelect(id) {
        let path = this.menu.filter(item => {
          return item.id === id
        })[0].href
        this.$router.push({path});
      },
    },
    mounted() {
       getUserInfo().then(res=>{
         this.userName = res.data.data.userName
         this.userRole = res.data.data.roleName
         this.menu = res.data.data.memuVoList
         this.$nextTick(()=>{
           this.$refs.menu.updateOpened()
           this.$refs.menu.updateActiveName();
         });
       })

    }
  }
</script>
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
    borderTop-radius: 4px;
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
    borderTop-radius: 3px;
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
    borderTop-radius: 20px;
    margin: 10px 0px 10px 10px;
    width: 40px;
    height: 40px;
    float: right;
  }


</style>
