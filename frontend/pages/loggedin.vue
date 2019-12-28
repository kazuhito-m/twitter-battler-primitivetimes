<template>
  <div class="container">
    <h1 class="subtitle">Twitter Buttler…もうすぐです！</h1>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'
import LoggedInRequest from '@/presentation/login/LoggedInRequest'
import AccessToken from '@/presentation/login/AccessToken'
import axios from '@/infrastructure/axios'

@Component
export default class LoggedIn extends Vue {
  public async mounted() {
    const result = await this.velifyAccessToken()
    this.$router.push(result ? '/' : 'login')
  }

  public async velifyAccessToken(): Promise<boolean> {
    const queryParams = this.$route.query
    const request = new LoggedInRequest(
      queryParams.oauth_token as string,
      queryParams.oauth_verifier as string
    )
    if (request.isEmpty()) return false

    const response = await axios.post('/api/twitter/accessToken', request)
    const accessToken: AccessToken = response.data
    localStorage.setItem('accessToken', accessToken.token)
    localStorage.setItem('accessTokenSecret', accessToken.tokenSecret)
    return true
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

.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}
</style>
