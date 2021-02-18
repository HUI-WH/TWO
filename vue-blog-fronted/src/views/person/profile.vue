<template xmlns:el-col="http://www.w3.org/1999/html">
  <el-row style="padding: 20px">
    <template v-if="isShowUserInfo">
      <!-- 展示用户信息 -->
      <el-row >
        <el-row>
          <div class="profile-header">
            <div class="profile-header-name">
              个人信息
            </div>
            <div class="profile-header-line"></div>
            <div class="profile-header-update" @click="isShowUserInfo=false"><a href="javascript:;">修改信息</a></div>
          </div>
        </el-row>
        <el-row class="personInfo-item">
          <el-col :span="2" class="personInfo-name">ID</el-col>
          <el-col :span="10" class="personInfo-value">{{userInfo.id}}</el-col>
        </el-row >
        <el-row class="personInfo-item">
          <el-col :span="2" class="personInfo-name">账号</el-col>
          <el-col :span="10" class="personInfo-value">{{userInfo.code}}</el-col>
        </el-row>
        <el-row class="personInfo-item">
          <el-col :span="2" class="personInfo-name">昵称</el-col>
          <el-col :span="10" class="personInfo-value">{{ userInfo.name }}</el-col>
        </el-row>
        <el-row class="personInfo-item">
          <el-col :span="2" class="personInfo-name">邮箱</el-col>
          <el-col :span="10" class="personInfo-value">{{userInfo.email}}</el-col>
        </el-row>
        <el-row class="personInfo-item">
          <el-col :span="2" class="personInfo-name">头像</el-col>
          <el-col :span="10" class="personInfo-value">
            <el-avatar shape="square" :size="50" :src="userInfo.icon">
            </el-avatar></el-col>
        </el-row>
      </el-row>
    </template>
    <template v-else>
      <!-- 编辑用户信息 -->
      <el-row >
        <el-row>
          <div class="profile-header">
            <div class="profile-header-name">
              个人信息
            </div>
            <div class="profile-header-line"></div>
            <div class="profile-header-update" @click="updateInfo"><a href="javascript:;">保存信息</a></div>
          </div>
        </el-row>
        <el-row class="personInfo-item">
          <el-form ref="form" label-width="80px">
            <el-form-item label="ID">
              <el-input v-model="userInfo.id" disabled></el-input>
            </el-form-item>
            <el-form-item label="账号">
              <el-input v-model="userInfo.code" disabled></el-input>
            </el-form-item>
            <el-form-item label="昵称">
              <el-input v-model="userInfo.name"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="userInfo.email"></el-input>
            </el-form-item>
            <el-form-item label="头像">
              <el-avatar shape="square" :size="50" :src="userInfo.icon"></el-avatar>
              <el-upload
                class="upload-demo"
                :headers="uploadHeader"
                name="multipartFile "
                :action="url"
                :show-file-list="false"
                :on-success="uploadSuccess"
                multiple>
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                <div slot="tip" class="el-upload__tip">再次上传替换原来图片</div>
              </el-upload>
            </el-form-item>
          </el-form>
        </el-row >

      </el-row>
    </template>

  </el-row>
</template>

<script>
import {getUserInfos, updateUserInfo} from "@/api/user";
import {getToken} from "@/utils/auth";

export default {
  name: "file",
  data(){
    return{
      uploadHeader:{},
      imageUrl: '',
      url:process.env.VUE_APP_BASE_API + '/upload',
      isShowUserInfo:true,
      userInfo:{}
    }
  },
  methods:{
    getInfo(){
      getUserInfos().then(resp=>{
        this.userInfo=resp.data
      }).catch(err=>{
        this.$message.error("获取用户信息失败")
      })
    },
    updateInfo(){
      updateUserInfo(this.userInfo).then(resp=>{
        if(resp.data>0){
          this.$store.dispatch('user/getInfo')
          this.$message.success("信息更新成功")
          this.getInfo()
          this.isShowUserInfo=true
        }else{
          this.$message.error("更新失败")
        }

      }).catch(err=>{

      })
      this.getInfo()
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    uploadSuccess(response, file,){
      this.$message.success("文件上传成功")
      this.userInfo.icon=response.data.path
    },

  },
  created() {
    const headers= {
      'Authorization' :'Bearer ' + getToken(),
    }
    this.uploadHeader=headers
    this.getInfo()
  }
}
</script>

<style scoped>
.profile-header{
  width: 100%;
  height: 26px;
}
.profile-header div{
  float: left;
}
.profile-header-name{
  color: #222226;
  font-size: 16px;
  font-weight: 600;
  width: 8%;
}
.profile-header-line{
  width: 80%;
  height: 1px ;
  background: #e5e5e5;
  margin-top: 10px;
}
.profile-header-update{
  padding: 0 10px  ;
  display: block;
  border: #e33e33 1px solid;
  color: #e33e33;
  border-radius: 2px;
  height: 100%;
  line-height: 24px;
  font-size: 14px;
  font-weight: 400;
}
.profile-header-update:hover{
  background-color: #fde3e2;
}
.personInfo-name{
  padding: 2px;
  height: 100%;
  line-height: 100%;
  text-align: right;
  color: #555666;
  font-size: 14px;
  font-weight: 400;
}
.personInfo-value{
  padding: 2px;
  margin-left: 10px;
  height: 100%;
  line-height: 100%;
  color: #555666;
  font-size: 14px;
  font-weight: 400;
}
.personInfo-item{
margin-top: 20px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
