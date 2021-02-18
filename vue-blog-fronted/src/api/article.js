import request from '@/utils/request'

export function getArticleList(data) {
  return request({
    url: '/article/list',
    method: 'post',
    params:data
  })
}


export function createArticle(data) {
  return request({
    url: '/article/create',
    method: 'post',
    data
  })
}
export function getArticle(id) {
  return request({
    url: '/article/get/'+id,
    method: 'get'
  })
}

export function getBlogClassList() {
  return request({
    url: '/blogClass/list',
    method: 'get'
  })
}




