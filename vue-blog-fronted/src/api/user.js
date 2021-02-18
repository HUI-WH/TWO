import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'get'
  })
}
export function getUserDetailInfo(id) {
  return request({
    url: '/user/detailInfo/'+id,
    method: 'get'
  })
}

export function getUserInfos() {
  return request({
    url: '/user/infos',
    method: 'get'
  })
}

export function updateUserInfo(data){
  return request(({
    url:'/user/update',
    method:'post',
    data
  }))

}

