<template>
  <div class="container">
    <div>
      <form action="/signin/twitter" method="post">
        <h1 class="subtitle">Twitter Buttlerへようこそ！</h1>
        <p>下のボタンから SingUp または SingIn してください。</p>
        <a @click="signIn" class="button--green">SingUp/SignIn</a>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'
import axios from '@/infrastructure/axios'

@Component
export default class Login extends Vue {
  public async signIn() {
    console.log('url:' + this.makeCallbackUrl())
    const response = await axios.post(
      '/api/twitter/authUrl',
      this.makeCallbackUrl()
    ) // TODO めんどくさいのでBody全体を文字列で、また変える。
    const singnInUrl = response.data
    location.href = singnInUrl
  }

  private makeCallbackUrl(): string {
    return location.href.replace(this.$route.name as string, 'loggedin')
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
