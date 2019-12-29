<template>
  <div class="container">
    <div>
      <header class="header">
        <h1 class="subtitle">Twitter Battler Battle Result</h1>
      </header>
      <p>おつかれの場合は下のボタンからどうぞ。</p>
      <form action="/logout" method="post">
        <button type="submit">SignOut</button>
      </form>
      <section id="status"></section>
      <section id="operations">
        <a id="okBattleResult" class="button--green">結果OK</a>
      </section>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

@Component
export default class BattleResult extends Vue {}
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
