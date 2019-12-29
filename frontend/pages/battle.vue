<template>
  <div class="container">
    <div>
      <dev>
        <h1 class="subtitle">Twitter Battler Let's Battle ！</h1>
      </dev>
      <p>おつかれの場合は下のボタンからどうぞ。</p>
      <form action="/logout" method="post">
        <button type="submit">SignOut</button>
      </form>
      <div>
        <div class="playerArea">
          <div class="playerBox">
            <table>
              <thead>
                <tr>
                  <th colspan="2"><span id="battlerId_1"></span></th>
                  <th colspan="2"><span id="battlerId_2"></span></th>
                  <th colspan="2"><span id="battlerId_3"></span></th>
                  <th colspan="2"><span id="battlerId_4"></span></th>
                  <th colspan="2"><span id="battlerId_5"></span></th>
                </tr>
                <tr>
                  <th colspan="2"><img id="battlerImage_1" src="" /></th>
                  <th colspan="2"><img id="battlerImage_2" src="" /></th>
                  <th colspan="2"><img id="battlerImage_3" src="" /></th>
                  <th colspan="2"><img id="battlerImage_4" src="" /></th>
                  <th colspan="2"><img id="battlerImage_5" src="" /></th>
                </tr>
              </thead>
              <tbody id="workResultDetails_1">
                <tr>
                  <th>H :</th>
                  <td><span id="battlerHitPoint_1"></span></td>
                  <th>H :</th>
                  <td><span id="battlerHitPoint_2"></span></td>
                  <th>H :</th>
                  <td><span id="battlerHitPoint_3"></span></td>
                  <th>H :</th>
                  <td><span id="battlerHitPoint_4"></span></td>
                  <th>H :</th>
                  <td><span id="battlerHitPoint_5"></span></td>
                </tr>
                <tr>
                  <th>S :</th>
                  <td><span id="battlerSpecialPoint_1"></span></td>
                  <th>S :</th>
                  <td><span id="battlerSpecialPoint_2"></span></td>
                  <th>S :</th>
                  <td><span id="battlerSpecialPoint_3"></span></td>
                  <th>S :</th>
                  <td><span id="battlerSpecialPoint_4"></span></td>
                  <th>S :</th>
                  <td><span id="battlerSpecialPoint_5"></span></td>
                </tr>
                <tr>
                  <th>Lv:</th>
                  <td><span id="battlerLv_1"></span></td>
                  <th>Lv:</th>
                  <td><span id="battlerLv_2"></span></td>
                  <th>Lv:</th>
                  <td><span id="battlerLv_3"></span></td>
                  <th>Lv:</th>
                  <td><span id="battlerLv_4"></span></td>
                  <th>Lv:</th>
                  <td><span id="battlerLv_5"></span></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="enemyArea">
          <table>
            <tbody>
              <tr>
                <th><img id="enemyImage_1" src="" /></th>
                <th><img id="enemyImage_2" src="" /></th>
                <th><img id="enemyImage_3" src="" /></th>
                <th><img id="enemyImage_4" src="" /></th>
                <th><img id="enemyImage_5" src="" /></th>
              </tr>
              <tr>
                <td><span id="enemyId_1"></span></td>
                <td><span id="enemyId_2"></span></td>
                <td><span id="enemyId_3"></span></td>
                <td><span id="enemyId_4"></span></td>
                <td><span id="enemyId_5"></span></td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="underArea">
          <div id="commands" class="commandBox">
            <a id="operationFight" class="button--green">たたかう</a>
            <a id="operationSurrender" class="button--grey">こうさん</a>
          </div>
          <div id="messagesArea" class="informationBox"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

@Component
export default class Battle extends Vue {}
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
