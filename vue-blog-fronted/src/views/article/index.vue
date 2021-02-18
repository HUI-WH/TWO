<template xmlns:el-col="http://www.w3.org/1999/html">
  <div style="height: 100%">
    <el-row :gutter="20" style="background-color: #99a9bf;padding-top: 20px;min-height: 600px">
      <el-col :span="4" :offset="1" style="margin-right: 20px">
        <el-row class="leftOption">
          <el-row>
            <el-col :span="5" :offset="1">
              <el-avatar :size="60" :src="UserDetailInfo.icon" ></el-avatar>
            </el-col>
            <el-col :span="8" :offset="3">
              <el-row class="username">
                {{ UserDetailInfo.name }}
              </el-row>
              <el-row class="blogAge">
                码龄{{UserDetailInfo.blogAge}}年
              </el-row>
            </el-col>
          </el-row>
          <el-row style="padding: 5px">
            <el-col :span="6">
              <el-row>
                <span class="infoNum">{{UserDetailInfo.fansNum}}</span>
              </el-row>
              <el-row>
                <span class="infoTitle">粉丝</span>
              </el-row>
            </el-col>
            <el-col :span="6">
              <el-row>
                <span class="infoNum">{{UserDetailInfo.articleNum}}</span>
              </el-row>
              <el-row>
                <span class="infoTitle">原创</span>
              </el-row>
            </el-col>
            <el-col :span="6">
              <el-row>
                <span class="infoNum">{{UserDetailInfo.readingNum}}</span>
              </el-row>
              <el-row>
                <span class="infoTitle">访问</span>
              </el-row>
            </el-col>
            <el-col :span="6">
              <el-row>
                <span class="infoNum">{{UserDetailInfo.followNum}}</span>
              </el-row>
              <el-row>
                <span class="infoTitle">关注</span>
              </el-row>
            </el-col>
          </el-row>
          <el-row style="height: 3px">
            <hr style="border:0;background-color:#f5f6f7;height:1px;"/>
          </el-row>
          <el-row style="padding: 5px">
            <el-col :span="6">
              <el-row>
                <span class="infoNum">{{UserDetailInfo.likesNum}}</span>
              </el-row>
              <el-row>
                <span class="infoTitle">获赞</span>
              </el-row>
            </el-col>
            <el-col :span="6">
              <el-row>
                <span class="infoNum">{{UserDetailInfo.commentNum}}</span>
              </el-row>
              <el-row>
                <span class="infoTitle">评论</span>
              </el-row>
            </el-col>
            <el-col :span="6">
              <el-row>
                <span class="infoNum">{{UserDetailInfo.collectionNum}}</span>
              </el-row>
              <el-row>
                <span class="infoTitle">收藏</span>
              </el-row>
            </el-col>
          </el-row>
          <el-row style="padding: 10px">
            <el-button size="small ">他的主页</el-button>
            <el-button type="danger" size="small">关注</el-button>
          </el-row>
        </el-row>
        <el-row class="leftOption">
          <el-input placeholder="搜索博主文章" v-model="searchInfo.keyword" class="input-with-select" size="small"  >
            <el-button slot="append" icon="el-icon-search" @click="blogSearch"></el-button>
          </el-input>
        </el-row>
        <el-row class="leftOption">
          目录
        </el-row>
        <el-row class="leftOption">
          最新文章
        </el-row>
        <el-row class="leftOption">
          热门文章
        </el-row>
        <el-row class="leftOption">
          最新评论
        </el-row>
      </el-col>
      <el-col :span="17" style="background-color: white">
        <el-row style="padding: 10px 30px 0 30px">
          <el-row>
            <h1>{{this.article.title}}</h1>
          </el-row>
          <el-row class="describe">

            <el-row>
              <el-col :span="2" style="color: #5893c2;">
                <router-link to="">{{article.authorName}}</router-link>
              </el-col>
              <el-col :span="4" class="describeItem">{{this.formatTimes}}</el-col>
              <template v-if="article.articleinfo">
                <el-col :span="3" class="describeItem"><i class="el-icon-view"></i> 阅读量：{{article.articleinfo.readings}}</el-col>
                <el-col :span="2" class="describeItem"><i class="el-icon-star-off"></i> 收藏：{{article.articleinfo.collections}}</el-col>
              </template>
              </el-row>
            <el-row style="margin-top: 5px">
              <el-col :span="4" :offset="2" class="describeItem">
                分类专栏:
                <template v-for="label in article.labels">
                  <span>
                  <el-button type="mini" style="color: #5094d5;">{{label.labelName}}</el-button>
                  </span>
                </template>
              </el-col>
            </el-row>
          </el-row>
        </el-row>
        <el-row>
          <el-col>
            <div>
              <div v-html="html" style="margin-top: 50px;margin-left: 50px" class="markdown-body"></div>
            </div>
          </el-col>
        </el-row>
        <el-row style="height: 5px;background-color: rgb(153, 169, 191);margin: 0 -10px" ></el-row>
