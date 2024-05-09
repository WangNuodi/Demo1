import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询用户列表
export function listUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}

// 查询交易单列表
export function list(query) {
  return request({
    url: '/system/order/list',
    method: 'get',
    params: query
  })
}

// 修改订单所需
export function getListOne(orderId) {
  return request({
    url: '/system/order/list?orderId=' + orderId,
    method: 'get',
    params: orderId
  })
}

// 查询用户详细
export function getUser(userId) {
  return request({
    url: '/system/user/' + parseStrEmpty(userId),
    method: 'get'
  })
}


// 查询订单详细
export function getOrder(orderId) {
  return request({
    url: '/system/order/getOrder' + parseStrEmpty(orderId),
    method: 'get'
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user',
    method: 'post',
    data: data
  })
}

// 新增订单
export function addOrder(data) {
  return request({
    url: '/system/order',
    method: 'post',
    data: data
  })
}
// 修改用户
export function updateUser(data) {
  return request({
    url: '/system/user',
    method: 'put',
    data: data
  })
}
// 修改订单
export function updateOrder(data) {
  return request({
    url: '/system/order',
    method: 'put',
    data: data
  })
}
// 修改交易单状态
export function changeOrderStatus(orderId,status) {
  const data = {
    orderId,
    status
  }
  return request({
    url: '/system/order',
    method: 'put',
    data: data
  })
}
// 修改是否收到付款
export function changeOrderrecive(orderId,recive) {
  const data = {
    orderId,
    recive
  }
  return request({
    url: '/system/order',
    method: 'put',
    data: data
  })
}
// 修改是否发货
export function changeOrdersend(orderId,send) {
  const data = {
    orderId,
    send
  }
  return request({
    url: '/system/order',
    method: 'put',
    data: data
  })
}
// 修改是否收货
export function changeOrderdefine(orderId,define) {
  const data = {
    orderId,
    define
  }
  return request({
    url: '/system/order',
    method: 'put',
    data: data
  })
}

// export function changeOrderrecive(orderId,receive) {
//   const data = {
//     orderId,
//     status
//   }
//   return request({
//     url: '/system/order',
//     method: 'put',
//     data: data
//   })
// }
// 删除用户
export function delUser(userId) {
  return request({
    url: '/system/user/' + userId,
    method: 'delete'
  })
}
// 删除订单
export function delOrder(userId) {
  return request({
    url: '/system/order/' + userId,
    method: 'delete'
  })
}

// 用户密码重置
export function resetUserPwd(userId, password) {
  const data = {
    userId,
    password
  }
  return request({
    url: '/system/user/resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/system/user/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/user/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/system/user/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}

// 查询授权角色
export function getAuthRole(userId) {
  return request({
    url: '/system/user/authRole/' + userId,
    method: 'get'
  })
}

// 保存授权角色
export function updateAuthRole(data) {
  return request({
    url: '/system/user/authRole',
    method: 'put',
    params: data
  })
}

// 查询部门下拉树结构
export function deptTreeSelect() {
  return request({
    url: '/system/user/deptTree',
    method: 'get'
  })
}
