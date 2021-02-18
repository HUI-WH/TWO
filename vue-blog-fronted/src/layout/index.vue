<template>

 <div style="background-color: #f5f6f7">
   <el-container>
       <el-row style="position: fixed;padding: 0 0;height:62px;width: 100%;z-index: 99">
          <el-menu default-active="home" class="el-menu-demo" mode="horizontal" @select="handleSelect" style="padding:0 15%" >
            <el-menu-item index="home"><router-link to="/">首页</router-link></el-menu-item>
            <el-submenu index="blog">
              <template slot="title">博客</template>
              <el-menu-item index="blog-all" @click="blogClassClick(0)">全部</el-menu-item>
              <template v-for="blogClass in blogClassList">
                <el-menu-item :index="blogClass.name" @click="blogClassClick(blogClass.id)">{{blogClass.name}}</el-menu-item>
              </template>
            </el-submenu>
            <el-menu-item index="dan" disabled>下载</el-menu-item>
            <el-menu-item index="lun"><a href="" target="_blank">论坛</a></el-menu-item>
            <el-menu-item style="width: 60%;">
              <div>
                <el-input placeholder="search" v-model="keyword" size="small"  class="input-with-select">
                  <el-select v-model="select" slot="prepend" placeholder="请选择">
                    <el-option label="餐" value="1"></el-option>
                    <el-option label="订" value="2"></el-option>
                    <el-option label="用" value="3"></el-option>
                  </el-select>
                  <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                </el-input>
              </div>
            </el-menu-item>
            <el-submenu index="person" >
              <template v-if="isLogin">
                <template slot="title">{{this.$store.getters.name}}</template>
                <router-link to="/person/info"><el-menu-item index="person-1">个人中心</el-menu-item></router-link>
                <el-menu-item index="person-2">
                  <router-link to="/article/create">发表博客</router-link>
                </el-menu-item>
                <el-menu-item index="person-3" @click="loginout">退出</el-menu-item>
              </template>
              <template v-else>
                <template slot="title">个人</template>
                <router-link to="/login">
                  <el-menu-item index="person-4">登录</el-menu-item>
                </router-link>
                <el-menu-item index="person-5">注册</el-menu-item>
              </template>
            </el-submenu>
          </el-menu>
       </el-row>
     <el-main style="min-height: 670px"><app-main class="appmain" style="height: 100%;margin-top: 50px"/></el-main>
     <el-footer style="height: 50px;background-color: white">
       <el-row style="width: 200px;height: 40px;margin: 20px auto 0 auto">
         Copyright © 2020 Gregorio
       </el-row>
     </el-footer>
   </el-container>
 </div>

</template>

<script>
import {AppMain } from './components'
import {getToken} from "@/utils/auth";
import {logout} from "@/api/user";
import {getBlogClassList} from "@/api/article";

export default {
  name: 'Layout',
  data() {
    return {
      select:'',
      keyword:'',
      activeIndex: '1',
      activeIndex2: '1',
      isLogin:false,
      blogClassList:[]
    };
  },
  methods: {
    blogClassClick(classId){
      let res={}
      if(classId===0){
        res={
        }
      }else {
        res={
          classId:classId
        }
      }
      this.$store.dispatch('data/updateArticleData',res)
      this.$router.push({path:"/search/article"})
    },
    loginout(){
      this.$store.dispatch("user/logout").then(res=>{
        location.reload();
      })
    }
    ,
    handleSelect(key, keyPath) {
    },
    search(){
      if(this.$route.name==='ArticleSerach'){
        var res={
          keyword:this.keyword
        }
        this.$store.dispatch('data/updateArticleData',res)
      }else {
        this.$store.dispatch('data/updateArticleData',res)
        this.$router.push({ path: '/search/article'})
      }
    },
    getblogClassList(){
      getBlogClassList().then(res=>{
        this.blogClassList=res.data
      })
    }
  },
  components: {
    AppMain
  },
  watch:{
    keyword(newValue,oldValue){
      this.$store.commit("data/SET_KEYWORD",newValue)
    }
  },
  created() {
    const hasToken = getToken()
    if(hasToken){
      this.isLogin=true
    }
    this.getblogClassList()
  }
}
</script>



<style>
.appmain{
  top: 65px;
}
</style>
