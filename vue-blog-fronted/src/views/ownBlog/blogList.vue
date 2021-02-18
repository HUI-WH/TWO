<template>
  <el-row>
    <el-row class="list-content">
      <template v-for="item in pageInfo.list">
        <el-row class="blog-item">
          <el-row class="blog-item-title">
            <router-link :to="{path:'/article/show',query:{id:item.id}}" >
              <a href="javascript:;" style="width: 200px;display: inline-block;overflow: hidden"> {{item.title}}</a>
            </router-link>

          </el-row>
          <el-row class="blog-item-info">
            <span class="info-left">{{formatTimes(item.createTime)}}</span>
            <span class="info-left">阅读</span>
            <span class="info-left">收藏</span>
            <span class="info-right">删除</span>
            <span class="info-right">编辑</span>
            <router-link :to="{path:'/article/show',query:{id:item.id}}" >
              <span class="info-right">查看</span>
            </router-link>

          </el-row>
        </el-row>
      </template>
    </el-row>
    <el-row>
      <div class="block">
        <el-pagination
          layout="prev, pager, next"
          :page-size="pageInfo.pageSize"
          :page-count="pageInfo.pages"
          :current-page="pageInfo.pageNum"
          :total="pageInfo.total"
          @current-change="pagechange"
        >
        </el-pagination>
      </div>
    </el-row>
  </el-row>
</template>

<script>
import {getArticleList} from "@/api/article";
import {formatDate} from "@/api/time";

export default {
name: "BlogList",
  data(){
    return{
      reqInfo:{
        authorId:this.$store.getters.uid,
        keyword:'',
        pageSize:10,
        pageNum:1
      },
      pageInfo:{

      }
    }
  },
  methods:{
    getArticleList(data){
      getArticleList(data).then(resp=>{
        this.pageInfo=resp.data
      })
    },
    pagechange(currNum){
      this.reqInfo.pageNum=currNum
      this.getArticleList(this.reqInfo)

    },
    formatTimes(time){
      var date = new Date(time);
      return formatDate(date,'yyyy-MM-dd  hh:mm:ss')
    }
  },
  created() {
    // 获得博客信息
    this.getArticleList(this.reqInfo)
  }
}
</script>

<style scoped>
.list-content{
  padding: 20px;
}
.blog-item{
  height: 90px;
  padding: 16px 0;
  border-bottom: 1px dotted #ddd ;
}
.blog-item-title{
  font-size: 18px;
  color: #4d4d4d;
}
.blog-item-info{
  padding: 10px 0;
  color: #999;
  font-size: 12px;
}
.info-left{
  display: inline-block;
  float: left;
  padding-right: 10px;
}
.info-right{
  display: inline-block;
  float: right;
  color: #349edf;
  padding:0 10px;
}
</style>
