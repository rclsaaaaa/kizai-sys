<template>
  <div class="equipment-management-system">
    <h1>機材管理システム</h1>
    <table>
      <thead>
        <tr>
          <th>機材ID</th>
          <th>種別</th>
          <th>使用者社員番号</th>
          <th>使用者名</th>
          <th>管理者社員番号</th>
          <th>管理者名</th>
          <th>管理状態</th>
          <th>管理場所</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="deviceList in displayedDeviceInfo" :key="deviceList.deviceId">
          <td>{{ deviceList.deviceId }}</td>
          <td>{{ deviceList.genre }}</td>
          <td>{{ deviceList.userEmployeeId }}</td>
          <td>{{ deviceList.userEmployeeName }}</td>
          <td>{{ deviceList.managerEmployeeId }}</td>
          <td>{{ deviceList.managerEmployeeName }}</td>
          <td>{{ deviceList.managerStatus }}</td>
          <td>{{ deviceList.deviceLocation }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

const URL = 'http://localhost:8080/device-info'

export default {
  name: 'HomeView',
  data () {
    return {
      displayedDeviceInfo: [],
      deviceInfoList: {},
      
       
    }
  },
  computed: {

  },
  mounted () {
    axios.get(URL)
      .then(response => {
        this.displayedDeviceInfo = response.data
        console.log('data recieved!')
        console.log('data =' + response.data.deviceId)
      })
      .catch(error => {
        console.error(error)
      })

  },
  method: {
    // selectDeviceInfoList(){
    //   axios.get(`${URL}`)
    //     .then(response => {
    //       this.selectDeviceInfoList = response.data.selectDeviceInfoList
    //     })
    //     .catch(error => {
    //       console.error(error)
    //     })
    // }
  }
}
</script>

<style scoped>
.equipment-management-system {
  padding: 20px;
  background-color: #f9f9f9;
}

h1 {
  text-align: center;
  color: #ff9933;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #ffcc99;
  color: white;
}
</style>
