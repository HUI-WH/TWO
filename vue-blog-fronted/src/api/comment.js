import request from '@/utils/request'

export function createComment(data) {
  return request({
    url: '/comment/create',
    method: 'post',
    data
  })
}

export function deleteComment(data) {
  return request({
    url: '/comment/delete',
    method: 'post',
    params:data
  })
}
