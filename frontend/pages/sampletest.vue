<template>
  <div class="container">
    <div>
      <h1 class="subtitle">ログインするまで見えない、サンプルページ。</h1>
      <div>
        <button id="sendTest" @click="sendTest" type="button">テスト</button>
        <input
          v-model="targetUrl"
          type="text"
          value="http://localhost:8080/api/twitter/commands"
        />
        <p>送信する値:<textarea v-model="sendJson"></textarea></p>
        <p>結果:<textarea v-model="resultJson"></textarea></p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'
import axios from '@/infrastructure/axios'

@Component
export default class SampleTest extends Vue {
  public targetUrl: string = 'http://localhost:8080/api/twitter/sessionTest'
  public sendJson: string = ''
  public resultJson: string = ''

  public async sendTest() {
    // const d = document as Document
    // const targetUrl = d.getElementById('targetUrl') as HTMLElement
    // const sendJson = d.getElementById('sendJson') as HTMLElement
    //   const xhr = new XMLHttpRequest()
    //   xhr.open('POST', targetUrl, false)
    //   xhr.setRequestHeader('Content-Type', 'application/json')
    //   xhr.send(sendJson)
    //   const jsonText = xhr.responseText
    //   document.getElementById('resultJson').value = jsonText

    const response = await axios.get(this.targetUrl)
    this.resultJson = response.data
    alert('成功')
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
