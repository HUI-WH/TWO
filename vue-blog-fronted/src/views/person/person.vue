<template>
<el-row>
<!--  个人中心顶部菜单  -->
  <el-row class="infoHeader">
    <el-col :span="5" class="infoHeadActor" >
      <el-row type="flex"  justify="end">
        <el-row>
          <el-row>
            <el-avatar :size="78" :src="this.$store.getters.avatar"></el-avatar>
          </el-row>
          <el-row>
          <span style="width: 78px;text-align: center;display: inline-block;color: #f0f0f5;font-size: 13px;">码龄2年</span>
          </el-row>
        </el-row>
      </el-row>
    </el-col>
    <el-col :span="19" >
      <el-row style="height: 100%;width: 100%;padding-top: 40px;color: white">
        <h2>{{this.$store.getters.name}}</h2>
      </el-row>
    </el-col>
  </el-row>
<!--  个人中心主体  -->
  <el-row  style="width: 1200px;margin: 8px auto 8px auto;height: 500px;padding: 5px"  >
    <el-row >
      <!--   左侧菜单   -->
      <el-col :span="3" style="height: 200px;background-color: white" >
        <el-menu
          default-active="1"
          class="el-menu-vertical-demo">
          <router-link to="profile">
            <el-menu-item index="1">
                <span slot="title"  class="leftItem">个人资料</span>
            </el-menu-item>
          </router-link>
          <router-link to="/blog/own">
            <el-menu-item index="2">
                <span slot="title" class="leftItem">我的博客</span>
            </el-menu-item>
          </router-link>
          <el-menu-item index="3" >
            <span slot="title"  class="leftItem">我的粉丝</span>
          </el-menu-item>
          <el-menu-item index="4" >
            <span slot="title"  class="leftItem">我关注的人</span>
          </el-menu-item>
          <el-menu-item index="5">
            <span slot="title" class="leftItem">我的收藏</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <!--   右侧内容   -->
      <el-col :span="21"  style="padding:0 10px">
        <el-row style="background-color: white;height: 100%;padding: 5px">
          <router-view/>
        </el-row>
      </el-col>
    </el-row>


  </el-row>
</el-row>
</template>

<script>
export default {
name: "profile",
  data(){
    return{
      defaultImg:'http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/6c7b2f8d-9354-4fd3-a6a4-494eed0a3e08.jpg'
    }
  },
  created() {
    this.defaultImg = this.$store.getters.avatar
  }
}
</script>

<style scoped>
.infoHeader{
  height: 150px;
  background:url("http://gregorio-vblog.oss-cn-beijing.aliyuncs.com/img/5057c941-b603-4fb2-9592-93d8760ac318.png") no-repeat;
  color: white;
}
.infoHeadActor{
  padding: 35px 48px 0 48px;
}
.leftItem{
  display: inline-block;
  text-align: center;
  width: 100%;
}
</style>
