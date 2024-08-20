<template>
  <div class="app">
    <header>
      <div class="search">
        <input type="text" placeholder="検索">
        <button @click="goToLoginPage">ログイン</button>
      </div>
      <nav>
        <a href="#" @click="openInsertDeviceInfoModal">機材情報登録  </a>
        <a href="#" @click="openUpdateUserInfoModal">個人情報編集  </a>
        <a href="#" @click="goToPasswordResetPage">パスワード変更  </a>
        <a href="#" @click="openLogoutModal">ログアウト</a>
      </nav>
    </header>
    <main>
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
              <td><a href="#" class="detail-Btn" value="詳細" @click="openDeviceInfoDetailModal(deviceInfoList.deviceId)">{{ deviceInfoList.deviceId }}</a></td>
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
      <!-- 機材情報詳細モーダル -->
      <div v-if="isDeviceInfoDetailModalOpen" class="detail-modal" @click="closeDeviceInfoDetailModal">
        <div class="modal-content" @click.stop>
          <span class="close" @click="closeDeviceInfoDetailModal">&times;</span>
          <div class="modaltitle">
            <h2>機材情報詳細</h2>
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
          <input type="submit" class="edit-Btn" value="編集" @click="openUpdateDeviceInfolModal">
        </div>
      </div>
      <!-- 機材情報編集モーダル -->
      <div v-if="isUpdateDeviceInfoModalOpen" class="edit-modal" @click="closeUpdateDeviceInfoModal">
        <div class="modal-content" @click.stop>
          <span class="close" @click="closeUpdateDeviceInfoModal">&times;</span>
          <div class="modaltitle">
            <h2>機材情報編集</h2>
          </div>
          <div class="user-edit">
            <!-- 編集フォーム内容 -->
            <table class="edit-modal-table">
              <tr>
                <th class="edit-userid-title">機材ID</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.deviceId"/></td>
              </tr>
              <tr>
                <th class="edit-name-title">種別</th>
                <td class="edit-name-deta"><input v-model="deviceInfoDetail.genre" /></td>
              </tr>
              <tr>
                <th class="edit-userid-title">使用者社員番号</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.userEmployeeId"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">使用者名</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.userEmployeeName"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">管理者社員番号</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.managerEmployeeId"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">管理者名</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.managerEmployeeName"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">管理状態</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.managerStatus"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">設置場所</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.deviceLocation"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">メーカー</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.deviceManufacturer"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">機種</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.deviceModel"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">メモリ</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.memory"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">CPU</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.cpu"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">OS</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.os"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">マウス</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.mouseFlag"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">セキュリティワイヤー</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.securityWireFlag"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">暗号化</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.encryptionFlag"/></td>
              </tr>
            </table>
            <input type="submit" class="save-Btn" value="保存" @click="updateDeviceInfo">
          </div>
        </div>
      </div>

      <!-- 機材情報登録モーダル -->
      <div v-if="isInsertDeviceInfoModalOpen" class="edit-modal" @click="closeInsertDeviceInfoModal">
        <div class="modal-content" @click.stop>
          <span class="close" @click="closeInsertDeviceInfoModal">&times;</span>
          <div class="modaltitle">
            <h2>機材情報登録</h2>
          </div>
          <div class="user-edit">
            <!-- 編集フォーム内容 -->
            <table class="edit-modal-table">
              <tr>
                <th class="edit-userid-title">機材ID</th>
                <td class="edit-userid-deta"><input/></td>
              </tr>
              <tr>
                <th class="edit-name-title">種別</th>
                <td class="edit-name-deta"><input v-model="deviceInfoDetail.genre" /></td>
              </tr>
              <tr>
                <th class="edit-userid-title">使用者社員番号</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.userEmployeeId"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">使用者名</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.userEmployeeName"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">管理者社員番号</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.managerEmployeeId"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">管理者名</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.managerEmployeeName"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">管理状態</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.managerStatus"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">設置場所</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.deviceLocation"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">メーカー</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.deviceManufacturer"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">機種</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.deviceModel"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">メモリ</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.memory"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">CPU</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.cpu"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">OS</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.os"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">マウス</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.mouseFlag"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">セキュリティワイヤー</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.securityWireFlag"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">暗号化</th>
                <td class="edit-userid-deta"><input v-model="deviceInfoDetail.encryptionFlag"/></td>
              </tr>
            </table>
            <input type="submit" class="save-Btn" value="保存" @click="updateDeviceInfo">
          </div>
        </div>
      </div>

      <!-- 個人情報登録モーダル -->
      <div v-if="isUpdateUserInfoModalOpen" class="edit-modal" @click="closeUpdateUserInfoModal">
        <div class="modal-content" @click.stop>
          <span class="close" @click="closeUpdateUserInfoModal">&times;</span>
          <div class="modaltitle">
            <h2>個人情報編集</h2>
          </div>
          <div class="user-edit">
            <!-- 編集フォーム内容 -->
            <table class="edit-modal-table">
              <tr>
                <th class="edit-userid-title">社員番号</th>
                <td class="edit-userid-deta"><input v-model="employeeId"/></td>
              </tr>
              <tr>
                <th class="edit-name-title">名前</th>
                <td class="edit-name-deta"><input v-model="employeeName" /></td>
              </tr>
              <tr>
                <th class="edit-userid-title">メールアドレス</th>
                <td class="edit-userid-deta"><input v-model="employeeAdress"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">所属部署</th>
                <td class="edit-userid-deta"><input v-model="employeeDepartment"/></td>
              </tr>
              <tr>
                <th class="edit-userid-title">所属PJ</th>
                <td class="edit-userid-deta"><input v-model="employeePj"/></td>
              </tr>
            </table>
            <input type="submit" class="save-Btn" value="保存" @click="updateUserInfo">
          </div>
        </div>
      </div>

      <!-- ログアウトモーダル -->
      <div v-if="isLogoutModalOpen" class="edit-modal" @click="closeLogoutModal">
        <div class="modal-content" @click.stop>
          <span class="close" @click="closeLogoutModal">&times;</span>
          <div class="modaltitle">
            <h2>個人情報編集</h2>
          </div>
          <div class="user-edit">
            
          </div>
        </div>
      </div>

      <nav class="pagination">
        <button @click="prevPage">◀</button>
        <span v-for="page in pageCount" :key="page" @click="setPage(page)">{{ page }}</span>
        <button @click="nextPage">▶</button>
      </nav>
    </main>
  </div>
