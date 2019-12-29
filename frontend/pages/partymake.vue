<template>
  <div class="container">
    <div>
      <header class="header">
        <h1 class="subtitle">Twitter Battler パーティ作成＆対戦相手決定</h1>
      </header>
      <p>おつかれの場合は下のボタンからどうぞ。</p>
      <form action="/logout" method="post">
        <button type="submit">SignOut</button>
      </form>
      <div id="status">
        <div class="container-fluid">
          <div class="page-header">
            <h2>自分のパーティ</h2>
          </div>
          <div class="row-fluid">
            <table id="sample">
              <thead>
                <tr>
                  <th>つよさ</th>
                  <th><img id="battlerImage_1_1" src="" /></th>
                  <th><img id="battlerImage_1_2" src="" /></th>
                  <th><img id="battlerImage_1_3" src="" /></th>
                  <th><img id="battlerImage_1_4" src="" /></th>
                  <th><img id="battlerImage_1_5" src="" /></th>
                </tr>
              </thead>
              <tbody id="workResultDetails_1_1">
                <tr>
                  <td>なまえ</td>
                  <td>
                    <span id="battlerId_1_1"></span> <br />
                    ( <span id="battlerName_1_1"></span> )
                  </td>
                  <td>
                    <span id="battlerId_1_2"></span> <br />
                    ( <span id="battlerName_1_2"></span> )
                  </td>
                  <td>
                    <span id="battlerId_1_3"></span> <br />
                    ( <span id="battlerName_1_3"></span> )
                  </td>
                  <td>
                    <span id="battlerId_1_4"></span> <br />
                    ( <span id="battlerName_1_4"></span> )
                  </td>
                  <td>
                    <span id="battlerId_1_5"></span> <br />
                    ( <span id="battlerName_1_5"></span> )
                  </td>
                </tr>
                <tr>
                  <td>レベル</td>
                  <td><span id="battlerLv_1_1"></span></td>
                  <td><span id="battlerLv_1_2"></span></td>
                  <td><span id="battlerLv_1_3"></span></td>
                  <td><span id="battlerLv_1_4"></span></td>
                  <td><span id="battlerLv_1_5"></span></td>
                </tr>
                <tr>
                  <td>こうげき力</td>
                  <td><span id="battlerAttackPoint_1_1"></span></td>
                  <td><span id="battlerAttackPoint_1_2"></span></td>
                  <td><span id="battlerAttackPoint_1_3"></span></td>
                  <td><span id="battlerAttackPoint_1_4"></span></td>
                  <td><span id="battlerAttackPoint_1_5"></span></td>
                </tr>
                <tr>
                  <td>しゅび力</td>
                  <td><span id="battlerDefensePoint_1_1"></span></td>
                  <td><span id="battlerDefensePoint_1_2"></span></td>
                  <td><span id="battlerDefensePoint_1_3"></span></td>
                  <td><span id="battlerDefensePoint_1_4"></span></td>
                  <td><span id="battlerDefensePoint_1_5"></span></td>
                </tr>
                <tr>
                  <td>すばやさ</td>
                  <td><span id="battlerSpeedPoint_1_1"></span></td>
                  <td><span id="battlerSpeedPoint_1_2"></span></td>
                  <td><span id="battlerSpeedPoint_1_3"></span></td>
                  <td><span id="battlerSpeedPoint_1_4"></span></td>
                  <td><span id="battlerSpeedPoint_1_5"></span></td>
                </tr>
                <tr>
                  <td>さいだいHP</td>
                  <td><span id="battlerMaxHitPoint_1_1"></span></td>
                  <td><span id="battlerMaxHitPoint_1_2"></span></td>
                  <td><span id="battlerMaxHitPoint_1_3"></span></td>
                  <td><span id="battlerMaxHitPoint_1_4"></span></td>
                  <td><span id="battlerMaxHitPoint_1_5"></span></td>
                </tr>
                <tr>
                  <td>さいだいSP</td>
                  <td><span id="battlerMaxSpecialPoint_1_1"></span></td>
                  <td><span id="battlerMaxSpecialPoint_1_2"></span></td>
                  <td><span id="battlerMaxSpecialPoint_1_3"></span></td>
                  <td><span id="battlerMaxSpecialPoint_1_4"></span></td>
                  <td><span id="battlerMaxSpecialPoint_1_5"></span></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="container-fluid">
          <div class="page-header">
            <h2>相手のパーティ</h2>
          </div>
          <div class="row-fluid">
            <table id="sample_2">
              <thead>
                <tr>
                  <th>つよさ</th>
                  <th><img id="battlerImage_2_1" src="" /></th>
                  <th><img id="battlerImage_2_2" src="" /></th>
                  <th><img id="battlerImage_2_3" src="" /></th>
                  <th><img id="battlerImage_2_4" src="" /></th>
                  <th><img id="battlerImage_2_5" src="" /></th>
                </tr>
              </thead>
              <tbody id="workResultDetails_2_1">
                <tr>
                  <td>なまえ</td>
                  <td>
                    <span id="battlerId_2_1"></span> <br />
                    ( <span id="battlerName_2_1"></span> )
                  </td>
                  <td>
                    <span id="battlerId_2_2"></span> <br />
                    ( <span id="battlerName_2_2"></span> )
                  </td>
                  <td>
                    <span id="battlerId_2_3"></span> <br />
                    ( <span id="battlerName_2_3"></span> )
                  </td>
                  <td>
                    <span id="battlerId_2_4"></span> <br />
                    ( <span id="battlerName_2_4"></span> )
                  </td>
                  <td>
                    <span id="battlerId_2_5"></span> <br />
                    ( <span id="battlerName_2_5"></span> )
                  </td>
                </tr>
                <tr>
                  <td>レベル</td>
                  <td><span id="battlerLv_2_1"></span></td>
                  <td><span id="battlerLv_2_2"></span></td>
                  <td><span id="battlerLv_2_3"></span></td>
                  <td><span id="battlerLv_2_4"></span></td>
                  <td><span id="battlerLv_2_5"></span></td>
                </tr>
                <tr>
                  <td>こうげき力</td>
                  <td><span id="battlerAttackPoint_2_1"></span></td>
                  <td><span id="battlerAttackPoint_2_2"></span></td>
                  <td><span id="battlerAttackPoint_2_3"></span></td>
                  <td><span id="battlerAttackPoint_2_4"></span></td>
                  <td><span id="battlerAttackPoint_2_5"></span></td>
                </tr>
                <tr>
                  <td>しゅび力</td>
                  <td><span id="battlerDefensePoint_2_1"></span></td>
                  <td><span id="battlerDefensePoint_2_2"></span></td>
                  <td><span id="battlerDefensePoint_2_3"></span></td>
                  <td><span id="battlerDefensePoint_2_4"></span></td>
                  <td><span id="battlerDefensePoint_2_5"></span></td>
                </tr>
                <tr>
                  <td>すばやさ</td>
                  <td><span id="battlerSpeedPoint_2_1"></span></td>
                  <td><span id="battlerSpeedPoint_2_2"></span></td>
                  <td><span id="battlerSpeedPoint_2_3"></span></td>
                  <td><span id="battlerSpeedPoint_2_4"></span></td>
                  <td><span id="battlerSpeedPoint_2_5"></span></td>
                </tr>
                <tr>
                  <td>さいだいHP</td>
                  <td><span id="battlerMaxHitPoint_2_1"></span></td>
                  <td><span id="battlerMaxHitPoint_2_2"></span></td>
                  <td><span id="battlerMaxHitPoint_2_3"></span></td>
                  <td><span id="battlerMaxHitPoint_2_4"></span></td>
                  <td><span id="battlerMaxHitPoint_2_5"></span></td>
                </tr>
                <tr>
                  <td>さいだいSP</td>
                  <td><span id="battlerMaxSpecialPoint_2_1"></span></td>
                  <td><span id="battlerMaxSpecialPoint_2_2"></span></td>
                  <td><span id="battlerMaxSpecialPoint_2_3"></span></td>
                  <td><span id="battlerMaxSpecialPoint_2_4"></span></td>
                  <td><span id="battlerMaxSpecialPoint_2_5"></span></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div id="operations">
        <a class="button--green"> バトルスタート！</a>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

@Component
export default class Login extends Vue {}
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
