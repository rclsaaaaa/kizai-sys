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
        <tr v-for="(item, index) in equipmentList" :key="index">
          <td>{{ item.id }}</td>
          <td>{{ item.type }}</td>
          <td>{{ item.userEmployeeNumber }}</td>
          <td>{{ item.userName }}</td>
          <td>{{ item.managerEmployeeNumber }}</td>
          <td>{{ item.managerName }}</td>
          <td>{{ item.managementStatus }}</td>
          <td>{{ item.managementLocation }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      equipmentList: [
        // データのサンプル
        {
          id: '001',
          type: 'PC',
          userEmployeeNumber: '1234',
          userName: '山田太郎',
          managerEmployeeNumber: '5678',
          managerName: '佐藤花子',
          managementStatus: '良好',
          managementLocation: '東京'
        },
        {
          id: '001',
          type: 'PC',
          userEmployeeNumber: '1234',
          userName: '山田太郎',
          managerEmployeeNumber: '5678',
          managerName: '佐藤花子',
          managementStatus: '良好',
          managementLocation: '東京'
        },
        {
          id: '001',
          type: 'PC',
          userEmployeeNumber: '1234',
          userName: '山田太郎',
          managerEmployeeNumber: '5678',
          managerName: '佐藤花子',
          managementStatus: '良好',
          managementLocation: '東京'
        }
        // 他のデータもここに追加可能
      ]
    }
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
