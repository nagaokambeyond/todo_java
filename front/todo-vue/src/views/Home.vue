<script lang="ts">
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {};
  },
  mounted() {
    axios
      .get("http://localhost:8090/api/v1/todos", {
        headers: {
          "X-AUTH-TOKEN": "Bearer " + this.$store.getters.getToken,
        },
      })
      .then((res) => {
        console.log("取得した");
        console.log(res);
      })
      .catch((e) => {
        alert("エラーが発生しました");
        console.log(e);
      });
  },
};
</script>

<template>
  <div>
    <h1>ホーム</h1>
  </div>
</template>
