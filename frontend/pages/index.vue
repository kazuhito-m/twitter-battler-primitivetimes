<template>
  <div class="container">
    <div>
      <h1 class="title">Twitter Battler</h1>
      <h2 class="subtitle">現在のステータス</h2>
      <div>
        <table id="sample">
          <thead>
            <tr>
              <th>つよさ</th>
              <th>
                <img id="playerImage" src />
              </th>
            </tr>
          </thead>
          <tbody id="workResultDetails">
            <tr>
              <td>なまえ</td>
              <td>
                <span id="playerId"></span>(<span id="playerName"></span>)
              </td>
            </tr>
            <tr>
              <td>レベル</td>
              <td>
                <span id="playerLv"></span>
              </td>
            </tr>
            <tr>
              <td>こうげき力</td>
              <td>
                <span id="playerAttackPoint"></span>
              </td>
            </tr>
            <tr>
              <td>しゅび力</td>
              <td>
                <span id="playerDefensePoint"></span>
              </td>
            </tr>
            <tr>
              <td>すばやさ</td>
              <td>
                <span id="playerSpeedPoint"></span>
              </td>
            </tr>
            <tr>
              <td>さいだいHP</td>
              <td>
                <span id="playerMaxHitPoint"></span>
              </td>
            </tr>
            <tr>
              <td>さいだいSP</td>
              <td>
                <span id="playerMaxSpecialPoint"></span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="links">
        <a class="button--green">
          対戦を開始
        </a>
      </div>
      <footer class="footer">
        <p>Version: <span id="appVer">取れない場合これを表示する</span></p>
      </footer>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

@Component
export default class Index extends Vue {}
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
