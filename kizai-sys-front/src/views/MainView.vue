<template>
  <div class="device-info-list">
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
        <tr v-for="deviceInfoList in displayedDeviceInfoList" :key="deviceInfoList.deviceId">
          <td><a href="#" class="detail-Btn" value="詳細" @click="openDeviceInfoDetail(deviceInfoList.deviceId)">{{ deviceInfoList.deviceId }}</a></td>
          <td>{{ deviceInfoList.genre }}</td>
          <td>{{ deviceInfoList.userEmployeeId }}</td>
          <td>{{ deviceInfoList.userEmployeeName }}</td>
          <td>{{ deviceInfoList.managerEmployeeId }}</td>
          <td>{{ deviceInfoList.managerEmployeeName }}</td>
          <td>{{ deviceInfoList.managerStatus }}</td>
          <td>{{ deviceInfoList.deviceLocation }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div v-if="isDetailModalOpen" class="detail-modal" @click="closeDetailModa">
    <div class="modal-content" @click.stop>
      <span class="close" @click="closeDetailModal">&times;</span>
      <div class="modal-line">
        <hr size="3" color="#bfb391" width="150">
      </div>
      <div class="modaltitle">
        <h2>ユーザー情報詳細</h2>
      </div>
      <div class="modal-line2">
        <hr size="3" color="#bfb391" width="150">
      </div>
      <div class="user-detail">
        <table class="detail-modal-table">
          <tr>
            <th class="detail-userid-title">機材ID</th>
            <td class="detail-userid-deta">{{ deviceInfoDetail.deviceId }}</td>
          </tr>
          <tr>
            <th class="detail-name-title">種別</th>
            <td class="detail-name-deta">{{ deviceInfoDetail.genre }}</td>
          </tr>
          <tr>
            <th class="detail-department-title">使用者社員番号</th>
            <td class="detail-department-deta">{{ deviceInfoDetail.userEmployeeId }}</td>
          </tr>
          <tr>
            <th class="detail-employee-num-title">使用者名</th>
            <td class="detail-employee-num-deta">{{ deviceInfoDetail.userEmployeeName }}</td>
          </tr>
          <tr>
            <th class="detail-skill-title">管理者社員番号</th>
            <td class="detail-skill-deta">{{ deviceInfoDetail.managerEmployeeId }}</td>
          </tr>
          <tr>
            <th class="detail-others-title">管理者名</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.managerEmployeeName }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">管理状態</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.managerStatus }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">設置場所</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.deviceLocation }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">メーカー</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.deviceManufacturer }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">機種</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.deviceModel }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">メモリ</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.memory }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">CPU</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.cpu }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">OS</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.os }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">マウス</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.mouseFlag }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">セキュリティワイヤー</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.securityWireFlag }}<br></td>
          </tr>
          <tr>
            <th class="detail-others-title">暗号化</th>
            <td class="detail-others-deta">{{ deviceInfoDetail.encryptionFlag }}<br></td>
          </tr>
        </table>
      </div>
      <input type="submit" class="edit-Btn" value="編集" @click="goToUserEdit">
    </div>
  </div>
</template>

<script>
import axios from 'axios';

const URL = 'http://localhost:18080/device-info'

export default {
  name: 'MainView',
  
  data () {
    return {
      displayedDeviceInfoList: [],
      isDetailModalOpen: false,
      deviceInfoDetail: {}
      
       
    }
  },
  computed: {

  },
  mounted () {
    axios.get(URL)
      .then(response => {
        this.displayedDeviceInfoList = response.data
      })
      .catch(error => {
        console.error(error)
      })

  },
  methods: {
    openModal () {
      this.isModalOpen = true
    },
    closeModal () {
      this.isModalOpen = false
    },
    openDeviceInfoDetail (deviceId) {
      axios.get(`${URL}/${deviceId}`)
        .then(response => {
          this.deviceInfoDetail = response.data
          this.isDetailModalOpen = true
          console.log(response.data)
          console.log(this.deviceInfoDetail)
        })
        .catch(error => {
          console.error(error)
        })
    },
    goToUserEdit () {
      this.$store.commit('SET_USER_DETAILS', this.userDetails)
      this.$router.push('/UserEdit')
    },
    closeDetailModal () {
      this.isDetailModalOpen = false
    },
  }
}
</script>

<style scoped>

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

td {
  background-color: #fadec3;
  color: rgb(99, 98, 98);
}

.modaltitle {
  text-align: center;
  color: #bfb391;
  position: absolute;
  top: 11%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.modal-line {
  position: absolute;
  top: 48px;
  left: 145px;
  top: 11%;
  left: 27%;
  transform: translate(-50%, -50%);
}
.modal-line2 {
  position: absolute;
  top: 48px;
  left: 515px;
  color: #bfb391;
  top: 11%;
  left: 73%;
  transform: translate(-50%, -50%);
}
.detail-modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
}
.detail-modal-table {
  position: absolute;
  width: 60%;
  height: 55%;
  top: 45%;
  left: 45%;
  transform: translate(-50%, -50%);
}
.detail-modal-table th {
  position: absolute;
  width: 40%;
  left: 8%;
  padding: 11px;
  border-radius: 10px;
  z-index: 1;
  border: 2px solid #fff;
  font-size: 20px;
  font-weight: bold;
  background-color: #efdeb1ff;
  color: #fff;
  text-align: center;
}
.detail-modal-table td {
  background-color: #fcf4ddff;
  border: 2px solid #fff;
  font-weight: bold;
  position: absolute;
  width: 50%;
  height: 8%;
  left: 45%;
  padding: 15px 15px 15px 50px;
}
.edit-Btn {
  position: absolute;
  top: 88%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 20px;
  font-weight: bold;
  color: #fff;
  background-color: #efdeb1ff;
  border: none;
  border-radius: 8px;
  padding: 7px 35px;
  cursor: pointer;
}
.close {
  color: #bfb391;
  font-size: 50px;
  font-weight: bold;
  cursor: pointer;
  position: absolute;
  top: 8%;
  left: 93%;
  transform: translate(-50%, -50%);
}
.close:hover,
.close:focus {
  color: #aaa;
  text-decoration: none;
  cursor: pointer;
}
</style>
