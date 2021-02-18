<template>
   <div >
     首页
   </div>
</template>

<script>
import { mapGetters } from 'vuex'
import {getArticleList} from "@/api/article";
export default {
  name: 'Dashboard',
  data(){
    return{
      id:1,
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
        pageSize: 3,
        pageNum: 1
      }
    }
  },
  methods:{

  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  created() {
  },
  components:{
  },
}
</script>

<style scoped>

</style>
