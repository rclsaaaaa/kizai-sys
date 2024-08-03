<template>
  <div class="table-container">
    <table class="table">
      <tr>
        <td class="td">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac porta nunc. Sed maximus, quam et efficitur ullamcorper, nunc purus dignissim tellus, id lacinia ante elit nec dui.</td>
        <td class="td">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac porta nunc. Sed maximus, quam et efficitur ullamcorper, nunc purus dignissim tellus, id lacinia ante elit nec dui.</td>
        <td class="td">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac porta nunc. Sed maximus, quam et efficitur ullamcorper, nunc purus dignissim tellus, id lacinia ante elit nec dui.</td>
      </tr>
      <tr>
        <td class="td">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac porta nunc. Sed maximus, quam et efficitur ullamcorper, nunc purus dignissim tellus, id lacinia ante elit nec dui.</td>
        <td class="td">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac porta nunc. Sed maximus, quam et efficitur ullamcorper, nunc purus dignissim tellus, id lacinia ante elit nec dui.</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: 'PassResetView'
}
</script>

<style>
.table-container {
  width: 500px; /* 表の幅を固定 */
  height: 300px; /* 表の高さを固定 */
  overflow-y: scroll; /* 垂直方向に常にスクロールバーを表示 */
}

.td {
  white-space: normal; /* テキストを折り返す */
  /* word-wrap: break-word;
  overflow-wrap: break-word; */
}
.table-container::-webkit-scrollbar {
  display: none; /* 右側のスクロールバーを非表示にする */
}
</style>
