<template>
  <div>
    <div style="position: fixed;width:80px;left: 12%; margin-top: 10px; z-index: 99">
      <el-row>
        <el-col :span="24" class="gohome">
          <router-link to="/">首页</router-link>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
            <el-menu-item index="0" @click="reSearch(0)">
              <span slot="title" >全部</span>
            </el-menu-item>
            <template v-for="item in blogClassList">
              <el-menu-item :index="item.id.toString()" @click="reSearch(item.id)">
                <span slot="title" >{{ item.name }}</span>
              </el-menu-item>
            </template>
          </el-menu>
        </el-col>
      </el-row>
    </div>
    <el-row :gutter="20" style="padding: 20px">
      <el-col :span="13" :offset="4">
        <el-row>
          <ul class="sort">
            <li class="sortli" :class="{'isactive':this.liactive===1}" @click="liclick(1)">综合排序</li>
            <li class="sortli" :class="{'isactive':this.liactive===2}" @click="liclick(2)">最新优先</li>
            <li class="sortli" :class="{'isactive':this.liactive===3}" @click="liclick(3)">热门优先</li>
            <li>时间不限</li>
            <li id="result">为您找到结果约{{pageInfo.total}}个</li>
          </ul>
        </el-row>
        <el-row>
          <el-row v-for="row in article_list " class="article">
            <router-link :to="{path:'/article/show',query:{id:row.id}}" >
              <el-row>
                <span class="articleName">{{row.title}}</span>
              </el-row>
              <el-row>
                   <span class="articleDescribe">
                     {{row.describe}}
                   </span>
              </el-row>
            </router-link>
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
      </el-col>
      <el-col :span="4" style="margin-top: 20px" >
        <el-row style="height: 100px; background-color: white;"></el-row>
        <el-row style="height: 400px; background-color: white; margin-top: 20px"></el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import {getArticleList, getBlogClassList} from "@/api/article";
export default {
  name: 'Serach',
  data(){
    return{
      liactive:1,
      id:1,
      blogClassList:[],
      article_list:[{
        id:0,
        title:'',
        authorId:0 ,
        describe:'',
        titleImg:'',
        content:'',
        createTime:''
      }],
      keyword:'',
      pageInfo: {
        total: 0,
        pages: 0,
        pageSize: 10,
        pageNum: 1
      }
    }
  },
  methods:{
    reSearch(classId){
      let resData={}

      if(classId!==0){
        resData={
          classId:classId
        }
      }

      this.$store.dispatch('data/updateArticleData',resData)
    },
    getblogClassList(){
      getBlogClassList().then(res=>{
        this.blogClassList=res.data
        console.log("class")
        console.log(this.blogClassList)
      })
    },
    pagechange(currNum){
      if(0<currNum&&currNum<=this.pageInfo.pages){
        const resData={
          keyword:this.keyword,
          pageNum:currNum,
          pageSize:this.pageInfo.pageSize
        }
        this.handleArticleList(resData)
        this.$store.dispatch('data/updateArticleData',resData)
        this.$store.getters.articleData
        // this.handleArticleList(resData)
      }

    },
    liclick(e){
      this.liactive=e
    },
    handleArticleList(data){
      getArticleList(data).then(result=>{
        const data = result.data
        this.article_list=data.list
        this.pageInfo.pageSize=data.pageSize
        this.pageInfo.pageNum=data.pageNum
        this.pageInfo.total=data.total
        this.pageInfo.pages=data.pages
      })

    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'articleData'
    ])
  },
  created() {
    this.getblogClassList()
    // console.log(this.$store.getters.keyword)
    // console.log(this.$store.getters.articleData)

    var articleData=this.$store.getters.articleData
    // console.log(!articleData)
    this.keyword=this.$store.getters.keyword

    if(!articleData){
      this.article_list=articleData.list
      this.pageInfo.pageSize=articleData.pageSize
      this.pageInfo.pageNum=articleData.pageNum
      this.pageInfo.total=articleData.total
      this.pageInfo.pages=articleData.pages
    }
    else {
      const resData={
        keyword:this.keyword,
        pageNum:this.pageInfo.pageNum,
        pageSize:this.pageInfo.pageSize
      }
      this.handleArticleList(resData)
      this.$store.dispatch('data/updateArticleData',resData)
    }
  },
  watch:{
    articleData:function (newData){
      this.article_list=newData.list
      this.pageInfo.pageSize=newData.pageSize
      this.pageInfo.pageNum=newData.pageNum
      this.pageInfo.total=newData.total
      this.pageInfo.pages=newData.pages
    }
  }
}
</script>

<style scoped>

.article{
  width:100%;
  background-color: white;
  margin: 0 0;
  border: 2px solid  #f5f6f7 ;
  padding: 5px 20px;
}
.articleDescribe{
  font-size: 14px;
  color: #555666;
  margin-top: 8px;
  margin-bottom: 8px;
  line-height: 22px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  max-height: 44px;
  word-break: break-word;
}
.articleName{
  width: 584px;
  font-size: 20px;
  font-weight: 700;
  line-height: 25px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.gohome{
  margin-top: 20px;
  height: 40px;
  text-align: center;
  padding: 0;
  border-bottom:5px solid #f5f6f7 ;
  border-radius: 5px ;
  line-height: 40px;
  background-color: white;
}
.gohome:hover{
  color: #409EFF;
  background-color: #edf3ff;
}
.sort{
  padding-left: 10px;
  background-color: white;
  height: 30px;
  line-height: 30px;
  margin: 10px 2px;
}
.sort li{
  float: left;
  list-style: none;
  height: 30px;
  line-height: 30px;
  width: 60px;
  text-align: center;
  margin-right: 25px;
  font-size:14px;

}
#result{
  float: right;
  width: auto;
  margin-right: 30px;
}
.sortli{
  color: #555666;
}

.sortli:hover{
  color: red;
  border-bottom: 2px solid red;
}
.isactive{
  color: red;
  border-bottom: 2px solid red;
}
</style>
