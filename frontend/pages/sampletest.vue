<template>
  <div class="container">
    <div>
      <h1 class="subtitle">ログインするまで見えない、サンプルページ。</h1>
      <div>
        <button id="sendTest" @click="sendTest" type="button">テスト</button>
        <input
          id="targetUrl"
          type="text"
          value="http://localhost:8080/api/twitter/commands"
        />
        <p>送信する値:<textarea id="sendJson"></textarea></p>
        <p>結果:<textarea id="resultJson"></textarea></p>
      </div>
      <div>
        <button id="sendTest" @click="test2" type="button">
          色々お試し
        </button>
        <button id="sendTest" @click="getToken" type="button">
          OAuthおくってみる
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

@Component
export default class SampleTest extends Vue {
  public sendTest() {
    //   const targetUrl = document.getElementById('targetUrl').value
    //   const sendJson = document.getElementById('sendJson').value
    //   const xhr = new XMLHttpRequest()
    //   xhr.open('POST', targetUrl, false)
    //   xhr.setRequestHeader('Content-Type', 'application/json')
    //   xhr.send(sendJson)
    //   const jsonText = xhr.responseText
    //   document.getElementById('resultJson').value = jsonText
    alert('成功')
  }

  public test2() {
    const serverUrl = location.href.replace(this.$route.name as string, '別の')
    alert('serverUrl:' + serverUrl)
  }
}
</script>

<style>
.container {
  margin: 0 auto;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.title {
  font-family: 'Quicksand', 'Source Sans Pro', -apple-system, BlinkMacSystemFont,
    'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}

.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}

.links {
  padding-top: 15px;
}
</style>
