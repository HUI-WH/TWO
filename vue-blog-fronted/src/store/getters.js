const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  keyword:state=>state.data.keyword,
  articleData:state=>state.data.articleData,
  uid:state=>state.user.id
}
export default getters