<!--  发表评论-->
        <template v-if="isLogin">
          <el-row style="padding: 10px;height: 50px" :gutter="20">
            <el-col :span="1">
              <el-avatar shape="square" :size="40" :src="this.$store.getters.avatar"></el-avatar>
            </el-col>
            <el-col :span="20">
              <el-input v-model="commentInfo.content" ref="comment" :placeholder="inputPlaceholder"></el-input>
            </el-col>
            <el-col :span="2">
              <el-button @click="pinglun">评论 </el-button>
            </el-col>
          </el-row>
        </template>
        <template v-else>
          <el-row style="padding: 10px;height: 50px" :gutter="20">
            <el-col :span="1">
              <el-avatar shape="square" :size="40" ></el-avatar>
            </el-col>
            <el-col :span="20">
              <el-input v-model="comment" placeholder="登录后可已发表评论" disabled></el-input>
            </el-col>
            <el-col :span="2">
              <el-button disabled>评论 </el-button>
            </el-col>
          </el-row>
        </template>

<!--  评论-->
        <el-row style="padding: 20px 20px">
          <template v-if="article.detailTreeDto">

          </template>
          <template v-for="comment in article.detailTreeDto">
            <el-row>
              <el-row class="parentComment">
                <el-col :span="20">
                  <el-avatar shape="square" :size="30" :src="comment.info.uicon"></el-avatar>
                  <span class="CommentName">{{comment.info.uname}}：</span>
                  <span class="commentContent">{{comment.info.content}}</span>
                  <span class="afterTime">{{ comment.info.afterTime }}</span>
                  <span class="huifu" @click="huifu(comment.id,comment.info.uname)"><a href="javascript:;">回复</a></span>
                  <template v-if="uid===comment.info.uid">
                    <a href="javascript:;" @click="deleteComment(comment.id)"><span class="afterTime"><i class="el-icon-delete-solid"></i></span></a>
                  </template>
                </el-col>
                <el-col :span="1" :offset="2">
                  <a href="javascript:;">
                    <i class="el-icon-thumb"></i>
                    <template v-if="comment.info.likes!==0">
                      {{comment.info.likes}}
                    </template>
                  </a>
                </el-col>
              </el-row>

              <template v-for="comm in comment.children">
                <el-row class="childComment">
                  <el-col :span="20">
                    <el-avatar shape="square" :size="30" :src="comm.uicon"></el-avatar>
                    <span class="CommentName">{{ comm.uname }}</span>
                    <span class="reSpeak">回复</span>
                    <template v-if="comm.pid!==comment.id">
                      <span class="CommentName">{{comm.puname}}</span>
                    </template>
                    <span class="CommentName">:</span>
                    <span class="commentContent">{{comm.content}}</span>
                    <span class="afterTime">{{comm.afterTime}}</span>
                    <span class="huifu" @click="huifu(comm.id,comm.uname)"><a href="javascript:;">回复</a></span>
                    <template v-if="uid===comm.uid">
                      <a href="javascript:;" @click="deleteComment(comm.id)"><span class="afterTime"><i class="el-icon-delete-solid"></i></span></a>
                    </template>
                  </el-col>
                  <el-col :span="1" :offset="2">
                    <a href="javascript:;">
                      <i class="el-icon-thumb"></i>
                      <template v-if="comment.info.likes!==0">
                        {{comm.likes}}
                      </template>
                    </a>
                  </el-col>
                </el-row>
              </template>
            </el-row>
          </template>
        </el-row>
        <el-row style="height: 10px;background-color: rgb(153, 169, 191);margin: 0 -10px" ></el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getArticle, getArticleList} from "@/api/article";
import marked from 'marked'
import {formatDate} from "@/api/time"
import {getUserDetailInfo} from "@/api/user";
import {getToken} from "@/utils/auth";
import {createComment, deleteComment} from "@/api/comment";

