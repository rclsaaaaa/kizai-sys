<template>
  <body>
    <!-- ページ名 -->
    <div class="broad-outline">
      <div class="square-title">
        <div class="main-line">
          <hr size="3" color="#bfb391" width="300">
        </div>
        <div class="userlist-title">
          <h2>ユーザー一覧</h2>
        </div>
        <div class="main-line2">
          <hr size="3" color="#bfb391" width="300">
        </div>
      </div>
      <!-- 四角 -->
      <div class="square-long">
        <div @click="openModal">
          <input type="submit" class="logout-link" value="ログアウト">
        </div>
        <div @click="openUserDeleteModal">
          <input type="submit" class="account-delete-link" value="アカウント削除">
        </div>
        <!-- 表の見出し -->
        <div class="span-element">
          <span class="span1">
            ユーザーID
          </span>
          <span  class="span2">
            氏名
          </span>
          <span  class="span3">
            部署
          </span>
          <span  class="span4">
                &nbsp;
          </span>
        </div>
        <table class="table-td">
          <tr v-for="userList in paginatedItems" :key="userList.userId">
            <td class="first-tag">{{ userList.userId }}</td>
            <td class="second-tag">{{ userList.name }}</td>
            <td class="third-tag">{{ userList.department }}</td>
            <td class="button-tag">
              <input type="submit" class="detail-Btn" value="詳細" @click="openDetailModal(userList.userId)">
            </td>
          </tr>
        </table>
        <div class="paging-btn">
          <button v-for="pageNumber in visiblePages" :key="pageNumber" @click="goToPage(pageNumber)" :disabled="pageNumber === '...'" :class="{ 'active-page': pageNumber === currentPage }">
            {{ pageNumber }}
          </button>
        </div>
      </div>
    </div>
    <!-- ログアウトモーダル 始 -->
    <div v-if="isModalOpen" class="modal" @click="closeModal">
      <div class="modal-content" @click.stop>
        <div class="modal-line">
          <hr size="3" color="#bfb391" width="150">
        </div>
        <div class="modaltitle">
          <h2>ログアウト</h2>
        </div>
        <div class="modal-line2">
          <hr size="3" color="#bfb391" width="150">
        </div>
        <div class="logout-sentence">
          <p>ログアウトしますか？</p>
        </div>
        <input type="submit" class="cancel-Btn" @click="closeModal" value="キャンセル">
        <input type="submit" class="logout-Btn" value="ログアウト" onclick="location.href='/'">
      </div>
    </div>
    <!-- ログアウトモーダル 終 -->
    <!-- アカウント削除モーダル 始 -->
    <div>
      <div v-if="isUserDeleteModalOpen" class="modal" @click="closeUserDeleteModal">
        <div class="modal-content" @click.stop>
          <div class="modal-line">
            <hr size="3" color="#bfb391" width="150">
          </div>
          <div class="modaltitle">
            <h2>アカウント削除</h2>
          </div>
          <div class="modal-line2">
            <hr size="3" color="#bfb391" width="150">
          </div>
          <div class="account-delete-sentence">
            <p>以下のアカウントを削除<br>しますか？</p>
          </div>
          <div class="account-delete-info">
            <table class="account-delete-table">
              <tr>
                <td class="delete-table-first-tag">ユーザーID</td>
                <td class="delete-table-second-tag">名前</td>
                <td class="delete-table-third-tag">部署</td>
              </tr>
            </table>
          </div>
          <input type="submit" class="cancel-Btn" @click="closeUserDeleteModal" value="キャンセル">
          <input type="submit" class="account-delete-Btn" value="削除"  onclick="location.href='/'">
        </div>
      </div>
    </div>
    <!-- アカウント削除モーダル 終 -->
    <!-- ユーザー情報詳細モーダル 始 -->
    <div v-if="isDetailModalOpen" class="detail-modal" @click="closeDetailModa">
      <div class="modal-content" @click.stop>
        <span class="close" @click="closeDetailModa">&times;</span>
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
              <th class="detail-userid-title">ユーザーID</th>
              <td class="detail-userid-deta">{{ userDetails.userId }}</td>
            </tr>
            <tr>
              <th class="detail-name-title">氏名</th>
              <td class="detail-name-deta">{{ userDetails.name }}</td>
            </tr>
            <tr>
              <th class="detail-department-title">部署</th>
              <td class="detail-department-deta">{{ userDetails.department }}</td>
            </tr>
            <tr>
              <th class="detail-employee-num-title">社員番号</th>
              <td class="detail-employee-num-deta">{{ userDetails.employeeNum }}</td>
            </tr>
            <tr>
              <th class="detail-skill-title">スキル</th>
              <td class="detail-skill-deta">{{ userDetails.skill }}</td>
            </tr>
            <tr>
              <th class="detail-others-title">その他</th>
              <td class="detail-others-deta">{{ userDetails.remarks }}<br></td>
            </tr>
          </table>
        </div>
        <input type="submit" class="edit-Btn" value="編集" @click="goToUserEdit">
      </div>
    </div>
    <!-- ユーザー情報詳細モーダル 終 -->
  </body>
