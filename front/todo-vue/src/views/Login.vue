<template>
  <div>
    <h1>ログイン</h1>
    <form>
      <div>
        <label>ユーザー名</label>
        <input v-model="username" type="text" id="username" />
      </div>
      <div>
        <label>パスワード</label>
        <input v-model="password" type="password" id="password" />
      </div>
      <div>
        <button type="button" @click="login">ログイン</button>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    login() {
      const data = new FormData();
      data.append("username", this.username);
      data.append("password", this.password);
      axios
        .post("http://localhost:8090/api/login", data, {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        })
        .then((res) => {
          console.log(res);
          this.$store.dispatch("saveToken", res.headers["x-auth-token"]);
          this.$router.push("/");
        })
        .catch((e) => {
          alert("ログインに失敗しました");
          console.log(e);
        });
    },
  },
};
</script>
