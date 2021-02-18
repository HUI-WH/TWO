import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import {getArticleList} from "@/api/article";

const getDefaultState = () => {
  return {
    keyword:'',
    articleData:{}
  }
}

const state = getDefaultState()

const mutations = {
  SET_KEYWORD: (state, keyword) => {
    state.keyword = keyword
  },
  SET_ARTICLEDATA: (state, articleData) => {
    state.articleData = articleData
  }
}

const actions = {
  updateArticleData({ commit }, data){
    getArticleList(data).then(result=>{
      commit('SET_ARTICLEDATA',result.data)
      console.log("ArticleData")
      console.log(result.data)
    })
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