</template>

<script>
import axios from 'axios'

const URL = 'http://localhost:18080/employee'
export default {
  name: 'MainView',
  data () {
    return {
      isModalOpen: false,
      isDetailModalOpen: false,
      isUserDeleteModalOpen: false,
      displayedEmployees: [],
      currentPage: 1,
      itemsPerPage: 4,
      userDetails: {}
    }
  },
  computed: {
    totalPages () {
      return Math.ceil(this.displayedEmployees.length / this.itemsPerPage)
    },
    paginatedItems () {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage
      const endIndex = startIndex + this.itemsPerPage
      return this.displayedEmployees.slice(startIndex, endIndex).map(employee => ({ ...employee }))
    },
    visiblePages () {
      const pages = []
      const totalPages = Math.ceil(this.displayedEmployees.length / this.itemsPerPage)
      let startPage = this.currentPage - 1
      let endPage = this.currentPage + 1

      if (startPage <= 0) {
        startPage = 1
        endPage = Math.min(5, totalPages)
      }

      if (endPage > totalPages) {
        startPage = Math.max(1, totalPages - 4)
        endPage = totalPages
      }

      if (startPage > 1) {
        pages.push(1, '...')
      }

      for (let i = startPage; i <= endPage; i++) {
        pages.push(i)
      }

      if (endPage < totalPages) {
        pages.push('...', totalPages)
      }

      return pages
    }
  },
  mounted () {
    axios.get(URL)
      .then(response => {
        this.displayedEmployees = response.data.userList
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
    openDetailModal (userId) {
      axios.get(`${URL}/detail/${userId}`)
        .then(response => {
          this.userDetails = response.data.userDetails
          this.isDetailModalOpen = true
          console.log(response.data)
          console.log(this.userDetails)
        })
        .catch(error => {
          console.error(error)
        })
    },
    closeDetailModa () {
      this.isDetailModalOpen = false
    },
    openUserDeleteModal () {
      this.isUserDeleteModalOpen = true
    },
    closeUserDeleteModal () {
      this.isUserDeleteModalOpen = false
    },
    goToPage (pageNumber) {
      this.currentPage = pageNumber
    },
    goToUserEdit () {
      this.$store.commit('SET_USER_DETAILS', this.userDetails)
      this.$router.push('/UserEdit')
    }
  }
}
</script>

<style>
/* 枠組み */
.broad-outline {
  width: 800px;
  height: 600px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.square-title {
  width: 800px;
  height: 50px;
  position: absolute;
  top: 9%;
  left: 50%;
  transform: translate(-50%, -50%);
}
/* タイトル */
.main-line {
  position: absolute;
  top: 50%;
  left: 20%;
  transform: translate(-50%, -50%);
}
.userlist-title {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: #bfb391;
}
.main-line2 {
  position: absolute;
  top: 50%;
  left: 80%;
  transform: translate(-50%, -50%);
  color: #bfb391;
}
/* 四角 */
.square-long {
  width: 800px;
  height: 500px;
  border: 2px solid black;
  background-color: white;
  border-radius: 60px;
  position: absolute;
  top: 57%;
  left: 50%;
  transform: translate(-50%, -50%);
}
/* リンク */
.logout-link {
  position: absolute;
  top: 8%;
  left: 9%;
  z-index: 15;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  background-color: #fff;
  border: none;
  color: #6d9eeb;
}
.account-delete-link {
  position: absolute;
  top: 8%;
  left: 75%;
  z-index: 15;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  background-color: #fff;
  border: none;
  color: #6d9eeb;
}
/* ユーザー一覧見出し */
.span1 {
  padding: 15px 20px;
  border-radius: 15px;
  border: 1px solid #bfb391;
  font-size: 20px;
  font-weight: bold;
  background-color: #efdeb1ff;
  color: #fff;
  position: absolute;
  top: 18%;
  left: 11%;
}
.span2 {
  padding: 15px 40px;
  border-radius: 15px;
  border: 1px solid #bfb391;
  font-size: 20px;
  font-weight: bold;
  background-color: #efdeb1ff;
  color: #fff;
  position: absolute;
  top: 18%;
  left: 32%;
}
.span3 {
  padding: 15px 40px;
  border-radius: 15px;
  border: 1px solid #bfb391;
  font-size: 20px;
  font-weight: bold;
  background-color: #efdeb1ff;
  color: #fff;
  position: absolute;
  top: 18%;
  left: 51%;
}
/* ユーザー一覧 */
.table-td {
  position: absolute;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 80%;
  height: 50%;
  text-align: center;
  border-collapse: collapse;
  border-spacing: 0;
  overflow-y: scroll;
  background-color: transparent;
}
.first-tag,.second-tag,.third-tag {
  border: solid 1px #efdeb1ff;
  position: absolute;
  width: 15%;
  font-weight: bold;
}
.second-tag,.third-tag {
  height: 34.6%;
  overflow-y: scroll;
  padding-top: 20px;
  padding-bottom: 19.8px;
  padding-left: 30px;
  padding-right: 30px;
}
.table-td .first-tag {
  font-size: 16px;
  padding-top: 21px;
  padding-bottom: 22px;
  padding-left: 29.5px;
  padding-right: 30px;
}
.table-td .second-tag {
  font-size: 14px;
  left: 24.5%;
  z-index: 1;
}
.table-td .third-tag {
  font-size: 14px;
  left: 49%;
}
.table-td .button-tag {
  padding: 10.7px 20px;
  font-size: 16px;
  font-weight: bold;
  position: absolute;
  width: 20%;
  left: 73.5%;
  border: solid 1px #efdeb1ff;
}
.second-tag::-webkit-scrollbar,.third-tag::-webkit-scrollbar {
  width: 5px;
  background-color: transparent;
}
.table-td tr:nth-child(4n+1) {
  background-color: #fcf4ddff;
  position: absolute;
  width: 100%;
  height: 25%;
}
.table-td tr:nth-child(4n+2) {
  background-color: transparent;
  position: absolute;
  width: 100%;
  height: 25%;
  top: 25%;
}
.table-td tr:nth-child(4n+3) {
  background-color: #fcf4ddff;
  width: 100%;
  height: 25%;
  position: absolute;
  top: 50%;
  left: 0px;
}
.table-td tr:nth-child(4n+4) {
  background-color: transparent;
  width: 100%;
  height: 25%;
  position: absolute;
  top: 75%;
  left: 0px;
}
.detail-Btn {
  width: 110px;
  height: 40px;
  font-size: 15px;
  background-color: #cec6aeff;
  color: #fff;
  border: none;
  font-weight: bold;
  border-radius: 30px;
  cursor: pointer;
}
/* ページング設定 */
.paging-btn {
  position: absolute;
  top: 92%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.active-page {
  font-size: 22px;
}
.paging-btn button {
  box-shadow: none;
  border: none;
  color: #b1a173;
  font-weight: bold;
  background-color: transparent;
  cursor: pointer;
}
/* モーダル設定 */
.modal {
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
  border: 2px solid black;
  border-radius: 60px;
  position: absolute;
  width: 750px;
  height: 460px;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
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
/* ログアウト */
.logout-sentence {
  position: absolute;
  top: 45%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 30px;
  font-weight: bold;
}
.cancel-Btn {
  position: absolute;
  top: 77%;
  left: 30%;
  transform: translate(-50%, -50%);
  font-size: 20px;
  font-weight: bold;
  color: #bfb391;
  background-color: #fff;
  border: 2px solid #efdeb1;
  border-radius: 8px;
  padding: 10px 20px;
  cursor: pointer;
}
.logout-Btn {
  position: absolute;
  top: 77%;
  left: 70%;
  transform: translate(-50%, -50%);
  font-size: 20px;
  font-weight: bold;
  color: #fff;
  background-color: #efdeb1ff;
  border: none;
  border-radius: 8px;
  padding: 10px 20px;
  cursor: pointer;
}
/* アカウント削除 */
.account-delete-sentence {
  position: absolute;
  top: 34%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 26px;
  text-align:left;
  font-weight: bold;
}
.account-delete-table {
  position: absolute;
  top: 54.5%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 65%;
  height: 15%;
  text-align: center;
  border-collapse: collapse;
  border-spacing: 0;
  background-color: #fcf4ddff;
}
.delete-table-first-tag,.delete-table-second-tag,
.delete-table-third-tag {
  border: solid 1px #efdeb1ff;
  font-size: 20px;
  font-weight: bold;
  position: absolute;
  width: 30%;
  height: 45%;
}
.delete-table-first-tag {
  padding: 20px 10px;
}
.delete-table-second-tag {
  padding: 20px 10px;
  left: 34%;
  overflow-y: scroll;
}
.delete-table-third-tag {
  padding: 20px 5px;
  left: 68%;
  overflow-y: scroll;
}
.delete-table-second-tag::-webkit-scrollbar,
.delete-table-third-tag::-webkit-scrollbar {
  width: 5px;
  background-color: transparent;
}
.account-delete-Btn {
  position: absolute;
  top: 77%;
  left: 70%;
  transform: translate(-50%, -50%);
  font-size: 20px;
  font-weight: bold;
  color: #fff;
  background-color: #e06666ff;
  border: none;
  border-radius: 8px;
  padding: 10px 43px;
  cursor: pointer;
}
/* アカウント詳細 */
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
.detail-userid-title {
  top: 0%;
}
.detail-name-title {
  top: 19%;
}
.detail-department-title {
  top: 38%;
}
.detail-employee-num-title {
  top: 57%;
}
.detail-skill-title {
  top: 76%;
}
.detail-others-title {
  top: 95%;
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
.detail-userid-deta {
  top: 0%;
}
.detail-name-deta {
  top: 19%;
}
.detail-department-deta {
  top: 38%;
}
.detail-employee-num-deta {
  top: 57%;
}
.detail-skill-deta {
  top: 76%;
}
.detail-others-deta {
  top: 95%;
}
.detail-others-deta,
.detail-skill-deta {
  overflow-y: scroll; /* 垂直方向に常にスクロールバーを表示 */
  white-space: normal; /* テキストを折り返す */
}
.detail-others-deta::-webkit-scrollbar,
.detail-skill-deta::-webkit-scrollbar {
  width: 5px;
  background-color: transparent;
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
