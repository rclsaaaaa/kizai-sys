import { createStore } from 'vuex'

export default createStore({
  strict: true,
  state: {
    deviceInfoDetail: {}, //機材一覧表示用
    authUserEmployeeId: ''
  },
  getters: {
  },
  mutations: {
    SET_DEVICE_INFO_DETAIL (state, deviceInfoDetail) {
      state.deviceInfoDetail = deviceInfoDetail
    },
    setDeviceId (state, deviceId) {
      state.deviceInfoDetail.deviceId = deviceId
    },
    setGenre(state, genre) {
      state.deviceInfoDetail.genre = genre
    },
    setUserEmployeeId(state, userEmployeeId) {
      state.deviceInfoDetail.userEmployeeId = userEmployeeId
    },
    setUserEmployeeName(state, userEmployeeName) {
      state.deviceInfoDetail.userEmployeeName = userEmployeeName
    },
    setManagerEmployeeId(state, managerEmployeeId) {
      state.deviceInfoDetail.managerEmployeeId = managerEmployeeId
    },
    setManagerEmployeeName(state, managerEmployeeName) {
      state.deviceInfoDetail.managerEmployeeName = managerEmployeeName
    },
    setManagerStatus(state, managerStatus) {
      state.deviceInfoDetail.managerStatus = managerStatus
    },
    setDeviceLocation(state, deviceLocation) {
      state.deviceInfoDetail.deviceLocation = deviceLocation
    },
    setDeviceManufacturer(state, deviceManufacturer) {
      state.deviceInfoDetail.deviceManufacturer = deviceManufacturer
    },
    setDeviceModel(state, deviceModel) {
      state.deviceInfoDetail.deviceModel = deviceModel
    },
    setMemory(state, memory) {
      state.deviceInfoDetail.memory = memory
    },
    setCpu(state, cpu) {
      state.deviceInfoDetail.cpu = cpu
    },
    setOs(state, os) {
      state.deviceInfoDetail.os = os
    },
    setMouseFlag(state, mouseFlag) {
      state.deviceInfoDetail.mouseFlag = mouseFlag
    },
    setSecurityWireFlag(state, securityWireFlag) {
      state.deviceInfoDetail.securityWireFlag = securityWireFlag
    },
    setEncryptionFlag(state, encryptionFlag) {
      state.deviceInfoDetail.encryptionFlag = encryptionFlag
    },
    setAuthUserEmployeeId(state, authUserEmployeeId){
      state.authUserEmployeeId = authUserEmployeeId
    }
  },
  actions: {
  },
  modules: {
  }
})
