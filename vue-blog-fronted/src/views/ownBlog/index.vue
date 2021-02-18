<template>
  <el-row>
    <el-row  style="width: 1200px;margin: 8px auto 8px auto;height: 500px;padding: 5px"  >
      <el-row >
        <!--   左侧菜单   -->
        <el-col :span="4" style="height: 200px;background-color: white" >
          <el-menu
            default-active="ownBlog-article">
            <el-submenu index="ownBlog">
              <template slot="title">
                <span>博客管理</span>
              </template>
              <el-menu-item index="ownBlog-article">文章管理</el-menu-item>
              <el-menu-item index="ownBlog-comment">评论管理</el-menu-item>
            </el-submenu>
              <el-menu-item index="1">
                <span slot="title"  class="leftItem">
                  <router-link to="/article/create">
                 发布文章
                 </router-link>
                </span>

              </el-menu-item>
          </el-menu>
        </el-col>
        <!--   右侧内容   -->
        <el-col :span="20"  style="padding:0 10px">
          <el-row style="background-color: white;height: 100%;padding: 5px">
            <router-view/>
          </el-row>
        </el-col>
      </el-row>


    </el-row>
  </el-row>
</template>

<script>
import {getArticleList} from "@/api/article";

export default {
name: "OwnBlog",
  data(){
    return{

    }
  }

}
</script>

<style scoped>

</style>
