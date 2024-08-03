import { createStore } from 'vuex'

export default createStore({
  strict: true,
  state: {
    deviceInfoList: {} //機材一覧表示用
  },
  getters: {
  },
  mutations: {
    SET_DEVICE_INFO_LIST (state, deviceInfoList) {
      state.deviceInfoList = deviceInfoList
    },
    setDeviceId (state, deviceId) {
      state.deviceInfoList.deviceId = deviceId
    },
    setGenre(state, genre) {
      state.deviceInfoList.genre = genre
    },
    setUserEmployeeId(state, userEmployeeId) {
      state.deviceInfoList.userEmployeeId = userEmployeeId
    },
    setUserEmployeeName(state, userEmployeeName) {
      state.deviceInfoList.userEmployeeName = userEmployeeName
    },
    setManagerEmployeeId(state, managerEmployeeId) {
      state.deviceInfoList.managerEmployeeId = managerEmployeeId
    },
    setManagerEmployeeName(state, managerEmployeeName) {
      state.deviceInfoList.managerEmployeeName = managerEmployeeName
    },
    setManagerStatus(state, managerStatus) {
      state.deviceInfoList.managerStatus = managerStatus
    },
    setDeviceLocation(state, deviceLocation) {
      state.deviceInfoList.deviceLocation = deviceLocation
    },
  },
  actions: {
  },
  modules: {
  }
})