export default {
  name: "art",
  data(){
    return{
      commentPid:0,
      comment:'',
      isLogin:false,
      id:0,
      uid:0,
      article:{},
      md:'',
      html:'',
      UserDetailInfo:{},
      inputPlaceholder:'发表评论',
      commentInfo:{
        pid:0,
        content:''
      },
      searchInfo:{
        authorId:null,
        keyword:''
      }
    }
  },
  computed:{
    formatTimes(){
      var date = new Date(this.article.createTime);
      return formatDate(date,'yyyy-MM-dd  hh:mm:ss')
    }
  },
  methods:{
    deleteComment(cid){
      const data={
        aid:this.id,
        cid:cid,
        uid:this.uid
      }
      deleteComment(data).then(resp=>{
        this.$message.success("评论删除成功")
        this.handleArticle()
      }).catch(err=>{
        this.$message.error("评论删除失败")
      })
    },
    blogSearch(){
      this.$store.dispatch("data/updateArticleData",this.searchInfo)
      this.$router.push({path:"/search/article"})
    },
    pinglun(){
      const res={
        uid:this.uid,
        articleScore:0,
        likes:0,
        content:this.commentInfo.content,
        aid:this.id,
        pid:this.commentInfo.pid
      }

      if(this.isLogin){
        createComment(res).then(res=>{
          this.$message.success("评论成功")
          this.handleArticle()
          this.commentInfo.content=''
          this.commentInfo.pid=0
          this.inputPlaceholder="发表评论"
        }).catch(err=>{
          this.$message.error("error")
          this.commentInfo.contentt=''
          this.commentInfo.pid=0
          this.inputPlaceholder="发表评论"
        })
      }
    },
    huifu(pid,pname){
      this.$refs.comment.focus()
      this.commentInfo.pid=pid
      this.inputPlaceholder="回复："+pname

    },
    handleArticle(){
      this.id=this.$route.query.id
      getArticle(this.id).then(result=>{
        var res = result.data
        this.article=res
        this.md=res.content
        this.html = marked(res.content)
        this.searchInfo.authorId=res.authorId
        // console.log(res)
        this.getUserDetailInfo(this.article.authorId)
      })
    },
    formatT(time){
      var date = new Date(time);
      console.log(formatDate(date, 'yyyy-MM-dd  hh:mm:ss'))
    },
    getUserDetailInfo(id){
      getUserDetailInfo(id).then(result=>{
        this.UserDetailInfo = result.data

      })
    }
  },
  created() {
    const hasToken = getToken()
    if(hasToken){
      this.isLogin=true
      this.uid=this.$store.getters.uid
    }
    this.handleArticle()


  },
  mounted() {
    const link = document.createElement('link')
    link.type = 'text/css'
    link.rel = 'stylesheet'
    link.href = 'https://cdn.bootcss.com/github-markdown-css/2.10.0/github-markdown.min.css'
    document.head.appendChild(link)
  }
}
</script>

<style scoped>
.leftOption{
  background-color: white;
  margin-bottom: 10px;
  padding: 5px;
}
.username{
  padding: 5px;
  width: 100%;
  font-size: 14px;
  font-weight: 500;
  height: 20px;
  line-height: 20px;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.blogAge{
  padding: 5px;
  color: #999aaa;
  font-size: 13px;
  line-height: 20px;
  height: 30px;
  overflow: hidden
}
.infoTitle{
  display: block;
  color: #4a4d52;
  font-size: 14px;
  font-weight: 500;
  line-height: 22px;
  text-align: center;
}
.infoNum{
  display: block;
  color: #999aaa;
  font-size: 14px;
  line-height: 22px;
  padding: 3px 0;
  width: 100%;
  text-align: center;
}
.describe{
  background: #f7f7fc;
  border-radius: 4px;
  padding: 10px;
}
.describeItem{
  color: #999aaa;
  font-weight: normal;
  font-size: 14px;
}
.parentComment{
  height: 30px;
  margin-left: 5px;
}
.childComment{
  height: 30px;
  margin:5px 0 5px 30px;
  padding-left: 10px;
  border-left: 2px solid #f5f6f7;
}
.CommentName{
  display: inline-block;
  font-size: 15px;
  font-weight: 400;
  color: #555666;
  height: 30px;
  line-height: 30px;
  padding-left: 5px;
  vertical-align: top;

}
.commentContent{
  display: inline-block;
  height: 30px;
  vertical-align: top;
  line-height: 30px;
  color: #222226;
  word-break: break-all;
  font-size: 14px;
}
.afterTime{
  display: inline-block;
  height: 30px;
  vertical-align: top;
  line-height: 30px;
  font-size: 12px;
  color: #999aaa;
  margin-left: 10px;
}
.huifu{
  display: inline-block;
  height: 30px;
  vertical-align: top;
  line-height: 30px;
  color: #5893c2;
  padding: 0 4px;
  font-size: 14px;
}
.reSpeak{
  display: inline-block;
  height: 30px;
  vertical-align: top;
  line-height: 30px;
  color: #999;
  padding: 0 4px;
  font-size: 12px;
}
</style>