</template>

<script>
import axios from 'axios';

const URL = 'http://localhost:18080/device-info'

export default {
  data() {
    return {
      equipments: [
      ],
      currentPage: 1,
      itemsPerPage: 5, // 1ページに表示するアイテム数
      displayedDeviceInfoList: [],
      isDeviceInfoDetailModalOpen: false,
      deviceInfoDetail: {},
      isUpdateDeviceInfoModalOpen: false,
      isInsertDeviceInfoModalOpen: false,
      isUpdateUserInfoModalOpen: false,
      isLogoutModalOpen: false,
      csrfToken: '',
      employeeId: '',
      employeeName:'',
      employeeAdress:'',
      employeeDepartment:'',
      employeePj:''
      
    }
  },
  computed: {
    pageCount() {
      return Math.ceil(this.equipments.length / this.itemsPerPage)
    },
    pagedEquipments() {
      const start = (this.currentPage - 1) * this.itemsPerPage
      const end = start + this.itemsPerPage
      return this.equipments.slice(start, end)
    }
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
    getCSRFToken(){


    },
    totalPages () {
      return Math.ceil(this.displayedEmployees.length / this.itemsPerPage)
    },
    paginatedItems () {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage
      const endIndex = startIndex + this.itemsPerPage
      return this.displayedEmployees.slice(startIndex, endIndex).map(employee => ({ ...employee }))
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--
      }
    },
    nextPage() {
      if (this.currentPage < this.pageCount) {
        this.currentPage++
      }
    },
    setPage(page) {
      this.currentPage = page
    },
    openModal () {
      this.isModalOpen = true
    },
    closeModal () {
      this.isModalOpen = false
    },

    //機材情報詳細モーダル
    openDeviceInfoDetailModal(deviceId){
      this.isDeviceInfoDetailModalOpen = true
      this.selectDeviceInfoDetail(deviceId)
    },
    selectDeviceInfoDetail (deviceId) {
      axios.get(`${URL}/${deviceId}`)
        .then(response => {
          this.deviceInfoDetail = response.data
          
          console.log(response.data)
          console.log(this.deviceInfoDetail)
        })
        .catch(error => {
          console.error(error)
        })
    },
    closeDeviceInfoDetailModal () {
      this.isDeviceInfoDetailModalOpen = false
    },


    //機材情報編集モーダル
    openUpdateDeviceInfolModal() {
      this.isDeviceDetailModalOpen = false;
      this.isUpdateDeviceInfoModalOpen = true;
      this.updateDeviceInfo
    },
    closeUpdateDeviceInfoModal() {
      this.isUpdateDeviceInfoModalOpen = false;
    },

    updateDeviceInfo() {

      const formData = {
        deviceId: this.deviceInfoDetail.deviceId,
        genre: this.deviceInfoDetail.genre,
        userEmployeeId: this.deviceInfoDetail.userEmployeeId,
        userEmployeeName: this.deviceInfoDetail.userEmployeeName,
        managerEmployeeId: this.deviceInfoDetail.managerEmployeeId,
        managerEmployeeName: this.deviceInfoDetail.managerEmployeeName,
        managerStatus: this.deviceInfoDetail.managerStatus,
        deviceLocation: this.deviceInfoDetail.deviceLocation,
        deviceManufacturer: this.deviceInfoDetail.deviceManufacturer,
        deviceModel: this.deviceInfoDetail.deviceModel,
        memory: this.deviceInfoDetail.memory,
        cpu: this.deviceInfoDetail.cpu,
        os: this.deviceInfoDetail.os,
        mouseFlag: this.deviceInfoDetail.mouseFlag,
        securityWireFlag: this.deviceInfoDetail.securityWireFlag,
        encryptionFlag: this.deviceInfoDetail.encryptionFlag
      };

      const URL2 = 'http://localhost:18080/api/csrf'

      axios.get(URL2)
        .then(response => {
          this.csrfToken = response.data.token
          console.log('csrfトークン取得')
          console.log(this.csrfToken)
        })
        .catch(error => {
          console.error(error)
        })

      // const axiosPost = axios.create({
      //   xsrfHeaderName: 'X-CSRF-Token',
      //   withXSRFToken: this.csrfToken
      // })

      axios.post(`${URL}/update/${this.deviceInfoDetail.deviceId}`,formData)
        .then(response => {
          alert('機材情報が更新されました。');
          console.log(response.data)
          this.isEditModalOpen = false;
        })
        .catch(error => {
          console.error(error);
          console.error('更新失敗');
        });
    },

    // goToUserEdit () {
    //   this.$store.commit('SET_USER_DETAILS', this.userDetails)
    //   this.$router.push('/UserEdit')
    // },
    
    //機材情報登録モーダル
    openInsertDeviceInfoModal () {
      this.isInsertDeviceInfoModalOpen = true;
    },
    closeInsertDeviceInfoModal () {
      this.isInsertDeviceInfoModalOpen = false
    },

    //ユーザー情報編集モーダル
    openUpdateUserInfoModal () {
      this.isUpdateUserInfoModalOpen = true;
    },
    closeUpdateUserInfoModal () {
      this.isUpdateUserInfoModalOpen = false
    },

    updateUserInfo(){

      const formData = {
        employeeId: this.employeeId,
        employeeName: this.employeeName,
        employeeAddress: this.employeeAdress,
        employeeDepartment: this.employeeDepartment,
        employeePj: this.employeePj
      }

      axios.post('http://localhost:18080/user-info/update', formData)
        .then(response => {
          console.log(response.data)

        })
        .catch(error => {
          console.error(error)
        });

    },

    //ログアウトモーダル
    openLogoutModal () {
      this.isLogoutModalOpen = true;
    },
    closeLogoutModal () {
      this.isLogoutModalOpen = false
    },

    goToLoginPage(){
      this.$router.push('/login')
    },

    goToPasswordResetPage(){
      this.$router.push('/password-reset')
    }
    
  }
}
</script>

<style>
/* CSS styles here */
.search{
  position: absolute; /* 要素を絶対配置にする */
  top: 100px; /* 上から10px */
  right: 50px; /* 左から20px */
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
  background-color: #a4bdff;
  color: rgb(94, 94, 94);
}

td {
  background-color: #fdfdfd;
  color: rgb(99, 98, 98);
}

.detail-modal { /* 機材情報モーダル画面全体 */
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

.modal-content {/* 機材情報モーダルボックス */
  background-color: #fefefe;
  margin: 10% auto;
  padding: 20px;
  border: 1px solid rgb(87, 87, 87);
  border-radius: 60px;
  position: absolute;
  width: 750px;
  height: 770px;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}

/* 機材情報モーダル戻るボタン */
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

.edit-modal {
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

.modal-content {
  background-color: #fefefe;
  margin: 10% auto;
  padding: 20px;
  border: 1px solid rgb(87, 87, 87);
  border-radius: 60px;
  position: absolute;
  width: 750px;
  height: auto;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>