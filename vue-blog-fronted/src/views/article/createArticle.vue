<template>
  <el-row style="padding:10px 50px">

    <el-row class="edit-header" type="flex"  justify="space-between">
      <el-col :span="3">
        <span class="return"><router-link to="/"><i class="el-icon-arrow-left"></i>返回首页</router-link></span>
      </el-col>
      <el-col :span="16">
        <el-input v-model="article.title" placeholder="请输入标题"></el-input>
      </el-col >
      <el-col :span="1" style="margin-right: 10px">
        <el-button @click="dialogVisible = true">更多信息</el-button>
        <el-dialog title="更多信息" :visible.sync="dialogVisible">
          <el-form >
            <el-form-item label="简介" >
              <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="article.describe">
              </el-input>
            </el-form-item>
            <el-form-item label="文章类别">
              <el-select v-model="article.classId" placeholder="请选择活动区域">
                <template v-if="articleClass!==null">
                  <template v-for="item in articleClass">
                    <el-option :label="item.name" :value="item.id"></el-option>
                  </template>
                </template>
                <template v-else>
                  <el-option label="0_0" value="0"></el-option>
                </template>
              </el-select>
            </el-form-item>
            <img :src="article.titleImg" style="height: 100px">
            <el-form-item label="博客标题图片上传">
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
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </div>
        </el-dialog>
      </el-col>
      <el-col :span="2" style="padding: 0 20px">
        <el-button type="danger" @click="submit">发布文章</el-button>
      </el-col>
      <el-col :span="1" style="padding-right: 10px">
        <el-dropdown>
          <span class="el-dropdown-link">
            <el-avatar shape="circle" scrollStyle="true" :size="40" :src="this.$store.getters.avatar"></el-avatar>
          </span>
          <el-dropdown-menu slot="dropdown">
            <router-link to="/person/info"><el-dropdown-item>个人中心</el-dropdown-item></router-link>
            <el-dropdown-item disabled>我的博客</el-dropdown-item>
            <el-dropdown-item  ><span @click="logout">退出</span></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
    <el-row>
      <el-row class="md-editor">
        <mavon-editor v-model="article.content" ref="md" @imgAdd="$imgAdd" @change="change" style="height: 700px"/>
      </el-row>
    </el-row>
    <el-row>
      <el-row style="width: 200px;height: 40px;margin: 20px auto 0 auto">
        Copyright © 2020 Gregorio
      </el-row>
    </el-row>
  </el-row>

</template>

<script>

import * as axios from "axios";
import {getToken} from "@/utils/auth";
import {createArticle, getBlogClassList} from "@/api/article";
export default {
name: "createArticle",
  data(){
    return{
      articleClass:null,
      uploadHeader:{},
      dialogVisible: false,
      html:'',
      uid:0,
      configs: {},
      article:{
        classId:0,
        title:'',
        describe:'',
        titleImg:'',
        content:''
      },
      url: process.env.VUE_APP_BASE_API + '/upload',
    }
  },
  methods:{
    // 将图片上传到服务器，返回地址替换到md中
    $imgAdd(pos, $file){
      let param = new FormData()  // 创建form对象
      param.append('multipartFile', $file)  // 通过append向form对象添加数据
      param.append('chunk', '0') // 添加form表单中其他数据
      let config = {
        headers: {'Content-Type': 'multipart/form-data',
          'Authorization' :'Bearer ' + getToken(),
        }
      }
      // console.log(this.$refs.md.d_render) md转html
      // 添加请求头
      axios.post(this.url, param, config)
        .then(res => {
          this.$refs.md.$img2Url(pos, res.data.data.path);
          console.log( res.data.data.path)
        }).catch(error=>{
        console.log(err)
      })
    },
    // 所有操作都会被解析重新渲染
    change(value, render){
      // render 为 markdown 解析后的结果[html]
      this.html = render;
    },
    validArticle(data){
      var res={
        valided:false,
        msg:'内容有误，上传失败'
      }
      if(!data.title||data.title.trim()===''){
        res.valided=false
        res.msg='标题不能为空'
        console.log(res.msg)
        console.log(data.title)
        console.log(data.title.trim())
      }else if(!data.content||data.content.trim()===''){
        res.valided=false
        res.msg='内容不能为空'
      } else if(!data.describe||data.describe.trim()===''){
        res.valided=false
        res.msg='简介不能为空，请打开右上角更多信息进行编辑'
      }else{
        res.valided=true
        res.msg='success'
      }

      return res
    },
    // 提交
    submit(){
      var res={
        title:this.article.title.trim(),
        describe:this.article.describe.trim(),
        titleImg:this.article.titleImg.trim(),
        authorId:this.uid,
        content: this.article.content.trim(),
        contentType:'0',
        cid:this.article.classId
      }
      let valid = this.validArticle(res)
      if(!valid.valided){
        this.$message.error(valid.msg)
      }else{
        createArticle(res).then(data=>{
          console.log('submit')
          console.log(res)
          console.log(data)
          this.$message.success('发布成功');
          this.article.title=''
          this.article.describe=''
          this.article.titleImg=''
          this.article.content=''
          this.article.classId=0
        })
      }
    },
    uploadSuccess(response, file,){
      this.$message.success("文件上传成功")
      this.titleImg=response.data.path
    },
    getArticleList(){
      getBlogClassList().then(resp=>{
        this.articleClass=resp.data
      })
    },
    logout(){
      this.$store.dispatch("user/logout").then(res=>{
        this.$router.push('/')
      })
    }
  },
  created() {
    const headers= {
      'Authorization' :'Bearer ' + getToken(),
    }
    this.uploadHeader=headers
    console.log(headers)
    this.uid=this.$store.getters.uid
    this.getArticleList()
  }
}
</script>

<style scoped>
.edit-header{
  height: 50px;
}
.md-editor{

}
.return{
  height: 40px;
  overflow: hidden;
  line-height: 40px;
  color: #7f7f7f;
  font-size: 17px;
  text-align: center;
  width: 87px;
}
</style>
